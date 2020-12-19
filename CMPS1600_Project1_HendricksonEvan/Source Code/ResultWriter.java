/**
 * This class writes the election results in an output file.
 * 
 *
 * @author Evan Hendrickson
 */
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ResultWriter {

	static String display; // static String display stores the winner to be output to file.

/**
* public static void writeResults(String filename, Ballot ballot) writes the results of the election to a given filename. This method throws an exception if the file cannot be written.
* @param String filename - the name of the file to be written to.
* @param Ballot ballot - the ballot of the election.
* @return void
*/
	public static void writeResults(String filename, Ballot ballot) throws IOException {
    	
        PrintWriter output = new PrintWriter(new FileWriter(filename)); // a PrintWriter object "output" that will write to the output file.
        ArrayList<Candidate> candidates = ballot.getCandidates(); // retrieves all candidates from the ballot and stores them in ArrayList candidates.
        
        output.println("RESULTS - " + ballot.getOfficeName()); // will write to the output file.
        output.println("----------------------------"); // will write to the output file.
        
        for(int i = 0; i < candidates.size(); i++) { // for loop that writes each candidate's formatted tag to the output file.
        	
	    	output.println(String.format("%-35s %d", candidates.get(i).toString(), candidates.get(i).getVoteCount())); // writes each candidate's tag to output. "%-35s" generates 35 spaces between the cadidate's affiliation and their voteCount for formatting purposes.
	    	display = Candidate.compareVotes(candidates); // display stores the winner of the election, if there is one. In the event of a tie, display will hold "NO WINNER". compareVotes is invoked to find the winner.
	    		
	    }
        
        output.println(); // writes an empty line to the output file.
        output.println(display); // writes the value of display to the file.
        output.close(); // closes the PrintWriter.
        
    }
}
