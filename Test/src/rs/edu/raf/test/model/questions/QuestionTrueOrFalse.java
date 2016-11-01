package rs.edu.raf.test.model.questions;

public class QuestionTrueOrFalse extends Question {

	private boolean correct;
	private String questionSentence;
	
	public QuestionTrueOrFalse(){
		super();
	}
	
	public QuestionTrueOrFalse(String questionSentence, boolean correct){
		super("Are these statements/questions true of false?");
		this.correct = correct;
		this.questionSentence = questionSentence;
	}
	
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
	
	
}