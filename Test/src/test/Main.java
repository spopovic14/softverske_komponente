package test;

import rs.edu.raf.test.exporter.JSONTestExporter;
import rs.edu.raf.test.exporter.XMLTestExporter;
import rs.edu.raf.test.importer.JSONTestImporter;
import rs.edu.raf.test.importer.XMLTestImporter;
import rs.edu.raf.test.model.Answer;
import rs.edu.raf.test.model.Test;
import rs.edu.raf.test.model.questions.Question;
import rs.edu.raf.test.model.questions.QuestionBasic;
import rs.edu.raf.test.model.questions.QuestionSurvey;
import rs.edu.raf.test.model.questions.QuestionTrueOrFalse;
import rs.edu.raf.test.model.questions.StatementInQuestionSurvey;

public class Main {

	public static void main(String[] args) {
		XMLTestImporter importer = new XMLTestImporter();
		try {
			Test test = importer.importFullTest("example.xml");
			for(Question q : test.getQuestions()) {
				if(q instanceof QuestionBasic){
					System.out.println(q.getTextInstructionForQuestion());
					System.out.println(((QuestionBasic)q).getQuestionSentence());
					System.out.println("Right answers:");
					for(Answer a : ((QuestionBasic)q).getRightAnswers()) {
						System.out.println(a.getText());
					}
					System.out.println("Wrong answers:");
					for(Answer a : ((QuestionBasic)q).getWrongAnswers()) {
						System.out.println(a.getText());
					}
					System.out.println();
				}
				
				if(q instanceof QuestionTrueOrFalse) {
					System.out.println(q.getTextInstructionForQuestion());
					System.out.println(((QuestionTrueOrFalse)q).getQuestionSentence());
					System.out.println(((QuestionTrueOrFalse)q).isCorrect());
					System.out.println();
				}
				
				if(q instanceof QuestionSurvey) {
					System.out.println(q.getTextInstructionForQuestion());
					for(StatementInQuestionSurvey siqs : ((QuestionSurvey)q).getListOfStatements()) {
						System.out.println(siqs.getStatement());
					}
					System.out.println();
				}
			}
			
			JSONTestExporter jexporter = new JSONTestExporter();
			XMLTestExporter exporter = new XMLTestExporter();
			
			JSONTestImporter imp = new JSONTestImporter();
			test = imp.importFullTest("example.json");
			
			for(Question q : test.getQuestions()) {
				if(q instanceof QuestionBasic){
					System.out.println(q.getTextInstructionForQuestion());
					System.out.println(((QuestionBasic)q).getQuestionSentence());
					System.out.println("Right answers:");
					for(Answer a : ((QuestionBasic)q).getRightAnswers()) {
						System.out.println(a.getText());
					}
					System.out.println("Wrong answers:");
					for(Answer a : ((QuestionBasic)q).getWrongAnswers()) {
						System.out.println(a.getText());
					}
					System.out.println();
				}
				
				if(q instanceof QuestionTrueOrFalse) {
					System.out.println(q.getTextInstructionForQuestion());
					System.out.println(((QuestionTrueOrFalse)q).getQuestionSentence());
					System.out.println(((QuestionTrueOrFalse)q).isCorrect());
					System.out.println();
				}
				
				if(q instanceof QuestionSurvey) {
					System.out.println(q.getTextInstructionForQuestion());
					for(StatementInQuestionSurvey siqs : ((QuestionSurvey)q).getListOfStatements()) {
						System.out.println(siqs.getStatement());
					}
					System.out.println();
				}
			}
			
			exporter.exportTest(test, "export.xml");
			jexporter.exportTest(test, "export.json");
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
