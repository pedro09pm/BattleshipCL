package BattleshipCL.Player;

import java.util.Scanner;

import BattleshipCL.Game.Colorizer;
import BattleshipCL.Game.Cons;
import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;
import BattleshipCL.Utils.*;

public class LocalPlayer extends Player {

	private Scanner inputValue;
	private boolean RENDER_COLORS;
	private boolean classic;

	public LocalPlayer(String name, String playerColor, Scanner inputValue, boolean RENDER_COLORS, boolean classic) {

		super(name, playerColor);
		this.inputValue = inputValue;
		this.RENDER_COLORS = RENDER_COLORS;
		this.classic = classic;

	}

	public LocalPlayer(Board board, String playerColor, Scanner inputValue, boolean RENDER_COLORS, boolean classic) {

		super(board, playerColor);
		this.inputValue = inputValue;
		this.RENDER_COLORS = RENDER_COLORS;
		this.classic = classic;

	}

	public boolean isClassic() {
		return classic;
	}

	public int[] getNextShot(BoardView board) {
		
		TerminalUtils.cls();

		String boardString = board.formatBoard(new String[]{"ENTER FIRE COORDINATES: ","SHOTS LEFT: " + board.getShotsLeft()});

		if (RENDER_COLORS) {
			System.out.println(Colorizer.colorizeBoard(board.getPlayerColor(), boardString));
		} else {
			System.out.println(boardString);
		}

		TerminalUtils.moveCursorUp(3);
		TerminalUtils.moveCursorForward(25);

		return inputShot(board);

	}

	private int[] inputShot(BoardView board) {
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

		if (classic) {
			TerminalUtils.cls();
			return;
		}

		TerminalUtils.cls();

		String boardString = super.getBoard().addMessageToBoardString(new String[]{"ENTER TO CONTINUE: ", " "});
		
		if (RENDER_COLORS) {
			System.out.println(Colorizer.colorizeBoard(playerColor, boardString));
		} else {
			System.out.println(boardString);
		}

		TerminalUtils.moveCursorUp(3);
		TerminalUtils.moveCursorForward(20);
		inputValue.nextLine();

	}

	public void showVictory() {
		showBoard();
		TerminalUtils.moveCursorToEnd();
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.GREEN);
		}
		StringUtils.revealString(Cons.VICTORY_TEXT, 5);
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RESET);
		}
	}

	public void showDefeat() {
		showBoard();
		TerminalUtils.moveCursorToEnd();
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RED);
		}
		StringUtils.revealString(Cons.DEFEAT_TEXT, 5);
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RESET);
		}
	}

}
