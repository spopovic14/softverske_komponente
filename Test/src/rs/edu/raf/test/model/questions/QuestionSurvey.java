package rs.edu.raf.test.model.questions;

import java.util.LinkedList;
import java.util.List;
/**
 * Survey question represent question where student or examinee give their opinion about the question
 * For example how much are they satisfied with some product, etc. 
 * 
 * @author marko
 *
 */
public class QuestionSurvey extends Question {

	private List<StatementInQuestionSurvey> listOfStatements;
	private List<String> listOfRateStrings;
	
	/**
	 * empty constructor that have deffault value ("Please rate how strongly you agree or disagree with each of these statements. ") for instrictionQuestion
	 * @param
	 * instrictionQuestion(deffault) - represent instructionForQuestion - example: "Please rate how strongly you agree or disagree with each of these statements. ". It helps students to understand question
	 */
	public QuestionSurvey(){
		super("Please rate how strongly you agree or disagree with each of these statements. ");
		listOfStatements = new LinkedList<>();
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
	
	/**
	 * 
	 * @param questionSentence - String that represent the actuall question , like : "Please rate how strongly you agree or disagree with each of these statements. : "
	 */
	public QuestionSurvey(String questionSentence){
		super(questionSentence);
		listOfStatements = new LinkedList<>();
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
	
	/**
	 * 
	 * @param statement - represent the list of statements
	 * instrictionQuestion(deffault) - represent instructionForQuestion - example: "Please rate how strongly you agree or disagree with each of these statements. ". It helps students to understand question
	 */
	public QuestionSurvey(List<StatementInQuestionSurvey> statement){
		super("Please rate how strongly you agree or disagree with each of these statements. ");
		listOfStatements = new LinkedList<>(statement);
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
/**
 * 
 * @param questionSentence - represent instructionForQuestion - example: "Please rate how strongly you agree or disagree with each of these statements. ". It helps students to understand question
 * @param statement -  represent the list of statements
 * @param listOfRateStrings - represent list of possible rates. For example: "Strongly Disagree","Disagree","Netural","Agree".
 */
	public QuestionSurvey(String questionSentence, List<StatementInQuestionSurvey> statement, List<String> listOfRateStrings){
		super(questionSentence);
		listOfStatements = new LinkedList<>(statement);
		this.listOfRateStrings = new LinkedList<>(listOfRateStrings);
	}
	/**
	 * 
	 * @param questionSentence - represent instructionForQuestion - example: "Please rate how strongly you agree or disagree with each of these statements. ". It helps students to understand question
	 * @param statement - represent the list of statements
	 */
	public QuestionSurvey(String questionSentence, List<StatementInQuestionSurvey> statement) {
		super(questionSentence);
		listOfStatements = new LinkedList<>(statement);
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
	
	 
	private void initListOfRateStingsByDefaultValues(){
		
		listOfRateStrings.add("Strongly Disagree");
		listOfRateStrings.add("Disagree");
		listOfRateStrings.add("Neutral");
		listOfRateStrings.add("Agree");
		listOfRateStrings.add("Strongly Agree");
		listOfRateStrings.add("I haven't used the product.");
		
	}

	public void addStatement(StatementInQuestionSurvey statement){
		listOfStatements.add(statement);
	}
	
	public void addRateString(String rateString){
		listOfRateStrings.add(rateString);
	}
	
	public List<StatementInQuestionSurvey> getListOfStatements() {
		return listOfStatements;
	}

	public void setListOfStatements(List<StatementInQuestionSurvey> listOfStatements) {
		this.listOfStatements = listOfStatements;
	}

	public List<String> getListOfRateStrings() {
		return listOfRateStrings;
	}

	public void setListOfRateStrings(List<String> listOfRateStrings) {
		this.listOfRateStrings = listOfRateStrings;
	}

	@Override
	public String toStringForDocument() {
	
		StringBuilder stringBuild = new StringBuilder();
		
		
		return null;
	}
	
	
	
}