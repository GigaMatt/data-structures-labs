/*
 * By: Matthew S Montoya
 * Purpose: To practice constructing and implementing B-Trees with loops & recursion  
 * Last Modified: 11 January 2018
 */
import java.io.*;
import java.util.*;
import java.lang.*;	//Should take care of the .contains method (in theory)

public class Lab04{
	public static void main(String[] args){
		/*OBJECTIVE 1
		 * 
		 * Build the B-Tree containing the words in the dictionary
		 */
		BTree englishWords = new BTree(354984);	//354984
		int n = 100000;
		//int n=S.length;
		for (int i=0;i<n;i++){
			//  System.out.println("Inserting "+S[i]);
			//englishWords.insert([i]);
			//  T.printNodes();
			//  System.out.println();
			
			fillWordsSet(englishWords);
			
			//Debugging  
			//englishWords.printNodes();
		
		
		
		
		/* OBJECTIVE 2
		 * 
		 * Ask for user input for the printAnagrams method
		 * Call the printAnagrams method
		 */
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the prefix");
		String prefix = input.nextLine();
		System.out.println("Enter the word");
		String word = input.nextLine();
		int numOfAnagrams = printAnagrams(englishWords, prefix, word, 0);
		System.out.println("The word "+word+" has "+numOfAnagrams+" anagrams.");
		
		
		/**
		 * OBJECTIVES 3 + 4
		 * 
		 * Call the mostAnagrams method
		 * Time how long it will take to find and print out the result
		 */
		//////////////////////////////////////////////////////////////////
		long timeStartMillis1 = System.currentTimeMillis();            //    begin logging time
		long timeStartNano1 = System.nanoTime();                      //
		///////////////////////////////////////////////////////////////

		//englishWordsSet.mostAnagrams(englishWords, "null", 0);             //Sort via insertion sort

		////////////////////////////////////////////////////////////
		long timeEndMillis1 = System.currentTimeMillis();        //   End logging time
		long timeEndNano1 = System.nanoTime();                  //
		/////////////////////////////////////////////////////////
		
		System.out.println("It took "+(timeEndMillis1-timeStartMillis1)+"ms to find the result.");
	
		input.close();
		}
	}
	
	
	
	
	private static void fillWordsSet(BTree englishWords) {
		try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				englishWords.insert(currentLine);
			}
		} catch (IOException e) {System.out.println("Error reading the file: "+ e.getMessage());}
	}

	
	



	/**
	 * ADDED Method wordsExist
	 * 
	 * This method will determine if a word exists.
	 * This method will be of return type boolean.
	 */ 
	public static boolean wordExists(BTreeNode T, String str){
		if(T.isLeaf){
			for(int i=0; i<T.n; i++){
				if(T.key[i].equalsIgnoreCase(str))
					return true;
			}
			return false;
		}
		for(int j=0; j<T.n; j++){
			if(T.key[j].equalsIgnoreCase(str))
				return wordExists(T, str);
			if(T.key[j].compareToIgnoreCase(str) < 0)
				return wordExists(T.c[j], str);
		}
		return (wordExists(T.c[T.n], str));    
	}



	/**
	 * ADDED METHOD mostAnagrams
	 * This method will find the word in the tree with the most amount
	 * of anagrams. From the main method, this method will take in a
	 * null String and an int 0
	 * 
	 */

	public static BTreeNode mostAnagrams(BTreeNode T, String maxAnagrams, int numberOfAnagramsFromBefore){
		if(T.isLeaf){
			for(int i=0; i<T.n; i++){
				int numOfAnagramsInThisWord = countAnagrams("",T.key[i], T, 0);
				if(numOfAnagramsInThisWord > numberOfAnagramsFromBefore){
					maxAnagrams = T.key[i];
					numberOfAnagramsFromBefore = numOfAnagramsInThisWord;
				}
			}
			System.out.println("The word with the most amount of anagrams is "+maxAnagrams+" with "+numberOfAnagramsFromBefore+" anagrams.");
		}
		else if(!T.isLeaf){
			for(int i=0; i<T.n; i++){
				int numOfAnagramsInThisWord = countAnagrams("",T.key[i], T, 0);
				if(numOfAnagramsInThisWord > numberOfAnagramsFromBefore){
					maxAnagrams = T.key[i];
					numberOfAnagramsFromBefore = numOfAnagramsInThisWord;
				}
			}
			return mostAnagrams(T.c[0], maxAnagrams, numberOfAnagramsFromBefore);
		}
		for(int i=0; i<T.n; i++){
			int numOfAnagramsInThisWord = countAnagrams("",T.key[i], T, 0);
			if(numOfAnagramsInThisWord > numberOfAnagramsFromBefore){
				maxAnagrams = T.key[i];
				numberOfAnagramsFromBefore = numOfAnagramsInThisWord;
			}
		}
		return mostAnagrams(T.c[T.n], maxAnagrams, numberOfAnagramsFromBefore);
	}


	/**
	 * ADDED METHOD countAnagrams
	 * This method will find the number of anagrams in a word
	 * This method will be of int return type.
	 * 
	 */

	public static int countAnagrams(String prefix, String word, BTreeNode englishWordsSet, int count) {
		if(word.length()>8){
			String str = prefix + word;
			if (englishWordsSet.contains(englishWordsSet, str)){
				count++;
			}   
		}
		else {
			for(int i = 0; i < word.length(); i++) {
				String cur = word.substring(i, i + 1);
				String before = word.substring(0, i); // letters before cur
				String after = word.substring(i + 1); // letters after cur
				if (!before.contains(cur))  // Check if permutations of cur have not been generated.
					countAnagrams(prefix + cur, before + after, englishWordsSet, count);
			}
		}
		return count;
	}


	/*

  public static void printAnagrams(String word) {
	  printAnagrams("",word);
  }



  public static int countAnagrams(String word, BTree englishWordsSet){
	    if (word.length()>8)
	        return 1;
	    return countAnagrams("",word,englishWordsSet);
	} 
	 */


	public static int countAnagrams(BTree englishWords, String prefix, String word, int count) {
		if(word.length() <= 1){
			String str = prefix + word;
			if (englishWords.contains(englishWords, str)){
				System.out.println(str);
				count++;
			}

		}
		else {
			for(int i = 0; i < word.length(); i++) {
				String cur = word.substring(i, i + 1);
				String before = word.substring(0, i); // letters before cur
				String after = word.substring(i + 1); // letters after cur
				if (!before.contains(cur)) // Check if permutations of cur have not been generated.
					printAnagrams(englishWords, prefix + cur, before + after, count);
			}
		}
		return count;
	}



	public static int printAnagrams(BTree englishWords, String prefix, String word, int count) {
		if(word.length() <= 1) {
			String str = prefix + word;
			if (englishWords.contains(englishWords, str)){
				System.out.println(str);
				return count;
			}

		}
		else {
			for(int i = 0; i < word.length(); i++) {
				String cur = word.substring(i, i + 1);
				String before = word.substring(0, i); // letters before cur
				String after = word.substring(i + 1); // letters after cur
				if (!before.contains(cur)) // Check if permutations of cur have not been generated.
					printAnagrams(englishWords, prefix + cur, before + after, count);
			}
		}
		return count;
	}
}
