package org.finos.ls;

import java.io.File;

import org.finos.ls.chatgpt.AnalysisTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LandscapeApp.class)
@ActiveProfiles("local")
public class RFIssueAnalysis {
	
	
	@Autowired
	AnalysisTool at;
	
	@Test
	public void javaIssues() throws Exception {
		File in = new File("src/main/resources/issues/Java.csv");
		File out = new File("src/main/resources/issues/Java-Analysis.csv");
		at.analyze(in, out);
	}
}
