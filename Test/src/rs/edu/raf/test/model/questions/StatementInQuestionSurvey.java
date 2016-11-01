package rs.edu.raf.test.model.questions;

public class StatementInQuestionSurvey {

	private String statement;
	
	/*VALUE represent opinion of customer for that statement.
	VALUE is initially set to null, and when customer mark one of the 
	possible solutions, it gets value of that marked solution*/
	/**
	 * Initially set to null, should get a value when the user selects an option.
	 * 
	 * Default possible values are: Strongly Disagree, Disagree, Neutral, Agree, Strongly Agree, I haven't used the product.
	 */
	private String value = null;
	
	public StatementInQuestionSurvey(){
		
	}
	
	public StatementInQuestionSurvey(String statement){
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
