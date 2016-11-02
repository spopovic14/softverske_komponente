package rs.edu.raf.test.model.questions;

/**
 * True/false questions are only composed of a statement.
 * Students respond to the questions by indicating whether the statement is true or false.
 * 
 * @author marko
 *
 */

public class QuestionTrueOrFalse extends Question {

	private boolean correct;
	private String questionSentence;
	
	public QuestionTrueOrFalse(){
		super();
	}
	/**
	 * 
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 * @param correct - tells us if the question is true or false;
	 */
	public QuestionTrueOrFalse(String questionSentence, boolean correct){
		super("Are these statements/questions true of false? Circle right answer. ");
		this.correct = correct;
		this.questionSentence = questionSentence;
	}
	
	/**
	 * 
	 * @param text - represent instructionForQuestion - example: "Circle the right answer(s):". It helps students to understand question
	 * @param correct - tells us if the question is true or false;
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 */
	public QuestionTrueOrFalse(String text, boolean correct, String questionSentence){
		super(text);
		this.correct = correct;
		this.questionSentence = questionSentence;
	}
	
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	public String getQuestionSentence() {
		return questionSentence;
	}

	public void setQuestionSentence(String questionSentence) {
		this.questionSentence = questionSentence;
	}

	@Override
	public String toStringForDocument() {
		
		StringBuilder stringBuild = new StringBuilder();
		
		stringBuild.append(questionSentence + "\r");
		stringBuild.append("\r\t" + "TRUE" + "\t FALSE ");
		
		return stringBuild.toString();
	}
	
	
}
