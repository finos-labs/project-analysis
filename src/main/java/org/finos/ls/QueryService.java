package org.finos.ls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.finos.scan.github.client.Blob;
import org.finos.scan.github.client.BranchProtectionRule;
import org.finos.scan.github.client.BranchProtectionRuleConnection;
import org.finos.scan.github.client.Comment;
import org.finos.scan.github.client.Commit;
import org.finos.scan.github.client.Issue;
import org.finos.scan.github.client.IssueComment;
import org.finos.scan.github.client.License;
import org.finos.scan.github.client.Organization;
import org.finos.scan.github.client.Ref;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryConnection;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Component
public class QueryService {
	
	enum QueryType {
		
		LICENSE_INFO(" licenseInfo {\n"
				+ "            spdxId\n"
				+ "          }", 50, r-> {License l = r.getLicenseInfo();
				return (l == null) ? "" : l.getSpdxId();}),
		
	
		FINOS_STATUS("object(expression: \"HEAD:README.md\") {\n"
				+ "      ... on Blob {\n"
				+ "        text\n"
				+ "      }\n"
				+ "    }", 10, r -> {
					Blob readme = (Blob) r.getObject();
					if (readme == null) {
						return "NO README";
					}
					
					String text = readme.getText();
					if (text.contains("https://finosfoundation.atlassian.net/wiki/display/FINOS/Incubating")) {
						return "INCUBATING";
					} else if (text.contains("https://finosfoundation.atlassian.net/wiki/display/FINOS/Active")) {
						return "ACTIVE";
					} else {
						return "NONE";
					}
				}),
		
		BRANCH_RULES("   defaultBranchRef {\n"
				+ "      id\n"
				+ "      name\n"
				+ "    }\n"
				+ "    branchProtectionRules(first: 10) {\n"
				+ "      edges {\n"
				+ "        node {\n"
				+ "          id\n"
				+ "          requiredApprovingReviewCount\n"
				+ "          requiredStatusCheckContexts\n"
				+ "          requiresApprovingReviews\n"
				+ "          requiresCodeOwnerReviews\n"
				+ "          requiresCommitSignatures\n"
				+ "          requiresConversationResolution\n"
				+ "          requiresLinearHistory\n"
				+ "          requiresStatusChecks\n"
				+ "          requiresStrictStatusChecks\n"
				+ "          restrictsPushes\n"
				+ "          restrictsReviewDismissals\n"
				+ "          pattern\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }", 20, r -> convertToRuleSummary(r)),
		
		OPENSSF_STATUS("object(expression: \"HEAD:README.md\") {\n"
				+ "      ... on Blob {\n"
				+ "        text\n"
				+ "      }\n"
				+ "    }", 20, r -> {
					Blob readme = (Blob) r.getObject();
					if (readme == null) {
						return "NO README";
					}
					
					String text = readme.getText();
					if (text.contains("https://bestpractices.coreinfrastructure.org")) {
						return "DONE";		
					} else {
						return "NONE";
					}
				}),
		
		MAIN_RECENT_COMMITTERS("defaultBranchRef {\n"
				+ "      target {\n"
				+ "        ... on Commit {\n"
				+ "          history(first: 100) {\n"
				+ "            edges {\n"
				+ "              node {\n"
				+ "                ... on Commit {\n"
				+ "                  committedDate\n"
				+ "                }\n"
				+ "                author {\n"
				+ "                  user {\n"
				+ "                    login\n"
				+ "                  }\n"
				+ "                }\n"
				+ "              }\n"
				+ "            }\n"
				+ "          }\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }", 10, r -> condenseRecentCommitters(r)),
		
		ISSUE_ACTIVITY("issues(\n"
				+ "      orderBy: {field: UPDATED_AT, direction: DESC}\n"
				+ "      first: 50\n"
				+ "    ) {\n"
				+ "      edges {\n"
				+ "        node {\n"
				+ "          id\n"
				+ "          comments(first: 30) {\n"
				+ "            totalCount\n"
				+ "            edges {\n"
				+ "              node {\n"
				+ "                author {\n"
				+ "                  login\n"
				+ "                }\n"
				+ "                updatedAt\n"
				+ "              }\n"
				+ "            }\n"
				+ "          }\n"
				+ "          lastEditedAt\n"
				+ "          title\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }", 10, r -> condenseIssueCommenters(r));

		private String fields;
		
		private Function<Repository, String> converter;
		
		private int pageSize;

		public int getPageSize() {
			return pageSize;
		}

		QueryType(String string, int pageSize, Function<Repository, String> converter) {
			this.fields = string;
			this.pageSize = pageSize;
			this.converter = converter;
		}


		String getFields() {
			return fields;
		}

		public Function<Repository, String> getConverter() {
			return converter;
		}

		public void setConverter(Function<Repository, String> converter) {
			this.converter = converter;
		}
	}

	@Autowired
	QueryExecutor qe;
	
	
	public Map<String, String> getAllFinosRepositories(QueryType qt) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		List<Repository> out = new ArrayList<Repository>();
		int total = 1000;
		String cursor = null;
		
		while (total > out.size()) {
			String query = "{\n"
					+ "    id\n"
					+ "    repositories(first: "+qt.pageSize+", after: &cursor) { \n"
					+ "      totalCount\n"
					+ "      totalDiskUsage\n"
					+ "      pageInfo {\n"
					+ "        endCursor\n"
					+ "        startCursor\n"
					+ "      }\n"
					+ "      edges {\n"
					+ "        node {\n"
					+ "          name \n"
					+ "          "+qt.getFields()
					+ "        }\n"
					+ "      }\n"
					+ "    }\n"
					+ "  }\n"
					+ "}";
			
			Organization o = qe.organization(query, "finos", "cursor", cursor);
			
			RepositoryConnection conn = o.getRepositories();
			out.addAll(
				conn.getEdges().stream()
					.map(e -> e.getNode())
					.collect(Collectors.toList()));
			total = conn.getTotalCount();
			cursor = conn.getPageInfo().getEndCursor();
		}
		
		return out.stream().collect(Collectors.toMap(r -> r.getName(), qt.getConverter()));
				
	}
	
	public static String convertToRuleSummary(Repository r) {
		BranchProtectionRuleConnection bprc = r.getBranchProtectionRules();
		if (r.getDefaultBranchRef() == null) {
			return "NO DEFAULT BRANCH";
		}
		
		String mainBranch = r.getDefaultBranchRef().getName();
		return "" + bprc.getEdges().stream()
			.map(e -> (BranchProtectionRule) e.getNode())
			.filter(bpr -> matches(bpr, mainBranch))
			.map(bpr -> countReviewers(bpr))
			.findFirst()
			.orElse("");
	}

	private static String countReviewers(BranchProtectionRule bpr) {
		if (bpr.getRequiresApprovingReviews()) {
			return ""+bpr.getRequiredApprovingReviewCount();
		} else {
			return "0";
		}
	}

	private static boolean matches(BranchProtectionRule bpr, String mainBranch) {
		String pattern = bpr.getPattern();
		return mainBranch.matches(pattern.replaceAll("\\*",".*"));
	}
	

	private static String condenseRecentCommitters(Repository r) {
		Date cutoff = oneYearAgo();
		
		Ref defaultBranchRef = r.getDefaultBranchRef();
		if (defaultBranchRef == null) {
			return "";  // private repo or some security reason
		}
		Commit target = (Commit) defaultBranchRef.getTarget();
		Map<String, Long> counts = target.getHistory().getEdges().stream()
			.map(ce -> (Commit) ce.getNode())
			.filter(c -> cutoff.before(c.getCommittedDate()))
			.filter(c -> c.getAuthor().getUser() != null)
			.collect(Collectors.groupingBy(c -> c.getAuthor().getUser().getLogin(), Collectors.counting()));
		
		
		return summarize(counts);
	}

	private static String summarize(Map<String, Long> counts) {
		String committers = counts.entrySet().stream()
			.sorted((a, b) -> -a.getValue().compareTo(b.getValue()))
			.map(e -> "@"+e.getKey())
			.collect(Collectors.joining(","));
		
		long totalCommitsInLastYear = counts.values().stream()
			.reduce(Long::sum)
			.orElse(0l);
		
		return totalCommitsInLastYear+";"+committers;
	}

	private static Date oneYearAgo() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date cutoff = cal.getTime();
		return cutoff;
	}


	private static String condenseIssueCommenters(Repository r) {
		Date cutoff = oneYearAgo();

		Map<String, Long> comments = r.getIssues().getEdges().stream()
			.map(e -> e.getNode())
			.flatMap(n -> getComments(n))
			.filter(c -> cutoff.before(c.getUpdatedAt()))
			.collect(Collectors.groupingBy(c -> c.getAuthor().getLogin(), Collectors.counting()));
		
		return summarize(comments);
	}

	private static Stream<IssueComment> getComments(Issue n) {
		return n.getComments().getEdges().stream()
			.map(e -> e.getNode());
	}
}
