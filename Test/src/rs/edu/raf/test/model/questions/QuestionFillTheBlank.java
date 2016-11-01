package rs.edu.raf.test.model.questions;

import java.util.HashMap;
import java.util.List;

import rs.edu.raf.test.model.Answer;

public class QuestionFillTheBlank extends Question {

	
	//HashMap as a KEY has Text placed before blank space and for VALUE 
	//have list of possible answers
	private HashMap<String, List<Answer>> map;
	
	public QuestionFillTheBlank(){
		super("Fill the blank spaces with right answers: ");
		map = new HashMap<>();
	} 
	
	public QuestionFillTheBlank(String textOfQuestion){
		super(textOfQuestion);
		map = new HashMap<>();
	}
	
	public QuestionFillTheBlank(HashMap <String,List<Answer>> hash){
		super("Fill the blank spaces with right answers: ");
		map = new HashMap<>(hash);
		
	}
	
	public QuestionFillTheBlank(String textOfQuestion, HashMap <String,List<Answer>> hash){
		super(textOfQuestion);
		map = new HashMap<>(hash);
		
	}

	
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
}
