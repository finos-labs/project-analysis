package org.finos.ls.report;

/**
 * This abstract class handles outputting the report details.
 * 
 * @author rob@kite9.com
 *
 */
public abstract class AbstractReport implements Report {

	
	public abstract String generateInner() throws Exception;
	
	public abstract void outputResults(String report)  throws Exception;
	
	public void generate() throws Exception {
		String done = generateInner();
		System.out.println("DONE: "+done);
		outputResults(done);
	}
	
	
}
