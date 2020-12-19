/**
 * This class reads the ballot from the input file.
 * 
 *
 * @author Evan Hendrickson
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BallotReader {
	
/**
 * public static Ballot reader(String filename) reads from an input file to properly format the ballot.
 * @param String filename - name of the input file
 * @return Ballot ballot - returns properly formatted ballot with all candidates
 * @throws IOException
 */
	public static Ballot readBallot(String filename) throws IOException {
		int candidateCount; // candidateCount holds the number of candidates on the ballot
        Scanner scanner = new Scanner(new FileReader("input.txt")); // create a scanner to read from the input file
        Ballot ballot; // Ballot object will be the formatted ballot
        
        ballot = new Ballot(scanner.nextLine()); // Create a ballot with the name of the office as a parameter (the first line of the input file will always be the office name).
        candidateCount = Integer.parseInt(scanner.nextLine()); // candidateCount is set to equal the next line in the file (the second line of the input file will always be the number of candidates). ParseInt is used to convert from String to int.
        
        for(int i = 1; i <= candidateCount; i++) { // for loop that iterates through each line of candidate names and affiliations to separate them for formatting.
        	
            String [] properties = scanner.nextLine().split(";"); // An array of Strings is created from the third line of the file (the candidate's name and affiliation). The two are stored as separate elements by splitting them at the semicolon. 
            String name = properties[0]; // retrieves name from properties array
            String affiliation = properties[1]; // retrieves affiliation from properties array

            ballot.addCandidate(new Candidate(name, affiliation)); // adds a candidate to the ballot
        }
        
        return ballot; // returns the ballot
    }
}
