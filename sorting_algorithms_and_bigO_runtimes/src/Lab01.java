/*CS 2302
 * Lab 01
 * By: Matthew S Montoya
 * Instructor: Dr. Olac Fuentes
 * TAs: Md Abdul Kader && Zakia Al Kadri
 * Purpose: To identify various sorting algorithms, alongside their time complexity
 * Last Modified: 27 January 2017
 */

import java.util.*;

public class Lab01 {
//MAIN METHOD
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter an integer:");                                           //int n -- will be the size of the array
    int n = input.nextInt();
    System.out.println("Enter another integer:");                                      //int m -- will randomly generate a number between 0 and m-1
    int m = input.nextInt();
    int [] randomizedArray = buildArray(n, m);                                                   //Calls method 1 to build the array
    
    int[] arrayForGivenMethod = arrayCopy(n, randomizedArray);
    System.out.println();
    boolean containsDuplicates = hasDuplicates(arrayForGivenMethod);
    System.out.println("Step 1: Are there duplicates, using the given method?\n "+containsDuplicates);       
    
    int[] arrayForEfficiency = arrayCopy(n, randomizedArray);
    boolean duplicateRevised = hasDuplicatesRevised(arrayForEfficiency);                            //Calls method 3 for more effient way of doing it
    System.out.println("\n\nStep 2: Are there duplicates, EVEN after making the given code more efficient?\n "+duplicateRevised);
    
    int[] bubbleSortArray = arrayCopy(n, randomizedArray);
    boolean duplicatesInBubbleSort = sortWithBubbleSort(bubbleSortArray);                        //sends array to be sorted via Bubble Sort and identifies if duplicate elements exist
    System.out.println("\n\nStep 3: Are there duplicates after bubble sort?\n "+duplicatesInBubbleSort);
    
    int[] mergeSortArray = arrayCopy(n, randomizedArray);
    int[] mergedArray = sortWithMergeSort(mergeSortArray);                          //sort with Merge Sort; do duplicates exist?
    printMergeSort(mergedArray);
    
    int[] unsortedArray = arrayCopy(n, randomizedArray);
    boolean duplicatesInUnsorted = singlePassDuplicates(unsortedArray, m);
    System.out.println("\n\nStep 5: Are duplicates found while running a single pass?\n "+duplicatesInUnsorted);
  }
  
//METHOD A: RECEIVES 2 INTERGERS N AND M. BUILDS & RETURNS AN ARRAY OF OF LENGTH N AND CONTAINS RANDOM INTEGERS IN THE 0 ---> M-1 RANGE
  public static int[] buildArray(int n, int m){
    int[] randomizedArray = new int[n];                     //builds an array of size n
    for(int i=0; i<randomizedArray.length; i++)
      randomizedArray[i] = ((int)(Math.random()*(m-1)));    //assigns a value of between (0, m-1) to the element
    
    System.out.println("The elements of the random array are:");
 
    for(int i=0; i<randomizedArray.length; i++){
      randomizedArray[i] = randomizedArray[i];    //builds a series of elements into an array
      System.out.print(randomizedArray[i]+" ");
    }
    System.out.println();
    return randomizedArray;
  }
  
//METHOD B: ARRAYCOPY
  public static int[] arrayCopy(int n, int[] randomizedArray){
    int[] randomArrayCopy = new int [randomizedArray.length];
    for(int i=0; i<randomArrayCopy.length; i++){  //create an exact copy of the array to aid with calling fresh, unsorted, array copies
      randomArrayCopy[i] = randomizedArray[i];
    }
    return randomArrayCopy;
  }
  
//METHOD 1: COMPARE EVERY ELEMENT IN THE ARRAY WITH EVERY OTHER ELEMENT USING A GIVEN NESTED LOOP
  public static boolean hasDuplicates(int[] randomizedArray){
    if(randomizedArray.length <= 1)                    //base case: only one element
      return false;
    
    boolean duplicates = false;       //given method to analyze
    for(int i=0;i<randomizedArray.length;i++){
      for(int j=0;j<randomizedArray.length;j++){
        if(randomizedArray[i]==randomizedArray[j] && i!=j)
          duplicates = true;
      }
    }
    return duplicates;
  }
  
//METHOD 2: IMPROVE THE CODE
  public static boolean hasDuplicatesRevised(int[] randomizedArray){    
    if(randomizedArray.length <= 1)                        //base case: not enough duplicates
      return false;
    
    boolean duplicates = false;
    for(int i=1;i<randomizedArray.length; i++){
      if(randomizedArray[i] == randomizedArray[i-1]){                //start at position 1 and compare to the previous position
        duplicates = true;                          //traverse one-by-one for duplicates
        break;                                      //break if a match is found
      }
    }
    return duplicates;
  }
  
//METHOD 3: BUBBLE SORT
  public static boolean sortWithBubbleSort(int[] randomizedArray){
    if(randomizedArray.length<=1)
      return true;                                  //base case: sorted by default
    
    boolean isSorted = false;                       //assume nothing is sorted
    int tempElement;                                //to hold a temp value if we need to swap elements
    
    while(isSorted == false){
      isSorted = true;                              //resets the trigger
      for(int i=0; i<randomizedArray.length-1; i++){
        if(randomizedArray[i] > randomizedArray[i+1]){
          tempElement=randomizedArray[i];   //use the temp element as a 3-way handshake to move elements around
          randomizedArray[i]=randomizedArray[i+1];
          randomizedArray[i+1] = tempElement;
          isSorted = false;                         //triggers at any point in the array
        }
      }
    }
    boolean duplicatesExist = false;         //assume no duplicates exist       
    int position=0;
    while(position<randomizedArray.length-1){
      if(randomizedArray[position] == randomizedArray[position+1]){
        duplicatesExist=true;                      //triggers at any point in traversal
      }
      position++;
    }
    
    System.out.println("Here are the elements after bubble sort:");
    for(int i=0; i<randomizedArray.length; i++){ //prove elements have been sorted
      System.out.print(randomizedArray[i]+" ");
    }
    return duplicatesExist;
  }
 
  
//METHOD 4a: MERGESORT (PRIMARY METHOD: SORTS INTS)
  public static int[] sortWithMergeSort(int[] randomizedArray){              
    
    if(randomizedArray.length <= 1)
      return randomizedArray;
    
      int[] leftHalf = new int [randomizedArray.length/2]; 
      int[] rightHalf = new int [randomizedArray.length-leftHalf.length];       //Mahdokht: "Explore 'arraycopy' via java.lang.system"
      
      System.arraycopy(randomizedArray, 0, leftHalf, 0, leftHalf.length);                    //copies leftHalf.length-elements of arrayCopy to the new leftHalf array 
      System.arraycopy(randomizedArray, leftHalf.length, rightHalf, 0, rightHalf.length);    //copies rightHalf.length-elements of arrayCopy to the leftHalf array
      
      sortWithMergeSort(leftHalf); 
      sortWithMergeSort(rightHalf);
    
    int[] merged = mergeTogether(leftHalf, rightHalf, randomizedArray);      //call the helper method
    
    return merged; 
  }
  
//METHOD 4b: MERGE EVERYTHING BACK TOGETHER
  public static int[] mergeTogether(int[] leftHalf, int[] rightHalf, int[] randomizedArray){ 
    
    int leftCounter=0; 
    int rightCounter=0;     //counters for left and right side
    int i=0;    //general counter

    while(leftCounter<leftHalf.length && rightCounter<rightHalf.length){ //analyze the smaller arrays to construct the larger array
      if(leftHalf[leftCounter] < rightHalf[rightCounter]){ 
        randomizedArray[i] = leftHalf[leftCounter];
        leftCounter++; 
      } 
      else{ 
        randomizedArray[i] = rightHalf[rightCounter]; 
        rightCounter++;
      }
      i++;        //increment the position of the larger array as you move each element into place
    }
    
    return randomizedArray; 
  }
  
//METHOD 4c: PRINT MERGE SORT ELEMENTS & RETURN IF DUPLICATES EXIST
  public static void printMergeSort(int[] merged){
    if (merged.length <= 1) 
      System.out.println("\nStep 4: The elements are sorted; there is <= 1 elements.");

    //Do duplicates exist?
    boolean mergedDuplicates = false; 
    for(int i=1; i<merged.length; i++){ //Assuming duplicates are in order, start at 1 and compare backwards 
      if(merged[i] == merged[i-1]){
        mergedDuplicates = true; 
        break; 
      }
    }
    
    System.out.println("\nAre there duplicates after merge sort?");
    if(mergedDuplicates == true)          //Prove there are duplicates after merge sort
      System.out.println(mergedDuplicates);
        
    System.out.println("The elements in the merge sorted array are ");
    for(int i=6; i<merged.length; i++)         //Prove the elements are sorted
      System.out.print(merged[i]+" ");
  }
  
  
//METHOD 5: DETERMINE IF THERE ARE DUPLICATES BY PERFORMING A SINGLE PASS
  public static boolean singlePassDuplicates(int[] randomizedArray, int m){
    if(randomizedArray.length <= 1)
      return true;
    
    boolean duplicates = false;                 //assume no duplicates exist
    boolean[] boolArray = new boolean[m];       //create boolean array of size m
    for(int c=0; c<boolArray.length; c++){
      boolArray[c]=false;                       //set all the booleans to false (by default)
    }
    int c=0;
    
    for(int i=0; i<randomizedArray.length; i++){
      c=randomizedArray[i];
      System.out.print(c+ " ");
      
      if(boolArray[c] == false)             //Set the numbers (position [c+1]) to true as you scan them
        boolArray[c] = true;
      else{
        duplicates = true;                    //If you come across a number again, it will recognize it as a duplicate and trigger
      }
    }
    
    System.out.println();
    for(int d=0; d<boolArray.length; d++){  //Prove that the boolean array[x] is triggered correctly
      System.out.print(boolArray[d]+" ");
    }
    return duplicates;
  } 
}