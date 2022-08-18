package org.finos.ls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.finos.ls.queries.QueryType;
import org.finos.scan.github.client.Organization;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.RepositoryConnection;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class QueryService {
	
	@Autowired
	QueryExecutor qe;
	
	public <X> X getSingleRepository(QueryType<X> qt, String owner, String name) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository r = getRawRepository(qt, owner, name);
		return qt.convert(r);
	}
	
	public <X> Repository getRawRepository(QueryType<X> qt, String owner, String name) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository r = qe.repository(qt.getFields(), true, name, owner);
		return r;
	}
	
	
	public <X> Map<String, X> getAllRepositories(QueryType<X> qt, String org) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		List<Repository> out = new ArrayList<Repository>();
		int total = 1000;
		String cursor = null;
		
		while (total > out.size()) {
			String query = "{\n"
					+ "    id\n"
					+ "    repositories(first: "+qt.getPageSize()+", after: &cursor) { \n"
					+ "      totalCount\n"
					+ "      totalDiskUsage\n"
					+ "      pageInfo {\n"
					+ "        endCursor\n"
					+ "        startCursor\n"
					+ "      }\n"
					+ "      edges {\n"
					+ "        node {\n"
					+ "          name \n"
					+ "          "+qt.getFields()
					+ "        }\n"
					+ "      }\n"
					+ "    }\n"
					+ "  }\n"
					+ "}";
			
			Organization o = qe.organization(query, org, "cursor", cursor);
			
			RepositoryConnection conn = o.getRepositories();
			out.addAll(
				conn.getEdges().stream()
					.map(e -> e.getNode())
					.collect(Collectors.toList()));
			total = conn.getTotalCount();
			cursor = conn.getPageInfo().getEndCursor();
		}
		
		return out.stream().collect(Collectors.toMap(r -> r.getName(), r-> qt.convert(r)));
				
	}
}
