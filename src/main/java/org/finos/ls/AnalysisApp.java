package org.finos.ls;


import java.util.Arrays;
import java.util.stream.Collectors;

import org.finos.ls.report.Report;
import org.finos.scan.github.client.spring_autoconfiguration.SpringConfiguration;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import com.graphql_java_generator.client.GraphQLConfiguration;

@SpringBootApplication(scanBasePackageClasses = { AnalysisApp.class, GraphQLConfiguration.class, QueryExecutor.class, SpringConfiguration.class })
@EnableConfigurationProperties
public class AnalysisApp implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(AnalysisApp.class, args);
	}
	
	@Value("${landscapeUrl}")
	String landscapeUrl;

	@Value("${reports}")
	String[] reports; 

	@Autowired
	ConfigurableApplicationContext ctx;
	
	/**
	 * This method is started by Spring, once the Spring context has been loaded. This is run, as this class implements
	 * {@link CommandLineRunner}
	 */
	@Override
	public void run(String... args) throws Exception {
		String[] reportArgs = Arrays.stream(args)
				.filter(i -> !i.startsWith("-"))
				.collect(Collectors.toList())
				.toArray(new String[] {});
		if (reportArgs.length > 0) {
			reports = reportArgs;
		}
		Arrays.asList(reports).forEach(r -> {
			try {
				Report report = (Report) ctx.getBean(r);
				report.generate();
			} catch (Exception e) {
				System.err.println("Couldn't generate: "+r);
				e.printStackTrace();
			}
		});
		
		ctx.close();
	}
}