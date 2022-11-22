package chess;

import chess.agent.Move;
import chess.agent.Player;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public class Board {
	private Spot[][] grid;
	public static final int SIZE = 8; // size of board

	public Board() {
		grid = new Spot[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[j][i] = new Spot(j, i);
				grid[j][i].setOccupied(false);
			}
		}
	}

	public void setupChessBoard(Player white, Player black) {

		for (int x = 0; x < SIZE; x++) {
			grid[x][1].setPiece(new Pawn(white));
			grid[x][6].setPiece(new Pawn(black));
		}
		for (int x = 2; x < 8; x += 3) {
			grid[x][0].setPiece(new Bishop(white));
			grid[x][7].setPiece(new Bishop(black));
		}

		for (int x = 1; x < 8; x += 5) {
			grid[x][0].setPiece(new Knight(white));
			grid[x][7].setPiece(new Knight(black));
		}

		for (int x = 0; x < 8; x += 7) {
			grid[x][0].setPiece(new Rook(white));
			grid[x][7].setPiece(new Rook(black));
		}

		grid[3][0].setPiece(new Queen(white));
		grid[3][7].setPiece(new Queen(black));

		grid[4][0].setPiece(new King(white));
		grid[4][7].setPiece(new King(black));
	}

	public void movePiece(Move mv) {
		grid[mv.xF][mv.yF].setPiece(grid[mv.xI][mv.yI].getPiece());
		grid[mv.xI][mv.yI].release();
	}
	
	public void undoMove(Move mv)
	{
		grid[mv.xI][mv.yI].setPiece(grid[mv.xF][mv.yF].getPiece());
		grid[mv.xF][mv.yF].release();
	}

	public Spot[][] getGrid() {
		return grid;
	}

	public String toString() {
		String s = "";
		for (int y = 0; y < SIZE; y++) {
			s += ((char) ('1' + y) + "| ");
			for (int x = 0; x < SIZE; x++) {
				if (grid[x][y].isOccupied()) {
					s += grid[x][y].getPiece() + " ";
				} else
					s += "  ";
			}
			s += "\n";
		}

		s += "  ";
		for (int x = 0; x < SIZE; x++)
			s += ("--");

		s += "\n";
		s += "   ";
		for (int x = 0; x < SIZE; x++)
			s += ((char) ('a' + x) + " ");
		s += "\n";
		return s;
	}

	public void print() {
		System.out.println(this.toString());
	}
	
	public Spot getKingSpot(int color)
	{
		for (int y = 0; y < SIZE; y++)
		{
			for (int x = 0; x < SIZE; x++)
			{
				if (this.grid[y][x].isOccupied() && this.grid[y][x].getPiece().getClass() == King.class)
				{
					if(this.grid[y][x].getPiece().getPlayer().getColor() == color)
						return this.grid[y][x];
				}
			}
		}
		return null;
	}

	public Board deepClone() {
		Board b = new Board();
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++)
			{
				if(this.grid[y][x].isOccupied())
				{
					Piece p = this.grid[y][x].getPiece().clone();
					b.getGrid()[y][x].setPiece(p);
				}
			}
		return b;
	}

}
