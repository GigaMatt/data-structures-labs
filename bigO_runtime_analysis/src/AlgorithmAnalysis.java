/************************************************************************************
  * By: Matthew S Montoya
  * Purpose: Analyze the big-O running time of different user inputs, using various approaches, including the Master Theorem.
  * Last Modified: 09 January 2018
  *************************************************************************************/
import java.util.Scanner;
import java.lang.Math;

public class AlgorithmAnalysis{
  public static void main(String[] args){
    programMessage();	//Call the method displaying the default message to the user
  }
  
  
//PROGRAM MESSAGE: THIS METHOD, DISPLAYING THE 5 OPTIONS, WILL BE LOOPED UNTIL THE USER SPECIFIES OTHERWISE  
  public static void programMessage(){
    boolean programLoop = true;		//boolean for use of looping
    while(programLoop){
      System.out.println("Please select—\n"+
                         "1) Given n0, n1, T(n0), T(n1), and f(n), compute T(n1)\n"+
                         "2) Given n0, T(n0), T(n1), and f(n), find n1\n"+
                         "3) Given n0, n1, T(n0), and T(n1), find f(n),\n"+
                         "4) Given a, b, and k, find O(n)\n"+
                         "5) Quit the program\n\n"+
                         
                         "Selection: ");
      Scanner input = new Scanner(System.in);
      int userSelection = input.nextInt();
      
      if(userSelection < 1 || userSelection> 5)		//handle input errors gracefully
        System.out.println("Invalid selction. Please selection an integer between 1-5\nProgram resetting...\n");
      
      programLoop = selectionComputation(userSelection);//Further investigate the user's selection in another method
    }
    System.exit(0);
  }
  
  
//USER WILL SELECT THEIR METHOD (CASE) OF COMPUTATION////////////////// 
  public static boolean selectionComputation(int userSelection){
    boolean continueLooping = true;
    Scanner input = new Scanner(System.in);
    switch (userSelection){		//case statements: one for each possible selection the user can make
      case 1:	//find T(n1)
        System.out.println("\nGiven n0, n1, T(n0), T(n1), and f(n), compute T(n1)\nEnter the following parameters:\n(Integer) n0");
        int n0 = input.nextInt();
        System.out.println("(Integer) n1");
        int n1 = input.nextInt();
        System.out.println("(Double) T(n0) (in seconds)");
        input.nextLine();		//flush the scanner
        double t_n0 = input.nextDouble();
        input.nextLine();		//flush the scanner again (because now we're taking in chars)
        System.out.println("(Char) Select f(n)—\n"+
                           "a) log n\n"+
                           "b) n\n"+
                           "c) n log n\n"+
                           "d) n^2\n"+
                           "e) n^3\n"+
                           "f) 2^n");
        char f_n = input.nextLine().toLowerCase().charAt(0);
        boolean computation = computeCase1(n0, n1, t_n0, f_n);		//if the case can be computed, it will return true
        continueLooping = computation;								//then call back the user selection option
        break;
      case 2:	//find n1    
        System.out.println("Given n0, T(n0), T(n1), and f(n), find n1\nEnter the following parameters:\n(Integer) n0");
        n0 = input.nextInt();
        System.out.println("(Double) T(n0) (in seconds)");
        input.nextLine();		//flush the scanner
        t_n0 = input.nextDouble();
        System.out.println("(Double) T(n1) (in seconds)");
        double t_n1 = input.nextDouble();
        input.nextLine();		//flush the scanner again
        System.out.println("(Char) Select f(n)—\n"+
                           "a) log n\n"+
                           "b) n\n"+
                           "c) n log n\n"+
                           "d) n^2\n"+
                           "e) n^3\n"+
                           "f) 2^n");
        f_n = input.nextLine().toLowerCase().charAt(0);		//Handles if the user types a capital letter
        computation = computeCase2(n0, t_n0, t_n1, f_n);	//if the case can be computed, it will return true
        continueLooping = computation;
        break;
      case 3:	//find f(n)
        System.out.println("Given n0, n1, T(n0), and T(n1), find f(n),\nEnter the following parameters:\n(Integer) n0");
        n0 = input.nextInt();
        System.out.println("\n(Interger) n1");
        n1 = input.nextInt();
        input.nextLine();		//flush the scanner, because now we're switching to doubles
        System.out.println("\n(Double) T(n0) (in seconds)");
        t_n0 = input.nextDouble();
        System.out.println("\n(Double) T(n1) (in seconds)");
        t_n1 = input.nextDouble();
        computation = computeCase3(n0, n1, t_n0, t_n1);
        continueLooping = true;//returns the method's default true value || continues looping reguardless of user input
        break;
      case 4:	//find the big-O notation via Master Theorem
        System.out.println("Given a, b, and k, find O(n)\nEnter the following parameters:\n(Interger) a");
        int a = input.nextInt();        
        System.out.println("\n(Interger) b");
        int b = input.nextInt();
        System.out.println("\n(Interger) k");
        int k = input.nextInt(); 
        computation = computeCase4(a, b, k);
        continueLooping = true;//returns the method's default true value || continues looping reguardless of user input
        break;
      case 5:	//program termination
        System.out.println("Program terminated!\nResetting instructions...");
        continueLooping = false;
        return false;
    }
    return continueLooping;
  }
  
  
//COMPUTE CASE 1////////////////////////////////////////////////////////////
  public static boolean computeCase1(int n0, int n1, double t_n0, char f_n){
    boolean canCompute = false;
    double t_n1;
    String method;	//the big-O notation (minus the "O")
    
    switch (f_n){	//The selection the user made
      case 'a':		//log n
        method = ("log n");
        t_n1 = (((Math.log(n1))/(Math.log(n0)))*t_n0);		//identify t_n1 using log n for (n0/n1)
        canCompute = true;
        break;
        
      case 'b':		//n
        method = ("n");
        t_n1 = (((n1)/(n0))*t_n0);							//identify t_n1 using n for (n0/n1)
        canCompute = true;
        break;
        
      case 'c':		//n log n
        method = ("n log n");
        t_n1 = (((n1*(Math.log(n1)))/(n0*Math.log(n0)))*t_n0);	//identify t_n1 using n log n for (n0/n1)
        canCompute = true;
        break;
        
      case 'd':		//n^2
        method = ("n^2");
        t_n1 = (((Math.pow(n1,2))/(Math.pow(n0,2)))*t_n0);		//identify t_n1 using n^2 for (n0/n1)
        canCompute = true;
        break;
        
      case 'e':		//n^3
        method = ("n^3");
        t_n1 = (((Math.pow(n1,3))/(Math.pow(n0,3)))*t_n0);		//identify t_n1 using n^3 for (n0/n1)
        canCompute = true;
        break;
        
      case 'f':		//2^n
        method = ("2^n");
        t_n1 = (((Math.pow(2,n1))/(Math.pow(2,n0)))*t_n0);		//identify t_n1 using 2^n for (n0/n1)
        canCompute = true;
        break;
        
      default:
        System.out.println("Invalid answer. Please selection a letter between a-f\nProgram resetting...\n");
        return false; 
    } 
    System.out.println("If a O("+method+") method takes "+t_n0+" seconds to run an input of size "+n0+
                       ", it will take "+t_n1+" seconds to run an input of size "+n1+".\n\n");//print the computation
    return canCompute;		//Was the program able to compute using the given inputs?
  }
  
  
//COMPUTE CASE 2/////////////////////////////////////////////////////////////////  
  public static boolean computeCase2(int n0, double t_n0, double t_n1, char f_n){
    boolean canCompute = false;
    double n1;
    String method;	//the big-O notation (minus the "O")
    
    switch (f_n){
      case 'a':		//log n
        method = ("log n");
        n1 = (t_n1/t_n0)*(Math.log(n0)); 		//n1 using log n*(t_n0/t_n1)
        n1 = (Math.log(n1));
        canCompute = true;
        break;
        
      case 'b':		//n
        method = ("n");
        n1 = ((t_n1/t_n0)*(n0));				//n1 using n*(t_n0/t_n1)
        canCompute = true;
        break;
        
      case 'c':		//n log n
        method = ("n log n");
        n1 = ((t_n1/t_n0)*((n0)*(Math.log(n0))));	//n1 using n log n*(t_n0/t_n1)
        n1 = ((n1)*(Math.log(n1)));
        canCompute = true;
        break;
        
      case 'd':		//n^2
        method = ("n^2");
        n1 = ((t_n1/t_n0)*(Math.pow(n0,2)));		//n1 using n^2*(t_n0/t_n1)
        n1 = Math.pow(n1,2);
        canCompute = true;
        break;
        
      case 'e':		//n^3
        method = ("n^3");
        n1 = ((t_n1/t_n0)*(Math.pow(n0,3)));		//n1 using n^3*(t_n0/t_n1)
        n1 = Math.pow(n1,3);
        canCompute = true;
        break;
        
      case 'f':		//2^n
        method = ("2^n");	
        n1 = ((t_n1/t_n0)*(Math.pow(2, n0)));		//n1 using 2^n*(t_n0/t_n1)
        n1 = Math.pow(2, n1);
        canCompute = true;
        break;
        
      default:
        System.out.println("Invalid answer. Please selection a letter between a-f\nProgram resetting...\n");
        return false; 
    } 
    System.out.println("If a O("+method+") method takes "+t_n0+" seconds to run an input of size "+n0+
                       ", it will take "+t_n1+" seconds to run an input of size "+n1+".\n\n");//print the computation
    return canCompute;
  }
  
  
//COMPUTE CASE 3/////////////////////////////////////////////////////////////////
  //LIST THE OUTPUT WITHIN THE METHOD; EASIER FOR A CASE-BY-CASE BASIS//////////
  public static boolean computeCase3(int n0, int n1, double t_n0, double t_n1){
    String f_n = null;
    //identify what f(n) is, by applying algebric methods to each side of the given equation values
    if((t_n1/t_n0) == ((Math.log(n1))/Math.log(n0))){
      f_n = "log n";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else if((t_n1/t_n0) == (n1/n0)){
      f_n = "n";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else if((t_n1/t_n0) == ((n1*(Math.log(n1)))/n0*(Math.log(n0)))){
      f_n = "n log n";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else if((t_n1/t_n0) == ((Math.pow(n1,2))/(Math.pow(n0,2)))){
      f_n = "n^2";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else if((t_n1/t_n0) == ((Math.pow(n1,3))/(Math.pow(n0,3)))){
      f_n = "n^3";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else if((t_n1/t_n0) == ((Math.pow(2,n1))/(Math.pow(2,n0)))){
      f_n = "2^n";
      System.out.println("The big-O running time of input sizes "+n0+" & "+n1+" with the respective running times "+t_n0+" and "+t_n1+" is O("+f_n+").\n\n");
    }
    else
      System.out.println("Unable to compute the running time based on your input.");
    
    return true;//allows the program to continue cycling until the user specifies otherwise
  }
  
  
//COMPUTE CASE 4///////////////////////////////////////////////////////////////// 
  //USING THE MASTER THEOREM////////////////////////////////////////////////////
  public static boolean computeCase4(int a, int b, int k){
    String big_O = null;
    if(a<(Math.pow(b,k))){	//Case 1: a<b^k
      if(k != 1)
        big_O = "n^"+k;
      else
        big_O = "n";
    }
    
    else if(a == Math.pow(b,k)){	//Case 2: a=b^k
      if(k != 1)
        big_O = "n^"+k+" log n";
      else	//if k=1
        big_O = "n log n";
    }
    
    else{	// assuming the last possibility is case 3, where a>b^k
        double logNumber = (Math.log(a)/Math.log(b));	//because if log is not base-2, rather base-4 or base-X
        int parsedLog = (int)logNumber;					//as it's a double, we need to parse it into an integer
        big_O = "n^"+parsedLog;							
    }
    System.out.println("The big-O running time of the method (with the variables "+a+", "+b+", "+k+		//print the result of big-O
                       ", representing a, b, and k, respectfully, is O("+big_O+").");
    return true;	//to keep the program looping
  }
}
