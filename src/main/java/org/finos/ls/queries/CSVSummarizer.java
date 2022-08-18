package org.finos.ls.queries;

import java.util.ArrayList;
import java.util.List;

import org.finos.ls.queries.BasicQueries.FinosStatus;
import org.finos.scan.github.client.Repository;

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
	public List<String> convert(Repository r) {
		Activity issue = BasicQueries.ISSUE_ACTIVITY.convert(r);
		Activity commit = BasicQueries.MAIN_RECENT_COMMITTERS.convert(r);
		FinosStatus finosStatus = BasicQueries.FINOS_STATUS.convert(r);
		String license = BasicQueries.LICENSE_INFO.convert(r);
		
		List<String> out = new ArrayList<>();
		out.add(r.getOwner().getLogin());
		out.add(r.getName());
		out.add(finosStatus.name());
		out.add(license);
		out.add(""+ issue.getScore());
		out.add("" + commit.getScore());
		out.add(BasicQueries.OPENSSF_STATUS.convert(r).name());
		out.add(""+r.getIsArchived());
		out.add(r.getIsPrivate() ? "PRIVATE" : ""+BasicQueries.BRANCH_RULES.convert(r));
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
