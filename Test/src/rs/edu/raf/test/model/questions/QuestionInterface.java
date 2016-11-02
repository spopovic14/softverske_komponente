package rs.edu.raf.test.model.questions;

public interface QuestionInterface {

	/** This method will retrun String representation of specific
	 * question that will be used for creating text document like .word doc,
	 * .pdf doc,... */
	public String toStringForDocument();
	
}
