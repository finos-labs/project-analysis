package org.finos.ls;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.finos.ls.queries.CSVSummarizer;
import org.finos.scan.github.client.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CSVGenerator {

	@Autowired
	QueryService qs;
	
	@Value("${scanning.csv.priority:}")
	String[] priority;
	
	@Value("${scanning.csv.ignore:}")
	String[] ignore;
		
	Map<String, Repository> cache = new HashMap<>();
	
	public String generate(String org) throws Exception {
		Map<String, List<Object>> activeProjects = qs.getAllRepositories(new CSVSummarizer(Arrays.asList(ignore), Arrays.asList(priority)), org);
		
		StringWriter sw = new StringWriter();
		CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withHeader(CSVSummarizer.FIELDS));
		
		List<List<Object>> sorted = activeProjects.values().stream()
			.sorted((a, b) -> -((Long) a.get(0)).compareTo((Long) b.get(0)))
			.collect(Collectors.toList());
		
		for (List<Object> record : sorted) {
			printer.printRecord(record.stream().toArray());
		}
		
		printer.close();
		
		return sw.toString();
	}


}
