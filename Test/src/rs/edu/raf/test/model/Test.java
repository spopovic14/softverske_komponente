package rs.edu.raf.test.model;

import java.util.LinkedList;
import java.util.List;

import rs.edu.raf.test.model.questions.Question;

/**
 * A simple class that contains a list of Question objects.
 * 
 * @author stefan
 *
 */
public class Test {
	
	
	private List<Question> questions;
	
	/**
	 * Generates a Test with an empty list of questions.
	 */
	public Test() {
		questions = new LinkedList<Question>();
	}
	
	/**
	 * Generates a Test with the given list of questions.
	 * 
	 * @param questions - list of Question objects to be added to the test
	 */
	public Test(List<Question> questions) {
		this.questions = questions;
	}
	
	public Question getQuestion(int index) {
		return questions.get(index);
	}
	
	/**
	 * Adds a question to this Test's list.
	 * 
	 * @param question - Question to add to the Test's list of questions
	 */
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
