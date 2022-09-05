package org.finos.ls;

import java.util.List;

import org.finos.scan.github.client.AddCommentInput;
import org.finos.scan.github.client.ClosePullRequestInput;
import org.finos.scan.github.client.CreatePullRequestInput;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.MutationExecutor;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class PullRequestService {

	@Autowired
	QueryExecutor qe;

	@Autowired
	MutationExecutor me;
	
	
	public void createOrUpdatePullRequest(String repoName, String owner, String baseRef, String headRef, List<String> mentions, String comment) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		
		Repository repo = getRepository(repoName, owner, baseRef, headRef);
		
		boolean exists = repo.getPullRequests().getEdges().size() > 0;
		
		String names = mentions.stream().reduce((a, b) -> a + " " + b).orElse("");
		
		if (!exists) {
			CreatePullRequestInput pr = CreatePullRequestInput.builder()
				.withBaseRefName(baseRef)
				.withHeadRefName(headRef)
				.withTitle(comment)
				.withRepositoryId(repo.getId())
				.withBody(names)
				.build();
			
			me.createPullRequest("", pr);
		} else {
			String id = repo.getPullRequests().getEdges().get(0).getNode().getId();
			
			// update with a new comment
			AddCommentInput commentInput = AddCommentInput.builder()
				.withBody(comment+"\n"+names)
				.withSubjectId(id)
				.build();
			
			me.addComment("", commentInput);
		}
		
	}

	private Repository getRepository(String repoName, String owner, String baseRef, String headRef)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qe.repository("{\n"
				+ "    id "
				+ "    pullRequests(baseRefName: \""+baseRef+"\", headRefName: \""+headRef+"\", first: 10, states: OPEN) {\n"
				+ "      edges {\n"
				+ "        node {\n"
				+ "          id\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }", true, repoName, owner);
		return repo;
	}
	
	public void closePullRequest(String repoName, String owner, String baseRef, String headRef) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {

		Repository repo = getRepository(repoName, owner, baseRef, headRef);
		boolean exists = repo.getPullRequests().getEdges().size() > 0;
		
		if (exists) {
			String id = repo.getPullRequests().getEdges().get(0).getNode().getId();

			ClosePullRequestInput closePR = ClosePullRequestInput.builder()
				.withPullRequestId(id)
				.build();
			
			me.closePullRequest("", closePR);
		}
	}
	
}
