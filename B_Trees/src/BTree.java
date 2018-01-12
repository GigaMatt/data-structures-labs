/*
 * By: Matthew S Montoya
 * Purpose: To practice constructing and implementing B-Trees with loops & recursion
 * Last Modified: 11 January 2018
 * Note: This is NOT the runner file
 */
public class BTree{
  private BTreeNode root;
  private int t; //2t is the maximum number of childen a node can have
  private int height;
  
  public BTree(int t){
    root = new BTreeNode(t);
    this.t = t;
    height = 0;
  }
  
  public void printHeight(){
    System.out.println("Tree height is "+height);
  }
  
  public void insert(String newKey){
    if (root.isFull()){//Split root;
      split();
      height++;
    }
    root.insert(newKey);
  }
  
  public void print(){
    // Wrapper for node print method
    root.print();
  }
  
  public void printNodes(){
    // Wrapper for node print method
    root.printNodes();
  }
  
  public void split(){
    // Splits the root into three nodes.
    // The median element becomes the only element in the root
    // The left subtree contains the elements that are less than the median
    // The right subtree contains the elements that are larger than the median
    // The height of the tree is increased by one
    
    // System.out.println("Before splitting root");
    // root.printNodes(); // Code used for debugging
    BTreeNode leftChild = new BTreeNode(t);
    BTreeNode rightChild = new BTreeNode(t);
    leftChild.isLeaf = root.isLeaf;
    rightChild.isLeaf = root.isLeaf;
    leftChild.n = t-1;
    rightChild.n = t-1;
    int median = t-1;
    for (int i = 0;i<t-1;i++){
      leftChild.c[i] = root.c[i];
      leftChild.key[i] = root.key[i];
    }
    leftChild.c[median]= root.c[median];
    for (int i = median+1;i<root.n;i++){
      rightChild.c[i-median-1] = root.c[i];
      rightChild.key[i-median-1] = root.key[i];
    }
    rightChild.c[median]=root.c[root.n];
    root.key[0]=root.key[median];
    root.n = 1;
    root.c[0]=leftChild;
    root.c[1]=rightChild;
    root.isLeaf = false;
    // System.out.println("After splitting root");
    // root.printNodes();
  }
  
  /**
   * ADDED Method .contains
   * 
   * This method will determine if a string is detected in a key
   * This method will be of return type String
   */
  public boolean contains(BTree root, String str){
	  return root.contains(root, str);
  }	  
  
  public String mostAnagrams(BTree root){
	  return root.mostAnagrams(root);
  }
}
