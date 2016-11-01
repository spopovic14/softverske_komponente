package rs.edu.raf.test.exception;

/**
 * Exception indicating that a question attempting to be imported doesn't have a valid text defined.
 * 
 * @author stefan
 *
 */
@SuppressWarnings("serial")
public class NoQuestionTextException extends Exception {
	
	public NoQuestionTextException() {
		super("Question text not defined");
	}
	
}
