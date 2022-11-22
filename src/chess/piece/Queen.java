package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public class Queen extends Piece {

	private final int cost = 40;

	public Queen(Player player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.p.getColor() == Player.WHITE) ? "D" : "d");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		Rook r = new Rook();
		Bishop b = new Bishop();
		return r.isMoveLegal(mv) || b.isMoveLegal(mv);
	}

	@Override
	public List<Move> getBasicMove(Spot c) 
	{
		List<Move> moves = new ArrayList<Move>();

		moves.addAll(new Bishop().getBasicMove(c));
		moves.addAll(new Rook().getBasicMove(c));
		
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
		Piece piece = new  Queen(this.p);
		return piece;
	}
}