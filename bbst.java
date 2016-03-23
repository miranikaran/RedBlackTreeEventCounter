package bbst;
import java.util.Scanner;
import java.io.*;
public class bbst {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
      if(args.length == 0)
    	  {
    	    System.out.println("File not found");
    	    System.exit(0);
    	  }
      
      
      final File input = new File(args[0]);                       //Get file name
      RedBlackTree rbt = new RedBlackTree(new Scanner(input));          //Create Red Black Tree
      interaction(rbt,System.in);                                      //call interaction method
      
	}

	
	public static void interaction(RedBlackTree rbt,InputStream in) throws IOException{
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));   
		 
		 String input = null;
		 while(!"quit".equals(input = reader.readLine())){
	    	  
	    	  String [] commands = input.split(" ");
	    	  String command = commands[0];
	    	  switch(command){
	    	  case "increase" : 
	    		   rbt.increase(Integer.parseInt(commands[1]),Integer.parseInt(commands[2]));
	    		   break;
	    	  case "reduce" :	 
	    		  rbt.reduce(Integer.parseInt(commands[1]),Integer.parseInt(commands[2]));
	    		  break;
	    	  case "count" :
	    		  rbt.count(Integer.parseInt(commands[1]));
	    		  break;
	    	  case "next" :
	    		  rbt.next(Integer.parseInt(commands[1]));
	    		  break;
	    	  case "previous" :
	    		  rbt.previous(Integer.parseInt(commands[1]));    		  
	    		  break;
	    	  case "inrange" :
	    		  rbt.inRange(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
	    		  break;    		  
	    	  }
	      }      
	}
	
}
