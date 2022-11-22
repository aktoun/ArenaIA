package chess.agent;

import java.util.Random;

import chess.Board;
import chess.Chess;
import chess.agent.Move;

public class AiPlayer extends Player {
	// dies roooooll
	private Random Dies = new Random(0);

	public AiPlayer(int arg, Chess game) {
		setColor(arg);
		this.game = game;
	}

	@Override
	public boolean makeMove(Move mv) {
		/*
		 * // TODO Auto-generated method stub if (mv == null) return false; if
		 * (!playGround.getGrid()[mv.xI][mv.yI].isOccupied()) return false; if
		 * (playGround.getGrid()[mv.xI][mv.yI].getPiece().getPlayer() ==
		 * this.getColor()) return false; if
		 * (!playGround.getGrid()[mv.xI][mv.yI].getPiece().isMoveLegal(mv)) return
		 * false; 
		 */
		return true;
	}

	@Override
	public Move doMove(Move mv) {
		/*
		 * Move mv; char initialX = '\0'; char initialY = '\0'; char finalX = '\0'; char
		 * finalY = '\0'; do{ this.game.getBoard().print(); System.out.print
		 * ("Votre coup? <a2a4> "); initialX = Lire(); initialY = Lire(); finalX =
		 * Lire(); finalY = Lire(); ViderBuffer();
		 * 
		 * mv = new Move(initialX-'a', initialY-'1', finalX - 'a', finalY-'1'); }
		 * while(!makeMove(mv));
		 */

		this.game.getBoard().movePiece(mv);

		System.out.println("\n"+(this.getColor()==Player.WHITE?"White":"Black") + " replies with AI");
		System.out.println("final move = "+mv.toString());

		this.game.getBoard().print();

		return null;
	}

	@Override
	public void makeMoveTestLocal()
	{

		Move mv; char initialX = '\0'; char initialY = '\0'; char finalX = '\0'; char
		finalY = '\0'; do{ this.game.getBoard().print(); System.out.print
		("Votre coup? <a2a4> "); initialX = Lire(); initialY = Lire(); finalX =
		Lire(); finalY = Lire(); ViderBuffer();

		mv = new Move(initialX-'a', initialY-'1', finalX - 'a', finalY-'1'); }
		while(!makeMove(mv));


		this.game.getBoard().movePiece(mv);

		System.out.println("\n"+(this.getColor()==Player.WHITE?"White":"Black") + " replies with AI");
		System.out.println("final move = "+mv.toString());

		this.game.getBoard().print();
	}

	private static char Lire() {
		char C = 'A';
		boolean OK;
		do {
			OK = true;
			try {
				C = (char) System.in.read();
			}catch (java.io.IOException e) {

				OK = false;
			}
		} while (!OK);
		return C;
	}

	private static void ViderBuffer() {
		try {
			while (System.in.read() != '\n')
				;
		} catch (java.io.IOException e) {
		}
	}
}
