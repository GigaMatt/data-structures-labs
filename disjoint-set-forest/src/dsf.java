/*************************************************************************
 ** Basic Disjoint set forest                                       **
 ** Programmed by Olac Fuentes for use by Matthew Montoya           **
 ** Last modified  11 January 2018                                  **
 **************************************************************************/

//import java.io.*;
public class dsf{	

	private int[] S; 

	public dsf(int n){ //Initialize disjoint set forest with n elements. Each element is a root
		S = new int[n];
		for(int i=0;i<n;i++)
			S[i] = -1;
	}   

	public dsf(int [] A){ //Initialize disjoint set forest copying its elements from an array A
		S = new int[A.length];
		for(int i=0;i<S.length;i++)
			S[i] = A[i];
	}   

	public int find(int i){ // Returns the root of the set element i belongs to
		if(S[i]<0)
			return i;
		return find(S[i]);   
	}    

	public int findAndCompress(int i){ // find with path compression
		if(S[i]<0)
			return i;
		return S[i]=findAndCompress(S[i]);
	} 

	public void union(int i, int j){
		int ri = find(i);
		int rj = find(j);
		if(ri!=rj)
			S[rj] = ri;    
	}  

	public boolean inSameSet(int i, int j){ 
		return find(i) == find(j);   
	}      

	public static int checkSets(int [] S){
		int count =0;
		for(int i=0; i<S.length; i++){
			if(S[i] == -1)
				count++;
		}
		return count;
	}

	public void unionByHeight(int i, int j){
		int ri = find(i);
		int rj = find(j);
		if (ri==rj)return;
		if(S[ri]>S[rj])
			S[ri]=rj;
		else
			if(S[ri]<S[rj])
				S[rj]=ri;
			else{
				S[rj]=ri;
				S[ri]--;
			}
	}


	public void unionBySize(int i, int j){
		int ri = find(i);
		int rj = find(j);
		if (ri==rj)return;
		int s;
		if(S[ri]>S[rj]){
			s=S[ri];
			S[ri]=rj;
			S[rj]+=s;
		}
		else{
			s = S[rj];
			S[rj]=ri;
			S[ri]+=s;
		}
	} 


	public void print(){
		for(int i=0;i<S.length;i++)  
			System.out.print(S[i]+" ");
		System.out.println();
	}   
}
