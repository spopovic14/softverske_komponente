package importer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import model.Answer;
import model.Question;
import model.Test;

public class XMLTestImporter implements TestImporter {
	
	
	public static final String QUESTION_NODE_TEXT_LABEL = "text";
	public static final String QUESTION_NODE_ANSWER_LABEL = "answer";
	public static final String ANSWER_NODE_TEXT_LABEL = "text";
	public static final String ANSWER_NODE_CORRECT_ATTRIBUTE = "correct";
	public static final String ANSWER_NODE_CORRECT_ATTRIBUTE_TRUE_VALUE = "true";
	

	@Override
	public Test importFullTest(String path) throws IOException, ParserConfigurationException, SAXException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File(path));
		document.getDocumentElement().normalize();
		NodeList questionNodes = document.getElementsByTagName("question");
		
		List<Question> questions = new LinkedList<Question>();
		
		List<Answer> rightAnswers = null;
		List<Answer> wrongAnswers = null;
		Question question = null;
		
		
		//loop through questions
		for(int i=0; i<questionNodes.getLength(); i++) {
			
			//init vars
			rightAnswers = new LinkedList<Answer>();
			wrongAnswers = new LinkedList<Answer>();
			String questionText = "";
			
			Node questionNode = questionNodes.item(i);
			NodeList childNodes = questionNode.getChildNodes();
			
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
				
				//error
				else {
//					throw new Exception();
				}
				
			}
			
			//add question to list
			question = new Question(questionText, wrongAnswers, rightAnswers);
			questions.add(question);
			
		}
		
		Test test = new Test(questions);
		
		return test;
	}

	@Override
	public Test importTestWithNQuestions(String path, int numOfQuestions) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Determine if answerNode is right or wrong and add it to the corresponding list
	 * 
	 * @param answerNode node to add to answer list
	 * @param rightAnswerList list of right answers
	 * @param wrongAnswerList list of wrong answers
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
	 * @param answerNode node named <text> that contains the text of an answer
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
	
	

}
