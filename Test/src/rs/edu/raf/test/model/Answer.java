package rs.edu.raf.test.model;


/**
 * A simple class that describes a single answer for a question.
 * 
 * @author stefan
 *
 */
public class Answer {
	
	private String text;
	
	
	/**
	 * Generates an empty Answer object. The text field will not be initialized.
	 */
	public Answer() {
		
	}
	
	/**
	 * Constructor that initializes this Answer's text to the given value.
	 * 
	 * @param text - the text of this answer
	 */
	public Answer(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
