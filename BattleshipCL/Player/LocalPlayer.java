package BattleshipCL.Player;

import java.util.Scanner;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

public class LocalPlayer extends Player {

	private Scanner inputValue;

	public LocalPlayer(String name, String playerColor, Scanner inputValue) {

		super(name, playerColor);
		this.inputValue = inputValue;

	}

	public LocalPlayer(Board board, String playerColor, Scanner inputValue) {

		super(board, playerColor);
		this.inputValue = inputValue;

	}

	public int[] getNextShot(BoardView board) {
		
		System.out.println(board.formatBoard(new String[]{"ENTER FIRE COORDINATES: ","SHOTS LEFT: " + board.getShotsLeft()}, true));

		int[] coordinates = new int[] { 0, 0 };
		String userInput;

		userInput = inputValue.nextLine();

		if (userInput.equalsIgnoreCase("dev_r")) { // Ruins the fun for the players. Makes life easy for developers...
			board.revealAll();
			return coordinates;
		}

		return board.parseCoordinates(userInput);

	}

	public void showBoard() {

		System.out.println(super.getBoard().toString());

	}

}
