package rs.edu.raf.test.exporter;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.edu.raf.test.model.Answer;
import rs.edu.raf.test.model.Test;
import rs.edu.raf.test.model.questions.*;


/**
 * Used for exporting a Test object to a .xml file.
 * 
 * 
 * @author stefan
 *
 */
public class XMLTestExporter implements TestExporter {

	@Override
	public boolean exportTest(Test test, String path) throws Exception {
		
		// Uses the org.w3c.dom package for creating XML
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		// Default importer and exporter versions use a root node with a <questions> tag
		Element root = document.createElement("questions");
		document.appendChild(root);
		Element newQuestion = null;
		
		/*----------------------------------------------------------------------
		 * Iterate through all questions and adds them to the root element
		 * 
		 * Every questions has a "type" attribute
		 * Other attributes and child nodes depend on the question type
		 *----------------------------------------------------------------------
		 */
		for(Question question : test.getQuestions()) {
			newQuestion = document.createElement("question");
			
			/*----------------------------
			 * Add QuestionBasic
			 * 
			 * Has a <text> node and
			 * multiple <answer>
			 * nodes
			 *----------------------------
			 */
			if(question instanceof QuestionBasic) {
				QuestionBasic basicQuestion = (QuestionBasic) question;
				newQuestion.setAttribute("type", QUESTION_TYPE_BASIC);
				
				Element text = document.createElement("text");
				text.setTextContent(basicQuestion.getQuestionSentence());
				newQuestion.appendChild(text);
				
				Element answerElement = null;
				for(Answer answer : basicQuestion.getRightAnswers()) {
					answerElement = document.createElement("answer");
					answerElement.setAttribute("correct", "true");
					answerElement.setTextContent(answer.getText());
					newQuestion.appendChild(answerElement);
				}
				
				for(Answer answer : basicQuestion.getWrongAnswers()) {
					answerElement = document.createElement("answer");
					answerElement.setAttribute("correct", "false");
					answerElement.setTextContent(answer.getText());
					newQuestion.appendChild(answerElement);
				}
				
			}
			
			/*----------------------------
			 * Add QuestionTrueOrFalse
			 * 
			 * Has a <text> node and an
			 * "answer" attribute
			 *----------------------------
			 */
			else if(question instanceof QuestionTrueOrFalse) {
				QuestionTrueOrFalse trueFalseQuestion = (QuestionTrueOrFalse) question;
				newQuestion.setAttribute("type", QUESTION_TYPE_TRUE_FALSE);
				
				Element text = document.createElement("text");
				text.setTextContent(trueFalseQuestion.getQuestionSentence());
				newQuestion.appendChild(text);
				
				newQuestion.setAttribute("correct", trueFalseQuestion.isCorrect() + "");
				
			}
			
			/*----------------------------
			 * Add QuestionSurvey
			 * 
			 * Has multiple <statement>
			 * nodes that contain
			 * text
			 *----------------------------
			 */
			else if(question instanceof QuestionSurvey) {
				QuestionSurvey surveyQuestion = (QuestionSurvey) question;
				newQuestion.setAttribute("type", QUESTION_TYPE_SURVEY);
				
				Element statementElement = null;
				
				for(StatementInQuestionSurvey statement : surveyQuestion.getListOfStatements()) {
					statementElement = document.createElement("statement");
					statementElement.setTextContent(statement.getStatement());
					newQuestion.appendChild(statementElement);
				}
			}
			
			root.appendChild(newQuestion);
		}
		
		// Uses a Transformer object for saving to the file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));
		
		transformer.transform(source, result);
		
		return true;
	}
	

}
