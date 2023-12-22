package org.finos.ls;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.finos.ls.queries.CSVSummarizer;
import org.finos.ls.queries.HasColumns;
import org.finos.ls.queries.TableSummarizer;
import org.finos.scan.github.client.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSVGenerator {

	@Autowired
	QueryService qs;
		
	Map<String, Repository> cache = new HashMap<>();
	
	public String generateOrg(String org, CSVSummarizer summ) throws Exception {
		Map<String, List<Object>> activeProjects = qs.getAllRepositoriesInOrg(summ, org);
		
		return convertToCSV(summ, activeProjects);
	}

	public String generateTopic(String topic, CSVSummarizer summ) throws Exception {
		Map<String, List<Object>> activeProjects = qs.getAllRepositoriesInTopic(summ, topic);
		
		return convertToCSV(summ, activeProjects);
	}

	public String generateMostPopular(CSVSummarizer summ) throws Exception {
		List<List<Object>> mostPopular = qs.getMostPopularRepositories(summ);
		StringWriter out = new StringWriter();
		convertToCSV(summ, mostPopular, out);
		return out.toString();
	}
	
	public void generateFromFile(TableSummarizer qt, String file, Writer w) throws Exception {
		List<List<Object>> data = qs.getRepositiesFromFile(qt, file);
		convertToCSV(qt, data, w);
	}
	
	private void convertToCSV(HasColumns summ, List<List<Object>> activeProjects, Writer sw) throws IOException {
		CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withHeader(summ.getColumnNames()));
		
		for (List<Object> record : activeProjects) {
			printer.printRecord(record.stream().toArray());
		}
		
		printer.close();
	}

	
	private String convertToCSV(HasColumns summ, Map<String, List<Object>> activeProjects) throws IOException {
		StringWriter sw = new StringWriter();
		CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withHeader(summ.getColumnNames()));
		
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
