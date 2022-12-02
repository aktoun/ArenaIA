package chess.algo;

import chess.Board;
import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

import java.util.ArrayList;
import java.util.List;

public class MoveAlgo {

	public static final int SIZE = 8; // size of board

	// The chess board to be worked o
	protected Board board;

	protected final int INFINITY = 100000;
	//protected Random rnd = new Random();

	// The number of levels in the tree to be searched.
	protected int dd = 5;       // Depth

	protected int stepcounter;  // Total node count
	protected Move mm;          // Current move
	protected int val = 0;      // Current estimation value

	protected int estnow;         // base estimation
	protected int est_cost;       // Cost estimation value
	protected int est_attackCost; // Attack cost estimation value


	public MoveAlgo () {
	}

	public void setBoard(Board b)
	{
		this.board = b;
	}

	protected void setDefaults() {
		dd = 5;       // Depth

		stepcounter = 0;  // Total node count
		mm = null;          // Current move
		val = 0;      // Current estimation value

		estnow = 0;         // base estimation
		est_cost = 0;       // Cost estimation value
		est_attackCost = 0; // Attack cost estimation value
	}

	protected List<Move> successors(int player) 
	{
		List<Move> allmoves = new ArrayList<Move>();

		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++)
			{
				if(board.getGrid()[i][j].isOccupied() && board.getGrid()[i][j].getPiece().getPlayer().getColor() == player) 	
				{
					allmoves.addAll(getRealAll(board.getGrid()[i][j]));
				} 
			}
		return allmoves;
	}

	public List<Move> getRealAll(Spot c) {

		Piece p = c.getPiece();

		if (p==null) 
			return null;

		List<Move> moveSpot = new ArrayList<Move>();

		if (p instanceof Pawn)
		{
			moveSpot.addAll(p.getBasicMove(c));
			moveSpot.addAll(p.getAttackMove(c));
		} 
		else if (p instanceof Knight)
		{
			moveSpot.addAll(p.getBasicMove(c));
		}
		else if (p instanceof Bishop)
		{
			moveSpot.addAll(p.getBasicMove(c));
		}
		else if (p instanceof Rook)
		{
			moveSpot.addAll(p.getBasicMove(c));
		}
		else if (p instanceof Queen)
		{
			moveSpot.addAll(p.getBasicMove(c));
		}
		else if (p instanceof King)
		{
			moveSpot.addAll(p.getBasicMove(c));
		}

		if(!moveSpot.isEmpty()) 
		{
			List<Move> moveToRemove = new ArrayList<Move>();
			for (Move move : moveSpot) {
				if(!p.getPlayer().makeMove(move))
					moveToRemove.add(move);
			}
			moveSpot.removeAll(moveToRemove);
		}

		return moveSpot;
	}

	protected int estimate() {
		stepcounter++;
		int dc = getCostBoard();
		int dac = getAttackCost();
		//System.out.println("getCost => " + getCost());
		//System.out.println("getAttackCost => " + getAttackCost());
		return dc*10+dac;
	}

	private int getCostBoard() {
		int out = 0;
		for(int c=0;c<SIZE;c++)
			for(int r=0;r<SIZE;r++)
				if(this.board.getGrid()[c][r].isOccupied())
				{
					out+=this.board.getGrid()[c][r].getPiece().getCost();
				}
		return out;
	}

	private int getAttackCost() {
		int out = 0;
		for(int c=0;c<SIZE;c++)
			for(int r=0;r<SIZE;r++)
				if(this.board.getGrid()[c][r].isOccupied()) 
				{
					Spot spot = this.board.getGrid()[c][r];
					Piece p = spot.getPiece();
					List<Move> attackMoves = p.getAttackMove(spot);

					if(!attackMoves.isEmpty())
						for (Move move : attackMoves) {
							if(p.getPlayer().makeMove(move))
								if(this.board.getGrid()[move.xF][move.yF].isOccupied())
									if(this.board.getGrid()[move.xF][move.yF].getPiece().getPlayer().getColor() != p.getPlayer().getColor())
										out += this.board.getGrid()[move.xF][move.yF].getPiece().getCost();
						}
				}
		return -out;
	}

	/*
	
	public boolean checkMate()
	{
		return isAttacked(this.board.getKingSpot(Player.BLACK),Player.WHITE) && !replyForMate(false) ||
				isAttacked(this.board.getKingSpot(Player.WHITE),Player.BLACK) && !replyForMate(true);
	}

	public boolean isAttacked(Spot c, int color) {
		for (int a=0;a<8;a++)
			for (int r=0;r<8;r++)
				if(this.board.getGrid()[a][r].isOccupied() && this.board.getGrid()[a][r].getPiece().getPlayer().getColor() == color) {

					List<Move> allmoves = new ArrayList<Move>();
					Vector v = getAttacks(new Coord(a,r));
					if (v!=null) {
						for (int i=0;i<v.size();i++) {
							Move m = (Move)v.elementAt(i);
							if (m.x2==c.x && m.y2==c.y)
								return true;
						}
					}
				}
		return false;
	}

	public boolean replyForMate(boolean white) {
		for(byte c=0;c<8;c++)
			for(byte r=0;r<8;r++)
				if(board.b[c][r]!=null && board.b[c][r].white==white)
					if(getRealAll(new Coord(c,r))!=null)
						return true;
		return false;
	}
	
	*/

}
