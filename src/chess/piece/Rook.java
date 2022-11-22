package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class Rook extends Piece {

	private final int cost = 5;

	public Rook(Player player) {
		super(player);
	}

	public Rook() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "T" : "t");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		return (( mv.yI - mv.yF) == 0) || ((mv.xI - mv.xF) == 0);
	}

	@Override
	public List<Move> getBasicMove(Spot c)
	{
		List<Move> moves = new ArrayList<Move>();

		int i;

		// Top
		i = 1;
		while(c.yPos+i < SIZE) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos+i));
			i++;
		}

		// Left
		i = 1;
		while(c.xPos-i>=0) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos-i,c.yPos));
			i++;
		}

		// Bottom
		i = 1;
		while(c.yPos-i>=0) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos,c.yPos-i));
			i++;
		}

		// Right
		i = 1;
		while(c.xPos+i < SIZE) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos+i,c.yPos));
			i++;
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
		Piece piece = new  Rook(this.p);
		return piece;
	}
}