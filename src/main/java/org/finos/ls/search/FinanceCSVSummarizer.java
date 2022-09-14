package org.finos.ls.search;

import java.util.ArrayList;
import java.util.List;

import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.CSVSummarizer;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

public class FinanceCSVSummarizer implements CSVSummarizer {

	public static final String[] FIELDS = {
			"stargazerCount",
			"Organisation",
			"Repo Name",
			"Commit Activity (count of commits in last 6 months, maxed at 100)",
			"Main Committers  (list of all participants for commits loaded in Commit Activity column)",
		};
	
	@Override
	public String getFields() {
		return BasicQueries.MAIN_RECENT_COMMITTERS.getFields() + " " + "stargazerCount url";
	}

	@Override
	public List<Object> convert(Repository r, QueryExecutor qe) {
		Activity commit = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r, qe);
		long stargazerCount = r.getStargazerCount();
		
		List<Object> out = new ArrayList<>();
		out.add(stargazerCount);
		out.add(r.getUrl());
		out.add(commit.getScore());
		out.add(convertToSpaceList(commit));

		return out;
	}
	
	private String convertToSpaceList(Activity issue) {
		return issue.getPeople().stream().reduce((a, b) -> a+" "+b).orElse("");
	}

	@Override
	public int getPageSize() {
		return 5;
	}

	@Override
	public String[] getColumnNames() {
		return FIELDS;
	}

	@Override
	public String getRepositoryQueryPrefix() {
		return "orderBy: {field: STARGAZERS, direction: DESC}, ";
	}

	@Override
	public int getMaxRepositories() {
		return 150;
	}

	
	
}

