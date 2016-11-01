package rs.edu.raf.test.exception;

/**
 * Exception indicating that a question defined in XML has an invalid node.
 * 
 * @author stefan
 *
 */
@SuppressWarnings("serial")
public class InvalidXMLQuestionNode extends Exception {
	
	public InvalidXMLQuestionNode() {
		super("Invalid question node tag");
	}
	
}
