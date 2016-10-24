package test;

import importer.XMLTestImporter;
import model.Answer;
import model.Question;
import model.Test;

public class Main {

	public static void main(String[] args) {
		XMLTestImporter importer = new XMLTestImporter();
		try {
			Test test = importer.importFullTest("example.xml");
			for(Question q : test.getQuestions()) {
				System.out.println(q.getText());
				System.out.println("Right answers:");
				for(Answer a : q.getRightAnswers()) {
					System.out.println(a.getText());
				}
				System.out.println("Wrong answers:");
				for(Answer a : q.getWrongAnswers()) {
					System.out.println(a.getText());
				}
				System.out.println();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
