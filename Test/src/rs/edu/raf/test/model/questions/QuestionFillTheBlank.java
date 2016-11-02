package rs.edu.raf.test.model.questions;

import java.util.HashMap;
import java.util.List;

import rs.edu.raf.test.model.Answer;

/**
 * Fill-the-bank questions are composed of one question with one or multiple wholes in text 
 * left for students to put in right word or sentence.
 * 
 * 
 * @author marko
 *
 */
public class QuestionFillTheBlank extends Question {

	
	//HashMap as a KEY has Text placed before blank space and for VALUE 
	//have list of possible answers
	private HashMap<String, List<Answer>> map;
	
	/**
	 * empty constructor that have deffault value ("Fill the blank spaces with right answers: ") for instrictionQuestion
	 * @param
	 * instrictionQuestion(deffault) - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question
	 */
	public QuestionFillTheBlank(){
		super("Fill the blank spaces with right answers: ");
		map = new HashMap<>();
	} 
	
	/**
	 * 
	 * @param textOfQuestion - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question.
	 */
	public QuestionFillTheBlank(String textOfQuestion){
		super(textOfQuestion);
		map = new HashMap<>();
	}
	
	/**
	 * 
	 * @param hash - hasmap of answers (HashMap as a KEY has Text placed before blank space and for VALUE have list of possible answers)
	 */
	public QuestionFillTheBlank(HashMap <String,List<Answer>> hash){
		super("Fill the blank spaces with right answers: ");
		map = new HashMap<>(hash);
		
	}
	
	/**
	 * 
	 * @param textOfQuestion - represent instructionForQuestion - example: "Are these statements/questions true of false?". It helps students to understand question
	 * @param hash - hasmap of answers (HashMap as a KEY has Text placed before blank space and for VALUE have list of possible answers)
	 */
	public QuestionFillTheBlank(String textOfQuestion, HashMap <String,List<Answer>> hash){
		super(textOfQuestion);
		map = new HashMap<>(hash);
		
	}

	/**
	 * 
	 * @param text - String that represent key in hashmap whose value we want (return value)
	 * @return - value of key whose equal to param : text
	 */
	public List<Answer> getAnswersForSpecifiedText(String text){
		
		for (String key : map.keySet()) {
		    
			if(key.equals(text)){
				return map.get(key);
			}
			
		}
		return null;
		
	}
	
	public void putTextWithBlankValueIntoHash(String text, List<Answer> blankValues){
		
		map.put(text, blankValues);
		
	}

	@Override
	public String toStringForDocument() {
	
		StringBuilder stringBuild = new StringBuilder();
		
		for (String key : map.keySet()) {
			stringBuild.append(key+ " ______________ ");
		}
		
		return stringBuild.toString();
	}
}
