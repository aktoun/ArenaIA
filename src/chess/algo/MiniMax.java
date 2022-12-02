package chess.algo;

import java.util.ArrayList;
import java.util.List;

import chess.Board;
import chess.agent.Move;
import chess.agent.Player;

public class MiniMax extends MoveAlgo {

	protected final int INFINITY = 100000;
	private boolean white;

	public MiniMax() {
	}

	/**
	 * Get a reply in the form of a move. NOTE: the move is stored in mm
	 * @param white - White player ?
	 * @return the move
	 */  
	public Move getReply(int player) {
		System.out.println("\n"+(player==Player.WHITE?"White":"Black") + " replies with "+toString());
		
		setDefaults();
	    
		minimax(player, dd);
		return mm;
	}

	/**
	 * Start the search algorithm with the right player
	 * @param white - White player ?
	 * @param depth - The current number of levels in the tree to be searched.
	 */
	public void minimax(int player, int depth){
		if(player == Player.WHITE) this.white = true;
		else this.white = false;

		int val = 0;
		
		if(this.white)
			val = Max(depth); // white moves first
		else
			val = Min(depth); // black moves first
	}

	/**
	 * The search algorithm for max (White player)
	 * @param depth - The current number of levels in the tree to be searched.
	 * @return the best evaluation
	 */
	private int Max(int depth) {
		if (depth == 0)
			return estimate();
		int best = -INFINITY;

		List<Move> allmoves = successors(Player.WHITE);
		List<Move> moveToRemove = new ArrayList<Move>();
		
		for (Move move : allmoves) 
		{	
			if (!allmoves.isEmpty()) 
			{
				moveToRemove.add(move);
				
				Board b = this.board.deepClone();
				this.board.movePiece(move);
				int val = -Min(depth-1);

				if (val>best) {
					best = val;
					if (this.white) {
						mm = move; // Current choice of move
					}
				}
				setBoard(b);
			}
		}
		allmoves.removeAll(moveToRemove);
		System.out.println("best Max:" + best);
		return best;
	}

	/**
	 * The search algorithm for min (Black player)
	 * @param depth - The current depth
	 * @return the best evaluation
	 */
	private int Min(int depth) {
		if (depth == 0)
			return estimate();
		int best = -INFINITY;
		
		List<Move> allmoves = successors(Player.BLACK);
		List<Move> moveToRemove = new ArrayList<Move>();
		
		for (Move move : allmoves) 
		{	
			if (!allmoves.isEmpty()) 
			{
				moveToRemove.add(move);

				Board b = this.board.deepClone();
				this.board.movePiece(move);
				int val = -Max(depth-1);

				if (val>best) {
					best = val;
					if (!this.white) {
						mm = move; // Current choice of move
					}
				}
				setBoard(b);
			}
		}
		allmoves.removeAll(moveToRemove);
		System.out.println("best Min:" + best);
		return best;
	}

	/**
	 * Get the name of this algorithm
	 */
	public String toString() {
		return "Minimax";
	}
}
