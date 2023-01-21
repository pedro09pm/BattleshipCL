package BattleshipCL.Player;

import java.util.ArrayList;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

abstract public class Player {

	private Board board;
	protected ArrayList<BoardView> boardViews = new ArrayList<>();
	private String name;

	public Player(String name, String boardColor) {

		this.board = new Board(name, boardColor);
		this.name = name;

	}

	public Player(Board board, String boardColor) {

		this.board = board;
		this.board.setBoardColor(boardColor);
		this.name = board.getBoardName();

	}

	public ArrayList<BoardView> getBoardViews() {
		return boardViews;
	}

	public Board getBoard() {
		return board;
	}

	public String getName() {
		return name;
	}

	public void addBoardView(BoardView boardView) {
		boardViews.add(boardView);
	}

	public void doTurn() {

		for (int i = 0; i < boardViews.size(); i++) {
			if (boardViews.get(i).getShotsLeft() > 0) {

				int[] coordinates = getNextShot(boardViews.get(i));
				shoot(boardViews.get(i), coordinates[0], coordinates[1]);

			}
		}

	}

	abstract public int[] getNextShot(BoardView boardView);

	abstract public void showBoard();

	public static Boolean shoot(BoardView board, int row, int column) {
		return board.revealCell(row, column);
	}

}
