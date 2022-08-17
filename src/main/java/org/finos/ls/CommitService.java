package org.finos.ls;

import java.util.Arrays;
import java.util.Base64;

import org.finos.scan.github.client.Commit;
import org.finos.scan.github.client.CommitMessage;
import org.finos.scan.github.client.CommittableBranch;
import org.finos.scan.github.client.CreateCommitOnBranchInput;
import org.finos.scan.github.client.CreateCommitOnBranchPayload;
import org.finos.scan.github.client.FileAddition;
import org.finos.scan.github.client.FileChanges;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.MutationExecutor;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class CommitService {

	@Autowired
	QueryExecutor qe;

	@Autowired
	MutationExecutor me;
	
	
	public void commitFile(String file, byte[] contents, String branch, String repoName, String owner) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qe.repository("{ref(qualifiedName: \"refs/heads/main\") {\n"
				+ "      target {\n"
				+ "        id\n"
				+ "        ... on Commit {\n"
				+ "          history(first: 1) {\n"
				+ "            nodes {\n"
				+ "              oid\n"
				+ "            }\n"
				+ "          }\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }", true, repoName, owner);	
		
		String oid = ((Commit) repo.getRef().getTarget()).getHistory().getNodes().get(0).getOid();

		String base64Contents = Base64.getEncoder().encodeToString(contents);

		FileAddition fileAddition = FileAddition.builder()
			.withPath(file)
			.withContents(base64Contents)
			.build();
		
		FileChanges fileChanges = FileChanges.builder()
			.withAdditions(Arrays.asList(fileAddition))
			.build();
		
		
		CreateCommitOnBranchInput input = CreateCommitOnBranchInput.builder()
			.withBranch(CommittableBranch.builder()
					.withRepositoryNameWithOwner(owner+"/"+repoName)
					.withBranchName("refs/heads/"+branch).build())
			.withFileChanges(fileChanges)
			.withMessage(CommitMessage.builder().withHeadline("Landscape Scanning Generated File").build())
			.withExpectedHeadOid(oid)
			.build();
		

		CreateCommitOnBranchPayload result = me.createCommitOnBranch("", input);
		
		// ok, now create a PR
		
		
			
	}
}
