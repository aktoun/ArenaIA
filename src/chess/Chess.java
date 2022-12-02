package chess;

import chess.UCI.UCI;
import chess.agent.AiPlayer;
import chess.agent.HumanPlayer;
import chess.agent.Player;

public class Chess {

	protected Board board;
	protected Player minmax;
	protected Player ordi;

	public Chess() {

		board = new Board();
		this.minmax = new HumanPlayer(Player.BLACK, this);
		this.ordi = new AiPlayer(Player.WHITE, this);
		board.setupChessBoard(ordi, minmax);

	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Player getOrdiPlayer()
	{
		return this.ordi;
	}
	
	public Player getMinMaxPlayer()
	{
		return this.minmax;
	}
	


	private void play() {
		while (true){			
			// TOUR IA
			ordi.makeMoveTestLocal();

			// TOUR MINMAX
			minmax.makeMoveTestLocal();
		}
	}
	
	public static void main(String[] args) {
		new Chess().play();
		//UCI.uciCommunication();
	}
}
