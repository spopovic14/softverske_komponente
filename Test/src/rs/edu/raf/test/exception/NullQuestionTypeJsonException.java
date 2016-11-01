package rs.edu.raf.test.exception;

/**
 * Exception indicating that a question defined in a JSON file doesn't have a type value defined.
 * 
 * @author stefan
 *
 */
@SuppressWarnings("serial")
public class NullQuestionTypeJsonException extends Exception {
	
	public NullQuestionTypeJsonException() {
		super("No type defined for question in JSON file");
	}

}
