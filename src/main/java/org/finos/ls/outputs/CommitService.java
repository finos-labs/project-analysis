package org.finos.ls.outputs;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.finos.scan.github.client.Commit;
import org.finos.scan.github.client.CommitMessage;
import org.finos.scan.github.client.CommittableBranch;
import org.finos.scan.github.client.CreateCommitOnBranchInput;
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

	public void commitFile(String file, byte[] contents, String branch, String repoName, String owner)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		commitFiles(Collections.singletonMap(file, contents), branch, repoName, owner);
	}

	public void commitFiles(Map<String, byte[]> files, String branch, String repoName, String owner)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository repo = qe.repository("{ref(qualifiedName: \"refs/heads/" + branch + "\") {\n"
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

		List<FileAddition> fileAdditions = files.entrySet().stream()
				.map(entry -> FileAddition.builder()
						.withPath(entry.getKey())
						.withContents(Base64.getEncoder().encodeToString(entry.getValue()))
						.build())
				.collect(Collectors.toList());

		FileChanges fileChanges = FileChanges.builder()
				.withAdditions(fileAdditions)
				.build();

		CreateCommitOnBranchInput input = CreateCommitOnBranchInput.builder()
				.withBranch(CommittableBranch.builder()
						.withRepositoryNameWithOwner(owner + "/" + repoName)
						.withBranchName("refs/heads/" + branch).build())
				.withFileChanges(fileChanges)
				.withMessage(CommitMessage.builder().withHeadline("Landscape Scanning Generated File").build())
				.withExpectedHeadOid(oid)
				.build();

		me.createCommitOnBranch("", input);
	}
}
