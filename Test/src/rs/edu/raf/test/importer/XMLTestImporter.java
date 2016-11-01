package rs.edu.raf.test.importer;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rs.edu.raf.test.exception.InvalidXMLQuestionNode;
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
public class XMLTestImporter implements TestImporter {
	
	
	public static final String XML_TEXT_NAME = "#text";
	public static final String QUESTION_NODE_TEXT_LABEL = "text";
	public static final String QUESTION_NODE_ANSWER_LABEL = "answer";
	public static final String ANSWER_NODE_TEXT_LABEL = "text";
	public static final String ANSWER_NODE_CORRECT_ATTRIBUTE = "correct";
	public static final String ANSWER_NODE_CORRECT_ATTRIBUTE_TRUE_VALUE = "true";
	public static final String QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL = "answer";
	public static final String QUESTION_SURVEY_STATEMENT_LABEL = "statement";
	
	//Question types
	public static final String QUESTION_TYPE_BASIC = "basic";
	public static final String QUESTION_TYPE_TRUE_FALSE = "trueFalse";
	public static final String QUESTION_TYPE_SURVEY = "survey";
	public static final String QUESTION_TYPE_MATCHING = "matching";
	public static final String QUESTION_TYPE_FILL_THE_BLANK = "fillTheBlank";
	

	@Override
	public Test importFullTest(String path) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File(path));
		document.getDocumentElement().normalize();
		NodeList questionNodes = document.getElementsByTagName("question");
		
		List<Question> questions = new LinkedList<Question>();
		
		//loop through questions
		for(int i=0; i<questionNodes.getLength(); i++) {
			
			Node questionNode = questionNodes.item(i);
			
			if(questionNode.getAttributes().getNamedItem("type") != null) {
				
				String type = questionNode.getAttributes().getNamedItem("type").getNodeValue();
				
				switch(type) {
				
				case QUESTION_TYPE_BASIC:
					questions.add(importBasicQuestion(questionNode));
					break;
					
				case QUESTION_TYPE_TRUE_FALSE:
					questions.add(importTrueFalseQuestion(questionNode));
					break;
					
				case QUESTION_TYPE_SURVEY:
					questions.add(importSurveyQuestion(questionNode));
					break;
					
				case QUESTION_TYPE_MATCHING:
					questions.add(importMatchingQuestion(questionNode));
					break;
					
				case QUESTION_TYPE_FILL_THE_BLANK:
					questions.add(importFillTheBlankQuestion(questionNode));
					break;
					
				default:
					questions.add(importBasicQuestion(questionNode));
				
				}
				
//				if(type.equals(QUESTION_TYPE_BASIC)) {
//					questions.add(importBasicQuestion(questionNode));
//				}
//				else if(type.equals(QUESTION_TYPE_TRUE_FALSE)) {
//					questions.add(importTrueFalseQuestion(questionNode));
//				}
//				else if(type.equals(QUESTION_TYPE_SURVEY)) {
//					questions.add(importSurveyQuestion(questionNode));
//				}
			}
			else {
				questions.add(importBasicQuestion(questionNode));
			}
			
		}
		
		Test test = new Test(questions);
		
		return test;
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
	 * Determine if answerNode is right or wrong and add it to the corresponding list
	 * 
	 * @param answerNode - node to add to answer list
	 * @param rightAnswerList - list of right answers
	 * @param wrongAnswerList - list of wrong answers
	 */
	private void extractAnswerNode(Node answerNode, List<Answer> rightAnswerList, List<Answer> wrongAnswerList) {
		String answerText = getTextFromAnswerNode(answerNode);
		Answer answer = new Answer(answerText);
		
		if(answerNode.hasAttributes()) {
			if(answerNode.getAttributes().getNamedItem(ANSWER_NODE_CORRECT_ATTRIBUTE).getNodeValue().equals(ANSWER_NODE_CORRECT_ATTRIBUTE_TRUE_VALUE)) {
				rightAnswerList.add(answer);
			}
			else {
				wrongAnswerList.add(answer);
			}
		}
		else {
			wrongAnswerList.add(answer);
		}
	}
	
	
	/**
	 * Extract text from answer node
	 * 
	 * @param - answerNode node named <text> that contains the text of an answer
	 * @return answer text
	 */
	private String getTextFromAnswerNode(Node answerNode) {
		NodeList children = answerNode.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			if(child.getNodeName().equals(ANSWER_NODE_TEXT_LABEL)) {
				return child.getTextContent();
			}
		}
		
		return null;
	}
	
	/**
	 * Extract a QuestionBasic from XML
	 * 
	 * @param questionNode - a <question> node with a "basic" type attribute
	 * @return QuestionBasic object defined by the given node
	 */
	private Question importBasicQuestion(Node questionNode) throws Exception {
		NodeList childNodes = questionNode.getChildNodes();
		
		//init vars
		LinkedList<Answer> rightAnswers = new LinkedList<Answer>();
		LinkedList<Answer> wrongAnswers = new LinkedList<Answer>();
		String questionText = "";
		
		//loop through child nodes of the current question
		for(int j=0; j<childNodes.getLength(); j++) {
			
			Node childNode = childNodes.item(j);
			
			//question text node
			if(childNode.getNodeName().equals(QUESTION_NODE_TEXT_LABEL)) {
				questionText = childNode.getTextContent();
			}
			
			//question answer node
			else if(childNode.getNodeName().equals(QUESTION_NODE_ANSWER_LABEL)) {
				extractAnswerNode(childNode, rightAnswers, wrongAnswers);
			}
			
			//plain text is labeled "#text" in XML, skip this so no exception is throws for whitespaces
			else if(childNode.getNodeName().equals(XML_TEXT_NAME)) {
				
			}
			
			//error
			else {
				throw new InvalidXMLQuestionNode();
			}
			
		}
		
		return new QuestionBasic(questionText, wrongAnswers, rightAnswers);
	}
	
	/**
	 * Extract a QuestionTrueOrFalse from XML
	 * 
	 * @param questionNode - a <question> node with a "trueFalse" type attribute
	 * @return QuestionTrueOrFalse object defined by the given node
	 */
	private Question importTrueFalseQuestion(Node questionNode) throws Exception {
		NodeList childNodes = questionNode.getChildNodes();
		
		//init vars
		String questionText = "";
		boolean isCorrect = false;
		
		//loop through child nodes of the current question
		for(int j=0; j<childNodes.getLength(); j++) {
			
			Node childNode = childNodes.item(j);
			
			//question text node
			if(childNode.getNodeName().equals(QUESTION_NODE_TEXT_LABEL)) {
				questionText = childNode.getTextContent();
			}
			
			//plain text is labeled "#text" in XML, skip this so no exception is throws for whitespaces
			else if(childNode.getNodeName().equals(XML_TEXT_NAME)) {
				
			}
			
			//error
			else {
				throw new InvalidXMLQuestionNode();
			}
			
		}
		
		if(questionNode.getAttributes().getNamedItem(QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL) != null) {
			if(questionNode.getAttributes().getNamedItem(QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL).getNodeValue().equals("true")) {
				isCorrect = true;
			}
		}
		
		return new QuestionTrueOrFalse(questionText, isCorrect);
	}
	
	
	/**
	 * Import a QuestionSurvey from XML
	 * 
	 * @param questionNode - a <question> node with a "survey" type attribute
	 * @return QuestionSurvey object defined by the given node
	 */
	private Question importSurveyQuestion(Node questionNode) throws Exception {
		NodeList childNodes = questionNode.getChildNodes();
		
		//init vars
		String questionText = null;
		LinkedList<StatementInQuestionSurvey> statementList = new LinkedList<>();
		String statement = "";
		
		//loop through child nodes of the current question
		for(int j=0; j<childNodes.getLength(); j++) {
			
			Node childNode = childNodes.item(j);
			
			//question text node
			if(childNode.getNodeName().equals(QUESTION_NODE_TEXT_LABEL)) {
				questionText = childNode.getTextContent();
			}
			
			else if(childNode.getNodeName().equals(QUESTION_SURVEY_STATEMENT_LABEL)) {
				statement = childNode.getTextContent().trim();
				statementList.add(new StatementInQuestionSurvey(statement));
			}
			
			//plain text is labeled "#text" in XML, skip this so no exception is throws for whitespaces
			else if(childNode.getNodeName().equals(XML_TEXT_NAME)) {
				
			}
			
			//error
			else {
				throw new InvalidXMLQuestionNode();
			}
			
		}
		
		if(questionNode.getAttributes().getNamedItem(QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL) != null) {
			if(questionNode.getAttributes().getNamedItem(QUESTION_TRUE_FALSE_ANSWER_CORRECT_LABEL).getNodeValue().equals("true")) {
			}
		}
		
		if(questionText != null) {
			return new QuestionSurvey(questionText, statementList);
		}
		else {
			return new QuestionSurvey(statementList);
		}
	}
	
	
	/**
	 * Import a QuestionMatching from XML
	 * 
	 * @param questionNode - a <question> node with a "matching" type attribute
	 * @return QuestionMatching object defined by the given node
	 */
	private Question importMatchingQuestion(Node questionNode) throws Exception {
		return null;
	}
	
	
	/**
	 * Import a QuestionFillTheBlank from XML
	 * 
	 * @param questionNode - a <question> node with a "fillTheBlank" type attribute
	 * @return QuestionFillTheBlank object defined by the given node
	 */
	private Question importFillTheBlankQuestion(Node questionNode) throws Exception {
		return null;
	}

}
