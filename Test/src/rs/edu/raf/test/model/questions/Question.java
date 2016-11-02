package rs.edu.raf.test.model.questions;


public abstract class Question implements QuestionInterface {
	
	private String textInstructionForQuestion;
	
	
	public Question() {
		
	}
	
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
