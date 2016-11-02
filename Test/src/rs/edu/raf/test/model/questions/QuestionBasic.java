package rs.edu.raf.test.model.questions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rs.edu.raf.test.model.Answer;
/**
 * Basic questions are composed of one question with multiple possible answers,
 * including the correct answer and several incorrect answers. 
 * Typically, students select the correct answer by circling the associated number or letter, 
 * or filling in the associated circle on the machine-readable response sheet.
 * 
 * 
 * @author marko
 *
 */
public class QuestionBasic extends Question {

	private String questionSentence;
	private List<Answer> wrongAnswers;
	private List<Answer> rightAnswers;
	
	
	public QuestionBasic(){
		super();
	}
	
	/**
	 * Constructor with questionSentence and default value of IntroductionQuestion: "Circle the right answer(s): "
	 * 
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 */
	public QuestionBasic(String questionSentence){
		super("Circle the right answer(s): ");
		this.questionSentence = questionSentence;
	}
	
	/**
	 * 
	 * @param text - represent instructionForQuestion - example: "Circle the right answer(s):". It helps students to understand question
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 */
	public QuestionBasic(String text, String questionSentence){
		super(text);
		wrongAnswers = new LinkedList<Answer>();
		rightAnswers = new LinkedList<Answer>();
		this.questionSentence = questionSentence;
	}
	/**
	 * 
	 * @param text - represent instructionForQuestion - example: "Circle the right answer(s):". It helps students to understand question
	 * @param wrongAnswers - list of all wrong answers
	 * @param rightAnswers - list of all right answers
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 */
	public QuestionBasic(String text, List<Answer> wrongAnswers, List<Answer> rightAnswers, String questionSentence){
		super(text);
		this.wrongAnswers = new LinkedList<>(wrongAnswers);
		this.rightAnswers = new LinkedList<>(rightAnswers);
		this.questionSentence = questionSentence;
		
	}
	/**
	 * 
	 * @param questionSentence - String that represent the actuall question , like : "Sharks are : "
	 * @param wrongAnswers - list of all wrong answers
	 * @param rightAnswers - list of all right answers
	 */
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

	@Override
	public String toStringForDocument() {
		
		StringBuilder stringBuild = new StringBuilder();
		
		stringBuild.append(questionSentence + "\r");
		LinkedList<Answer> bothLists = new LinkedList<Answer>(); 
		bothLists.addAll(rightAnswers);
		bothLists.addAll(wrongAnswers);
		int counter = 1;
		for (Answer answer : bothLists) {
			Collections.shuffle(bothLists);
			stringBuild.append("\r\t" + (counter++) + ". " + answer.getText());
		}
		
		return stringBuild.toString();
		
	}
	
}
