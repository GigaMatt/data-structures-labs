/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing time-efficient hash tables
 * Last Modified: 18 January 2018
 * Note: This is the runner file
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;

	/**OBJECTIVE 4: REPEAT STEPS 1-3 USING LINEAR PROBING 
	 * 
	 * This method takes in no @param and identifies the word with
	 * the most amount of anagrams
	 */
public class Lab5b {
	public static void main(String[] args) throws IOException {
		
		//Objective 4.1: Read & store words
		hashTableChaining H = new hashTableChaining(400000);
		readAndStoreWords(H);

		//Objective 4.2: Have user enter a word & display ALL of the anagrams
		H.search();

		//Objective 4.3: Find the word with the most anagrams
		H.countAnagrams();
	}

	
	/**OBJECTIVE 4.1: READ AND STORE WORDS
	 * 
	 * This method takes in a @param H and reads the dictionary and
	 * fills the words accordingly.
	 * This method is of type NON-RETURN
	 */
	public static void readAndStoreWords(hashTableChaining H) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				H.insert(currentLine);		//redirects to H.insert(w), where w is a string
			}
		} catch (IOException e) {System.out.println("Error reading the file: "+ e.getMessage());}
	}
}
