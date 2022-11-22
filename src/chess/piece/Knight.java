package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class Knight extends Piece {
	
	private final int cost = 5;

	public Knight(Player player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "C" : "c");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		if (Math.abs(mv.xI - mv.xF) == 2) {
			return Math.abs((mv.yI - mv.yF)) == 1;
		} else if (Math.abs(mv.xI - mv.xF) == 1) {
			return Math.abs((mv.yI - mv.yF)) == 2;
		}
		return false;
	}

	@Override
	public List<Move> getBasicMove(Spot c) 
	{
		List<Move> moves = new ArrayList<Move>();

		if(c.xPos+1 < SIZE) {
			if(c.yPos+2 < SIZE)
				moves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos+2));
			if(c.yPos-2>=0)
				moves.add(new Move(c.xPos,c.yPos,c.xPos+1,c.yPos-2));
			if(c.xPos+2 < SIZE) {
				if(c.yPos+1 < SIZE)
					moves.add(new Move(c.xPos,c.yPos,c.xPos+2,c.yPos+1));
				if(c.yPos-1>=0)
					moves.add(new Move(c.xPos,c.yPos,c.xPos+2,c.yPos-1));
			}                                                    
		}
		
		if(c.xPos-1>=0) {
			if(c.yPos+2 < SIZE)
				moves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos+2));
			if(c.yPos-2>=0)
				moves.add(new Move(c.xPos,c.yPos,c.xPos-1,c.yPos-2));
			if(c.xPos-2>=0) {
				if(c.yPos+1 < SIZE)
					moves.add(new Move(c.xPos,c.yPos,c.xPos-2,c.yPos+1));
				if(c.yPos-1>=0)
					moves.add(new Move(c.xPos,c.yPos,c.xPos-2,c.yPos-1));
			}                                                    
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
		return getBasicMove(c);
	}
	
	@Override
	public Piece clone()
	{
		Piece piece = new Knight(this.p);
		return piece;
	}
}