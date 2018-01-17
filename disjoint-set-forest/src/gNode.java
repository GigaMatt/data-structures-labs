/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing a maze using Disjoint Set Forests
 * Last Modified: 17 January 2018
 * Note: This is NOT the runner file
 */

public class gNode {
	
	public int item;
	public gNode dest;
	
	public gNode(){
		item = 0;
		dest = null;
	}
	
	public gNode(int i, gNode n){
		item = i;
		dest = n;
	}

}
