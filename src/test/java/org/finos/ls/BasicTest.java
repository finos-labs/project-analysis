package org.finos.ls;

import java.util.Map;

import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.BasicQueries.FinosStatus;
import org.finos.ls.queries.BasicQueries.OpenSSFStatus;
import org.finos.ls.queries.Summarizer;
import org.finos.ls.queries.Summarizer.SummaryLevel;
import org.finos.scan.github.client.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@SpringBootTest(classes = LandscapeApp.class)
public class BasicTest {
	
	@Autowired
	QueryService qs;
	
	@Autowired
	ReadmeGenerator readme;

	@Test
	public void testSingleRepo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qs.getSingleRepository(BasicQueries.PASSTHROUGH, "spring-bot", "finos");
		Assertions.assertEquals("finos", repo.getOwner().getLogin());
		Assertions.assertEquals("spring-bot", repo.getName());
	}

	@Test
	public void testRepoDownloadViaOrganisation() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {		
		Map<String, Repository> allRepos = qs.getAllFinosRepositories(BasicQueries.PASSTHROUGH);
		
		Repository firstRepo = allRepos.get("OpenMAMA");
		Assertions.assertEquals("OpenMAMA", firstRepo.getName());
		
		// ok, can we pull back the entire list of repositories?
		for (Repository re : allRepos.values()) {
			System.out.println(re.getName());
		}
	}
	
	@Test
	public void testListOfReposWithLicenseInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> licenseDetails = qs.getAllFinosRepositories(BasicQueries.LICENSE_INFO);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithStatusInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, FinosStatus> licenseDetails = qs.getAllFinosRepositories(BasicQueries.FINOS_STATUS);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithOpenSSFStatus() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, OpenSSFStatus> licenseDetails = qs.getAllFinosRepositories(BasicQueries.OPENSSF_STATUS);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testMainBranchReviewers() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Integer> reviewers = qs.getAllFinosRepositories(BasicQueries.BRANCH_RULES);
		outputMap(reviewers);
		Assertions.assertTrue(reviewers.size() > 110);
	}
	
	@Test
	public void testRecentCommitterDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Activity> committers = qs.getAllFinosRepositories(BasicQueries.MAIN_RECENT_COMMITTERS);
		outputMap(committers);
		Assertions.assertTrue(committers.size() > 110);
	}
	
	@Test
	public void testRecentIssueActivity() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Activity> activity = qs.getAllFinosRepositories(BasicQueries.ISSUE_ACTIVITY);
		outputMap(activity);
		Assertions.assertTrue(activity.size() > 110);
	}

	private void outputMap(Map<String, ? extends Object> licenseDetails) {
		licenseDetails.entrySet().stream()
			.forEach(e -> System.out.println(e.getKey()+ " "+e.getValue()));
	}
	
	@Test
	public void testSummaryGenerationMain() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new Summarizer(SummaryLevel.MAIN), "finos", "spring-bot");
		System.out.println(out);
	}
	
	@Test
	public void testSummaryGenerationSubitem() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new Summarizer(SummaryLevel.SUBITEM), "finos", "electron-fdc3");
		System.out.println(out);
	}
	
	@Test
	public void testWholeReadme() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = readme.generate(25);
		System.out.println(out);
	}
	
	
}
