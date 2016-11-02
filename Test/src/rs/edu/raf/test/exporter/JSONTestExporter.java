package rs.edu.raf.test.exporter;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rs.edu.raf.test.model.Answer;
import rs.edu.raf.test.model.Test;
import rs.edu.raf.test.model.questions.*;


/**
 * Used for exporting a Test object to a .json file.
 * 
 * 
 * @author stefan
 *
 */
public class JSONTestExporter implements TestExporter {

	@SuppressWarnings("unchecked")
	@Override
	public boolean exportTest(Test test, String path) throws Exception {
		JSONObject root = new JSONObject();
		JSONArray questionsArray = new JSONArray();
		JSONObject newQuestion = null;
		
		/*----------------------------------------------------------------------
		 * Iterate through all questions and adds them to the JSONArray
		 * 
		 * Every questions has a "type" attribute
		 * Other attributes and child objects depend on the question type
		 *----------------------------------------------------------------------
		 */
		for(Question question : test.getQuestions()) {
			newQuestion = new JSONObject();
			
			/*----------------------------
			 * Add QuestionBasic
			 *----------------------------
			 */
			if(question instanceof QuestionBasic) {
				QuestionBasic basicQuestion = (QuestionBasic) question;
				newQuestion.put("type", QUESTION_TYPE_BASIC);
				
				newQuestion.put("text", basicQuestion.getQuestionSentence());
				
				JSONArray rightAnswers = new JSONArray();
				for(Answer answer : basicQuestion.getRightAnswers()) {
					rightAnswers.add(answer.getText());
				}
				newQuestion.put("rightAnswers", rightAnswers);
				
				JSONArray wrongAnswers = new JSONArray();
				for(Answer answer : basicQuestion.getWrongAnswers()) {
					wrongAnswers.add(answer.getText());
				}
				newQuestion.put("wrongAnswers", wrongAnswers);
				
			}
			
			/*----------------------------
			 * Add QuestionTrueOrFalse
			 *----------------------------
			 */
			else if(question instanceof QuestionTrueOrFalse) {
				QuestionTrueOrFalse trueFalseQuestion = (QuestionTrueOrFalse) question;
				newQuestion.put("type", QUESTION_TYPE_TRUE_FALSE);
				newQuestion.put("text", trueFalseQuestion.getQuestionSentence());
				newQuestion.put("answer", trueFalseQuestion.isCorrect() + "");
				
			}
			
			/*----------------------------
			 * Add QuestionSurvey
			 *----------------------------
			 */
			else if(question instanceof QuestionSurvey) {
				QuestionSurvey surveyQuestion = (QuestionSurvey) question;
				newQuestion.put("type", QUESTION_TYPE_SURVEY);
				
				JSONArray statementArray = new JSONArray();
				for(StatementInQuestionSurvey statement : surveyQuestion.getListOfStatements()) {
					statementArray.add(statement.getStatement());
				}
				
				newQuestion.put("statements", statementArray);
				
			}
			
			questionsArray.add(newQuestion);
		}
		
		// Default importer and exporter versions use a root object with an
		// array labeled "questions"
		root.put("questions", questionsArray);
		
		FileWriter writer = new FileWriter(path);
		writer.write(root.toJSONString());
		writer.close();
		
		return true;
	}

}
