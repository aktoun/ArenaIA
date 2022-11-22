package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class Bishop extends Piece {

	private final int cost = 10;

	public Bishop(Player player) {
		super(player);
	}

	public Bishop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "B" : "b");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		float x = mv.xI - mv.xF;
		float y = mv.yI - mv.yF;
		if (x == 0) {
			return false;
		}
		return Math.abs(y / x) == 1.0;
	}

	@Override
	public List<Move> getBasicMove(Spot c) 
	{
		List<Move> moves = new ArrayList<Move>();

		int i; // Increment

		// Top Right
		i = 1;
		while(c.xPos+i < SIZE && c.yPos+i < SIZE) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos+i,c.yPos+i));
			i++;
		}

		// Bottom Right
		i = 1;
		while(c.xPos+i < SIZE && c.yPos-i>=0) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos+i,c.yPos-i));
			i++;
		}
		// Bottom Left
		i = 1;
		while(c.xPos-i>=0 && c.yPos-i>=0) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos-i,c.yPos-i));
			i++;
		}

		// Top Left
		i = 1;
		while(c.xPos-i>=0 && c.yPos+i < SIZE) {
			moves.add(new Move(c.xPos,c.yPos,c.xPos-i,c.yPos+i));
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
		Piece piece = new Bishop(this.p);
		return piece;
	}
}
