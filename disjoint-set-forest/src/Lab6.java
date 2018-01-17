/*
 * By: Matthew S Montoya
 * Purpose: To practice implementing a maze using Disjoint Set Forests
 * Last Modified: 17 January 2018
 * Note: This is the runner file
 */
import java.io.IOException;
import java.util.*;

public class Lab6{
	public static void main(String[] args)throws IOException{

		userSelection();
	}


	/**
	 * METHOD 0: USER SELECTION
	 * This method prompts a user to select which one of the four 
	 * different options they would like to run.
	 * This is of type non-return. 
	 */
	public static void userSelection(){
		int selection;
		Scanner input = new Scanner(System.in);
		System.out.println("Which program would you like to run?");
		selection=input.nextInt();

		if(selection == 1)
			mazeUnion();
		if(selection == 2)
			unionPathCompression();
		if(selection == 3)
			mazeHeight();
		if(selection == 4)
			mazeSize();
		if(selection == 5)
			sizePathCompression();
	}


	/**
	 * METHOD 1: Standard Union
	 * This method runs the standard union to solve the maze.
	 * This is of type non-return. 
	 */
	public static void mazeUnion(){

		int n = 10;	

		dsf S = new dsf(n*n);
		Maze one = new Maze(n);

		Random random;
		int sets = n*n;	//number of sets in the DSF
		int randomNumber;
		int randomDirection;
		int row;
		int col;

		char upperRight; 	//bottom or right

		S.print();

		while(sets > 1){
			random = new Random();
			randomNumber = random.nextInt((n*n) - 1);
			System.out.println("RANDOM NUMBER: "+randomNumber);
			row = randomNumber /n;	//SWITCHED
			col = randomNumber %n;	//SWITCHED
			randomDirection = random.nextInt(2);
			String direct;
			if(randomDirection == 0)
				direct = "upper";
			else
				direct = "right";
			System.out.println("RANDOM DIRECTI0N: "+direct);
			upperRight = one.direction(randomDirection);

			if(upperRight == 'u'){
				if((randomNumber) < ((n*n)-n)){
					System.out.println("Sets: "+sets);
					if(S.find(randomNumber+n) != S.find(randomNumber)){
						S.union(randomNumber+n, randomNumber);
						one.remove_wall(col, row, 'u');
						S.print();
						sets--;
					}
				}
			}

			if(upperRight == 'r'){
				if(((randomNumber)%(n*n)) != n-1){
					if(S.findAndCompress(randomNumber) != S.findAndCompress(randomNumber+1)){
						S.union(randomNumber, randomNumber+1);
						one.remove_wall(col, row, 'r');
						//S.print();
						sets--;
					}
				}
			}
		}

		StdDraw.show(0);
		one.draw();
		one.printCellNumbers();
		
		buildAdjacencyList(one, n);
		userSelection_SolveMaze(n);

	}


	/**
	 * METHOD 2: Union-By-Compression
	 * This method runs  union-by-compression to solve the maze.
	 * This is of type non-return. 
	 */
	public static void unionPathCompression(){
		int n = 10;	

		dsf S = new dsf(n*n);
		Maze two = new Maze(n);

		Random random;
		int sets = n*n;	//number of sets in the DSF
		int randomNumber;
		int randomDirection;
		int row;
		int col;

		char upperRight; 	//bottom or right

		S.print();

		while(sets > 1){
			random = new Random();
			randomNumber = random.nextInt((n*n) - 1);
			//System.out.println("RANDOM NUMBER: "+randomNumber);
			row = randomNumber /n;	//SWITCHED
			col = randomNumber %n;	//SWITCHED
			randomDirection = random.nextInt(2);
			String direct;
			if(randomDirection == 0)
				direct = "upper";
			else
				direct = "right";
			System.out.println("RANDOM DIRECTI0N: "+direct);
			upperRight = two.direction(randomDirection);

			if(upperRight == 'u'){
				if((randomNumber) < ((n*n)-n)){
					System.out.println("Sets: "+sets);
					if(S.findAndCompress(randomNumber+n) != S.findAndCompress(randomNumber)){
						S.union(randomNumber+n, randomNumber);
						two.remove_wall(col, row, 'u');
						S.print();
						sets--;
					}
				}
			}

			if(upperRight == 'r'){
				if(((randomNumber)%(n*n)) != n-1){
					if(S.findAndCompress(randomNumber) != S.findAndCompress(randomNumber+1)){
						S.union(randomNumber, randomNumber+1);
						two.remove_wall(col, row, 'r');
						S.print();
						sets--;
					}
				}
			}
		}

		buildAdjacencyList(two, n);
		userSelection_SolveMaze(n);


		StdDraw.show(0);
		two.draw();
		two.printCellNumbers();
	}


	/**
	 * METHOD 3: Union-By-Height
	 * This method runs  union-by-height to solve the maze.
	 * This is of type non-return. 
	 */
	public static void mazeHeight(){
		int n = 10;	

		dsf S = new dsf(n*n);
		Maze three = new Maze(n);

		Random random;
		int sets = n*n;	//number of sets in the DSF
		int randomNumber;
		int randomDirection;
		int row;
		int col;

		char upperRight; 	//bottom or right

		S.print();

		while(sets > 1){
			random = new Random();
			randomNumber = random.nextInt((n*n) - 1);
			//System.out.println("RANDOM NUMBER: "+randomNumber);
			row = randomNumber /n;	//SWITCHED
			col = randomNumber %n;	//SWITCHED
			randomDirection = random.nextInt(2);
			String direct;
			if(randomDirection == 0)
				direct = "upper";
			else
				direct = "right";
			//System.out.println("RANDOM DIRECTI0N: "+direct);
			upperRight = three.direction(randomDirection);

			if(upperRight == 'u'){
				if((randomNumber) < ((n*n)-n)){
					System.out.println("Sets: "+sets);
					if(S.find(randomNumber) != S.find(randomNumber + n)){
						S.unionByHeight(randomNumber, randomNumber+n);
						three.remove_wall(col, row, 'u');
						S.print();
						sets--;
					}
				}
			}

			if(upperRight == 'r'){
				if(((randomNumber)%(n*n)) != n-1){
					if(S.find(randomNumber) != S.find(randomNumber+1)){
						S.unionByHeight(randomNumber, randomNumber+1);
						three.remove_wall(col, row, 'r');
						S.print();
						sets--;
					}
				}
			}
		}
		buildAdjacencyList(three, n);
		userSelection_SolveMaze(n);


		StdDraw.show(0);
		three.draw();
		three.printCellNumbers();	
	}


	/**
	 * METHOD 4: Union-By-Size
	 * This method runs  union-by-size to solve the maze.
	 * This is of type non-return. 
	 */
	public static void mazeSize(){
		int n = 10;	

		dsf S = new dsf(n*n);
		Maze four = new Maze(n);

		Random random;
		int sets = n*n;	//number of sets in the DSF
		int randomNumber;
		int randomDirection;
		int row;
		int col;

		char upperRight; 	//bottom or right

		S.print();

		while(sets > 1){
			random = new Random();
			randomNumber = random.nextInt((n*n) - 1);
			//System.out.println("RANDOM NUMBER: "+randomNumber);
			row = randomNumber /n;	//SWITCHED
			col = randomNumber %n;	//SWITCHED
			randomDirection = random.nextInt(2);
			String direct = null;
			if(randomDirection == 0)
				direct = "upper";
			if(randomDirection == 1)
				direct = "right";
			//System.out.println("RANDOM DIRECTI0N: "+direct);
			upperRight = four.direction(randomDirection);

			if(upperRight == 'u'){
				if((randomNumber) < ((n*n)-n)){
					//System.out.println("Sets: "+sets);
					if(S.find(randomNumber) != S.find(randomNumber + n)){
						S.unionBySize(randomNumber, randomNumber+n);
						four.remove_wall(col, row, 'u');
						//S.print();
						sets--;
					}
				}
			}

			if(upperRight == 'r'){
				if(((randomNumber)%(n*n)) != n-1){
					if(S.find(randomNumber) != S.find(randomNumber+1)){
						S.unionBySize(randomNumber, randomNumber+1);
						four.remove_wall(col, row, 'r');
						//S.print();
						sets--;
					}
				}
			}
		}

		buildAdjacencyList(four, n);
		userSelection_SolveMaze(n);


		StdDraw.show(0);
		four.draw();
		four.printCellNumbers();	
	}


	/**
	 * METHOD 5: Union-By-Size w/ Path Compression
	 * This method runs  union-by-size, using path
	 * compression to solve the maze.
	 * This is of type non-return. 
	 */
	public static void sizePathCompression(){
		int n = 10;	

		dsf S = new dsf(n*n);
		Maze five = new Maze(n);

		Random random;
		int sets = n*n;	//number of sets in the DSF
		int randomNumber;
		int randomDirection;
		int row;
		int col;

		char upperRight; 	//bottom or right

		S.print();

		while(sets > 1){
			random = new Random();
			randomNumber = random.nextInt((n*n) - 1);
			//System.out.println("RANDOM NUMBER: "+randomNumber);
			row = randomNumber /n;	//SWITCHED
			col = randomNumber %n;	//SWITCHED
			randomDirection = random.nextInt(2);
			String direct;
			if(randomDirection == 0)
				direct = "upper";
			else
				direct = "right";
			//System.out.println("RANDOM DIRECTI0N: "+direct);
			upperRight = five.direction(randomDirection);

			if(upperRight == 'u'){
				if((randomNumber) < ((n*n)-n)){
					System.out.println("Sets: "+sets);
					if(S.findAndCompress(randomNumber) != S.findAndCompress(randomNumber + n)){
						S.unionBySize(randomNumber+n, randomNumber);
						five.remove_wall(col, row, 'u');
						//S.print();
						sets--;
					}
				}
			}

			if(upperRight == 'r'){
				if(((randomNumber)%(n*n)) != n-1){
					if(S.findAndCompress(randomNumber) != S.findAndCompress(randomNumber+1)){
						S.unionBySize(randomNumber, randomNumber+1);
						five.remove_wall(col, row, 'r');
						S.print();
						sets--;
					}
				}
			}
		}

		buildAdjacencyList(five, n);
		userSelection_SolveMaze(n);

		StdDraw.show(0);
		five.draw();
		five.printCellNumbers();	
	}

	public static int checkSets(int[] S){
		int count =0;
		for(int i=0; i<S.length; i++){
			if(S[i] == -1)
				count++;
		}
		return count;
	}

	/*********************************************************************************************
	 * CODE ADDED FOR LAB 7	*
	 ********************************************************************************************/ 

	/**
	 * METHOD 6: USER SELECTION
	 * 
	 * This method will ask the user how they would like to
	 * solve the maze. This will be of type non-return.
	 */
	public static void userSelection_SolveMaze(int userInputN){

		int[] S = new int[(int) Math.pow(userInputN, 2)];
		gNode[] G = new gNode[S.length];

		boolean [] visited = new boolean[S.length];		//Initialize
		for(int i=0; i<visited.length;i++)
			visited[i] = false;

		int [] path = new int[S.length];		//Initialize 
		for(int i=0; i<path.length; i++)
			path[i]=-1;

		Scanner input = new Scanner(System.in);
		System.out.println("How would you like to solve the maze?\n"
				+ "1.\tBreadth-First Search\n"
				+ "2.\tDepth-First Search\n"
				+ "3.\tDepth-First Search with Recursion");
		
		int ur = input.nextInt();
		
		if(ur==1)	//Breadth-First Search		\///gNode && start @ position 0
			bfs(G, visited, path, 0);
		else if(ur==2)	//Depth-First Search
			dfs_Stack(G, visited, path, 0);
		else if(ur==3)	//Depth-First Search w/recursion	
			dfsRecursion(G, visited, path, 0);	
		else{
			System.out.println("Invalid option. Please enter the appropriate integer value");
			userSelection_SolveMaze(userInputN);
		}
		
		input.close();
	}

	/**
	 * METHOD 7: Breadth-First Search
	 * 
	 * This method solves the maze using breadth-first search,
	 * taking in the maze as a graph @param G and using @param s
	 * as it's origin point.
	 * 
	 * This will @return path []
	 */
	public static void bfs(gNode[] G, boolean[] visited, int[] path, int s){
		Queue<Integer> Q = new LinkedList<Integer>();

		Q.add(s);
		visited[s] = true;
		while(!Q.isEmpty()){
			s = Q.poll();
			for(gNode t=G[s]; t!= null; t=t.dest){
				System.out.println("BFS");
				if(!visited[t.item]){		//once an item has been visited, set it equal to true
					visited[t.item] = true;
					path[t.item] = s;		//change the int value of the path
					Q.add(t.item);
					System.out.println(t.item);
				}
				
			}
		}
		
	}

	/**
	 * METHOD 8: DEPTH-FIRST SEARCH USING A STACK
	 * This method solves the maze using breadth-first search,
	 * this time, using a stack. It will still take in the maze
	 * as a graph @param G and use @param s as it's origin point.
	 * 
	 */
	public static int[] dfs_Stack(gNode[] G, boolean[] visited, int[] path, int s){
		Stack <Integer> dfs = new Stack<Integer>();

		dfs.push(s);
		visited[s] = true;
		while(!dfs.isEmpty()){
			s = dfs.pop();
			for(gNode t=G[s]; t!= null; t=t.dest){
				if(!visited[t.item]){		//once an item has been visited, set it equal to true
					visited[t.item] = true;
					path[t.item] = s;		//change the int value of the path
					dfs.push(t.item);
				}
			}
		}
		return path;


	}

	/**
	 * METHOD 9: DEPTH-FIRST SEARCH WITH RECURSION
	 * This method solves the maze using depth-first search (w/recursion),
	 * taking in the maze as a graph @param G, and the arrays, @param visited,
	 * @param path and and using @param s as it's origin point.
	 * 
	 * This will @return 
	 */
	public static void dfsRecursion(gNode[] G, boolean[] visited, int[] path, int s){
		visited[s] =true;
		for(gNode t = G[s]; t!=null; t=t.dest){
			if(!visited[t.item]){
				path[t.item]=s;
				dfsRecursion(G, visited, path, t.item);
			}
		}	
	}

	/**
	 * METHOD 10: ADJACENCY LIST
	 * 
	 * This method will create the adjacency list based on the graph
	 * 
	 */
	public static void buildAdjacencyList(Maze m, int n){

		int mazeSize = n*n;
		gNode[] AL = new gNode[mazeSize]; 
		for(int i=0; i<AL.length-1; i++){

			//for the right and left walls
			if(!(i%AL.length == n-1)){
				if(m.check_wall(i, i+1, 'r') == false){
					AL[i] = new gNode(i+1, AL[i]);
				}
			}


			//for the upper and bottom walls (just to avoid another for-loop)
			if(i < AL.length-n){
				if(m.check_wall(i, i+n, 'u') == false){
					AL[i] = new gNode(i+n, AL[i]);
				}

			}
		}

		for(int j=0; j<AL.length; j++){
			System.out.println("VERTEX "+j);
			for(gNode t= AL[j]; t!=null; t=t.dest){
				System.out.println(t.item);
			}
		}


	}
}
