package org.finos.ls.report;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * This abstract class handles outputting multiple report details.
 * 
 * @author rob@kite9.com
 *
 */
public abstract class AbstractMultiReport implements Report {

	
	public abstract Map<String, String> generateInner() throws Exception;
		
	public void generate() throws Exception {
		Map<String, String> done = generateInner();
		System.out.println("DONE: "+done.size());

		for (Map.Entry<String, String> entry : done.entrySet()) {
			outputResults(entry.getKey(), entry.getValue());			
		}
		
	}
	
	public void outputResults(String filename, String report) throws Exception {
		File out = new File(filename);
		FileWriter fw = new FileWriter(out);
		fw.write(report);
		fw.close();
	}

	
}
