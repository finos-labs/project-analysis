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
		System.out.println("DONE: " + done.size());
		outputResults(done);
	}

	public void outputResults(Map<String, String> done) throws Exception {
		for (Map.Entry<String, String> entry : done.entrySet()) {
			File out = new File(entry.getKey());
			// Create parent directories if they don't exist
			if (out.getParentFile() != null) {
				out.getParentFile().mkdirs();
			}
			FileWriter fw = new FileWriter(out);
			fw.write(entry.getValue());
			fw.close();
		}
	}

}
