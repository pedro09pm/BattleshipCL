package BattleshipCL;

import java.util.ArrayList;
import java.util.Scanner;
import BattleshipCL.Game.*;
import BattleshipCL.Game.Primitive.*;
import BattleshipCL.Network.*;
import BattleshipCL.Player.*;
import BattleshipCL.Utils.*;

/**
 * <pre>
 *            ┌┐ ┌─┐┌┬┐┌┬┐┬  ┌─┐┌─┐┬ ┬┬┌─┐
 *       ───  ├┴┐├─┤ │  │ │  ├┤ └─┐├─┤│├─┘  ───
 *  ────────  └─┘┴ ┴ ┴  ┴ ┴─┘└─┘└─┘┴ ┴┴┴    ────────
 *              COMMAND-LINE EDITION V.1
 * <pre/>
 *
 * @author Pedro Marín Sanchis
 * @version V.1
 * @since 13/01/2023
 *
 */
public class BattleshipCL {

	private static Scanner inputValue = new Scanner(System.in);
	public static boolean RENDER_COLORS = false;

	public static void main(String[] args) {
		parseArgs(args);
		RENDER_COLORS = true;
		TerminalUtils.hideCursor();
		showTitle();
		menu(); // <-- HAS A WHILE (TRUE) LOOP, MAIN PROGRAM LOOP IS HERE.
	}

	private static void parseArgs(String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("--COLOR")) {
				RENDER_COLORS = true;
			}
		}
	}

	private static void enterToContinue() {
		System.out.print("Press ENTER to continue:");
		inputValue.nextLine();
	}

	/* @deprecated
	 * private static void showMessage(String message) {
	 * TerminalUtils.cls();
	 * System.out.println(message);
	 * enterToContinue();
	 * }
	 *
	 * @deprecated
	 * private static String askUserForString(String message) {
	 * TerminalUtils.cls();
	 * System.out.print(message);;
	 * return inputValue.nextLine();
	 * }
	 */

	/**
	 * @param message Message to be displayed to the user.
	 * @return TRUE upon user entering "Y" or "y". Needs to use a class defined Scanner.
	 */
	private static boolean askUserForConfirmation(String message) {
		TerminalUtils.cls();
		System.out.print(message);
		return (inputValue.nextLine().equalsIgnoreCase("Y"));
	}

	private static void showTitle() {
		TerminalUtils.cls();
		StringUtils.revealString(Cons.MENU_TEXT, 10);
		inputValue.nextLine();
	}

	private static void showLegend() {
		TerminalUtils.cls();
		StringUtils.revealString("   LEGEND\n\n███  SHIP \n\n▓╬▓  HIT  \n\n X   MISS \n\n ·   EMPTY\n\n", 25);
		enterToContinue();
	}

	private static void showExitMessage() {
		TerminalUtils.cls();
		StringUtils.revealString(Cons.GOODBYE_TEXT, 5);
		inputValue.nextLine();
		TerminalUtils.cls();
	}

	private static void showMenuText() {
		TerminalUtils.cls();
		System.out.println(StringUtils.surroundStringWithBox(
			" 1.- Play Classic | 2.- Play Against CPU | 3.- Show Legend | 4.- Quit Game "));
		System.out.print(StringUtils.surroundStringWithBox(
			" ENTER CHOICE:" + " ".repeat(11)));
		TerminalUtils.moveCursorUp(1);
		TerminalUtils.moveCursorBack(11);
	}

	private static void menu() {
		while (true) {
			showMenuText();
			switch (inputValue.nextLine()) {
				case "1":
					playSingleplayer(true);
					break;
				case "2":
					playSingleplayer(false);
					break;
				case "3":
					showLegend();
					break;
				case "4":
					showExitMessage();
					inputValue.close(); // Close Scanner before exiting.
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}

	private static void playSingleplayer(boolean classicMode) {
		String playerColor = ConsoleColors.WHITE;
		String enemyColor = ConsoleColors.RED;
		int boardSize = Cons.CPU_MODE_BOARD_SIZE;
		int shotNumber = 9999;
		Ship[] fleet = Cons.CPU_MODE_FLEET;
		ArrayList<Player> players = new ArrayList<>();
		if (RENDER_COLORS) {
			if (!classicMode && !askUserForConfirmation("· Use default board color? [Y/N]: ")) {
				playerColor = chooseColor();
			}
		}
		if (classicMode) {
			fleet = Cons.CLASSIC_MODE_FLEET;
			boardSize = Cons.CLASSIC_MODE_BOARD_SIZE;
			shotNumber = Cons.CLASSIC_MODE_SHOT_COUNT;
		}
		players.add(new LocalPlayer(new Board(boardSize, boardSize, "YOUR FLEET"), playerColor, inputValue, RENDER_COLORS, classicMode));
		players.add(new CPUPlayer(new Board(boardSize, boardSize, "ENEMY FLEET"), enemyColor));
		Game localGame = new LocalGame(players, shotNumber, fleet);
		localGame.startGame();
		enterToContinue();
	}

	private static String chooseColor() {
		while (true) {
			TerminalUtils.cls();
			System.out.println(StringUtils.surroundStringWithBox("      COLOR  TABLE      "));
			System.out.println("    · 1: " + ConsoleColors.RED + "███" + ConsoleColors.RESET + " Red.\n" +
								"    · 2: " + ConsoleColors.GREEN + "███" + ConsoleColors.RESET + " Green.\n" +
								"    · 3: " + ConsoleColors.YELLOW + "███" + ConsoleColors.RESET + " Yellow.\n" +
								"    · 4: " + ConsoleColors.BLUE + "███" + ConsoleColors.RESET + " Blue.\n" +
								"    · 5: " + ConsoleColors.PURPLE + "███" + ConsoleColors.RESET + " Purple.\n" +
								"    · 6: " + ConsoleColors.CYAN + "███" + ConsoleColors.RESET + " Cyan.\n" +
								"    · 7: " + ConsoleColors.WHITE + "███" + ConsoleColors.RESET + " White.");
			System.out.print(StringUtils.surroundStringWithBox(" CHOSE A COLOR:         "));
			TerminalUtils.moveCursorUp(1);
			TerminalUtils.moveCursorBack(9);
			switch (inputValue.nextLine()) {
				case "1":
					TerminalUtils.cls();
					return ConsoleColors.RED;
				case "2":
					TerminalUtils.cls();
					return ConsoleColors.GREEN;
				case "3":
					TerminalUtils.cls();
					return ConsoleColors.YELLOW;
				case "4":
					TerminalUtils.cls();
					return ConsoleColors.BLUE;
				case "5":
					TerminalUtils.cls();
					return ConsoleColors.PURPLE;
				case "6":
					TerminalUtils.cls();
					return ConsoleColors.CYAN;
				case "7":
					TerminalUtils.cls();
					return ConsoleColors.WHITE;
			}
		}
	}
}