/*
 * By: Matthew S Montoya
 * Purpose: To practice recursive and iterative Mergesort algorithms using lists and stacks, respectively  
 * Last Modified: 10 January 2018
 */

import java.util.*;
public class Lab03{
  public static void main(String[] args){
    
    userInput();																		//call the method displaying the message to the user
  }
  
  /**
   * Method 1
   * 
   * This method receives no intergers and prompts the user to specify the length
   * of the list, as well as the range which its integer elements can be.
   * This method will be of type NON-RETURN.
   */ 
  public static void userInput(){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter an integer (list length):"); 								//int n -- will be the size of the list
    int n = input.nextInt();
    System.out.println("Enter another integer (the pool of list values):");				 //int m -- will randomly generate a number between 0 and m-1
    int m = input.nextInt();
    System.out.println("The items in the randomly generated list are:");
    iNode constructedList = buildList(n, m);//method 3
    
    printList(constructedList);															//method 2; will print the contents of the LinkedList
    iNode mergeSortedList = mergeSort(constructedList);//method 4
    
    System.out.println("\nThe contents of the sorted list are:");
    printList(mergeSortedList);															//method 2; will print the contents of the merge-sorted LinkedList
    
    System.out.println("\nThe unsorted stack is:");
    printList(constructedList);															//method 2; will inform user this is the unsorted stack
    
    //Stack stack = reverseLinkedList(constructedList);//method 6; will reverse the LinkedList to use only one stack
  }
  
  /**
   * Method 2
   *    printList();
   * 
   * This helper method prints the contents of a LinkedList, to show the user the
   * program is executing properly.
   * This method will be a NON-RETURN method.
   */
  public static void printList(iNode list){
    System.out.println();
    while(list != null){
      System.out.print(list.item+" ");
      list=list.next;
    }
  }
  
  /**
   * OBJECTIVE 1
   *    Method 3
   * 
   * This method receives two integers, n and m, representing the
   * length of the list and the the range (0 to m-1) which the node.item can be.
   * This method WILL RETURN the constructed list.
   */ 
  public static iNode buildList(int n, int m){
    iNode x = new iNode(0, null);
    for(int i=0; i<n; i++){
      x= new iNode((int)(Math.random()*(m-1)),x);										//populates the list with integers from 0 to (m-1)
    }
    return x;
  }
  
  /**
   * OBJECTIVE 2
   *    Method 4
   * 
   * This method receives the constructed list that was created in method 3, and 
   * split the list into indivudal elements for sorting with mergeSort.
   * This method WILL RETURN a list sorted via merge sort.
   */ 
  
  public static iNode mergeSort(iNode constructedlist){
    iNode temp = constructedlist;
    if(constructedlist==null || constructedlist.next == null)
      return constructedlist;
    
    iNode leftHalf = null;
    iNode rightHalf = null;
    int nodeLength = 0;
    
    while(constructedlist != null){ 								//get length of list
      nodeLength++;
      constructedlist = constructedlist.next;
    }
    
    for(int i=0; i<nodeLength; i++){								//split into evens and odds
      if(i%2==0)
        leftHalf = new iNode(temp.item, leftHalf);
      if(i%2==1)
        rightHalf = new iNode(temp.item, rightHalf);
      
      temp = temp.next;
    }
    
    leftHalf = mergeSort(leftHalf);
    rightHalf = mergeSort(rightHalf);
    
    iNode mergedList = merge(leftHalf, rightHalf);	//method 5; merge everything back together
    return mergedList;
  }
  
  /**
   * OBJECTIVE 2
   *    Method 5
   * 
   * This method receives two iNodes, leftHalf and rightHalf of the lists, 
   * which will build a new list of sorted items, sorted by Merge Sort
   * This method WILL RETURN the sorted list.
   */ 
  public static iNode merge(iNode leftHalf, iNode rightHalf){
    iNode mergeSorted = null;
    while(leftHalf != null && rightHalf != null){
      if(leftHalf.item <= rightHalf.item){
        mergeSorted = new iNode(leftHalf.item, mergeSorted);
        leftHalf = leftHalf.next;
      }
      else{
        mergeSorted = new iNode(rightHalf.item, mergeSorted);
        rightHalf = rightHalf.next;
      }
    }
    
    //Lesson from lab 1: if one of the halves is empty, daisy-chain the rest of the elements from the other half into the last part of the list
    while(leftHalf != null){//while the rightHalf is empty
      mergeSorted = new iNode(leftHalf.item, mergeSorted);//place the rest of the leftHalf elements into the list
      leftHalf = leftHalf.next;
    }
    
    while(rightHalf != null){//while the leftHalf is empty
      mergeSorted = new iNode(rightHalf.item, mergeSorted);//add the rest of the rightHalf elements to the list
      rightHalf = rightHalf.next;
    }
    //printList(sorted);//prove to user that the list is sorted
    
    //okay just kidding, its backwards switch the list around
    iNode returnMergeSorted = null;
    while(mergeSorted != null){
      returnMergeSorted = new iNode (mergeSorted.item, returnMergeSorted);
      mergeSorted = mergeSorted.next;
    }
    return returnMergeSorted;
  }
  
  
  /**
   * OBJECTIVE 3
   *    Method 6
   * 
   * This method receives the origional constructed LinkedList, in which
   * it will reverse it, so that the values can be pushed into a stack.
   * This WILL RETURN 
   * 
   * 
   public static void reverseLinkedList(iNode constructedlist){
   iNode reversed = null;   //FILO: So switch the link list around
   int counter = 0;         //Apply the same logic as the recursive method
   while(constructedlist != null){
   reversed = new iNode (constructedlist.item, reversed);
   counter++;
   constructedlist = constructedlist.next;
   }
   System.out.println("The reversed list is:\n");
   printList(reversed);
   return merge(reversed, counter);
   }
   
   public static Stack merge(iNode reversed, int counter){
   Stack stack = new Stack();
   while(reversed != null){
   stack1.push(reversed.item);
   reversed = reversed.next;
   }
   
   
   
   }
   
   * OBJECTIVE 3
   *    Method 7
   * 
   * This method receives the original constructed LinkedList, in which
   * it will apply iterative sorting (the first half of mergeSort) to stacks.
   * This WILL RETURN a sorted iNode stack
   * 
   public static iNode stackMergeSort(iNode list, int n){   //Apply the code Dr. Fuentes provided and make minor edits
   Stack<MergesortRecord> stack = new Stack();   //use stacks
   MergesortRecord msR = new MergesortRecord(false, list);
   stack.push(msR);
   while(!stack.empty()){
   msR=stack.pop();   //Keep poping elements and create new stacks
   if(msR.getSorted()){
   list = mergeStacks(list, n);
   
   * OBJECTIVE 3
   *    Method 8
   * 
   * This method receives the stacks created in method 7, and will return
   * the mergesorted stacks to method 7, which will then return it to the main method
   * This WILL RETURN a sorted iNode stack
   * 
   public static iNode mergeStacks(iNode list, int n){
   iNode temp = list;
   for(int i =0; i<(n/2-1); i++){
   temp=temp.next;
   }
   iNode list2 = recursiveMerge(temp.next, n-(n/2));//apply the same logic as mergesort in the lists
   temp.next=null;   //assign the final value to null
   }
   */
}
