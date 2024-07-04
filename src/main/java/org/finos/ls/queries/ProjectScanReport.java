package org.finos.ls.queries;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.inject.Named;

import org.finos.ls.CSVGenerator;
import org.finos.ls.landscape.LandscapeReader;
import org.finos.ls.landscape.ProjectInfo;
import org.finos.ls.report.AbstractReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Named("scan")
public class ProjectScanReport extends AbstractReport {

	@Value("${landscapeUrl}")
	String landscapeUrl;

	@Autowired
	LandscapeReader lr;
	
	@Autowired
	CSVGenerator csv;

	@Value("${githubOrgs}")
	List<String> orgs;
	
	@Value("${scan.output:}")
	String filename;

	public String getFilename() {
		return filename;
	}

	public List<String> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}

	@Override
	public String generateInner() {
		List<ProjectInfo> projects = lr.readFromLandscape(landscapeUrl);
		ProjectScanCSVSummarizer summ = new ProjectScanCSVSummarizer(projects);
		StringBuilder sb = new StringBuilder();
		orgs.forEach(o -> {
			try {
				sb.append(csv.generateOrg(o, summ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return sb.toString();
	}
	
}
