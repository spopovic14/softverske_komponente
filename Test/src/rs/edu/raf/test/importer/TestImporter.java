package rs.edu.raf.test.importer;

import rs.edu.raf.test.model.Test;


/**
 * Used for importing Test objects in various formats.
 * 
 * @author stefan
 *
 */
public interface TestImporter {
	
	/**
	 * Creates a new test and fills it with all the questions from a given file. Does not limit the number of questions.
	 * 
	 * @param path - full path to a file with questions
	 * @return a test with every question from the given file
	 * @throws Exception
	 */
	public Test importFullTest(String path) throws Exception;
	
	
	/**
	 * Creates a new test and fills it with N random questions from a given file.
	 * 
	 * @param path - full path to a file with questions
	 * @param numOfQuestions - maximum number of questions to load
	 * @return a test with numOfQuestions random questions from the given file
	 * @throws Exception
	 */
	public Test importTestWithNQuestions(String path, int numOfQuestions) throws Exception;
	
}
