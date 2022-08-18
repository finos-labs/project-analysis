package org.finos.ls;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.finos.ls.queries.CSVSummarizer;
import org.finos.scan.github.client.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSVGenerator {

	@Autowired
	QueryService qs;
	
	Map<String, Repository> cache = new HashMap<>();
	
	public String generate(String org) throws Exception {
		Map<String, List<String>> activeProjects = qs.getAllRepositories(new CSVSummarizer(), org);
		
		StringWriter sw = new StringWriter();
		CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withHeader(CSVSummarizer.FIELDS));
		
		for (List<String> record : activeProjects.values()) {
			printer.printRecord(record.stream().toArray());
		}
		
		printer.close();
		
		return sw.toString();
	}


}
