package org.finos.ls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.Summarizer;
import org.finos.ls.queries.Summarizer.SummaryLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class ReadmeGenerator {

	@Autowired
	QueryService qs;
	
	public String generate(int cutoff) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Activity> activeProjects = qs.getAllFinosRepositories(BasicQueries.COMBINED_ACTIVITY);
		
		List<String> names = activeProjects.entrySet().stream()
			.filter(r -> r.getValue().getScore() > cutoff)
			.sorted((a, b) -> ((Long) b.getValue().getScore()).compareTo(a.getValue().getScore()))
			.map(e -> e.getKey().toLowerCase())
			.collect(Collectors.toList());
		
		StringBuilder out = new StringBuilder();
		out.append("# FINOS Projects\n\n");
		out.append("Here are some of FINOS' most popular projects:\n\n");
		
		System.out.println(names.toString().replace(",", "\n"));
		
		Map<String, List<String>> bucketedProjects = bucketNames(names);
		
		return report(bucketedProjects);
	}

	private String report(Map<String, List<String>> bucketedProjects) {
		Summarizer topLevel = new Summarizer(SummaryLevel.MAIN);
		Summarizer secondLevel = new Summarizer(SummaryLevel.SUBITEM);
		StringBuilder out = new StringBuilder();
		bucketedProjects.entrySet().stream()
			.sorted((a, b) -> a.getKey().compareTo(b.getKey()))
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

	private void appendUsing(Summarizer l, String name, StringBuilder out) {
		try {
			out.append(qs.getSingleRepository(l, "finos", name));
		} catch (Exception e) {
			throw new RuntimeException("Couldn't process: ", e);
		}
	}

	private Map<String, List<String>> bucketNames(List<String> names) {
		Map<String, List<String>> out = new HashMap<String, List<String>>();
		names = bucket(out, names, "Legend", n -> n.contains("legend"));
		names = bucket(out, names, "Morphir", n -> n.contains("morphir"));
		names = bucket(out, names, "Symphony", n -> n.contains("symphony"));
		names = bucket(out, names, "FDC3", n -> n.contains("fdc3"));
		names = bucket(out, names, "Waltz", n -> n.contains("waltz"));
		names = remove(names, 
				"finos-landscape", 
				"software-project-blueprint", 
				"standards-project-blueprint", 
				"clabot-config",
				"finos-parent-pom");
		names = bucketItems(out, names, "Symphony", "messageml-utils");
		names = bucketItems(out, names, "SIGs", "dei_sig", "innersource", "curref-data", "open-source-readiness", "compliant-financial-infrastructure");
		singleBucketTheRest(out, names);
		return out;
	}

	private void singleBucketTheRest(Map<String, List<String>> out, List<String> names) {
		names.stream()
			.forEach(n -> out.put(n, Collections.singletonList(n)));
	}

	private List<String> remove(List<String> items, String... toRemove) {
		List<String> toGo = Arrays.asList(toRemove);
		return items.stream()
			.filter(n -> toGo.indexOf(n) == -1)
			.collect(Collectors.toList());
	}
	
	private List<String> bucketItems(Map<String, List<String>> out, List<String> in, String key, String... toInclude) {
		List<String> toGo = Arrays.asList(toInclude);
		return bucket(out, in, key, n -> toGo.indexOf(n) > -1);
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
			.toList();
	}
}
