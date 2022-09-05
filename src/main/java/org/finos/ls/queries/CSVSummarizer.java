package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.List;

import org.finos.ls.queries.BasicQueries.FinosStatus;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

public class CSVSummarizer implements QueryType<List<String>> {
	
	public static final String[] FIELDS = {
		"Organisation",
		"Repo Name",
		"Finos Status",
		"License",
		"Issue Activity",
		"Commit Activity",
		"OpenSSF Status",
		"Github Archived",
		"Branch Rules/Private",
		"SemGrep",
		"CVE Scanning",
		"Default Branch Name",
		"Main Issue Participants",
		"Main Committers",
	};

	@Override
	public String getFields() {
		// could do with a more solid way of merging these things
		
		return  BasicQueries.BRANCH_RULES_QUERY+" "+
				BasicQueries.PASSTHROUGH.getFields() + " " + 
				BasicQueries.COMBINED_ACTIVITY.getFields() + " " + 
				BasicQueries.OPENSSF_STATUS.getFields() + " " +
				BasicQueries.LICENSE_INFO.getFields();
	}

	@Override
	public List<String> convert(Repository r, QueryExecutor qe) {
		Activity issue = BasicQueries.ISSUE_ACTIVITY.convert(r, qe);
		Activity commit = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r, qe);
		FinosStatus finosStatus = BasicQueries.FINOS_STATUS.convert(r, qe);
		String license = BasicQueries.LICENSE_INFO.convert(r, qe);
		Boolean semGrep = BasicQueries.SEMGREP_ACTION.convert(r, qe);
		Boolean cveScan = BasicQueries.CVE_SCANNING_ACTION.convert(r, qe);
		String defaultBranchName = BasicQueries.DEFAULT_BRANCH_NAME.convert(r, qe);
		
		List<String> out = new ArrayList<>();
		out.add(r.getOwner().getLogin());
		out.add(r.getName());
		out.add(finosStatus.name());
		out.add(license);
		out.add(""+ issue.getScore());
		out.add("" + commit.getScore());
		out.add(BasicQueries.OPENSSF_STATUS.convert(r, qe).name());
		out.add(""+r.getIsArchived());
		out.add(r.getIsPrivate() ? "PRIVATE" : ""+BasicQueries.BRANCH_RULES.convert(r, qe));
		out.add(""+semGrep);
		out.add(""+cveScan);
		out.add(defaultBranchName);
		out.add(convertToSpaceList(issue));
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

}
