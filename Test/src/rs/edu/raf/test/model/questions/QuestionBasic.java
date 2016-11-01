package rs.edu.raf.test.model.questions;

import java.util.LinkedList;
import java.util.List;

import rs.edu.raf.test.model.Answer;

public class QuestionBasic extends Question {

	private String questionSentence;
	private List<Answer> wrongAnswers;
	private List<Answer> rightAnswers;
	
	
	public QuestionBasic(){
		super();
	}
	
	public QuestionBasic(String questionSentence){
		super("Circle the right answer(s): ");
		this.questionSentence = questionSentence;
	}
	
	public QuestionBasic(String text, String questionSentence){
		super(text);
		wrongAnswers = new LinkedList<Answer>();
		rightAnswers = new LinkedList<Answer>();
		this.questionSentence = questionSentence;
	}
	
	
	public QuestionBasic(String text, List<Answer> wrongAnswers, List<Answer> rightAnswers, String questionSentence){
		super(text);
		this.wrongAnswers = new LinkedList<>(wrongAnswers);
		this.rightAnswers = new LinkedList<>(rightAnswers);
		this.questionSentence = questionSentence;
		
	}
	
	public QuestionBasic(String questionSentence, List<Answer> wrongAnswers, List<Answer> rightAnswers){
		super("Circle the right answer(s): ");
		this.wrongAnswers = new LinkedList<>(wrongAnswers);
		this.rightAnswers = new LinkedList<>(rightAnswers);
		this.questionSentence = questionSentence;
		
	}
	
	public void addRightAnswer(Answer answer) {
		rightAnswers.add(answer);
	}
	
	public void addWrongAnswer(Answer answer) {
		wrongAnswers.add(answer);
	}
	
	public List<Answer> getWrongAnswers() {
		return wrongAnswers;
	}
	public void setWrongAnswers(List<Answer> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	public List<Answer> getRightAnswers() {
		return rightAnswers;
	}
	public void setRightAnswers(List<Answer> rightAnswers) {
		this.rightAnswers = rightAnswers;
	}
	
	public String getQuestionSentence() {
		return questionSentence;
	}

	public void setQuestionSentence(String questionSentence) {
		this.questionSentence = questionSentence;
	}

	public String toStringForDocument() {
		
		StringBuilder stringBuild = new StringBuilder();
		
		
		
		return stringBuild.toString();
		
	}
	
}
