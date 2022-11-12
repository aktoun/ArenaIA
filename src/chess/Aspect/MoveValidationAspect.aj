package chess.Aspect;

import chess.Board;
import chess.agent.Move;
import chess.agent.Player;
import chess.piece.Knight;

public aspect MoveValidationAspect {

	// Pointcut+Advice to check if a move is valid
    boolean around(Player player, Move mv) : call (boolean chess.agent.Player+.makeMove(Move)) && target(player) && args(mv) 
    {

    	if (mv == null) return false;
    	
    	// d) le mouvement final ne sort pas de l’échiquier (1<= xfinal, yfinal <= 8)
    	if (mv.xI < 0 || mv.xI >= Board.SIZE || mv.xF < 0 || mv.xF >= Board.SIZE)
		{
			//System.out.println("Le mouvement final sort de l'échiquer ! ");
			return false;
		}
    	
    	if (mv.yI < 0 || mv.yI >= Board.SIZE || mv.yF < 0 || mv.yF >= Board.SIZE)
		{
			//System.out.println("Le mouvement final sort de l'échiquer ! ");
			return false;
		}
    	
    	// a) le joueur déplace une pièce et non une case vide
		if (!player.getPlayGround().getGrid()[mv.xI][mv.yI].isOccupied())
		{
			//System.out.println("C'est une case vide ! ");
			return false;
		}

		// b) cette pièce lui appartient
		if (player.getPlayGround().getGrid()[mv.xI][mv.yI].getPiece().getPlayer() == player.getColor())
		{
			//System.out.println("Cette pièce ne vous appartient pas ! ");
			return false;
		}
		
		// c) le mouvement est autorisé par la pièce en question
		if (!player.getPlayGround().getGrid()[mv.xI][mv.yI].getPiece().isMoveLegal(mv))
		{
			//System.out.println("Ce mouvement n'est pas autorisé par cette pièce ! ");
			return false;
		}
		
		
		// e) la pièce ne mange pas une pièce qui appartient à ce même joueur
		if (player.getPlayGround().getGrid()[mv.xF][mv.yF].isOccupied() &&
				player.getPlayGround().getGrid()[mv.xF][mv.yF].getPiece().getPlayer() != player.getColor())
		{
			//System.out.println("Une pièce qui vous appartient se trouve sur la case finale ! ");
			return false;
		}
		
		// f) la pièce ne saute pas d’autres pièces, excepté pour le cavalier
        if (player.getPlayGround().getGrid()[mv.xI][mv.yI].getPiece().getClass() != Knight.class) 
        {
            // Horizontal
            if (mv.yI == mv.yF) {
            	if(!checkHorizontalMovement(player, mv))
            	{
            		//System.out.println("Une pièce sur le chemin vous bloque !");
            		return false;
            	}
            }

            // Vertical
            if (mv.xI == mv.xF) {
                if(!checkVerticalMovement(player, mv))
                {
            		//System.out.println("Une pièce sur le chemin vous bloque !");
            		return false;
            	}
            }

            // Diagonal
            else {
                if(!checkDiagonalMovement(player, mv))
                {
            		//System.out.println("Une pièce sur le chemin vous bloque !");
            		return false;
            	}
            }
        }
		
		// Movement is valid
		player.getPlayGround().movePiece(mv);
		
        return true;
    }
    
    private boolean checkHorizontalMovement(Player p, Move mv)
    {
		// On ne vérifie pas la case initiale
    	int xPos = mv.xI+1;
    	int xMax = mv.xF;
    	
    	if(xPos > xMax) 
    	{
    		xPos = mv.xF+1;
    		xMax = mv.xI;
    	}
    	
    	for(;xPos < xMax; xPos++)
    	{
    		if (p.getPlayGround().getGrid()[xPos][mv.yI].isOccupied()) { return false; }
    	}
    	
    	return true;
    }
    
    private boolean checkVerticalMovement(Player p, Move mv)
    {
		// On ne vérifie pas la case initiale
    	int yPos = mv.yI+1;
    	int yMax = mv.yF;
    	
    	if(yPos > yMax) 
    	{
    		yPos = mv.yF+1;
    		yMax = mv.yI;
    	}
    	
    	for(;yPos < yMax; yPos++)
    	{
    		if (p.getPlayGround().getGrid()[mv.xI][yPos].isOccupied()) { return false; }
    	}
    	
    	return true;
    }
    
    private boolean checkDiagonalMovement(Player p, Move mv)
    {
    	int xPos = mv.xI;
    	int yPos = mv.yI;
    	int xMax = mv.xF;
    	int yMax = mv.yF;

		// Diag bas droite
    	if(xPos < xMax && yPos < yMax)
    	{
    		// On ne vérifie pas la case finale
    		xPos++; 
    		yPos++;
    		
    		for(;xPos <= xMax-1 && yPos <= yMax-1; xPos++, yPos++)
        	{
    			if (p.getPlayGround().getGrid()[xPos][yPos].isOccupied()) { return false; }
        	}
    	} 
    	// Diag bas gauche
    	else if (yPos < yMax && xPos > xMax) 
    	{
    		// On ne vérifie pas la case finale
    		xPos--; 
    		yPos++;
    		for(;xPos >= xMax+1 && yPos <= yMax-1; xPos--, yPos++)
        	{
        		if (p.getPlayGround().getGrid()[xPos][yPos].isOccupied()) { return false; }
        	}
    	}
    	// Diag haut droite
    	else if (yPos > yMax && xPos < xMax) 
    	{
    		// On ne vérifie pas la case finale
    		xPos++; 
    		yPos--;
    		for(;xPos <= xMax-1 && yPos+1 >= yMax; xPos++, yPos--)
        	{
        		if (p.getPlayGround().getGrid()[xPos][yPos].isOccupied()) { return false; }
        	}
    	}
    	// Diag haut gauche
    	else 
    	{
    		// On ne vérifie pas la case finale
    		xPos--; 
    		yPos--;
    		for(;xPos >= xMax+1 && yPos >= yMax+1; xPos--, yPos--)
        	{
        		if (p.getPlayGround().getGrid()[xPos][yPos].isOccupied()) { return false; }
        	}
    	}
    	
    	return true;
    }
    
}
