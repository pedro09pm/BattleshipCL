package BattleshipCL.Player;

import java.util.ArrayList;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

abstract public class Player {

	private Board board;
	protected ArrayList<BoardView> boardViews = new ArrayList<>();
	private String name;
	protected String playerColor = "";

	public Player(String name, String playerColor) {
		this.board = new Board(name);
		this.playerColor = playerColor;
		this.name = name;
	}

	public Player(Board board, String playerColor) {
		this.board = board;
		this.playerColor = playerColor;
		this.name = board.getBoardName();
	}

	public boolean isAlive() {
		return (board.getAliveShipNumber() > 0);
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

	public String getPlayerColor() {
		return playerColor;
	}

	public int getTotalShotsLeft() {
		int totalShotsLeft = 0;
		for (int i = 0; i < boardViews.size(); i++) {
			totalShotsLeft = totalShotsLeft + boardViews.get(i).getShotsLeft();
		}
		return totalShotsLeft;
	}

	public void addBoardView(BoardView boardView) {
		if (!boardViews.contains(boardView)) {
			boardViews.add(boardView);
		}
	}

	public void doTurn() {
		for (int i = 0; i < boardViews.size(); i++) {
			if (boardViews.get(i).getShotsLeft() > 0) {
				showBoard();
				int[] coordinates = getNextShot(boardViews.get(i));
				shoot(boardViews.get(i), coordinates[0], coordinates[1]);
			}
		}
	}

	public static Boolean shoot(BoardView board, int row, int column) {
		return board.revealCell(row, column);
	}

	abstract public int[] getNextShot(BoardView boardView);

	abstract public void showBoard();

	abstract public void showVictory();

	abstract public void showDefeat();

}
