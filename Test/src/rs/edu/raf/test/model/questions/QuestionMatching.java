package rs.edu.raf.test.model.questions;

import java.util.Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Students respond to matching questions by pairing each of a set of stems with one of the choices provided on the exam.
 * These questions are often used to assess recognition and recall and
 * so are most often used in courses where acquisition of detailed knowledge is an important goal.
 * 
 * @author marko
 *
 */
public class QuestionMatching extends Question {

	//First part of sentence is KEY in hash , 
	//and second part of sentence is his correct attribute(match)
	private HashMap<String, String> sentences;
	
	//List of all possible matches
	private List<String> listOfAttribute;
	
	/**
	 * empty constructor that have default value ("Match each question type with one attribute: ") for instrictionQuestion
	 * 
	 * instrictionQuestion(default) - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question
	 */
	public QuestionMatching(){
		super("Match each question type with one attribute: ");
		sentences = new HashMap<>();
		listOfAttribute = new LinkedList<>();
	}
	
	/**
	 * 
	 * @param textOfQuestion - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question.
	 */
	public QuestionMatching(String textOfQuestion){
		super(textOfQuestion);
		sentences = new HashMap<>();
		listOfAttribute = new LinkedList<>();
		
	}
	
	/**
	 * 
	 * @param map - hashmap that represent collection of sentences (First part of sentence is KEY in hash , 
	 *              and second part of sentence is his correct attribute(match))
	 */
	public QuestionMatching(HashMap<String, String> map){
		super("Match each question type with one attribute: ");
		sentences = new HashMap<>(map);
		listOfAttribute = new LinkedList<>();
		fillTheList();
	}
	
	/**
	 * 
	 * @param textOfQuestion - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question.
	 * @param map - hashmap that represent collection of sentences (First part of sentence is KEY in hash , 
	 *              and second part of sentence is his correct attribute(match))
	 */
	public QuestionMatching(String textOfQuestion, HashMap<String, String> map){
		super(textOfQuestion);
		sentences = new HashMap<>(map);
		listOfAttribute = new LinkedList<>();
		fillTheList();
	}
	
	/**
	 * This method put one sentence into hashmap.
	 * 
	 * @param firstPartOfSentence - represent KEY in hashmap.
	 * @param attribute - represent VALUE in hashmap, correct attribute(match) for that KEY.
	 */
	public void addSentenceToQuestion(String firstPartOfSentence, String attribute){
		
		sentences.put(firstPartOfSentence, attribute);
		listOfAttribute.add(attribute);
		Collections.shuffle(listOfAttribute);
		
	}

	/**
	 * This method will fill the helplist with all attributes from hashmap, 
	 * and he will shuffle that list.
	 * 
	 */
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
			stringBuild.append("\r" + key + "\t\t\t\t" + getListOfAttributeShuffeled().get(counter++));
		}
		
		return stringBuild.toString();
	}

	
	
}

