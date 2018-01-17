/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing and editing Dynamic Programming
 * Last Modified: 17 January 2018
 * Note: This is the runner file
 */

import java.io.*;
import java.util.*;

public class Runner{

	/**
	 * METHOD 0: MAIN METHOD
	 * 
	 * This method will launch the program
	 * @throws IOException 
	 */
	public static void main(String[] arg) throws IOException{		
		//readFile();
		System.out.println("Done writing file!");
		userInput();	
	}

	/**
	 * METHOD 1: READ FILE
	 * 
	 * This method will read the file and call the editDistance method 
	 * to determine the word with the closest match
	 * THIS METHOD IS A NON-RETURN METHOD
	 */
	public static void readFile() throws IOException{
		String[] array = new String[5350];
		int counter=0;
		try (BufferedReader br = new BufferedReader(new FileReader("proteinSequences.txt"))){
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				array[counter] = currentLine;
				counter++;
			}
		}
		catch (IOException e) {
			System.out.println("Error reading the file: "+ e.getMessage());
		}

		File file = new File("bestMatches.txt");		//create the fileWriter object
		file.createNewFile();
		FileWriter fw = new FileWriter(file);

		for(int i=0;i<array.length-2;i++){
			String s1 = array[i];
			String s2;
			int maxDistance= 0;
			String cloesestMatch=" ";
			for(int j=0;j<array.length-2;j++){
				if(j!=i){
					s2= array[j];
					int eDistance = editDistance(s1,s2);		//call editDistance method

					if(eDistance>maxDistance){
						maxDistance=eDistance;
						cloesestMatch = s2;
					}	
				}
			}

			System.out.println("The closest word to "+s1+ " is "+cloesestMatch +", costing "+ maxDistance+"\n");		//Display on screen each time for verification
			fw.write("The closest word to "+s1+ " is "+cloesestMatch +", costing "+ maxDistance+"\n");
		}
		fw.close();
	}

	/**
	 * METHOD 2: EDIT DISTANCE
	 * 
	 * This method will take a @param s1 and @param s2,
	 * the Strings to compare to the blossum matrix and
	 * calculate the max distance by calling the returnValue
	 * method, to help find the perfect match.
	 * THIS METHOD WILL RETURN THE DISTANCE
	 */
	public static int editDistance (String s1, String s2){
		int[][] d = new int[s1.length()+1][s2.length()+1];
		for(int i=0;i<s1.length()+1;i++)		//cost of insertions/deletions
			d[i][0] = i*-3;
		for(int j=0;j<s2.length()+1;j++)
			d[0][j] = j*-3;

		BlossumB b =new BlossumB();

		for(int j=0; j<s1.length(); j++){
			for(int i =0; i<s2.length(); i++){
				if(s1.charAt(j)==s2.charAt(i))
					d[j+1][i+1]= b.searchChar(s1.charAt(j),s2.charAt(i));

				else{
					int temp = Math.max(Math.max(d[j+1][i],d[j][i+1]),d[j][i]);		//Compare the three areas around the target area \/ Fuentes: Math.max!
					if(temp == d[j][i+1]){
						d[j+1][i+1]=d[j][i+1]-3;
					}
					if(temp == d[j+1][i]){
						d[j+1][i+1] = d[j+1][i]-3;
					}
					if(temp == d[j][i]){
						int value = b.searchChar(s1.charAt(j), s2.charAt(i));
						d[j+1][i+1] = value;
					}
				}
			}
		}
		return blossumValue(d, s1, s2);
	}

	/**
	 * METHOD 3: BLOSSUM VALUE
	 * 
	 * This method will calculate the best matching value
	 * using @param array @param s1 @param s2.
	 * 
	 * THIS METHOD WILL RETURN THE BEST MATCHING VALUE
	 * 
	 */
	public static int blossumValue(int[][] array, String s1, String s2){
		int i=s1.length()-1;
		int j=s2.length()-1;
		int value=array[i][j];
		while((i>0) && (j>0)){
			if((s1.charAt(i)==s2.charAt(j))&& array[i-1][j-1]==Math.max(Math.max(array[i-1][j], array[i][j-1]), array[i-1][j-1])){
				value+=array[i][j];
				i--;
				j--;
			}
			else{
				if(i==0)
					j--;
				if(j==0)
					i--;
				else{
					i--;
					j--;
				}
			}


		}
		return value;
	}

	/**
	 * METHOD 4: USER INPUT
	 * 
	 * This method will ask the user for their string, which 
	 * will then call a fileReader method and a value method to
	 * determine the closest match to the string
	 */
	public static void userInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a word to be compared:\n");
		String s1 = input.nextLine();

		readFileDeux(s1);
		input.close();
	}

	/**
	 * METHOD 5: READ FILE PART DEUX
	 * 
	 * This method will read the file again and compare to the user's word
	 * This method will print the closest sequence in the list
	 */
	public static void readFileDeux(String s1){
		String[] array = new String[5350];
		int counter =0;
		try (BufferedReader br = new BufferedReader(new FileReader("proteinSequences.txt"))){
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				array[counter] = currentLine;
				counter++;
			}
		}
		catch (IOException e) {
			System.out.println("Error reading the file: "+ e.getMessage());
		}

		for(int i=0;i<array.length-2;i++){
			String s2 = array[i];
			int maxDistance= 0;
			String bestMatch=null;
			for(int j=0;j<array.length-2;j++){
				if(j!=i){
					s2= array[j];
					int eDistance = editDistance(s1,s2);		//call editDistance method

					if(eDistance>maxDistance){
						maxDistance=eDistance;
						bestMatch = s2;
					}	
				}
			}
		}
		System.out.println("The cloesest match to "+s1+" is "+bestMatch+", costing "+maxDistance+"\n");

	}
}
