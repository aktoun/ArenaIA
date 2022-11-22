package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class King extends Piece {	

	private final int cost = 100;

	public King(Player player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "R" : "r");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		return (Math.abs(mv.xI - mv.xF) <= 1) && (Math.abs(mv.yI - mv.yF) <= 1);
	}

	@Override
	public List<Move> getBasicMove(Spot c) 
	{
		List<Move> moves = new ArrayList<Move>();

		// Basic movements
		if(c.xPos+1 < SIZE) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos));
			if(c.yPos+1 < SIZE)
				moves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos+1));
			if(c.yPos-1>=0)
				moves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos-1));
		}

		if(c.xPos-1>=0) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos));
			if(c.yPos+1 < SIZE)
				moves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos+1));
			if(c.yPos-1>=0)
				moves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos-1));
		}

		if(c.yPos+1 < SIZE)
			moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos+1));
		if(c.yPos-1>=0)
			moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos-1));

		// Roque
	    // TODO 
	    
		return moves;
	}
	
	@Override
	public int getCost() {
		return this.p.getColor()*cost;
	}
	
	@Override
	public List<Move> getAttackMove(Spot c) 
	{
		return getBasicMove(c);
	}
	
	@Override
	public Piece clone()
	{
		Piece piece = new King(this.p);
		return piece;
	}
}