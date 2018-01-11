/*
 * By: Matthew S Montoya
 * Purpose: To practice recursive and iterative Mergesort algorithms using lists and stacks, respectively  
 * Last Modified: 10 January 2018
 */

public class iNode{ 
   public int item;
   public iNode next;
 
   public iNode(int i, iNode n){
      item = i;
      next = n;
   }
 
   public iNode(int i){
    item = i;
      next = null;
   }
}
