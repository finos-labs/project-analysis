package org.finos.ls;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.finos.ls.comments.CommentDownloader;
import org.finos.scan.github.client.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LandscapeApp.class)
@ActiveProfiles("local")
public class RFIssueContent {
	
	@Autowired
	CommentDownloader cd;

	@Test
	public void javaIssues() throws Exception {
		FileReader fr = new FileReader("src/main/resources/issues/Java.csv");
		CSVParser p = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader());
		List<CSVRecord> records = p.getRecords().stream().collect(Collectors.toList());
		int last = records.size();
		for(int i = 1121; i< last; i++) {
			createIssueReport(records.get(i));

		}
		p.close();
	}

	private void createIssueReport(CSVRecord r) {
		try {
			String repo = r.get(8);
			String owner = r.get(7);
			String issue = r.get(3);
			Issue i = cd.getIssue(owner, repo, Integer.parseInt(issue));
			System.out.println();
			FileWriter out = new FileWriter("src/main/resources/content/"+owner+"-"+repo+"-"+issue+".md");
			out.write(cd.formatIssue(i, owner, repo));
			out.close();
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
