package BattleshipCL.Player;

import java.util.Scanner;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

public class LocalPlayer extends Player {

	private Scanner inputValue;

	public LocalPlayer(String name, String boardColor, Scanner inputValue) {

		super(name, boardColor);
		this.inputValue = inputValue;

	}

	public LocalPlayer(Board board, String boardColor, Scanner inputValue) {

		super(board, boardColor);
		this.inputValue = inputValue;

	}

	public int[] getNextShot(BoardView board) {

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

		// TODO: Whole method :/

	}

}
