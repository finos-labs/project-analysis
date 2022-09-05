package org.finos.ls;

import java.util.Arrays;
import java.util.Map;

import org.finos.ls.queries.Activity;
import org.finos.ls.queries.BasicQueries;
import org.finos.ls.queries.BasicQueries.FinosStatus;
import org.finos.ls.queries.BasicQueries.OpenSSFStatus;
import org.finos.ls.queries.MarkdownSummarizer;
import org.finos.ls.queries.MarkdownSummarizer.SummaryLevel;
import org.finos.scan.github.client.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@SpringBootTest(classes = LandscapeApp.class)
public class BasicTest {
	
	private static final String ORG = "finos";

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
	public void testSingleRepo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qs.getSingleRepository(BasicQueries.PASSTHROUGH, ORG, "spring-bot");
		Assertions.assertEquals(ORG, repo.getOwner().getLogin());
		Assertions.assertEquals("spring-bot", repo.getName());
	}

	@Test
	public void testRepoDownloadViaOrganisation() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {		
		Map<String, Repository> allRepos = qs.getAllRepositories(BasicQueries.PASSTHROUGH, ORG);
		
		Repository firstRepo = allRepos.get("OpenMAMA");
		Assertions.assertEquals("OpenMAMA", firstRepo.getName());
		
		// ok, can we pull back the entire list of repositories?
		for (Repository re : allRepos.values()) {
			System.out.println(re.getName());
		}
	}
	
	@Test
	public void testListOfReposWithLicenseInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> licenseDetails = qs.getAllRepositories(BasicQueries.LICENSE_INFO, ORG);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithStatusInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, FinosStatus> licenseDetails = qs.getAllRepositories(BasicQueries.FINOS_STATUS, ORG);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithOpenSSFStatus() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, OpenSSFStatus> licenseDetails = qs.getAllRepositories(BasicQueries.OPENSSF_STATUS, ORG);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testMainBranchReviewers() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Integer> reviewers = qs.getAllRepositories(BasicQueries.BRANCH_RULES, ORG);
		outputMap(reviewers);
		Assertions.assertTrue(reviewers.size() > 110);
	}
	
	@Test
	public void testRecentCommitterDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Activity> committers = qs.getAllRepositories(BasicQueries.MAIN_RECENT_COMMITTERS, ORG);
		outputMap(committers);
		Assertions.assertTrue(committers.size() > 110);
	}
	
	@Test
	public void testAdminDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String admins = qs.getSingleRepository(BasicQueries.WRONG_ADMINS, "robmoffat", "landscape-scanning");
		System.out.println(admins);
		Assertions.assertEquals("@robmoffat", admins);
	}
	
	@Test
	public void testCVEScanningDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Boolean> cve = qs.getAllRepositories(BasicQueries.CVE_SCANNING_ACTION, ORG);
		outputMap(cve);
		Assertions.assertTrue(cve.size() > 110);
		Assertions.assertTrue(cve.get("electron-fdc3"));
	}
	
	@Test
	public void testSemgrepDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Boolean> semgrep = qs.getAllRepositories(BasicQueries.SEMGREP_ACTION, ORG);
		outputMap(semgrep);
		Assertions.assertTrue(semgrep.size() > 110);
		Assertions.assertTrue(semgrep.get("electron-fdc3"));
	}
	
	@Test
	public void testRecentIssueActivity() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, Activity> activity = qs.getAllRepositories(BasicQueries.ISSUE_ACTIVITY, ORG);
		outputMap(activity);
		Assertions.assertTrue(activity.size() > 110);
	}

	private void outputMap(Map<String, ? extends Object> licenseDetails) {
		licenseDetails.entrySet().stream()
			.forEach(e -> System.out.println(e.getKey()+ " "+e.getValue()));
	}
	
	@Test
	public void testSummaryGenerationMain() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new MarkdownSummarizer(SummaryLevel.MAIN), ORG, "spring-bot");
		System.out.println(out);
	}
	
	@Test
	public void testSummaryGenerationSubitem() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new MarkdownSummarizer(SummaryLevel.SUBITEM), ORG, "electron-fdc3");
		System.out.println(out);
	}
	
	@Test
	public void testBadReadme() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new MarkdownSummarizer(SummaryLevel.SUBITEM), ORG, "symphonyelectron");
		System.out.println(out);
	}
	
	@Test
	public void testWholeReadme() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = readme.generate(25, ORG);
		System.out.println(out);
	}
	
	@Test
	public void testDifferentlyNamedReadme() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = qs.getSingleRepository(new MarkdownSummarizer(SummaryLevel.SUBITEM), ORG, "kdb-studio");
		System.out.println(out);
	}
	
	@Test
	public void testCSV() throws Exception {
		String out = csv.generate(ORG);
		System.out.println(out);
	}
	
	@Test
	public void testCommit() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		cs.commitFile("somefile.md", "This is some contents".getBytes(), "generated-branch", "landscape-scanning", "robmoffat");
	}
	
	@Test
	public void testPR() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		pr.createOrUpdatePullRequest("landscape-scanning", "robmoffat", "main", "generated-branch",Arrays.asList("@robmoffat"), "Get this merged");
		pr.createOrUpdatePullRequest("landscape-scanning", "robmoffat", "main", "generated-branch",Arrays.asList("@robmoffat"), "new comment");
		pr.closePullRequest("landscape-scanning", "robmoffat", "main", "generated-branch");
	}
	
}
