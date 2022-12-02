package chess.agent;

import chess.Board;
import chess.Chess;
import chess.algo.AlphaBeta;
import chess.algo.MiniMax;

public class HumanPlayer extends Player {


	private AlphaBeta algo;
	//private MiniMax algo;

	public HumanPlayer(int arg, Chess game) {
		setColor(arg);
		this.game = game;
		//this.algo = new MiniMax();
		this.algo = new AlphaBeta();
	}

	@Override
	public boolean makeMove(Move mv) {
		/*
		 * // TODO Auto-generated method stub if(mv == null) return false;
		 * if(!playGround.getGrid()[mv.xI][mv.yI].isOccupied()) return false;
		 * if(playGround.getGrid()[mv.xI][mv.yI].getPiece().getPlayer() ==
		 * this.getColor()) return false;
		 * if(!playGround.getGrid()[mv.xI][mv.yI].getPiece().isMoveLegal(mv)) return
		 * false; playGround.movePiece(mv);
		 */
		return true;
	}

	@Override
	public Move doMove(Move move) {

		Board b = this.game.getBoard().deepClone();
		algo.setBoard(b);
		Move mv = algo.getReply(Player.BLACK);
		System.out.println("final move = "+mv.toString());

		this.game.getBoard().movePiece(mv);
		this.game.getBoard().print();

		return mv;
	}

	@Override
	public void makeMoveTestLocal()
	{
		Board b = this.game.getBoard().deepClone();
		algo.setBoard(b);
		Move mv = algo.getReply(Player.BLACK);
		System.out.println("final move = "+mv.toString());

		this.game.getBoard().movePiece(mv);
		this.game.getBoard().print();
	}

}
