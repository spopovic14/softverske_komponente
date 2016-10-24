package model;

import java.util.LinkedList;
import java.util.List;

public class Test {
	
	private List<Question> questions;
	
	public Test() {
		questions = new LinkedList<Question>();
	}
	
	public Test(List<Question> questions) {
		this.questions = questions;
	}
	
	public Question getQuestion(int index) {
		return questions.get(index);
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
