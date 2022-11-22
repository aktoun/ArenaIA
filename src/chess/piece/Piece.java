package chess.piece;

import java.util.List;

import chess.Spot;
import chess.agent.Move;
import chess.agent.Player;

public abstract class Piece {
	
	public static final int SIZE = 8; // size of board
	protected Player p;
    
    public Piece() {
		// TODO Auto-generated constructor stub
	}
    
    public Piece(Player p) {
		// TODO Auto-generated constructor stub
   	 	setPlayer(p);
	}
	public Player getPlayer() {
            return this.p;
    }
    public void setPlayer(Player player) {
            this.p = player;
    }

     public abstract boolean isMoveLegal(Move mv);

     public abstract List<Move> getBasicMove(Spot c);
     public abstract List<Move> getAttackMove(Spot c);
     
     public abstract int getCost();

     public abstract String toString();
     
     public void print(){
             System.out.println(this.toString()); 
     }
     
     
	public abstract Piece clone();
}
