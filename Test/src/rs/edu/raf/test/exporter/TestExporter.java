package rs.edu.raf.test.exporter;

import rs.edu.raf.test.model.Test;


/**
 * Used for exporting Test objects in various formats.
 * 
 * @author stefan
 *
 */
public interface TestExporter {
	
	
	//Question types
	public static final String QUESTION_TYPE_BASIC = "basic";
	public static final String QUESTION_TYPE_TRUE_FALSE = "trueFalse";
	public static final String QUESTION_TYPE_SURVEY = "survey";
	public static final String QUESTION_TYPE_MATCHING = "matching";
	public static final String QUESTION_TYPE_FILL_THE_BLANK = "fillTheBlank";
	
	
	/**
	 * Exports all the questions from the given test to a file specified by the given path.
	 * 
	 * @param test - Test object to export
	 * @param path - path to save the test to
	 * @return true if exporting was successful
	 * @throws Exception if exporting failed
	 */
	public boolean exportTest(Test test, String path) throws Exception;

}
