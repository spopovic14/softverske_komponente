package rs.edu.raf.test.model.questions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuestionMatching extends Question {

	//First part of sentence is KEY in hash , 
	//and second part of sentence is his correct attribute(match)
	private HashMap<String, String> sentences;
	
	//List of all possible matches
	private List<String> listOfAttribute;
	
	public QuestionMatching(){
		super("Match each question type with one attribute: ");
		sentences = new HashMap<>();
		listOfAttribute = new LinkedList<>();
	}
	
	public QuestionMatching(String textOfQuestion){
		super(textOfQuestion);
		sentences = new HashMap<>();
		listOfAttribute = new LinkedList<>();
		
	}
	
	public QuestionMatching(HashMap<String, String> map){
		super("Match each question type with one attribute: ");
		sentences = new HashMap<>(map);
		listOfAttribute = new LinkedList<>();
		fillTheList();
	}
	
	public QuestionMatching(String textOfQuestion, HashMap<String, String> map){
		super(textOfQuestion);
		sentences = new HashMap<>(map);
		listOfAttribute = new LinkedList<>();
		fillTheList();
	}
	
	public void addSentenceToQuestion(String firstPartOfSentence, String attribute){
		
		sentences.put(firstPartOfSentence, attribute);
		listOfAttribute.add(attribute);
		Collections.shuffle(listOfAttribute);
		
	}

	public void fillTheList(){
		
		Iterator<Map.Entry<String, String>> it = sentences.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> pair = it.next();
			listOfAttribute.add(pair.getValue());
			it.remove();
		}
		Collections.shuffle(listOfAttribute);
	}
	
	public HashMap<String, String> getSentences() {
		return sentences;
	}

	public void setSentences(HashMap<String, String> sentences) {
		this.sentences = sentences;
	}

	public List<String> getListOfAttributeShuffeled() {
		return listOfAttribute;
	}

	@Override
	public String toStringForDocument() {
		
		StringBuilder stringBuild = new StringBuilder();
		int counter = 0;
		for (String key : sentences.keySet()) {
			stringBuild.append("\n" + key + "\t\t\t\t" + getListOfAttributeShuffeled().get(counter++));
		}
		
		return stringBuild.toString();
	}

	
	
}

