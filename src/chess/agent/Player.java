package chess.agent;

import chess.Board;
import chess.Chess;

public abstract class Player {
	public static final int WHITE = 1;
	public static final int BLACK = -1;
	
	protected int Colour;
	protected Chess game;

	public Board getPlayGround() {
		return this.game.getBoard();
	}
	
	public abstract boolean makeMove(Move mv);
	public abstract Move doMove(Move mv);
	
	public abstract void makeMoveTestLocal();
	
	public int getColor(){
		return this.Colour;
	}
	public void setColor(int arg){
		this.Colour = arg;
	}
}
