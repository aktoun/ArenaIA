package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class Pawn extends Piece {
	
	private final int cost = 1;

	public Pawn(Player player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "P" : "p");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		boolean special = false;
		// player1
		if (this.p.getColor() == Player.WHITE) {
			if ((Math.abs(mv.xF - mv.xI) == 0) && (Math.abs(mv.yF - mv.yI) < 3)) {
				special = true;
			}
			return (((mv.yF - mv.yI) == 1) && (Math.abs(mv.xF - mv.xI) <= 1)) || special;
		}
		// player 2
		if ((Math.abs(mv.xF - mv.xI) == 0) && (Math.abs(mv.yF - mv.yI) < 3)) {
			special = true;
		}
		return (((mv.yF - mv.yI) == -1) && (Math.abs(mv.xF - mv.xI) <= 1)) || special;

	}

	@Override
	public List<Move> getBasicMove(Spot c) 
	{
		List<Move> moves = new ArrayList<Move>();
		
		// Basics Movements
	    if(this.p.getColor() == Player.WHITE){
	    	moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos+1));
	        if (c.yPos==1) //beginning jump
	        	moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos+2));
	    }else{
	    	moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos-1));
	        if (c.yPos==6)
	        	moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos-2));
	    }
	    
	    return moves;
	}

	@Override
	public int getCost() {
		return this.p.getColor()*cost;
	}

	@Override
	public List<Move> getAttackMove(Spot c) 
	{
		List<Move> attackMoves = new ArrayList<Move>();
		
		// Pawn Attack
	    if(this.p.getColor() == Player.WHITE) {
	      if(c.xPos+1 < SIZE)
	    	  attackMoves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos+1));
	      if(c.xPos-1>=0)
	    	  attackMoves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos+1));
	    } else {
	      if(c.xPos+1 < SIZE)
	    	  attackMoves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos-1));
	      if(c.xPos-1>=0)
	    	  attackMoves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos-1));
	    }
	    
	    return attackMoves;
	}
	
	@Override
	public Piece clone()
	{
		Piece piece = new Pawn(this.p);
		return piece;
	}

}
