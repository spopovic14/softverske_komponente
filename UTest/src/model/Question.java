package model;

import java.util.LinkedList;
import java.util.List;

public class Question {
	
	private String text;
	private List<Answer> wrongAnswers;
	private List<Answer> rightAnswers;
	
	
	public Question() {
		
	}
	
	public Question(String text) {
		this.text = text;
		wrongAnswers = new LinkedList<Answer>();
		rightAnswers = new LinkedList<Answer>();
	}
	
	public Question(String text, List<Answer> wrongAnswers, List<Answer> rightAnswers) {
		this.text = text;
		this.wrongAnswers = wrongAnswers;
		this.rightAnswers = rightAnswers;
	}
	
	public void addRightAnswer(Answer answer) {
		rightAnswers.add(answer);
	}
	
	public void addWrongAnswer(Answer answer) {
		wrongAnswers.add(answer);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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

}
