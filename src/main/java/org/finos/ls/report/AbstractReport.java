package org.finos.ls.report;

import java.io.File;
import java.io.FileWriter;

/**
 * This abstract class handles outputting the report details.
 * 
 * @author rob@kite9.com
 *
 */
public abstract class AbstractReport implements Report {

	
	public abstract String generateInner() throws Exception;
		
	public void generate() throws Exception {
		String done = generateInner();
		System.out.println("DONE: "+done);
		outputResults(done);
	}
	
	public abstract String getFilename();
	
	public void outputResults(String report) throws Exception {
		if (this.getFilename() != null) {
			File out = new File(this.getFilename());
			out.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(out);
			fw.write(report);
			fw.close();
		}
	}
	
}
