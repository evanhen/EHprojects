/**
 * This class represents each Ballot. Each Object is stored in an ArrayList
 * of type Candidate.
 *
 * @author Evan Hendrickson
 */
import java.util.ArrayList;

public class Ballot {
	
	ArrayList <Candidate> ballot; // Ballot, an ArrayList of candidates.
    private ArrayList <String> duplicateCheck = new ArrayList<String>(); // duplicateCheck, an ArrayList of Candidate names that will be used to ensure no duplicate candidates may be added.
    String officeName; // officeName stores the name of the office being elected.
    
/**
 * Ballot Object that sets the officeName (office being elected)
 * @param String officeName - office being elected
 */
    public Ballot(String officeName) {
    	
        ballot = new ArrayList<Candidate>(); // ballot object is created
        this.officeName = officeName; // stores the name of the office being elected in officeName.
    }

/**
 * getOfficeName() returns the name of the office being elected
 * @return String - name of office
 */
    public String getOfficeName() {
    	
        return officeName; // returns officeName.
    }
    
/**
 * addCandidate(Candidate c) adds a Candidate to the ArrayList. This method
 * does not allow duplicates to be added.
 * @param Candidate c - Candidate to be added
 * @return void
 */
    public void addCandidate(Candidate c) {
    	if (duplicateCheck.contains(c.getName())) { // if statement that checks if a candidate of the same name is already on the ballot.
    		; // if true, do nothing
    		
    	}
    	
    	else { // if false, add the Candidate to the ballot and their name to duplicateCheck.
            
    		ballot.add(c);
            duplicateCheck.add(c.getName());

    	}
    }
    
/**
 * getCandidates returns the ArrayList of Candidates, ballot.
 * @return ArrayList<Candidate> - all Candidates on Ballot
 */
    public ArrayList<Candidate> getCandidates() {
    	
        return ballot; // returns ballot. 
        
    }
}
