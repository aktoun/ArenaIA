package chess.UCI;


import java.util.*;

import chess.Chess;
import chess.agent.Move;

public class UCI {
    static String ENGINENAME="ZERO v1";
    public static  Chess c;
    
    public static void uciCommunication() {
        Scanner input = new Scanner(System.in);
        while (true)
        {
        	
            String inputString=input.nextLine();
            if ("uci".equals(inputString))
            {
                inputUCI();
            }
            else if (inputString.startsWith("setoption"))
            {
                inputSetOption(inputString);
            }
            else if ("isready".equals(inputString))
            {
                inputIsReady();
            }
            else if ("ucinewgame".equals(inputString))
            {
                inputUCINewGame();
            }
            else if (inputString.startsWith("position"))
            {
                inputPosition(inputString);
            }
            else if (inputString.startsWith("go"))
            {
                ChooseMove();
            }
            else if (inputString.equals("quit"))
            {
                inputQuit();
            }
            else if ("print".equals(inputString))
            {
                inputPrint();
            }
        }
    }
    
    /// uci connection with engine
    public static void inputUCI() {
    	
        System.out.println("id name "+ENGINENAME);
        System.out.println("id author Lelouch");
        //options go here
        System.out.println("uciok");
    }
    
    // To put some option TODO ? 
    public static void inputSetOption(String inputString) {
        //set options
    }
    
    
    // Switch to check if the engine and uci communicate
    public static void inputIsReady() {
         System.out.println("readyok");
    }
    
    // New fresh game 
    public static void inputUCINewGame() {
    	 c = new Chess();
      
    }
    
    // TODO
    public static void inputPosition(String input) {
    	
        input=input.substring(9).concat(" ");  // it begins with 'position', we cut that
        
        if (input.contains("moves")) 
        {
        	input = input.substring(Math.max(input.length() - 5, 0));  // Retrieve the string of the last move
      
            if (input.length()>4) //Verification
            	AlgebraToMove(input);
        }
    }
    
    public static void AlgebraToMove(String input) {
    	
    	
        int from_letter=input.charAt(0);
        int from_digit=input.charAt(1);
        int to_letter=input.charAt(2);
        int to_digit=input.charAt(3);
        
        Move mv = new Move(from_letter-'a',from_digit-'1',to_letter-'a',to_digit-'1');
        c.getOrdiPlayer().doMove(mv);
        
    }
    
    
    public static String MoveToAlgebra( ) {
    	
    	Move move = c.getMinMaxPlayer().doMove(null);
    	return("bestmove "+move.toString());
    
    }
    
    public static void ChooseMove() {
    	
    	// TODO MinMax --> move
    	System.out.println(MoveToAlgebra());
    }
   
    
    
    public static void inputQuit() {
        System.exit(0);
    }
    public static void inputPrint() {
    	c.getBoard().print();
    }
}