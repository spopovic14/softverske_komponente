package rs.edu.raf.test.model.questions;

/**
 * Abstract class that represent Question
 * 
 * @author marko
 *
 */
public abstract class Question implements QuestionInterface {
	
	private String textInstructionForQuestion;
	
	
	public Question() {
		
	}
	/**
	 * 
	 * @param text - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question
	 */
	public Question(String text) {
		this.textInstructionForQuestion = text;
		
	}
	
	
	public String getTextInstructionForQuestion() {
		return textInstructionForQuestion;
	}

	public void setTextInstructionForQuestion(String textInstructionForQuestion) {
		this.textInstructionForQuestion = textInstructionForQuestion;
	}
	

}
