//          13/01/2023 - Pedro Marín Sanchis

/*
 *            ┌┐ ┌─┐┌┬┐┌┬┐┬  ┌─┐┌─┐┬ ┬┬┌─┐
 *       ───  ├┴┐├─┤ │  │ │  ├┤ └─┐├─┤│├─┘  ───
 *  ────────  └─┘┴ ┴ ┴  ┴ ┴─┘└─┘└─┘┴ ┴┴┴    ────────
 *              COMMAND-LINE EDITION V.0
 */

package BattleshipCL;

import java.util.ArrayList;
import java.util.Scanner;
import BattleshipCL.Game.*;
import BattleshipCL.Game.Primitive.*;
import BattleshipCL.Network.*;
import BattleshipCL.Player.*;
import BattleshipCL.Utils.*;

public class BattleshipCL {

	private static Scanner inputValue = new Scanner(System.in);
	public static boolean RENDER_COLORS = false;

	public static void main(String[] args) {
		parseArgs(args);
		if (askUserForConfirmation("PLAY IN COLOR? [Y/N]: ")) {
			RENDER_COLORS = true;
		}
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

	/*
	 * private static void showMessage(String message) {
	 * TerminalUtils.cls();
	 * System.out.println(message);
	 * enterToContinue();
	 * }
	 *
	 * private static String askUserForString(String message) {
	 * TerminalUtils.cls();
	 * System.out.print(message);;
	 * return inputValue.nextLine();
	 * }
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

	private static void showVictory() {
		TerminalUtils.moveCursorToEnd();
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.GREEN);
		}
		StringUtils.revealString(Cons.VICTORY_TEXT, 5);
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RESET);
		}
		enterToContinue();
	}

	private static void showDefeat() {
		TerminalUtils.moveCursorToEnd();
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RED);
		}
		StringUtils.revealString(Cons.DEFEAT_TEXT, 5);
		if (RENDER_COLORS) {
			System.out.print(ConsoleColors.RESET);
		}
		enterToContinue();
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

	private static void menu() {

		while (true) {
			showMenuText();
			switch (inputValue.nextLine()) {
				case "1":
					showLegend();
					playSingleplayer(true);
					break;

				case "2":
					showLegend();
					playSingleplayer(false);
					break;

				case "3":
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

	private static void showMenuText() {
		TerminalUtils.cls();

		System.out.println(StringUtils.surroundStringWithBox(
				" 1.- Play Classic | 2.- Play Against CPU | 3.- Local Network Multiplayer | 4.- Quit Game "));
		System.out.print(StringUtils.surroundStringWithBox(
				" ENTER CHOICE:" + " ".repeat(11)));

		TerminalUtils.moveCursorUp(1);
		TerminalUtils.moveCursorBack(11);
	}

	private static void playSingleplayer(boolean classicMode) {

		String playerColor = Cons.NO_COLOR_DEFAULT;
		String enemyColor = Cons.NO_COLOR_DEFAULT;
		int boardSize = Cons.CPU_MODE_BOARD_SIZE;
		int shotNumber = 9999;
		Ship[] fleet = Cons.CPU_MODE_FLEET;

		if (RENDER_COLORS && !classicMode) {
			if (!askUserForConfirmation("· Use default board color? [Y/N]: ")) {
				playerColor = chooseColor();
				enemyColor = ConsoleColors.RED;
			}
		}

		if (classicMode) {
			fleet = Cons.CLASSIC_MODE_FLEET;
			boardSize = Cons.CLASSIC_MODE_BOARD_SIZE;
			shotNumber = Cons.CLASSIC_MODE_SHOT_COUNT;
		}

		ArrayList<Player> players = new ArrayList<>();
		
		players.add(new LocalPlayer(new Board(boardSize, boardSize, "YOUR FLEET"), playerColor, inputValue));
		players.add(new CPUPlayer(new Board(boardSize, boardSize, "ENEMY FLEET"), enemyColor));

		Game localGame = new LocalGame(players, shotNumber, fleet);
		localGame.startGame();

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
