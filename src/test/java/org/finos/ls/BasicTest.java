package org.finos.ls;

import java.util.Map;

import org.finos.ls.QueryService.QueryType;
import org.finos.scan.github.client.Organization;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryConnection;
import org.finos.scan.github.client.RepositoryEdge;
import org.finos.scan.github.client.util.QueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@SpringBootTest(classes = LandscapeApp.class)
public class BasicTest {

	@Autowired
	QueryExecutor qe;
	
	@Autowired
	QueryService qs;
	
	@Autowired
	Summarizer sum;

	@Test
	public void testSingleRepo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qe.repository("{id name owner}", true, "spring-bot",
				"finos");
		Assertions.assertEquals("finos", repo.getOwner().getLogin());
		Assertions.assertEquals("spring-bot", repo.getName());
	}

	@Test
	public void testRepoDownloadViaOrganisation() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {		
		Organization o = qe.organization("{\n"
				+ "    id\n"
				+ "    repositories(first:10) {\n"
				+ "      edges {\n"
				+ "        node {\n"
				+ "          name\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }}"
				
				, "finos");
		
		RepositoryConnection conn = o.getRepositories();
		Repository firstRepo = conn.getEdges().get(0).getNode();
		Assertions.assertEquals("OpenMAMA", firstRepo.getName());
		
		// ok, can we pull back the entire list of repositories?
		for (RepositoryEdge re : conn.getEdges()) {
			System.out.println(re.getNode().getName());
		}
	}
	
	@Test
	public void testListOfReposWithLicenseInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> licenseDetails = qs.getAllFinosRepositories(QueryType.LICENSE_INFO);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithStatusInfo() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> licenseDetails = qs.getAllFinosRepositories(QueryType.FINOS_STATUS);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testListOfReposWithOpenSSFStatus() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> licenseDetails = qs.getAllFinosRepositories(QueryType.OPENSSF_STATUS);
		outputMap(licenseDetails);
		Assertions.assertTrue(licenseDetails.size() > 110);
	}
	
	@Test
	public void testMainBranchReviewers() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> reviewers = qs.getAllFinosRepositories(QueryType.BRANCH_RULES);
		outputMap(reviewers);
		Assertions.assertTrue(reviewers.size() > 110);
	}
	
	@Test
	public void testRecentCommitterDetails() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> committers = qs.getAllFinosRepositories(QueryType.MAIN_RECENT_COMMITTERS);
		outputMap(committers);
		Assertions.assertTrue(committers.size() > 110);
	}
	
	@Test
	public void testRecentIssueActivity() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Map<String, String> activity = qs.getAllFinosRepositories(QueryType.ISSUE_ACTIVITY);
		outputMap(activity);
		Assertions.assertTrue(activity.size() > 110);
	}

	private void outputMap(Map<String, String> licenseDetails) {
		licenseDetails.entrySet().stream()
			.forEach(e -> System.out.println(e.getKey()+ " "+e.getValue()));
	}
	
	@Test
	public void testSummaryGeneration() throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		String out = sum.getSummary("finos", "spring-bot");
		System.out.println(out);
	}
	
}
