package rs.edu.raf.test.model.questions;

import java.util.LinkedList;
import java.util.List;

public class QuestionSurvey extends Question {

	private List<StatementInQuestionSurvey> listOfStatements;
	private List<String> listOfRateStrings;
	
	public QuestionSurvey(){
		super("Please rate how strongly you agree or disagree with each of these statements. ");
		listOfStatements = new LinkedList<>();
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
	
	public QuestionSurvey(String questionSentence){
		super(questionSentence);
		listOfStatements = new LinkedList<>();
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}
	
	public QuestionSurvey(List<StatementInQuestionSurvey> statement){
		super("Please rate how strongly you agree or disagree with each of these statements. ");
		listOfStatements = new LinkedList<>(statement);
		listOfRateStrings = new LinkedList<>();
		initListOfRateStingsByDefaultValues();
	}

	public QuestionSurvey(String questionSentence, List<StatementInQuestionSurvey> statement, List<String> listOfRateStrings){
		super(questionSentence);
		listOfStatements = new LinkedList<>(statement);
		this.listOfRateStrings = new LinkedList<>(listOfRateStrings);
	}
	
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