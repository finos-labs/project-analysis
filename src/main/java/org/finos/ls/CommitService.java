package org.finos.ls;

import java.util.Arrays;

import org.finos.scan.github.client.CommittableBranch;
import org.finos.scan.github.client.CreateCommitOnBranchInput;
import org.finos.scan.github.client.CreateCommitOnBranchPayload;
import org.finos.scan.github.client.FileAddition;
import org.finos.scan.github.client.FileChanges;
import org.finos.scan.github.client.util.MutationExecutor;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

public class CommitService {

	@Autowired
	QueryExecutor qe;

	@Autowired
	MutationExecutor me;
	
	
	public String commitAndPR(String file, String contents, String branch, String repoNameWithOwner) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		FileAddition fileAddition = FileAddition.builder()
			.withPath(file)
			.withContents(contents)
			.build();
		
		FileChanges fileChanges = FileChanges.builder()
			.withAdditions(Arrays.asList(fileAddition))
			.build();
		
		CreateCommitOnBranchInput input = CreateCommitOnBranchInput.builder()
			.withBranch(CommittableBranch.builder()
					.withRepositoryNameWithOwner(repoNameWithOwner)
					.withBranchName("refs/heads/"+branch).build())
			.withFileChanges(fileChanges)
			.build();
		

		CreateCommitOnBranchPayload result = me.createCommitOnBranch("", input);
		
		// ok, now create a PR
		
		
			
	}
}
