package org.finos.ls;

import java.util.Collections;

import org.finos.ls.queries.BigProjectsSummarizer;
import org.finos.ls.queries.SecurityCSVSummarizer;
import org.finos.ls.search.FinanceCSVSummarizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LandscapeApp.class)
@ActiveProfiles("local")
public class SearchTests {
	
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
	public void top150ReposInFinanceTopic() throws Exception {
		String out = csv.generateTopic("finance", new FinanceCSVSummarizer());
		System.out.println(out);
	}
	
	@Test
	public void top150MostPopular() throws Exception {
		String out = csv.generateMostPopular(new BigProjectsSummarizer());
		System.out.println(out);
	}
}
