package rs.edu.raf.test.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.aspose.words.Body;
import com.aspose.words.Document;
import com.aspose.words.PaperSize;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Run;
import com.aspose.words.Section;
import com.aspose.words.Style;
import com.aspose.words.StyleType;

import rs.edu.raf.test.model.questions.Question;
import rs.edu.raf.test.model.questions.QuestionBasic;
import rs.edu.raf.test.model.questions.QuestionFillTheBlank;
import rs.edu.raf.test.model.questions.QuestionMatching;
import rs.edu.raf.test.model.questions.QuestionTrueOrFalse;

/**
 * A simple class that contains a list of Question objects.
 * 
 * @author stefan
 *
 */
public class Test {
	
	private String nameOfTest;
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
	
	public Test(String nameOfTest) {
		questions = new LinkedList<Question>();
		this.nameOfTest = nameOfTest;
	}
	
	public Test(List<Question> questions, String nameOfTest) {
		this.questions = questions;
		this.nameOfTest = nameOfTest;
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

	/**This method will generate Word document for this test and that document
	 * will be saved in location made of parameters "directory" + "nameOfTheDocument.
	 * Extension of the document is .doc".
	 * For generating this document we are using external library "Apose.Words for Java".
	 * Download that library from this link :
	 * http://www.aspose.com/downloads/words/java/new-releases/aspose.words-for-java-16.10.0/
	 * Visit this link for detail documentation of "Apose.Words for Java" : 
	 * http://www.aspose.com/docs/display/wordsjava/Home */
	public void generateWordDocument(String directory, String nameOfTheDocument) throws Exception{
		
		//Create empty document (instance of Document classe from Apose.Words)
		Document doc = new Document();
		
		//Every word document have section, so now we are creating section
		Section section = new Section(doc);
		doc.appendChild(section);
		
		section.getPageSetup().setPaperSize(PaperSize.A4);
		section.getPageSetup().setHeaderDistance (35.4); // 1.25 cm
		section.getPageSetup().setFooterDistance (35.4); // 1.25 cm
		
		//Crating the body of section
		Body body = new Body(doc);
		section.appendChild(body);
		
		//Crating paragraph
		Paragraph paragraph = new Paragraph(doc);
		
		paragraph.getParagraphFormat().setStyleName("Heading 1");
		paragraph.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
		//Crating styles
		Style style = doc.getStyles().add(StyleType.PARAGRAPH, "Style1");
		style.getFont().setSize(24);
		style.getFont().setBold(true);
		style.getFont().setColor(Color.RED);
		paragraph.getParagraphFormat().setStyle(style);
		body.appendChild(paragraph);
		
		Style styleForIntroduction = doc.getStyles().add(StyleType.PARAGRAPH, "Style2");
		style.getFont().setSize(17);
		style.getFont().setBold(false);
		style.getFont().setItalic(true);
		style.getFont().setColor(Color.CYAN);
		
		Style styleForText = doc.getStyles().add(StyleType.PARAGRAPH, "Style3");
		style.getFont().setSize(15);
		style.getFont().setBold(false);
		style.getFont().setItalic(false);
		style.getFont().setColor(Color.BLACK);
		
		//Crating run of text
		Run textRunHeadin1 = new Run(doc);
		try {
			textRunHeadin1.setText("Probni test fajl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		paragraph.appendChild(textRunHeadin1);
		
		//Creating paragraph1 for every question in list
		
		for (Question question : questions) {
			
			
				//Paragraph for Instruction Question
				Paragraph paragraphForInstruction = new Paragraph(doc);
				paragraphForInstruction.getParagraphFormat().setStyleName("Heading 1");
				paragraphForInstruction.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
				paragraphForInstruction.getParagraphFormat().setStyle(styleForIntroduction);
				
				Run runIntroduction = new Run(doc);
				try {
					runIntroduction.setText(((Question)question).getTextInstructionForQuestion());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paragraphForInstruction.appendChild(runIntroduction);
				body.appendChild(paragraphForInstruction);
				
				
				
				//Paragraph for Question
				Paragraph paragraphForQuestion = new Paragraph(doc);
				paragraphForQuestion.getParagraphFormat().setStyleName("Heading 1");
				paragraphForQuestion.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
				paragraphForQuestion.getParagraphFormat().setStyle(styleForText);
				
				Run runText = new Run(doc);
				if(question instanceof QuestionBasic){
					
					try {
						runText.setText(((QuestionBasic)question).toStringForDocument());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				
				if(question instanceof QuestionFillTheBlank){
					
					try {
						runText.setText(((QuestionFillTheBlank)question).toStringForDocument());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				
				if(question instanceof QuestionMatching){
					
					try {
						runText.setText(((QuestionMatching)question).toStringForDocument());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				
				if(question instanceof QuestionTrueOrFalse){
					
					try {
						runText.setText(((QuestionTrueOrFalse)question).toStringForDocument());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				
				paragraphForQuestion.appendChild(runText);
				body.appendChild(paragraphForQuestion);
			
			
		}
		
		doc.save(directory  + nameOfTheDocument +".doc");
	}
	
}
