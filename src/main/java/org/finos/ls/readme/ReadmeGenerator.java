package org.finos.ls.readme;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.finos.ls.QueryService;
import org.finos.ls.landscape.LandscapeReader;
import org.finos.ls.landscape.ProjectInfo;
import org.finos.ls.landscape.ProjectInfo.ProjectType;
import org.finos.ls.outputs.CommitService;
import org.finos.ls.outputs.PullRequestService;
import org.finos.ls.queries.MarkdownSummarizer;
import org.finos.ls.queries.MarkdownSummarizer.SummaryLevel;
import org.finos.ls.report.AbstractMultiReport;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
@Named("readme")
@ConfigurationProperties(prefix = "readme")
@EnableConfigurationProperties
public class ReadmeGenerator extends AbstractMultiReport {

	@Autowired
	Environment env;

	@Autowired
	LandscapeReader lr;

	@Value("${landscapeUrl}")
	String landscapeUrl;

	@Autowired
	QueryService qs;

	@Autowired
	QueryExecutor qe;

	@Value("${githubOrgs}")
	List<String> orgs;

	@Autowired
	CommitService commit;

	@Autowired
	PullRequestService pr;

	@Value("${readme.repo}")
	String repo;

	@Value("${readme.owner}")
	String owner;

	@Value("${readme.base}")
	String base;

	@Value("${readme.head}")
	String head;

	Map<String, Repository> cache = new HashMap<>();

	@Override
	public Map<String, String> generateInner()
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> allReports = new HashMap<>();
		List<ProjectInfo> info = lr.readFromLandscape(landscapeUrl);

		// remove gitlab projects for now
		info = info.stream().filter(pi -> pi.mainRepo != null).filter(pi -> !pi.mainRepo.contains("gitlab.com"))
				.collect(Collectors.toList());

		StringBuilder out = new StringBuilder();
		out.append("# FINOS Projects\n\n");
		String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
		out.append("Here are some of FINOS' most active projects (as of " + date + "):\n\n");

		Map<String, ProjectInfo> bucketedProjects = bucketNames(info);
		Map<String, String> projectSummaries = summariseProjects(bucketedProjects);

		out.append("\n**Special Interest Groups:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.SIG, null));
		out.append("\n**Active Projects:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.ACTIVE, null));
		out.append("\n**Incubating Projects:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.INCUBATING, null));

		out.append("# Projects by Tag\n");
		List<String> tags = collectTags(info);

		System.out.println("Tags: " + String.join(", ", tags));

		for (String tag : tags) {
			out.append(" - [" + tag + "](#" + tag + ".md)\n");
		}

		out.append("# Special Interest Groups\n");
		out.append(report(bucketedProjects, projectSummaries, ProjectInfo.ProjectType.SIG, null));

		out.append("# Active Projects\n");
		out.append(report(bucketedProjects, projectSummaries, ProjectInfo.ProjectType.ACTIVE, null));

		out.append("# Incubating Projects\n");
		out.append(report(bucketedProjects, projectSummaries, ProjectInfo.ProjectType.INCUBATING, null));

		out.append("\n\n_For the full list see the repositories below_\n");
		allReports.put("README-generated.md", out.toString());

		for (String tag : tags) {
			StringBuilder tagOut = new StringBuilder();
			tagOut.append("\n\n# " + tag + "\n");
			tagOut.append(tableOfContents(bucketedProjects, null, tag));
			tagOut.append(report(bucketedProjects, projectSummaries, null, tag));
			allReports.put(tag + ".md", tagOut.toString());
		}

		return allReports;
	}

	private List<String> collectTags(List<ProjectInfo> info) {
		return info.stream().flatMap(pi -> pi.tags.stream()).distinct().collect(Collectors.toList());
	}

	private String tableOfContents(Map<String, ProjectInfo> bucketedProjects, ProjectType t, String tag)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
				.filter(a -> (a.getValue().type == t) || t == null)
				.filter(a -> (a.getValue().tags.contains(tag)) || tag == null)
				.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey())).forEach(entry -> {
					String key = entry.getKey();
					out.append(" - [" + key + "](#" + key.replace(" ", "-") + ")\n");
				});

		return out.toString();
	}

	private String report(
			Map<String, ProjectInfo> bucketedProjects,
			Map<String, String> projectSummaries,
			ProjectType t, String tag) {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
				.filter(a -> (a.getValue().type == t) || t == null)
				.filter(a -> (a.getValue().tags.contains(tag)) || tag == null)
				.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey())).forEach(e -> {
					String summary = projectSummaries.get(e.getKey());
					out.append(summary);
				});

		return out.toString();
	}

	private Map<String, String> summariseProjects(Map<String, ProjectInfo> bucketedProjects) {
		return bucketedProjects.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> {
					MarkdownSummarizer topLevel = new MarkdownSummarizer(SummaryLevel.MAIN, e.getValue());
					StringBuilder out = new StringBuilder();
					appendUsing(topLevel, e.getValue(), out);
					return out.toString();
				}));
	}

	private void appendUsing(MarkdownSummarizer l, ProjectInfo pi, StringBuilder out) {
		try {
			String[] parts = pi.mainRepo.substring("https://github.com/".length()).split("/");
			String name = parts[1];
			String org = parts[0];

			out.append(l.convert(getRepoDetails(l, name, org), qe));
		} catch (Exception e) {
			out.append("## " + pi.mainRepo + "\n");
			out.append("Couldn't process this repo: \n");
			System.err.println("Couldn't process: " + pi.mainRepo);
			e.printStackTrace();
		}
	}

	private Repository getRepoDetails(MarkdownSummarizer l, String name, String org)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {

		if (!cache.containsKey(name)) {
			cache.put(name, qs.getRawRepository(l, org, name));
		}

		return cache.get(name);
	}

	private Map<String, ProjectInfo> bucketNames(List<ProjectInfo> list) {
		return list.stream().collect(Collectors.toMap(pi -> pi.name, pi -> pi));
	}

	@Override
	public void outputResults(Map<String, String> entries) throws Exception {
		super.outputResults(entries);
		if (Arrays.asList(env.getActiveProfiles()).contains("pr")) {
			Map<String, byte[]> files = entries.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getBytes()));
			commit.commitFiles(files, head, repo, owner);
			pr.createOrUpdatePullRequest(repo, owner, base, head, Collections.singletonList("@robmoffat"),
					"Updated Generated Files");
		}
	}

}
