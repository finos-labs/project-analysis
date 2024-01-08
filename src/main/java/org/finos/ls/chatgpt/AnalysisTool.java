package org.finos.ls.chatgpt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class AnalysisTool{

	@Autowired
	ChatGPTService gpt;	
	
	private static final String[] COLUMNS = { "repo", "owner", "issue", "problem", "risks", "resolution", "preventative" };
	
	public void analyze(File in, File out) throws Exception {
		FileReader fr = new FileReader(in);
		CSVParser p = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader());
		CSVPrinter printer = new CSVPrinter(new FileWriter(out), CSVFormat.DEFAULT.withHeader(COLUMNS));
		List<CSVRecord> records = p.getRecords().stream().collect(Collectors.toList());
		int last = records.size();
		for(int i = 0; i< last; i++) {
			try {
				CSVRecord r = records.get(i);
				String repo = r.get(8);
				String owner = r.get(7);
				String issue = r.get(3);
				String response = analyze(repo, owner, issue);
				Map<String, String> map = convertResponseToMap(response);	
				writeRecord(repo, owner, issue, map, printer);
			} catch (FileNotFoundException e) {
				System.err.println("Not found file");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		p.close();
		printer.close();
	}

	private void writeRecord(String repo, String owner, String issue, Map<String, String> map, CSVPrinter p) throws IOException {
		p.printRecord(repo, owner, issue, map.get("problem"), map.get("risks"), map.get("resolution"),map.get("preventative"));
		p.flush();
		System.out.println(repo+" "+owner+" "+issue+" "+map);
	}

	private Map<String, String> convertResponseToMap(String r) {
		String[] lines = r.split("\n");
		Map<String, String> out = new HashMap<>();
		Arrays.stream(lines)
			.forEach(l -> {
				int colon = l.indexOf(":");
				if (colon > -1) {
					String key = l.substring(0, colon);
					String value = l.substring(colon+1);
					out.put(key.toLowerCase(), value);
				}				
			});
		
		return out;
	}
	
	
	private String analyze(String repo, String owner, String issue) throws FileNotFoundException, IOException {
		
		String markdownContent = StreamUtils.copyToString(new FileInputStream(new File("src/main/resources/content/"+owner+"-"+repo+"-"+issue+".md")), Charsets.UTF_8);
		String input = markdownContent;
		String output = gpt.makeRequest(input);
		return output;
	}

}
