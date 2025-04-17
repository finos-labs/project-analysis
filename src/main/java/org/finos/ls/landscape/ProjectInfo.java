package org.finos.ls.landscape;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {

	public enum ProjectType {
		SIG, ACTIVE, INCUBATING
	}

	public String name;
	public String category;
	public String subcategory;
	public String mainRepo;
	public List<String> additionalRepos;
	public String logo;
	public String homepageUrl;
	public ProjectType type;
	public String mailingList;
	public List<String> slackChannels;
	public List<String> zoomEmails;
	public List<String> tags = new ArrayList<>();
	public String calendarSearchString;

	public static ProjectType projectType(String in) {
		if (in != null) {
			switch (in) {
				case "incubating":
					return ProjectType.INCUBATING;
				case "active":
					return ProjectType.ACTIVE;
				case "sig":
					return ProjectType.SIG;
				default:
					return null;
			}
		}
		return null;

	}

	@Override
	public String toString() {
		return "ProjectInfo [name=" + name + ", category=" + category + ", subcategory=" + subcategory + ", mainRepo="
				+ mainRepo + ", additionalRepos=" + additionalRepos + ", logo=" + logo + ", homepageUrl=" + homepageUrl
				+ ", type=" + type + ", mailingList=" + mailingList + ", slackChannels=" + slackChannels
				+ ", zoomEmails=" + zoomEmails + "]";
	}

}
