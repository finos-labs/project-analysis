package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.List;

import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

public class BigProjectsSummarizer implements CSVSummarizer{
	
	private String language;
	
	public BigProjectsSummarizer(String language) {
		super();
		this.language = language;
	}

	
	public static final String[] FIELDS = {
		"Stargazers",
		"Owner",
		"Repo Name",
		"Created At",
		"Last Updated At",
		"Primary Language",
		"Forks",
		"Issue Count",
		"URL"
	};

	@Override
	public String getFields() {
		return """
          name
          owner {
            login
          }
          stargazerCount
          issues(states: CLOSED) {
				totalCount
          }
          forkCount
          createdAt
          updatedAt
          primaryLanguage {
            name
          }
          url
          """;
	}

	@Override
	public List<Object> convert(Repository r, QueryExecutor qe) {
		List<Object> out = new ArrayList<>();
		out.add(r.getStargazerCount());
		out.add(r.getOwner().getLogin());
		out.add(r.getName());
		out.add(r.getCreatedAt());
		out.add(r.getUpdatedAt());
		out.add(safeGetPrimaryLanguage(r));
		out.add(r.getForkCount());
		out.add(r.getIssues().getTotalCount());
		out.add(r.getUrl());
		return out;		
	}

	private String safeGetPrimaryLanguage(Repository r) {
		return r.getPrimaryLanguage() == null ? null : r.getPrimaryLanguage().getName();
	}

	@Override
	public int getPageSize() {
		return 2;
	}

	@Override
	public String[] getColumnNames() {
		return FIELDS;
	}

	@Override
	public String getRepositoryQueryPrefix() {
		return "created:2010-01-01..2024-12-31 stars:>10000 sort:stars-desc language:"+language;
	}

	@Override
	public int getMaxRepositories() {
		return 50;
	}

}
