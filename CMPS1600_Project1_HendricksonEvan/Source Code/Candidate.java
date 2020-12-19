/**
 * This class sets each Candidate's name, party affiliation, holds their vote count, and has methods to return these attributes.
 *
 * @author Evan Hendrickson
 */
import java.util.ArrayList;

public class Candidate {
	
    String name; // name of candidate
    String affiliation; // party affiliation
    int voteCount; // number of votes

/**
* Candidate Object that sets each Candidate's name, party affiliation, and vote count.
* @param String name -  Candidate name
* @param String affiliation - Candidate party affiliation
*/
	public Candidate(String name, String affiliation) {
		
        this.name = name; // stores the particular candidate's name in the name variable.
        this.affiliation = affiliation; // stores the particular candidate's affiliation in the affiliation variable.
        voteCount = 0; // all candidates begin with a voteCount of 0.
	}
	
/**
* getName() returns the name of a Candidate
* @return String - name of Candidate
*/
	public String getName() {
		
        return name; // return Candidate name

	}
	
/**
* getAffiliation() returns the affiliation of a Candidate
* @return String - affiliation of Candidate
*/
	public String getAffiliation() {
		
        return affiliation; // return Candidate affiliation

	}
    
/**
* getVoteCount() returns the number of votes a Candidate has
* @return int - number of votes a Candidate has
*/
    public int getVoteCount() {
    	
        return voteCount; // return number of votes

    }
    
/**
* tallyVote() increments voteCount by 1 each time a Candidate gets a vote.
* @return void
*/
	public void tallyVote() {
		
        voteCount++; // increment voteCount

	}
	
/**
* toString() formats the Candidates' tags for the ballot
* @return formatted results for output file
*/
	public String toString() {
		
		return name + " - " + affiliation; // returns the name and affiliation of a Candidate formatted for output
        
	}
	
/**
* compareVotes(ArrayList<Candidate> c) compares all of the voteCounts to determine the winning candidate of the election.
* @param ArrayList<Candidate> c - an ArrayList of all Candidate objects 
* @return String winner - the name of the winning Candidate
*/
	public static String compareVotes(ArrayList<Candidate> c) {
	    	
		int winningVote = 0; // the number of winning votes
		int tieChecker = 0; // tiechecker that will be incremented each time the same number of votes is found
		String winner = "NO WINNER"; // default as no winner
		    
		for (int i = 0; i < c.size(); i++) { // for loop that finds the winner of the election using the getter methods
			if (c.get(i).getVoteCount() > winningVote) { // if statement that compares each Candidate's voteCount
				winningVote = c.get(i).getVoteCount(); // highest number of votes becomes winningVote
				winner = "WINNER: " + c.get(i).getName(); // winner becomes Candidate with highest votes
			}
		}
		    
		    for (int j = 0; j < c.size(); j++) { // for loop that checks for a tie
		    	if (c.get(j).getVoteCount() == winningVote) { // if statement that compares each voteCount with winningVote
		    		tieChecker++; // if true, tieChecker is incremented
		    	}
		    	
		    	if (tieChecker >= 2) { // if statement to declare NO WINNER if tieChecker is greater than or equal to 2, indicating a tie
		    		winner = "NO WINNER";
		    	}
		    }
		    
		    return winner; // return winner to be output to file
	}
}
