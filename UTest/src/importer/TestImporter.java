package importer;

import model.Test;

public interface TestImporter {
	
	public Test importFullTest(String path) throws Exception;
	public Test importTestWithNQuestions(String path, int numOfQuestions) throws Exception;
	
}
