package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.finos.scan.github.client.Blob;
import org.finos.scan.github.client.BranchProtectionRule;
import org.finos.scan.github.client.BranchProtectionRuleConnection;
import org.finos.scan.github.client.Commit;
import org.finos.scan.github.client.Issue;
import org.finos.scan.github.client.IssueComment;
import org.finos.scan.github.client.License;
import org.finos.scan.github.client.Ref;
import org.finos.scan.github.client.Repository;

public class BasicQueries {

	public enum FinosStatus { NO_README, INCUBATING, ACTIVE, NONE }

	public enum OpenSSFStatus { NO_README, DONE, NONE }
	
	public static QueryType<Repository> PASSTHROUGH = new AbstractQueryType<Repository>(
			"id\n"
			+ "owner", 50, r -> r);
	
	public static QueryType<Integer> BRANCH_RULES = new AbstractQueryType<>("   defaultBranchRef {\n"
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
		+ "    }", 20, r -> convertToRuleSummary(r));
	
	
	public static QueryType<FinosStatus> FINOS_STATUS = new AbstractQueryType<FinosStatus>(	
			"object(expression: \"HEAD:README.md\") {\n"
		+ "      ... on Blob {\n"
		+ "        text\n"
		+ "      }\n"
		+ "    }", 10, r -> {
			Blob readme = (Blob) r.getObject();
			if (readme == null) {
				return FinosStatus.NO_README;
			}
			
			String text = readme.getText();
			if (text.contains("https://finosfoundation.atlassian.net/wiki/display/FINOS/Incubating")) {
				return FinosStatus.INCUBATING;
			} else if (text.contains("https://finosfoundation.atlassian.net/wiki/display/FINOS/Active")) {
				return FinosStatus.ACTIVE;
			} else {
				return FinosStatus.NONE;
			}
		});
	
	
	public static QueryType<Activity> ISSUE_ACTIVITY = new AbstractQueryType<>("issues(\n"
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
		
	
	public static QueryType<String> LICENSE_INFO = new AbstractQueryType<String>(" licenseInfo {\n"
		+ "            spdxId\n"
		+ "          }", 50, r-> {
			License l = r.getLicenseInfo();
			return (l == null) ? "" : l.getSpdxId();});
	
	public static QueryType<Activity> MAIN_RECENT_COMMITTERS = new AbstractQueryType<>("defaultBranchRef {\n"
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
		+ "    }", 10, r -> condenseRecentCommitters(r));
		
	
	public static QueryType<OpenSSFStatus> OPENSSF_STATUS = new AbstractQueryType<>("object(expression: \"HEAD:README.md\") {\n"
		+ "      ... on Blob {\n"
		+ "        text\n"
		+ "      }\n"
		+ "    }", 20, r -> {
		Blob readme = (Blob) r.getObject();
		if (readme == null) {
			return OpenSSFStatus.NO_README;
		}
		
		String text = readme.getText();
		if (text.contains("https://bestpractices.coreinfrastructure.org")) {
			return OpenSSFStatus.DONE;		
		} else {
			return OpenSSFStatus.NONE;
		}
	});
	
	public static final QueryType<Activity> COMBINED_ACTIVITY = new AbstractQueryType<>(
		BasicQueries.MAIN_RECENT_COMMITTERS.getFields() + 
		BasicQueries.ISSUE_ACTIVITY.getFields()+"  isArchived",
		5,
		r -> combinedActivity(r));

	private static Activity combinedActivity(Repository r) {
		if (r.getIsArchived()) {
			return new Activity(0, Collections.EMPTY_LIST);
		} else {
			Activity a = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r);
			Activity b = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r);
			LinkedHashSet<String> combinedPeople = new LinkedHashSet<String>(a.getPeople());
			combinedPeople.addAll(b.getPeople());
			return new Activity(a.getScore() + b.getScore(), new ArrayList<>(combinedPeople));
		}
	}
	
	private static Activity condenseIssueCommenters(Repository r) {
		Date cutoff = cutoff();
	
		Map<String, Long> comments = r.getIssues().getEdges().stream()
			.map(e -> e.getNode())
			.flatMap(n -> getComments(n))
			.filter(c -> cutoff.before(c.getUpdatedAt()))
			.collect(Collectors.groupingBy(c -> c.getAuthor().getLogin(), Collectors.counting()));
		
		return summarize(comments);
	}
	
	private static Activity condenseRecentCommitters(Repository r) {
		Date cutoff = cutoff();
		
		Ref defaultBranchRef = r.getDefaultBranchRef();
		if (defaultBranchRef == null) {
			return new Activity(0, Collections.emptyList());  // private repo or some security reason
		}
		Commit target = (Commit) defaultBranchRef.getTarget();
		Map<String, Long> counts = target.getHistory().getEdges().stream()
			.map(ce -> (Commit) ce.getNode())
			.filter(c -> cutoff.before(c.getCommittedDate()))
			.filter(c -> c.getAuthor().getUser() != null)
			.collect(Collectors.groupingBy(c -> c.getAuthor().getUser().getLogin(), Collectors.counting()));
		
		
		return summarize(counts);
	}
	
	private static Activity summarize(Map<String, Long> counts) {
		List<String> committers = counts.entrySet().stream()
			.sorted((a, b) -> -a.getValue().compareTo(b.getValue()))
			.map(e -> "@"+e.getKey())
			.collect(Collectors.toList());
		
		long totalCommitsInLastYear = counts.values().stream()
			.reduce(Long::sum)
			.orElse(0l);
		
		return new Activity(totalCommitsInLastYear,committers);
	}
	
	private static int countReviewers(BranchProtectionRule bpr) {
		if (bpr.getRequiresApprovingReviews()) {
			return bpr.getRequiredApprovingReviewCount();
		} else {
			return 0;
		}
	}
	
	private static Stream<IssueComment> getComments(Issue n) {
		return n.getComments().getEdges().stream()
			.map(e -> e.getNode());
	}
	
	private static boolean matches(BranchProtectionRule bpr, String mainBranch) {
		String pattern = bpr.getPattern();
		return mainBranch.matches(pattern.replaceAll("\\*",".*"));
	}
	
	private static Date cutoff() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		Date cutoff = cal.getTime();
		return cutoff;
	}
	
	public static int convertToRuleSummary(Repository r) {
		BranchProtectionRuleConnection bprc = r.getBranchProtectionRules();
		if (r.getDefaultBranchRef() == null) {
			return -1;
		}
		
		String mainBranch = r.getDefaultBranchRef().getName();
		return bprc.getEdges().stream()
			.map(e -> (BranchProtectionRule) e.getNode())
			.filter(bpr -> matches(bpr, mainBranch))
			.map(bpr -> countReviewers(bpr))
			.findFirst()
			.orElse(0);
	}

}

