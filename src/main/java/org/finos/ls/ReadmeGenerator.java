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

import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.MarkdownSummarizer;
import org.finos.ls.queries.MarkdownSummarizer.SummaryLevel;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "scanning.readme")
public class ReadmeGenerator {

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

	private Map<String, List<String>> buckets;
	
	public Map<String, List<String>> getBuckets() {
		return buckets;
	}

	public void setBuckets(Map<String, List<String>> buckets) {
		this.buckets = buckets;
	}

	Map<String, Repository> cache = new HashMap<>();
	
	public String generate(int cutoff, List<String> orgs) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
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
		
		Map<String, List<String>> bucketedProjects = bucketNames(names);
		
		out.append(tableOfContents(bucketedProjects));
		
		out.append(report(bucketedProjects));
		
		out.append("\n\n_For the full list see the repositories below_\n");
		return out.toString();
	}

	private String tableOfContents(Map<String, List<String>> bucketedProjects) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
			.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
			.forEach(entry -> {
				String key = entry.getKey();
				List<String> val = entry.getValue();
				Collections.sort(val);
				if (val.size() == 1) {
					String string = getTitleForRepo(val.get(0));
					out.append(" - ["+string+"](#"+string.replace(" ", "-")+")\n");
				} else {
					out.append(" - "+key+"\n");
					for (String string : val) {
						String title = getTitleForRepo(string);
						out.append("   - ["+title+"](#"+title.replace(" ", "-")+")\n");
					}
				}
		});
		
		return out.toString();
	}

	private String getTitleForRepo(String name) {
		try {
			MarkdownSummarizer s = new MarkdownSummarizer(SummaryLevel.SUBITEM);
			String[] parts = name.split(",");
			Repository repo = getRepoDetails(s, parts[1], parts[0]);
			String title = s.getTitleFromNameOrH1(name, repo);
			return title;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String report(Map<String, List<String>> bucketedProjects) {
		MarkdownSummarizer topLevel = new MarkdownSummarizer(SummaryLevel.MAIN);
		MarkdownSummarizer secondLevel = new MarkdownSummarizer(SummaryLevel.SUBITEM);
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
			.sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
			.forEach(e -> {
				List<String> items = e.getValue();
				Collections.sort(items);
				if (items.size() > 1) {
					out.append("## "+e.getKey()+"\n\n");
					items.forEach(i -> appendUsing(secondLevel, i, out));
				} else {
					appendUsing(topLevel, items.get(0), out);
				}
			});
		
		return out.toString();
	}

	private void appendUsing(MarkdownSummarizer l, String id, StringBuilder out) {
		try {
			String[] parts = id.split(",");
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

	private Map<String, List<String>> bucketNames(List<String> names) {
		Map<String, List<String>> out = new HashMap<String, List<String>>();
		
		names = remove(names, this.remove);
		
		for (Map.Entry<String, List<String>> bucket : this.buckets.entrySet()) {
			names = bucketItems(out, names, bucket.getKey(), bucket.getValue());
		}
		
		singleBucketTheRest(out, names);
		return out;
	}

	private void singleBucketTheRest(Map<String, List<String>> out, List<String> names) {
		names.stream()
			.forEach(n -> out.put(n, Collections.singletonList(n)));
	}

	private List<String> remove(List<String> items, List<String> toGo) {
		return remove(items, n -> toGo.contains(n));
	}
	
	private List<String> remove(List<String> items, Predicate<String> toRemove) {
		return items.stream()
				.filter(n -> !toRemove.test(n))
				.collect(Collectors.toList());
	}
	
	private List<String> bucketItems(Map<String, List<String>> out, List<String> in, String key, List<String> toGo) {
		List<String> lcToGo = toGo.stream().map(e -> e.toLowerCase()).collect(Collectors.toList());
		Predicate<String> theTest = j -> lcToGo.stream()
				.map(e -> j.toLowerCase().indexOf(e) > -1)
				.reduce(false, (a, b) -> a || b);
		return bucket(out, in, key, theTest);
	}

	private List<String> bucket(Map<String, List<String>> out, List<String> in, String key, Predicate<String> test) {
		return in.stream()
			.map(n -> { 
				if (test.test(n)) {
					List<String> names = out.getOrDefault(key, new ArrayList<>());
					names.add(n);
					out.put(key, names);
				}
				return n;
			})
			.filter(test.negate())
			.collect(Collectors.toList());
	}
}
