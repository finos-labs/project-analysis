package org.finos.ls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Block;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.finos.scan.github.client.Blob;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryTopicEdge;
import org.finos.scan.github.client.Topic;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

/**
 * Generates some nice Markdown summary of a project.
 * 
 * @author rob@kite9.com
 *
 */
@Service
public class Summarizer {
	
	final Parser p;	
	
	public Summarizer() {
		// configures the parser for github-flavoured markdown
		p = Parser.builder().extensions(Arrays.asList(
				TablesExtension.create(),
				YamlFrontMatterExtension.create(),
				AutolinkExtension.create())).build();
	}
	
	
	@Autowired
	QueryExecutor qe;

	private static final String GRAPH_QL = "{"
			+ "    id\n"
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
	
	
	public String getSummary(String owner, String name) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qe.repository(GRAPH_QL, true, name, owner);
		String text = ((Blob)repo.getObject()).getText();
		Node n = p.parse(text);
	
		String slugBasedTitle = getTitleFromName(name);
		String description = repo.getDescription();
		List<String> tags = getTopicTags(repo.getRepositoryTopics().getEdges());
		String homepage = repo.getHomepageUrl();
		String githubUrl = repo.getUrl();
		List<Paragraph> firstThreeParagraphs = getParagraphs(n, 3);
		
		// ok, let's put it all together
		
		StringBuilder out = new StringBuilder();
		
		addTitle(out, slugBasedTitle, repo);
		addTopicTags(out, tags, repo);
		addDescription(out, description);
		addQuotedFirstThreeParagraphs(out, firstThreeParagraphs, githubUrl);
		addLinks(homepage, githubUrl, out);
		
		return out.toString();
	}

	private void addLinks(String homepage, String githubUrl, StringBuilder out) {
		out.append("#### Further Details:\n");
		addLink(out, githubUrl, githubUrl);
		addLink(out, homepage, homepage);
	}

	private void addQuotedFirstThreeParagraphs(StringBuilder out, List<Paragraph> firstThreeParagraphs, String githubUrl) {
		out.append("#### From the README:\n\n");
		firstThreeParagraphs.stream()
			.map(p -> (Block)p)
			.map(b -> convertBackToMarkdown(b))
			.forEach(md -> out.append("> "+md+">\n"));
		out.append("\n_[read more]("+githubUrl+")_\n\n");
	}


	private void addTopicTags(StringBuilder out, List<String> tags, Repository repo) {		
		tags.forEach(t -> out.append(t+" "));
		out.append("\n\n");
	}

	private void writeSummaryCounts(StringBuilder out, Repository repo) {
		out.append(" &middot; ");
		out.append(createBadge("⭐", "GitHub Stars", ""+repo.getStargazerCount(), "yellow"));
		out.append(createBadge("⚡", "GitHub Forks", ""+repo.getForkCount(), "yellow"));
		out.append(createBadge("👁️", "GitHub Watchers", ""+ repo.getWatchers().getTotalCount(), "yellow"));
		out.append(createBadge("🔎", "GitHub Issues", ""+ repo.getIssues().getTotalCount(), "yellow"));
	}

	private String createBadge(String title, String altText, String value, String colour){
		return "!["+altText+"](https://img.shields.io/badge/"+title+"-"+value+"-"+colour+") ";
	}

	private void addLink(StringBuilder out, String url, String linkText) {
		if (url != null) {
			out.append(" - ["+linkText+"]("+url+")\n");
		}
	}

	private void addDescription(StringBuilder out, String description) {
		out.append("_"+description+"_\n\n");
	}


	private void addTitle(StringBuilder out, String slugBasedTitle, Repository r) {
		out.append("## "+slugBasedTitle);
		writeSummaryCounts(out, r);
		out.append("\n\n");
	} 


	private List<Paragraph> getParagraphs(Node n, int i) {
		List<Paragraph> found = new ArrayList<>();
		n.accept(new AbstractVisitor() {

			@Override
			public void visit(Paragraph paragraph) {
				if (found.size() < i) {
					found.add(paragraph);
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
				.extensions(Arrays.asList(LinkImageExtension.create()))
				
				.build();
		return tcr.render(b);
	}
	
	

	private String getTitleFromName(String name) {
		return WordUtils.capitalizeFully(name.replace("-", " "));
	}
}
