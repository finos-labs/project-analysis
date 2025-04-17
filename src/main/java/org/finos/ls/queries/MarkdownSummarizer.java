package org.finos.ls.queries;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.Charsets;
import org.apache.commons.text.WordUtils;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Document;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.finos.ls.landscape.ProjectInfo;
import org.finos.ls.markdown.MarkdownExcerptExtension;
import org.finos.ls.markdown.MarkdownHeadingExtension;
import org.finos.scan.github.client.Blob;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryTopicEdge;
import org.finos.scan.github.client.Topic;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.util.StringUtils;

/**
 * Generates some nice Markdown summary of a project.
 * 
 * @author rob@kite9.com
 *
 */
public class MarkdownSummarizer implements QueryType<String> {

	public enum SummaryLevel {
		MAIN, SUBITEM
	};

	final Parser p;
	final SummaryLevel sl;
	final ProjectInfo pi;

	public MarkdownSummarizer(SummaryLevel sl, ProjectInfo projectInfo) {
		this.sl = sl;
		this.pi = projectInfo;
		// configures the parser for github-flavoured markdown
		this.p = Parser.builder().extensions(Arrays.asList(
				TablesExtension.create(),
				YamlFrontMatterExtension.create(),
				AutolinkExtension.create())).build();
	}

	private static final String GRAPH_QL = "    homepageUrl\n"
			+ "    stargazerCount\n"
			+ "    description\n"
			+ "    forkCount\n"
			+ "    object(expression: \"HEAD:README.md\") {\n"
			+ "      ... on Blob {\n"
			+ "        text\n"
			+ "        __typename\n"
			+ "      }\n"
			+ "    }\n"
			+ "    descriptionHTML\n"
			+ "    repositoryTopics(first: 10) {\n"
			+ "      edges {\n"
			+ "        node {\n"
			+ "          topic {\n"
			+ "            name\n"
			+ "          }\n"
			+ "        }\n"
			+ "      }\n"
			+ "    }\n"
			+ "    url\n"
			+ "    watchers {\n"
			+ "      totalCount\n"
			+ "    }\n"
			+ "    issues(filterBy: {states: OPEN}) {\n"
			+ "      totalCount\n"
			+ "    }\n";

	@Override
	public String convert(Repository repo, QueryExecutor qe) {
		String name = this.pi.name;

		if (name == null) {
			name = repo.getName();
		}

		Document d = extractMarkdown(repo);

		String slugBasedTitle = getTitleFromNameOrH1(name, repo);
		String description = repo.getDescription();
		List<String> tags = getTopicTags(repo.getRepositoryTopics().getEdges());
		String homepage = repo.getHomepageUrl();
		if (homepage == null) {
			homepage = this.pi.homepageUrl;
		}
		String githubUrl = repo.getUrl();

		// ok, let's put it all together

		StringBuilder out = new StringBuilder();

		addTitle(out, slugBasedTitle, repo);
		addLogo(out, this.pi.logo);
		writeSummaryCounts(out, repo, this.pi);
		out.append("\n\n");
		addDescription(out, description);
		addTopicTags(out, tags, repo);
		addQuotedMarkdown(out, d, githubUrl);
		addLinks(homepage, githubUrl, this.pi.additionalRepos, out);

		return out.toString();
	}

	private void addLogo(StringBuilder out, String logo) {
		out.append("\n<img src=\"" + this.pi.logo + "\" width=\"100px\" />\n\n");
	}

	private Document extractMarkdown(Repository repo) {
		if (repo.getObject() instanceof Blob) {
			String text = ((Blob) repo.getObject()).getText();
			Document n = (Document) p.parse(text);
			return n;
		} else {
			return null;
		}
	}

	private void addLinks(String homepage, String githubUrl, List<String> additionalRepos, StringBuilder out) {
		Set<String> all = new TreeSet<String>();
		out.append("#### Further Details\n");
		addLink(out, githubUrl, githubUrl);
		addLink(out, homepage, homepage);
		all.add(homepage);
		all.add(githubUrl);
		if (additionalRepos != null) {
			for (String string : additionalRepos) {
				if (!all.contains(string)) {
					all.add(string);
					addLink(out, string, string);
				}
			}
		}
	}

	private void addQuotedMarkdown(StringBuilder out, Document d, String githubUrl) {
		if (d != null) {
			out.append("#### From the README:\n\n");
			String md = convertToQuotedMarkdown(d, githubUrl);
			out.append(md);
		}
	}

	private void addTopicTags(StringBuilder out, List<String> tags, Repository repo) {
		tags.forEach(t -> out.append(t + " "));
		out.append("\n\n");
	}

	private void writeSummaryCounts(StringBuilder out, Repository repo, ProjectInfo pi) {
		out.append(
				createBadge("⭐%20Stars", "GitHub Stars", "" + repo.getStargazerCount(), "grey", repo, "/stargazers"));
		out.append(createBadge("⚡%20Forks", "GitHub Forks", "" + repo.getForkCount(), "grey", repo, ""));
		out.append(createBadge("🔎%20Issues", "GitHub Issues", "" + repo.getIssues().getTotalCount(), "grey", repo,
				"/issues"));

		if (pi.mailingList != null) {
			String email = pi.mailingList;
			String subscribe = "mailto:" + email.replace("@", "+subscribe@");
			out.append(createBadge("📫%20Mailing%20List", "Join The Mailing List", "Join", "orange", null, subscribe));
		}

		if (pi.slackChannels != null) {
			pi.slackChannels.forEach(sc -> {
				out.append(createBadge("Slack", "Chat On Slack", "Join", "orange", null,
						"https://app.slack.com/client/T01E7QRQH97/" + sc));
			});
		}

		if (pi.calendarUrl != null) {
			out.append(createBadge("📅%20Calendar", "Add To Calendar", "Add", "orange", null, pi.calendarUrl));
		}

	}

	private String createBadge(String title, String altText, String value, String colour, Repository r, String url) {
		String badgeImage = "![" + altText + "](https://img.shields.io/badge/" + title + "-" + value + "-" + colour
				+ "?labelColor=eaebec&style=for-the-badge) ";
		return "[" + badgeImage + "](" + (r != null ? r.getUrl() : "") + url + ")";
	}

	private void addLink(StringBuilder out, String url, String linkText) {
		if (StringUtils.hasText(url)) {
			out.append(" - [" + linkText + "](" + url + ")\n");
		}
	}

	private void addDescription(StringBuilder out, String description) {
		if (StringUtils.hasText(description)) {
			out.append("_" + description.trim() + "_\n\n");
		}
	}

	private void addTitle(StringBuilder out, String slugBasedTitle, Repository r) {
		out.append(getTitleLevel() + slugBasedTitle);
		out.append("\n");
	}

	private String getTitleLevel() {
		return sl == SummaryLevel.MAIN ? "## " : "### ";
	}

	private Heading getMostImportantHeading(Node n) {
		Map<Integer, List<Heading>> found = new HashMap<Integer, List<Heading>>();
		n.accept(new AbstractVisitor() {
			@Override
			public void visit(Heading h1) {
				List<Heading> atLevel = found.getOrDefault(h1.getLevel(), new ArrayList<Heading>());
				found.put(h1.getLevel(), atLevel);
				atLevel.add(h1);
			}
		});

		int highestLevel = found.keySet().stream()
				.sorted()
				.findFirst()
				.orElse(-1);

		if (highestLevel == -1) {
			return null;
		}

		List<Heading> topMostLevel = found.get(highestLevel);
		if (topMostLevel.size() == 1) {
			return topMostLevel.get(0);
		}

		return null;
	}

	private List<String> getTopicTags(List<RepositoryTopicEdge> edges) {
		return edges.stream()
				.map(e -> e.getNode().getTopic())
				.map(n -> createLink(n, createImage(n)))
				.collect(Collectors.toList());
	}

	private String createLink(Topic n, String i) {
		return "[" + i + "](https://github.com/topics/" + n.getName() + ")";
	}

	private String createImage(Topic n) {
		String spacedTopic = n.getName().replace("-", "%20");
		return "![Topic: " + n.getName() + "](https://img.shields.io/badge/" + spacedTopic + "-fafbfc)";
	}

	private String convertToQuotedMarkdown(Document d, String githubUrl) {
		TextContentRenderer tcr = TextContentRenderer.builder()
				.extensions(Arrays.asList(MarkdownExcerptExtension.create(githubUrl)))

				.build();
		return tcr.render(d);
	}

	public String getTitleFromNameOrH1(String name, Repository repo) {
		Heading h = null;
		String out = null;
		if (repo.getObject() instanceof Blob) {
			String text = ((Blob) repo.getObject()).getText();
			Node n = p.parse(text);
			h = getMostImportantHeading(n);
		}

		if (h != null) {
			out = TextContentRenderer.builder()
					.extensions(Arrays.asList(MarkdownHeadingExtension.create()))
					.build().render(h).replace("\n", "");
		}

		if (StringUtils.hasText(out)) {
			return out.trim();
		} else {
			// figure something out from the slug
			return WordUtils.capitalize(name.replace("-", " "));
		}
	}

	@Override
	public String getFields() {
		return GRAPH_QL;
	}

	@Override
	public int getPageSize() {
		return 3;
	}
}
