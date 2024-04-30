package org.finos.ls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.finos.ls.landscape.LandscapeReader;
import org.finos.ls.landscape.ProjectInfo;
import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.MarkdownSummarizer;
import org.finos.ls.queries.MarkdownSummarizer.SummaryLevel;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class ReadmeGenerator {
	
	@Autowired
	LandscapeReader lr;
	
	@Value("${landscapeUrl}")
	String landscapeUrl;
	
	@Autowired
	QueryService qs;
	
	@Autowired 
	QueryExecutor qe;
	
	private List<String> remove;
	
	public List<String> getRemove() {
		return remove;
	}

	public void setRemove(List<String> remove) {
		this.remove = remove;
	}

	Map<String, Repository> cache = new HashMap<>();
	
	public String generate(int cutoff, List<String> orgs) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		List<ProjectInfo> info = lr.readFromLandscape(landscapeUrl);
		
		Map<String, Activity> activeProjects = new HashMap<>(); 
		orgs.forEach(o -> {
			try {
				activeProjects.putAll(qs.getAllRepositoriesInOrg(BasicQueries.COMBINED_ACTIVITY, o));
			} catch (GraphQLRequestExecutionException | GraphQLRequestPreparationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		List<String> names = activeProjects.entrySet().stream()
			.filter(r -> r.getValue().getScore() > cutoff)
			.sorted((a, b) -> ((Long) b.getValue().getScore()).compareTo(a.getValue().getScore()))
			.map(e -> e.getKey().toLowerCase())
			.collect(Collectors.toList());
		
		StringBuilder out = new StringBuilder();
		out.append("# FINOS Projects\n\n");
		String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
		out.append("Here are some of FINOS' most active projects (as of "+date+"):\n\n");
		
		Map<String, ProjectInfo> bucketedProjects = bucketNames(info);
		
		out.append(tableOfContents(bucketedProjects));
		
		out.append(report(bucketedProjects));
		
		out.append("\n\n_For the full list see the repositories below_\n");
		return out.toString();
	}

	private String tableOfContents(Map<String, ProjectInfo> bucketedProjects) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
			.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
			.forEach(entry -> {
				String key = entry.getKey();
				out.append(" - ["+key+"](#"+key.replace(" ", "-")+")\n");
		});
		
		return out.toString();
	}

	private String report(Map<String, ProjectInfo> bucketedProjects) {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
			.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
			.forEach(e -> {
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

}
