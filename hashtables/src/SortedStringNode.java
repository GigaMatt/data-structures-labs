/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing time-efficient hash tables
 * Last Modified: 18 January 2018
 * Note: This is NOT the runner file
 */


/**CLASS FOR OBJECTIVE 4
 * 
 * 
 */
public class SortedStringNode {

	public String SortedString;    
	public StringNode anagrams;  
	public int numberofAnagrams;		//added to keep track of the max # of anagrams per position


	public SortedStringNode(String S, SortedStringNode n){                        
		SortedString = S;      
		anagrams = null;   
		numberofAnagrams = 0;			//added to keep track of the max # of anagrams per position

	}

	public SortedStringNode(String S, SortedStringNode n, String w){                        
		SortedString = S;      
		anagrams = new StringNode(w, null);
		numberofAnagrams++;				//added to keep track of the max # of anagrams per position
	}
}
