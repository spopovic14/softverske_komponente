/**
 * 
 */
package rs.edu.raf.test.importer;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import rs.edu.raf.test.exception.NoQuestionTextException;
import rs.edu.raf.test.exception.NullQuestionTypeJsonException;
import rs.edu.raf.test.model.Answer;
import rs.edu.raf.test.model.Test;
import rs.edu.raf.test.model.questions.Question;
import rs.edu.raf.test.model.questions.QuestionBasic;
import rs.edu.raf.test.model.questions.QuestionSurvey;
import rs.edu.raf.test.model.questions.QuestionTrueOrFalse;
import rs.edu.raf.test.model.questions.StatementInQuestionSurvey;

/**
 * @author stefan
 *
 */
public class JSONTestImporter implements TestImporter {
	
	
	public static final String QUESTION_TEXT_LABEL = "text";
	public static final String QUESTION_BASIC_RIGHT_ANSWERS_LABEL = "rightAnswers";
	public static final String QUESTION_BASIC_WRONG_ANSWERS_LABEL = "wrongAnswers";
	public static final String QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL = "answer";
	
	//Question types
	public static final String QUESTION_TYPE_BASIC = "basic";
	public static final String QUESTION_TYPE_TRUE_FALSE = "trueFalse";
	public static final String QUESTION_TYPE_SURVEY = "survey";
	public static final String QUESTION_TYPE_MATCHING = "matching";
	public static final String QUESTION_TYPE_FILL_THE_BLANK = "fillTheBlank";
	

	@Override
	public Test importFullTest(String path) throws Exception {
		JSONParser parser = new JSONParser();
		Reader input = new FileReader(new File(path));
		JSONObject questionsObj = (JSONObject) parser.parse(input);
		JSONArray questionsArray = (JSONArray) (questionsObj.values()).iterator().next();
		
		String type = null;
		JSONObject currentQuestion = null;
		List<Question> questions = new LinkedList<>();
		
		for(Object obj : questionsArray) {
			currentQuestion = (JSONObject) obj;
			type = currentQuestion.get("type").toString();
			
			if(type == null) {
				throw new NullQuestionTypeJsonException();
			}
			
			switch(type) {
			
			case QUESTION_TYPE_BASIC:
				questions.add(importBasicQuestion(currentQuestion));
				break;
				
			case QUESTION_TYPE_TRUE_FALSE:
				questions.add(importTrueFalseQuestion(currentQuestion));
				break;
				
			case QUESTION_TYPE_SURVEY:
				questions.add(importSurveyQuestion(currentQuestion));
				break;
				
			case QUESTION_TYPE_MATCHING:
				questions.add(importMatchingQuestion(currentQuestion));
				break;
				
			case QUESTION_TYPE_FILL_THE_BLANK:
				questions.add(importFillTheBlankQuestion(currentQuestion));
				break;
				
			default:
				questions.add(importBasicQuestion(currentQuestion));
			}
		}
		
		return new Test(questions);
	}


	@Override
	public Test importTestWithNQuestions(String path, int numOfQuestions) throws Exception {
		Test fullTest = importFullTest(path);
		List<Question> allQuestions = fullTest.getQuestions();
		Collections.shuffle(allQuestions);
		
//		if(allQuestions.size() < numOfQuestions) {
//			throw new Exception();
//		}
		
		List<Question> questions = new LinkedList<Question>();
		for(int i=0; i<numOfQuestions; i++) {
			questions.add(allQuestions.get(i));
		}
		return new Test(questions);
	}
	
	
	/**
	 * Extract a QuestionBasic from a JSONObject
	 * 
	 * @param question - JSONObject with a type of "basic" or no type defined
	 * @return QuestionBasic defined by the given object
	 */
	private Question importBasicQuestion(JSONObject question) throws Exception {
		List<Answer> rightAnswers = new LinkedList<>(); 
		List<Answer> wrongAnswers = new LinkedList<>();
		String text = null;
		
		if(!question.containsKey(QUESTION_TEXT_LABEL)) {
			throw new NoQuestionTextException();
		}
		
		text = question.get(QUESTION_TEXT_LABEL).toString();
		
		JSONArray right = (JSONArray) question.get(QUESTION_BASIC_RIGHT_ANSWERS_LABEL);
		for(Object rightAnswer : right) {
			rightAnswers.add(new Answer(rightAnswer.toString()));
		}
		
		JSONArray wrong = (JSONArray) question.get(QUESTION_BASIC_WRONG_ANSWERS_LABEL);
		for(Object wrongAnswer : wrong) {
			wrongAnswers.add(new Answer(wrongAnswer.toString()));
		}
		
		return new QuestionBasic(text, wrongAnswers, rightAnswers);
	}
	
	
	/**
	 * Extract a QuestionTrueOrFalse from a JSONObject
	 * 
	 * @param question - JSONObject with a type of "trueFalse"
	 * @return QuestionTrueOrFalse defined by the given object
	 */
	private Question importTrueFalseQuestion(JSONObject question) throws Exception {
		String text = null;
		boolean answer = false;
		
		if(!question.containsKey(QUESTION_TEXT_LABEL)) {
			throw new NoQuestionTextException();
		}
		
		text = question.get(QUESTION_TEXT_LABEL).toString();
		answer = Boolean.parseBoolean(question.get(QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL).toString());
		
		return new QuestionTrueOrFalse(text, answer);
	}
	
	
	/**
	 * Extract a QuestionSurvey from a JSONObject
	 * 
	 * @param question - JSONObject with a type of "survey"
	 * @return QuestionSurvey defined by the given object
	 */
	private Question importSurveyQuestion(JSONObject question) throws Exception {
		String text = null;
		List<StatementInQuestionSurvey> statementList = new LinkedList<>();
		
		if(question.containsKey(QUESTION_TEXT_LABEL)) {
			text = question.get(QUESTION_TEXT_LABEL).toString();
		}
		
		JSONArray statementArray = (JSONArray) question.get("statements");
		for(Object statement : statementArray) {
			statementList.add(new StatementInQuestionSurvey(statement.toString()));
		}
		
		if(text == null) {
			return new QuestionSurvey(statementList);
		}
		else {
			return new QuestionSurvey(text, statementList);
		}
	}
	
	
	/**
	 * Extract a QuestionMatching from a JSONObject
	 * 
	 * @param question - JSONObject with a type of "matching"
	 * @return QuestionMatching defined by the given object
	 */
	private Question importMatchingQuestion(JSONObject question) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Extract a QuestionFillTheBlank from a JSONObject
	 * 
	 * @param question - JSONObject with a type of "fillTheBlank"
	 * @return QuestionFillTheBlank defined by the given object
	 */
	private Question importFillTheBlankQuestion(JSONObject question) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
