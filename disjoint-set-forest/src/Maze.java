/*************************************************************************
Code to generate and draw a maze to be used as starting point for lab 6
Some code borrowed from http://introcs.cs.princeton.edu and 
Programmed by Olac Fuentes for use by Matthew Montoya
Last modified 17 January 2018
**************************************************************************/
import java.util.*;
public class Maze {
    private int N;                  // dimension of maze
    private boolean[][] v_wall;     
    private boolean[][] h_wall;
  
    public Maze(int N) {
        this.N = N;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        init();
    }

    private void init() {
    // initialze all walls as present
    // Vertical wall v_wall[x][y] separates cells (x + N*y) and (x + N*y + 1)
    // Horizontal wall h_wall[x][y] separates cells (x + N*y) and (x + N*(y + 1))

        h_wall = new boolean[N][N-1];        
        v_wall = new boolean[N-1][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N-1; j++)
                v_wall[j][i] = h_wall[i][j] = true;
    }
 
    public void remove_wall(int x, int y, char d){
    // Notice x is the column and y is the row (opposite of usual indexing)
      if ((d=='u') && (y<N-1)) //Do not remove walls at the top
 	      h_wall[x][y] = false;
	   if ((d=='r') && (x<N-1)) //Do not remove rightmost walls
	      v_wall[x][y] = false;
	 }
    
   public void printCellNumbers() { 
      StdDraw.setPenColor(StdDraw.BLUE);
      for (int x = 0; x < N; x++){
         for (int y = 0; y < N; y++){
            StdDraw.text(x+.5,y+.5,Integer.toString(x+N*y));    
         }
      }
      StdDraw.show(0);
   } 
 
    
   public void draw() {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.filledCircle(.5, .5, 0.375);
      StdDraw.setPenColor(StdDraw.GREEN);
      StdDraw.filledCircle(N-0.5, N-0.5, 0.375);
        
      //Draw Periphery. 
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.line(0, 0, N, 0); 
      StdDraw.line(N, 0, N, N); 
      StdDraw.line(N, N, 0, N);          
      StdDraw.line(0, N, 0, 0); 
      
      // Draw remaining walls
      for (int x = 0; x < N; x++)
         for (int y = 0; y < N-1; y++)
            if (h_wall[x][y]) StdDraw.line(x, y+1, x+1, y+1);      
      for (int x = 0; x < N-1; x++)
         for (int y = 0; y < N; y++)
            if (v_wall[x][y]) StdDraw.line(x+1, y, x+1, y+1);
      StdDraw.show(0);
    }
   
   
   public int find(int[] S, int a){
	   if(S[a] < 0)
		   return a;
	   else //S[a] = find(S, S[a]);
	   return S[a];
   }
   

public void union(int [] S, int i, int j){
	   int ri = find(S, i);
	   int rj = find(S, j);
	   if(ri!=rj)
		   S[rj] = ri;  
	   else
		   S[ri] = rj;

   }
   
   
   public char direction(int d){
	   if(d==0)
		   return 'u';
	   else
		   return 'r';   
   }
   
   public void print(int[] S){
	      for(int i=0;i<S.length;i++)  
	         System.out.print(S[i]+" ");
	      System.out.println();
	   }   
   
   public int identify(int[] S){
	   int count =0;
	   for(int i=0; i<S.length; i++){
		   if(S[i] == -1)
			   count++;
	   }
	   return count;
	 
		   
   }
   
   
   /**
    * ADDED CODE FOR LAB 7
    */
   public boolean check_wall(int x, int y, char d){
	    // Vertical wall v_wall[x][y] separates cells (x + N*y) and (x + N*y + 1)
	    // Horizontal wall h_wall[x][y] separates cells (x + N*y) and (x + N*(y + 1)) upper

	    // Notice x is the column and y is the row (opposite of usual indexing)
	      if ((d=='u') && (y<N-1)) //Do not remove walls at the top
	 	      if((h_wall[x][y]) == false)
	 	    	  return true;;
		   if ((d=='r') && (x<N-1)) //Do not remove rightmost walls
		      if(v_wall[x][y] == false)
		    	  return true;
		   return false;
 	 }
   
	/** 
    public static void main(String[] args) {
        int N = 20; // Maze will have N rows and N columns
        Maze maze = new Maze(N);
        StdDraw.show(0);
		  //Remove some walls
        maze.remove_wall(15,10,'U'); 
        maze.remove_wall(5,12,'R');  
        maze.remove_wall(1,4,'R');
        maze.remove_wall(10,12,'U');  
        maze.remove_wall(0,0,'U');  
        maze.remove_wall(0,1,'R');  
        maze.remove_wall(12,10,'U');  
        maze.draw();

        //maze.printCellNumbers();
    }
    */
}
