/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing time-efficient hash tables
 * Last Modified: 18 January 2018
 * Note: This is NOT the runner file
 */

import java.util.Arrays;
import java.util.Scanner;

public class hashTableChaining{	

	private SortedStringNode [] H;		

	public hashTableChaining(int n){	   
		H = new SortedStringNode[n];     
		for(int i=0;i<n;i++)         
			H[i] = null;   
	}

	/**OBJECTIVE 4.1: INSERT
	 */
	public String sort(String w){	//This method will sort the string into ascending order
		char[] wCharArray = w.toCharArray();
		Arrays.sort(wCharArray);	//call the .sort method from util.Array to sort the elements
		String sorted = new String(wCharArray);

		//System.out.println(sorted);		//verify that the String is actually sorted

		return sorted;
	}

	public int value(String sorted){		//long 100-line method
		char[] wCharArray = sorted.toCharArray();	//so now we have the sorted string in a char array
		int sum = 0, count = 0;
		for(int i=wCharArray.length-1; i>= 0; i--){	//use switch-case for ease-of-use
			switch(wCharArray[i]){
			case 'a':
				sum+=(0*(Math.pow(26, count)));
				break;
			case 'b':
				sum+=(1*(Math.pow(26, count)));
				break;
			case 'c':
				sum+=(2*(Math.pow(26, count)));
				break;	
			case 'd':
				sum+=(3*(Math.pow(26, count)));
				break;
			case 'e':
				sum+=(4*(Math.pow(26, count)));
				break;
			case 'f':
				sum+=(5*(Math.pow(26, count)));
				break;
			case 'g':
				sum+=(6*(Math.pow(26, count)));
				break;
			case 'h':
				sum+=(7*(Math.pow(26, count)));
				break;
			case 'i':
				sum+=(8*(Math.pow(26, count)));
				break;
			case 'j':
				sum+=(9*(Math.pow(26, count)));
				break;
			case 'k':
				sum+=(10*(Math.pow(26, count)));
				break;
			case 'l':
				sum+=(11*(Math.pow(26, count)));
				break;
			case 'm':
				sum+=(12*(Math.pow(26, count)));
				break;
			case 'n':
				sum+=(13*(Math.pow(26, count)));
				break;
			case 'o':
				sum+=(14*(Math.pow(26, count)));
				break;
			case 'p':
				sum+=(15*(Math.pow(26, count)));
				break;
			case 'q':
				sum+=(16*(Math.pow(26, count)));
				break;
			case 'r':
				sum+=(17*(Math.pow(26, count)));
				break;
			case 's':
				sum+=(18*(Math.pow(26, count)));
				break;
			case 't':
				sum+=(19*(Math.pow(26, count)));
				break;
			case 'u':
				sum+=(20*(Math.pow(26, count)));
				break;
			case 'v':
				sum+=(21*(Math.pow(26, count)));
				break;
			case 'w':
				sum+=(22*(Math.pow(26, count)));
				break;
			case 'x':
				sum+=(23*(Math.pow(26, count)));
				break;
			case 'y':
				sum+=(24*(Math.pow(26, count)));
				break;
			case 'z':
				sum+=(25*(Math.pow(26, count)));
				break;
			}
			count++;
		}

		/**Debugging
		 * int position = sum%5;		//for "lake," this should equal 0
		 * System.out.println(position);
		 */
		return sum;
	}

	public void insert(String w){			//inserts new sorted words as well as just anagrams if sorted exists
		String sorted = sort(w);				//sorts the word alphabetically
		int pos = value(sorted)%H.length;			//Hash Function
		if(H[pos] == null){
			//H[pos] = sorted;
			//System.out.println("Inserting "+sorted);
			H[pos] = new SortedStringNode(sorted, H[pos], w);	//forget about incrementing anagrams here \/ it won't matter (in theory)
		}
		else{
			//if(H[pos].SortedString.equalsIgnoreCase(sorted)){	//if the sorted word already exists
			//System.out.println("Inserting "+w);
			H[pos].anagrams = new StringNode(w, H[pos].anagrams);		//add more anagrams
		}
	}

	/**OBJECTIVE 4.2: IDENTIFY ANAGRAMS FOR A WORD
	 * 
	 * @param w
	 */
	public void search(){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the word I shall search for?");
		String w = input.nextLine();
		System.out.println("Searching the Hash Table for all anagrams for '"+w+".' Please wait... ");

		String sorted = sort(w);				//sorts the word alphabetically
		int pos = value(sorted)%H.length;		//Hash Function \/ determines where to start looking		

		//for(int i=0; i<H.length; i++){
		if(H[pos].SortedString.equalsIgnoreCase(sorted)){//if a match is found
			System.out.println("The anagrams for "+w+" are:");
			for(StringNode j=H[pos].anagrams; j!=null; j=j.next){
				System.out.println(j.word);
			}
		}
		if(H[pos].equals(null)){		//if no match was found
			System.out.println("There are no anagrams for the word"+w+".");
			//break;
		}
		//}
		input.close();
	}

	/**OBJECTIVE 4.3: IDENTIFY WORD WITH MOST ANAGRAMS
	 * 
	 */
	public void countAnagrams(){
////////////////////////////////////////////////////////////
long timeStartMillis1 = System.currentTimeMillis();  	 //    begin logging time
/////////////////////////////////////////////////////////
		String wordWithMostAnagrams = null;
		int numberofAnagramsInWord = -1;	//use -1 for debugging purposes

		for(int i=0; i< H.length; i++){
			if(H[i] != null){
				if(H[i].numberofAnagrams > numberofAnagramsInWord){
					numberofAnagramsInWord = H[i].numberofAnagrams;
					wordWithMostAnagrams = H[i].anagrams.word;
				}
			}
		}

			//Debugging
			if(numberofAnagramsInWord == -1)
				System.out.println("Debugging Message: Error computing this information.");
			else
				System.out.println("\nThe word with the most amount of anagrams is "+wordWithMostAnagrams
						+" with a total of "+numberofAnagramsInWord+" anagrams.");
////////////////////////////////////////////////////////////
long timeEndMillis1 = System.currentTimeMillis();    	 //   	End logging time
/////////////////////////////////////////////////////////

System.out.println("\nThe time to find the word with the most anagrams is "     //Calculate & print out times
+((timeEndMillis1-timeStartMillis1))+" miili-seconds.");
		}
}
