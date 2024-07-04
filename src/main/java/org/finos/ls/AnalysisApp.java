package org.finos.ls;


import java.util.Arrays;
import java.util.List;

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
//@ConfigurationProperties(prefix = "scanning")
public class AnalysisApp implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(AnalysisApp.class, args);
	}
	
	@Value("${landscapeUrl}")
	String landscapeUrl;

	@Value("${reports}")
	String[] reports; 
//	
//
//	@Autowired
//	ReadmeGenerator readme;
//	
//	@Autowired
//	CSVGenerator csv;
//	
//	@Autowired
//	CommitService commit;
//	
//	@Autowired
//	PullRequestService pr;
//	
//	@Autowired
//	LandscapeReader lr;
//	
//	@Value("${spring.profiles.active:}")
//	String activeProfiles;
//		
//	@Value("${scanning.readme:README.md}")
//	String readmeFile;
//	
//	@Value("${scanning.csv:scan.csv}")
//	String csvFile;
//	
//	@Value("${scanning.finance.csv:finance-topic.csv}")
//	String fcsvFile;
//	
//	@Value("${scanning.write-to.repo}")
//	String repo;
//	
//	@Value("${scanning.write-to.owner}")
//	String owner;
//
//	@Value("${scanning.write-to.base}")
//	String base;
//
//	@Value("${scanning.write-to.head}")
//	String head;
//	
//	@Value("${scanning.csv.priority:}")
//	String[] priority;
//	
//	@Value("${scanning.csv.ignore:}")
//	String[] ignore;
////	
//	List<String> orgs;
//
//	public List<String> getOrgs() {
//		return orgs;
//	}
//
//	public void setOrgs(List<String> orgs) {
//		this.orgs = orgs;
//	}

	@Autowired
	ConfigurableApplicationContext ctx;
	
	/**
	 * This method is started by Spring, once the Spring context has been loaded. This is run, as this class implements
	 * {@link CommandLineRunner}
	 */
	@Override
	public void run(String... args) throws Exception {
		Arrays.asList(reports).forEach(r -> {
			try {
				Report report = (Report) ctx.getBean(r);
				report.generate();
			} catch (Exception e) {
				System.err.println("Couldn't generate: "+r);
				e.printStackTrace();
			}
		});
		
		
//		if (activeProfiles.contains("summarize")) {
////			// first, write the readme
////			String readmeContent = readme.generate(25, orgs);
////			commit.commitFile(readmeFile, readmeContent.getBytes(), head, repo, owner);
//			
//			// then write the csv
//			List<ProjectInfo> projects = lr.readFromLandscape(landscapeUrl);
//			ProjectScanCSVSummarizer summ = new ProjectScanCSVSummarizer(Arrays.asList(ignore), Arrays.asList(priority), projects);
//			StringBuilder sb = new StringBuilder();
//			orgs.forEach(o -> {
//				try {
//					sb.append( csv.generateOrg(o, summ));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//			commit.commitFile(csvFile, sb.toString().getBytes(), head, repo, owner);
//			
////			FinanceCSVSummarizer fcsv = new FinanceCSVSummarizer();
////			String fcsvContent = csv.generateTopic("finance", fcsv);
////			commit.commitFile(fcsvFile, fcsvContent.getBytes(), head, repo, owner);
////			
//			// create a pr
//			pr.createOrUpdatePullRequest(repo, owner, base, head, Collections.singletonList("@robmoffat"), "Updated Generated Files");
//			ctx.close();
//		}
	}
}