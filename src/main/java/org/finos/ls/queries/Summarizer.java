package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Block;
import org.commonmark.node.BulletList;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.finos.ls.markdown.MarkdownExcerptExtension;
import org.finos.scan.github.client.Blob;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryTopicEdge;
import org.finos.scan.github.client.Topic;
import org.springframework.util.StringUtils;

/**
 * Generates some nice Markdown summary of a project.
 * 
 * @author rob@kite9.com
 *
 */
public class Summarizer implements QueryType<String> {
	
	public enum SummaryLevel { MAIN, SUBITEM };

	final Parser p;	
	final SummaryLevel sl;
	
	public Summarizer(SummaryLevel sl) {
		this.sl = sl;
		// configures the parser for github-flavoured markdown
		this.p = Parser.builder().extensions(Arrays.asList(
				TablesExtension.create(),
				YamlFrontMatterExtension.create(),
				AutolinkExtension.create())).build();
	}

	private static final String GRAPH_QL = "{"
			+ "    id\n"
			+ "    name\n"
			+ "    homepageUrl\n"
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
			+ "    }\n"
			+ "  }";
	
	@Override
	public String convert(Repository repo) {
		String name = repo.getName();
		List<Block> firstParagraphs = extractParagraphsIfPresent(repo);
	
		String slugBasedTitle = getTitleFromName(name);
		String description = repo.getDescription();
		List<String> tags = getTopicTags(repo.getRepositoryTopics().getEdges());
		String homepage = repo.getHomepageUrl();
		String githubUrl = repo.getUrl();
		
		// ok, let's put it all together
		
		StringBuilder out = new StringBuilder();
		
		addTitle(out, slugBasedTitle, repo);
		writeSummaryCounts(out, repo);
		addTopicTags(out, tags, repo);
		addDescription(out, description);
		addQuotedParagraphs(out, firstParagraphs, githubUrl);
		addLinks(homepage, githubUrl, out);
		
		return out.toString();
	}



	private List<Block> extractParagraphsIfPresent(Repository repo) {
		if (repo.getObject() instanceof Blob) {
			String text = ((Blob)repo.getObject()).getText();
			Node n = p.parse(text);
			List<Block> firstParagraphs = getParagraphs(n, 4);
			return firstParagraphs;
		} else {
			return Collections.emptyList();
		}
	}



	private void addLinks(String homepage, String githubUrl, StringBuilder out) {
		out.append("#### Further Details\n");
		addLink(out, githubUrl, githubUrl);
		addLink(out, homepage, homepage);
	}

	private void addQuotedParagraphs(StringBuilder out, List<Block> paras, String githubUrl) {
		if (paras.size() > 0) {
			out.append("#### From the README\n\n");
			for (int i = 0; i < paras.size(); i++) {
				out.append("> " + convertBackToMarkdown(paras.get(i)).replace("\n", ""));
				if (i<paras.size()-1) {
					out.append("\n>\n");
				} else {
					out.append("... _[read more]("+githubUrl+")_\n\n");
				}
			}
		}
	}


	private void addTopicTags(StringBuilder out, List<String> tags, Repository repo) {		
		tags.forEach(t -> out.append(t+" "));
		out.append("\n\n");
	}

	private void writeSummaryCounts(StringBuilder out, Repository repo) {
		out.append(createBadge("⭐", "GitHub Stars", ""+repo.getStargazerCount(), "yellow", repo, "/stargazers"));
		out.append(createBadge("⚡", "GitHub Forks", ""+repo.getForkCount(), "yellow", repo, ""));
		out.append(createBadge("👁️", "GitHub Watchers", ""+ repo.getWatchers().getTotalCount(), "yellow", repo, "/watchers"));
		out.append(createBadge("🔎", "GitHub Issues", ""+ repo.getIssues().getTotalCount(), "yellow",repo, "/issues"));
	}

	private String createBadge(String title, String altText, String value, String colour, Repository r, String url){
		String badgeImage = "!["+altText+"](https://img.shields.io/badge/"+title+"-"+value+"-"+colour+"?labelColor=fafbfc) ";
		return "["+badgeImage+"]("+r.getUrl()+url+")";
	}

	private void addLink(StringBuilder out, String url, String linkText) {
		if (StringUtils.hasText(url)) {
			out.append(" - ["+linkText+"]("+url+")\n");
		}
	}

	private void addDescription(StringBuilder out, String description) {
		if (StringUtils.hasText(description)) {
			out.append("_"+description.trim()+"_\n\n");
		}
	}


	private void addTitle(StringBuilder out, String slugBasedTitle, Repository r) {
		out.append(getTitleLeve()+slugBasedTitle);
		out.append("\n\n");
	} 


	private String getTitleLeve() {
		return sl == SummaryLevel.MAIN ? "## " : "### ";
	}



	private List<Block> getParagraphs(Node n, int i) {
		List<Block> found = new ArrayList<>();
		n.accept(new AbstractVisitor() {

			@Override
			public void visit(Paragraph paragraph) {
				if (found.size() < i) {
					found.add(paragraph);
				}
			}

			@Override
			public void visit(BulletList bulletList) {
				if (found.size() < i) {
					found.add(bulletList);
				}
			}
			
			
		});
		
		return found;
	}


	private List<String> getTopicTags(List<RepositoryTopicEdge> edges) {
		return edges.stream()
			.map(e -> e.getNode().getTopic())
			.map(n -> createLink(n, createImage(n)))
			.collect(Collectors.toList());
	}


	private String createLink(Topic n, String i) {
		return "["+i+"](https://github.com/topics/"+n.getName()+")";
	}


	private String createImage(Topic n) {
		String spacedTopic = n.getName().replace("-", "%20");
		return "![Topic: "+n.getName()+"](https://img.shields.io/badge/"+spacedTopic+"-fafbfc)";
	}

	private String convertBackToMarkdown(Block b) {
		TextContentRenderer tcr = TextContentRenderer.builder()
				.extensions(Arrays.asList(MarkdownExcerptExtension.create()))
				
				.build();
		return tcr.render(b);
	}

	private String getTitleFromName(String name) {
		return WordUtils.capitalize(name.replace("-", " "));
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
