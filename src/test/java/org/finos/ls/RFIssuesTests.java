package org.finos.ls;

import java.io.FileWriter;

import org.finos.ls.queries.BigProjectsSummarizer;
import org.finos.ls.queries.IssueQuery;
import org.finos.ls.search.FinanceCSVSummarizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LandscapeApp.class)
@ActiveProfiles("local")
public class RFIssuesTests {
	
	@Autowired
	QueryService qs;
	
	@Autowired
	CommitService cs;
	
	@Autowired
	PullRequestService pr;
	
	@Autowired
	ReadmeGenerator readme;
	
	@Autowired
	CSVGenerator csv;

	@Test
	public void javascriptIssues() throws Exception {
		FileWriter fw = new FileWriter("src/main/resources/issues/Javascript.csv");
		csv.generateFromFile(new IssueQuery(), "src/main/resources/projects/Javascript-Table 1.csv", fw);
		fw.close();
	}
	
	@Test
	public void javaIssues() throws Exception {
		FileWriter fw = new FileWriter("src/main/resources/issues/Java.csv");
		csv.generateFromFile(new IssueQuery(), "src/main/resources/projects/Java-Table 1.csv", fw);
		fw.close();
	}
	
	@Test
	public void pythonIssues() throws Exception {
		FileWriter fw = new FileWriter("src/main/resources/issues/Python.csv");
		csv.generateFromFile(new IssueQuery(), "src/main/resources/projects/Python-Table 1.csv", fw);
		fw.close();
	}
	
	@Test
	public void randomIssues() throws Exception {
		FileWriter fw = new FileWriter("src/main/resources/issues/Random.csv");
		csv.generateFromFile(new IssueQuery(), "src/main/resources/projects/Random-Table 1.csv", fw);
		fw.close();
	}
}
