package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.List;

import org.finos.scan.github.client.Issue;
import org.finos.scan.github.client.IssueEdge;
import org.finos.scan.github.client.LabelConnection;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

/**
 * Downloads 20 issues and reports them.
 */
public class IssueQuery implements TableSummarizer {

	@Override
	public String getFields() {
		return """
				 issues(first: 20, states:CLOSED) {
      edges  {
        node {
          ... on Issue {
					title
				          createdAt
				          updatedAt
				          number
				          state
				          author {
				            login
				          }
				          labels(first: 5) {
				            edges {
				              node {
				                name
				              }
				            }
				          }
				        }
				      }
				    }
				 }
				""";
	}

	@Override
	public List<List<Object>> convert(Repository r, QueryExecutor qe) {
		List<List<Object>> out = new ArrayList<>();
		for (IssueEdge ie : r.getIssues().getEdges()) {
			Issue n = ie.getNode();
			List<Object> iMap = new ArrayList<>();
			iMap.add(n.getTitle());
			iMap.add(n.getCreatedAt());
			iMap.add(n.getUpdatedAt());
			iMap.add(n.getNumber());
			iMap.add(n.getState());
			String login = (n.getAuthor() == null) ? "" : n.getAuthor().getLogin();
			iMap.add(login);
			LabelConnection lc = n.getLabels();
			String labels = lc.getEdges().stream()
					.map(l -> l.getNode().getName()+" ")
					.reduce(String::concat)
					.orElse("");
			iMap.add(labels);
			iMap.add(r.getOwner().getLogin());
			iMap.add(r.getName());
			out.add(iMap);
		}
		
		return out;
	}

	@Override
	public int getPageSize() {
		return 0;
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "title", "createdAt", "updatedAt", "number", "state", "author", "labels", "owner", "repository" };
	}

}
