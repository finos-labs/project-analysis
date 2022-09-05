package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.finos.ls.queries.BasicQueries.FinosStatus;
import org.finos.ls.queries.BasicQueries.OpenSSFStatus;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.util.StringUtils;

public class CSVSummarizer implements QueryType<List<Object>> {
	
	private List<String> ignoreList;
	
	public CSVSummarizer(List<String> ignoreList, List<String> priorityList) {
		super();
		this.ignoreList = ignoreList;
		this.priorityList = priorityList;
	}

	private List<String> priorityList;
	
	public static final String[] FIELDS = {
		"Score",
		"Pass",
		"Organisation",
		"Repo Name",
		"Finos Lifecycle State (from the README badge)",
		"License (from GitHub Api)",
		"Issue Activity (for most recent 50 issues, count most recent 30 comments in last 6 months)",
		"Commit Activity (count of commits in last 6 months, maxed at 100)",
		"OpenSSF Status (from the README badge)",
		"Github Archived (has the Archive tag in GitHub API)",
		"Branch Rules/Private (number of approvers or 0 if no approvers, -1 no branch rules",
		"SemGrep (checks .github/workflows/semgrep.yml file exists)",
		"CVE Scanning (checks .github/workflows/cve-scanning.yml file exists)",
		"Default Branch Name",
		"Excess Admins",
		"Main Issue Participants (list of all participants for issues loaded in Issue Activity column)",
		"Main Committers  (list of all participants for commits loaded in Commit Activity column)",
	};

	@Override
	public String getFields() {
		// could do with a more solid way of merging these things
		
		return  BasicQueries.BRANCH_RULES_QUERY+" "+
				BasicQueries.PASSTHROUGH.getFields() + " " + 
				BasicQueries.COMBINED_ACTIVITY.getFields() + " " + 
				BasicQueries.OPENSSF_STATUS.getFields() + " " +
				BasicQueries.WRONG_ADMINS.getFields() + " " +
				BasicQueries.LICENSE_INFO.getFields();
	}

	@Override
	public List<Object> convert(Repository r, QueryExecutor qe) {
		Activity issue = BasicQueries.ISSUE_ACTIVITY.convert(r, qe);
		Activity commit = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r, qe);
		FinosStatus finosStatus = BasicQueries.FINOS_STATUS.convert(r, qe);
		OpenSSFStatus openSSF = BasicQueries.OPENSSF_STATUS.convert(r, qe);
		String license = BasicQueries.LICENSE_INFO.convert(r, qe);
		Boolean semGrep = BasicQueries.SEMGREP_ACTION.convert(r, qe);
		Boolean cveScan = BasicQueries.CVE_SCANNING_ACTION.convert(r, qe);
		String defaultBranchName = BasicQueries.DEFAULT_BRANCH_NAME.convert(r, qe);
		String wrongAdmins = BasicQueries.WRONG_ADMINS.convert(r, qe);
		int branchReviewers = BasicQueries.BRANCH_RULES.convert(r, qe);
		long score = calculateScore(issue, commit, r.getName(), r.getIsPrivate() | r.getIsArchived());
		String pass = passes(finosStatus, openSSF, wrongAdmins, branchReviewers, license, semGrep, cveScan, score == 0);
		
		
		List<Object> out = new ArrayList<>();
		out.add(score);
		out.add(pass);
		out.add(r.getOwner().getLogin());
		out.add(r.getName());
		out.add(finosStatus.name());
		out.add(license);
		out.add(issue.getScore());
		out.add(commit.getScore());
		out.add(openSSF.name());
		out.add(r.getIsArchived());
		out.add(r.getIsPrivate() ? "PRIVATE" : ""+branchReviewers);
		out.add(semGrep);
		out.add(cveScan);
		out.add(defaultBranchName);
		out.add(wrongAdmins);
		out.add(convertToSpaceList(issue));
		out.add(convertToSpaceList(commit));

		return out;
	}

	private String passes(FinosStatus finosStatus, OpenSSFStatus openSSFStatus, String wrongAdmins, int branchReviewers, String license, boolean semGrep, Boolean cveScan, boolean ignore) {
		StringBuilder sb = new StringBuilder();
		
		if (ignore) {
			return "";
		} 
		
		if ((finosStatus == FinosStatus.NONE) || (finosStatus == FinosStatus.NO_README)) {
			sb.append(" (Finos Lifecycle)");
		}
		
		if (wrongAdmins.length() > 0) { 
			sb.append(" (wrong admins)");
		}
		
		if ((openSSFStatus == OpenSSFStatus.NONE) || (openSSFStatus == OpenSSFStatus.NO_README)) {
			sb.append(" (OpenSSF Status)");
		}
		
		if (branchReviewers == -1) {
			sb.append(" (Branch Reviewers)");
		}
		
		if (!StringUtils.hasLength(license)) {
			sb.append(" (No License Set)");
		}
		
		if (isSoftwareLicense(license)) {
			if (!cveScan) {
				sb.append(" (No CVE Scan)");				
			}
			if (!semGrep) {
				sb.append(" (No Sem Grep)");				
			}
		}
		
		if (sb.length() > 0) { 
			return "FAIL"+sb.toString();
		} else {
			return "";
		}
		
  	}

	private boolean isSoftwareLicense(String license) {
		return !"CC-BY-4.0".equals(license);
	}

	private long calculateScore(Activity issue, Activity commit, String name, boolean ignore) {
		if (ignore) {
			return 0;
		} else if (matches(name, ignoreList)) {
			return 0;
		} else if (matches(name, priorityList)) {
			return 1000;
		} else {
			return issue.getScore() + commit.getScore();
		}
	}

	private boolean matches(String name, List<String> patterns) {
		for (String p : patterns) {
			if (name.toLowerCase().contains(p.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private String convertToSpaceList(Activity issue) {
		return issue.getPeople().stream().reduce((a, b) -> a+" "+b).orElse("");
	}

	@Override
	public int getPageSize() {
		return 2;
	}

}
