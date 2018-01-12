/*
 * By: Matthew S Montoya
 * Purpose: To practice constructing and implementing B-Trees with loops & recursion  
 * Last Modified: 11 January 2018
 * Note: This is NOT the runner file
 */
import java.lang.*;

public class BTreeNode{
  private int t;         // BTree parameter, each node has at least t-1 and at most 2t-1 keys
  public int n;          // Actual number of keys on the node
  public boolean isLeaf; // Boolean indicator 
  public String[] key;      // Keys stored in the node. They are sorted ion ascending order
  public BTreeNode[] c;  // Children of node. Keys in c[i] are less than key[i] (if it exists) and greater than key[i+1] if it exists
  
  public  BTreeNode(int t){  // Build empty node
    this.t = t;                   
    isLeaf = true;
    key = new String[2*t-1];   // Array sizes are set to maximum possible value
    c = new BTreeNode[2*t];
    n=0;                   // Number of elements is zero, since node is empty
  }
  
  public boolean isFull(){
    return n==(2*t-1);
  }
  
  public void insert(String newKey){
    //System.out.println("inserting " + newKey); // Debugging code
    int i=n-1;
    if (isLeaf){
      while ((i>=0) && (newKey.compareToIgnoreCase(key[i]) < 0)){ // Shift key greater than newKey to left
        key[i+1] = key[i];             
        i--;
      }
      n++;                    // Update number of keys in node
      key[i+1]=newKey;        // Insert new key
    }
    else{
      while ((i>=0)&& (newKey.compareToIgnoreCase(key[i]) < 0)) {
        i--;
      }
      int insertChild = i+1;  // Subtree where new key must be inserted
      if (c[insertChild].isFull()){
        n++;
        c[n]=c[n-1];
        for(int j = n-1;j>insertChild;j--){
          c[j] =c[j-1];
          key[j] = key[j-1];
        }
        key[insertChild]= c[insertChild].key[t-1];
        c[insertChild].n = t-1;
        
        BTreeNode newNode = new BTreeNode(t);
        for(int k=0;k<t-1;k++){
          newNode.c[k] = c[insertChild].c[k+t];
          newNode.key[k] = c[insertChild].key[k+t];
        }
        
        newNode.c[t-1] = c[insertChild].c[2*t-1];
        newNode.n=t-1;
        newNode.isLeaf = c[insertChild].isLeaf;
        c[insertChild+1]=newNode;
        
        if (newKey.compareToIgnoreCase(key[insertChild]) < 0){
          c[insertChild].insert(newKey);     }
        else{
          c[insertChild+1].insert(newKey);    }
      }
      else
        c[insertChild].insert(newKey);  //No need to split node
    }
  }
  
//Prints all keys in the tree in ascending order
  public void print(){
    if (isLeaf){
      for(int i =0; i<n;i++)
        System.out.print(key[i]+" ");
      System.out.println();
    }
    else{
      for(int i =0; i<n;i++){
        c[i].print();
        System.out.print(key[i]+" ");
      }
      c[n].print();
    }
  }
  
  public void printNodes(){
    //Prints all keys in the tree, node by node, using preorder
    //It also prints the indicator of whether a node is a leaf
    //Used mostly for debugging purposes
    for(int i =0; i<n;i++)
      System.out.println(key[i]+" ");
    System.out.println(isLeaf);
    if (!isLeaf){
      for(int i =0; i<=n;i++){
        c[i].printNodes();
      }
    }
  }
  
  /**
   * ADDED Method 4
   * 
   * This method will print the contents of all the nodes at 
   * depth d or less.
   * This method will be of type NON-RETURN.
   */ 
  public void printDepthNodes(BTreeNode T, int depth){
	  if(depth==0){
		  System.out.println();
		  for(int i=0; i<= T.n; i++)
			  System.out.print(T.key[i]+", ");
		  }
	  else if(T.isLeaf)
		  System.out.println("No keys in depth "+depth);
	  else{
		  for(int i=0; i<T.n; i++){
			  System.out.println(T.key[i]+", ");
			  printDepthNodes(T.c[0], depth-1);
		  }  
	  }
  }
  
  
  /**
   * ADDED Method 1
   * 
   * This method will search the tree using sequential search
   * This method will be of return type BTreeNode, returning
   * the node at which the string is found.
   */ 
  public BTreeNode sequentialSearch(BTreeNode T, String k){
	    if(T.isLeaf){
	        for(int i=0; i<T.n; i++){
	          if(T.key[n].equalsIgnoreCase(k))
	            return T;
	        }
	        return null;
	      }
	      for(int j=0; j<T.n; j++){
	        if(T.key[j].equalsIgnoreCase(k))
	          return T;
	        if(T.key[j].compareToIgnoreCase(k) < 0)
	          return sequentialSearch(T.c[j], k);
	      }
	      return (sequentialSearch(T.c[T.n], k));    
  }
  
  /**
   * ADDED Methods
   * 
   * These methods will search the tree using binary search
   * This method will be of type type BTreeNode, returning
   * the node at which the string is found.
   */ 
  public BTreeNode binarySearch(BTreeNode T, String k){
	    BTreeNode found = binaryContains(T, k, 0, T.n); //last two: int starting position, int ending position
	    return found;
  }
  public BTreeNode binaryContains(BTreeNode T, String k, int start, int end){
	    if(T.isLeaf){
	      if(start<end){
	        int middle = (end/2);
	        if(k.compareToIgnoreCase(T.key[middle]) < 0)   //if the key is before the middle
	          return binaryContains(T, k, start, middle);    
	        if(k.compareToIgnoreCase(T.key[middle]) > 0)   //if the key is after the middle
	          return binaryContains(T, k, middle+1, end);
	        if(k.compareToIgnoreCase(T.key[middle]) == 0)   //if the key is after the middle
		          return T;
	      }
	      
	      if(start == end){
	        if (k.compareToIgnoreCase(T.key[start]) == 0)                          //if the key is the middle
	          return T; 
	    
	    }
	    
	    if(!T.isLeaf){
	      for(int i=0; i<T.n; i++){
	        if(k.compareToIgnoreCase(T.key[i]) < 0)
	          return binaryContains(T.c[i], k, 0, T.n);
	        return binaryContains(T.c[T.n], k, 0, T.n);  
	      }
	    }
	  }
	    return null;
  }
  
  /**
   * ADDED Method
   * 
   * This method will search the tree and determine
   * if what we're looking for is contained in the 
   * B-Tree.
   * This method will be of a boolean return type.
   */ 
  public boolean contains(BTreeNode root, String str){
	    if(root.isLeaf){
	        for(int i=0; i<root.n; i++){
	          if(root.key[i].equalsIgnoreCase(str))
	            return true;
	        }
	        return false;
	      }
	      for(int j=0; j<root.n; j++){
	        if(root.key[j].equalsIgnoreCase(str))
	          return true;
	        if(root.key[j].compareToIgnoreCase(str) < 0)
	          return contains(root.c[j], str);
	      }
	      return (contains(root.c[root.n], str));
	      }
  
  /**
   * ADDED Method
   * 
   * This method will search the tree and find the
   * word with the most amount of anagrams.
   * This method will be of a String return type.
   */ 
  public String mostAnagrams(BTreeNode root){
	  if(root.isLeaf){
		  
	  }
	  
	  
	  return "null";
  }
}
