package org.finos.ls;


import org.finos.scan.github.client.User;
import org.finos.scan.github.client.spring_autoconfiguration.SpringConfiguration;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.graphql_java_generator.client.GraphQLConfiguration;

@SpringBootApplication(scanBasePackageClasses = { LandscapeApp.class, GraphQLConfiguration.class, QueryExecutor.class, SpringConfiguration.class })
public class LandscapeApp implements CommandLineRunner {

	/**
	 * The executor, that allows to execute GraphQL queries. The class name is the one defined in the GraphQL schema.
	 */
	@Autowired
	QueryExecutor queryExecutor;
	
	public static void main(String[] args) {
		SpringApplication.run(LandscapeApp.class, args);
	}

	/**
	 * This method is started by Spring, once the Spring context has been loaded. This is run, as this class implements
	 * {@link CommandLineRunner}
	 */
	@Override
	public void run(String... args) throws Exception {
		User u = queryExecutor.user("{id login}", "robmoffat");
		
		System.out.println(u);
	}
}