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
import org.finos.ls.report.AbstractReport;
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
public class ReadmeGenerator extends AbstractReport {

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

	@Value("${readme.file:README.md}")
	String readmeFile;

	@Value("${readme.repo}")
	String repo;

	@Value("${readme.owner}")
	String owner;

	@Value("${readme.base}")
	String base;

	@Value("${readme.head}")
	String head;

	@Value("${readme.output:}")
	String filename;

	public String getFilename() {
		return filename;
	}

	Map<String, Repository> cache = new HashMap<>();

	@Override
	public String generateInner() throws Exception {
		return generate(orgs);
	}

	public String generate(List<String> orgs)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		List<ProjectInfo> info = lr.readFromLandscape(landscapeUrl);

		// remove gitlab projects for now
		info = info.stream().filter(pi -> pi.mainRepo != null).filter(pi -> !pi.mainRepo.contains("gitlab.com"))
				.collect(Collectors.toList());

		StringBuilder out = new StringBuilder();
		out.append("# FINOS Projects\n\n");
		String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
		out.append("Here are some of FINOS' most active projects (as of " + date + "):\n\n");

		Map<String, ProjectInfo> bucketedProjects = bucketNames(info);

		out.append("\n**Special Interest Groups:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.SIG));
		out.append("\n**Active Projects:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.ACTIVE));
		out.append("\n**Incubating Projects:**\n");
		out.append(tableOfContents(bucketedProjects, ProjectInfo.ProjectType.INCUBATING));

		out.append("# Special Interest Groups\n");
		out.append(report(bucketedProjects, ProjectInfo.ProjectType.SIG));

		out.append("# Active Projects\n");
		out.append(report(bucketedProjects, ProjectInfo.ProjectType.ACTIVE));

		out.append("# Incubating Projects\n");
		out.append(report(bucketedProjects, ProjectInfo.ProjectType.INCUBATING));

		out.append("\n\n_For the full list see the repositories below_\n");
		return out.toString();
	}

	private String tableOfContents(Map<String, ProjectInfo> bucketedProjects, ProjectType t)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream().filter(a -> a.getValue().type == t)
				.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey())).forEach(entry -> {
					String key = entry.getKey();
					out.append(" - [" + key + "](#" + key.replace(" ", "-") + ")\n");
				});

		return out.toString();
	}

	private String report(Map<String, ProjectInfo> bucketedProjects, ProjectType t) {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream().filter(a -> a.getValue().type == t)
				.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey())).forEach(e -> {
					MarkdownSummarizer topLevel = new MarkdownSummarizer(SummaryLevel.MAIN, e.getValue());
					appendUsing(topLevel, e.getValue(), out);
				});

		return out.toString();
	}

	private void appendUsing(MarkdownSummarizer l, ProjectInfo pi, StringBuilder out) {
		try {
			String[] parts = pi.mainRepo.substring("https://github.com/".length()).split("/");
			String name = parts[1];
			String org = parts[0];

			out.append(l.convert(getRepoDetails(l, name, org), qe));
		} catch (Exception e) {
			throw new RuntimeException("Couldn't process: ", e);
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
	public void outputResults(String report) throws Exception {
		super.outputResults(report);
		if (Arrays.asList(env.getActiveProfiles()).contains("pr")) {
			commit.commitFile(readmeFile, report.getBytes(), head, repo, owner);
			pr.createOrUpdatePullRequest(repo, owner, base, head, Collections.singletonList("@robmoffat"),
					"Updated Generated Files");
		}
	}

}
