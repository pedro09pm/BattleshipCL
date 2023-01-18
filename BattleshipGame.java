import java.util.Scanner;

import Utils.*;

// 13/01/2023 - Pedro Marín Sanchis

// BATTLESHIP COMMAND-LINE EDITION V0

public class BattleshipGame {

    private static final String MENU_TEXT = "          ┌┐ ┌─┐┌┬┐┌┬┐┬  ┌─┐┌─┐┬ ┬┬┌─┐          \n"+
                                            "     ───  ├┴┐├─┤ │  │ │  ├┤ └─┐├─┤│├─┘  ───     \n"+
                                            "────────  └─┘┴ ┴ ┴  ┴ ┴─┘└─┘└─┘┴ ┴┴┴    ────────\n"+
                                            "           COMMAND-LINE EDITION V.0\n \n \n           Press ENTER to continue:";
    private static final String VICTORY_TEXT = "\n"+"__      ___      _                   _         \n"+
                                                    "\\ \\    / (_)    | |                 | |      \n"+
                                                    " \\ \\  / / _  ___| |_ ___  _ __ _   _| |      \n"+
                                                    "  \\ \\/ / | |/ __| __/ _ \\| '__| | | | |     \n"+
                                                    "   \\  /  | | (__| || (_) | |  | |_| |_|       \n"+
                                                    "    \\/   |_|\\___|\\__\\___/|_|   \\__, (_)   \n"+
                                                    "                                __/ |          \n"+
                                                    "                               |___/           \n"+
                                                    "\n        ";
    private static final String DEFEAT_TEXT = "\n"+
                                                    " _____  ______ ______ ______       _______         \n"+
                                                    "|  __ \\|  ____|  ____|  ____|   /\\|__   __|      \n"+
                                                    "| |  | | |__  | |__  | |__     /  \\  | |          \n"+
                                                    "| |  | |  __| |  __| |  __|   / /\\ \\ | |         \n"+
                                                    "| |__| | |____| |    | |____ / ____ \\| |_ _ _     \n"+
                                                    "|_____/|______|_|    |______/_/    \\_\\_(_|_|_)   \n"+
                                                    "\n         ";
    private static final String GOODBYE_TEXT = "\n"+
                                                    " _______ _                 _                                    \n"+
                                                    "|__   __| |               | |                                  \n"+
                                                    "   | |  | |__   __ _ _ __ | | __  _   _  ___  _   _            \n"+
                                                    "   | |  | '_ \\ / _` | '_ \\| |/ / | | | |/ _ \\| | | |        \n"+
                                                    "   | |  | | | | (_| | | | |   <  | |_| | (_) | |_| |           \n"+
                                                    "   |_|  |_| |_|\\__,_|_| |_|_|\\_\\  \\__, |\\___/ \\__,_|     \n"+
                                                    " ______           _____  _         __/ |             _         \n"+
                                                    "|  ____|         |  __ \\| |       |___(_)           | |       \n"+
                                                    "| |__ ___  _ __  | |__) | | __ _ _   _ _ _ __   __ _| |        \n"+
                                                    "|  __/ _ \\| '__| |  ___/| |/ _` | | | | | '_ \\ / _` | |      \n"+
                                                    "| | | (_) | |    | |    | | (_| | |_| | | | | | (_| |_|        \n"+
                                                    "|_|  \\___/|_|    |_|    |_|\\__,_|\\__, |_|_| |_|\\__, (_)    \n"+
                                                    "                                  __/ |         __/ |          \n"+
                                                    "                                 |___/         |___/           \n"+
                                                    "\n                Press ENTER to quit:";                                               
                                                                                                
                                                
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static Scanner inputValue = new Scanner(System.in);
    private static final boolean RENDER_COLORS = true;

    private static final int CLASSIC_MODE_SHOT_COUNT = 30;
    private static final int CLASSIC_MODE_SHIP_AMMOUNT = 10;

    public static void main(String[] args) {
        TerminalUtils.hideCursor();
        playAgainstCPU();
        playClassic();
        showTitle();
        menu(); // <-- HAS A WHILE (TRUE) LOOP, MAIN PROGRAM LOOP IS HERE.
    }

    private static void enterToContinue() {
        System.out.print("Press ENTER to continue:");
        inputValue.nextLine();
    }

    /*private static void showMessage(String message) {
        TerminalUtils.cls();
        System.out.println(message);
        enterToContinue();
    }

    private static String askUserForString(String message) {
        TerminalUtils.cls();
        System.out.print(message);;
        return inputValue.nextLine();
    }*/

    private static boolean askUserForConfirmation(String message) {
        TerminalUtils.cls();
        System.out.print(message);
        return (inputValue.nextLine().equalsIgnoreCase("Y"));
    }

    private static void showTitle() {
        TerminalUtils.cls();
        StringUtils.revealString(MENU_TEXT, 10);
        inputValue.nextLine();
    }

    private static void showVictory() {
        TerminalUtils.moveCursorToEnd();
        if (RENDER_COLORS) {System.out.print(ConsoleColors.GREEN);}
        StringUtils.revealString(VICTORY_TEXT, 5);
        if (RENDER_COLORS) {System.out.print(ConsoleColors.RESET);}
        enterToContinue();
    }

    private static void showDefeat() {
        TerminalUtils.moveCursorToEnd();
        if (RENDER_COLORS) {System.out.print(ConsoleColors.RED);}
        StringUtils.revealString(DEFEAT_TEXT, 5);
        if (RENDER_COLORS) {System.out.print(ConsoleColors.RESET);}
        enterToContinue();
    }

    private static void showLegend() {
        TerminalUtils.cls();
        StringUtils.revealString("   LEGEND\n\n███  SHIP \n\n▓╬▓  HIT  \n\n X   MISS \n\n ·   EMPTY\n\n", 50);
        enterToContinue();
    }

    private static void showExitMessage() {
        TerminalUtils.cls();
        StringUtils.revealString(GOODBYE_TEXT, 5);
        inputValue.nextLine();
        TerminalUtils.cls();
    }

    private static void menu() {

        while (true) {
            showMenuText();
            switch(inputValue.nextLine()) {
                case "1":
                    showLegend();
                    playClassic();
                    break;
    
                case "2":
                    showLegend();
                    playAgainstCPU();
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
                " 1.- Play Classic | 2.- Play Against CPU | 3.- ??? | 4.- Quit Game "));
        System.out.print(StringUtils.surroundStringWithBox(
                " ENTER CHOICE:" + " ".repeat(11)));

        TerminalUtils.moveCursorUp(1);
        TerminalUtils.moveCursorBack(11);
    }

    private static void playClassic() {

        /* In classic mode you only see the enemy board. It's implemented in
        a way that you actually shoot the board you're controlling. */

        Player player = new Player("ENEMY", RENDER_COLORS);
        if (RENDER_COLORS) {
            if (!askUserForConfirmation("· Use default board color? [Y/N]: ")) {
                    player.getBoard().setBoardColor(choseColor());
            }
        }
        player.addBoardView(new BoardView(player.getBoard(), CLASSIC_MODE_SHOT_COUNT));

        placeShipsClassic(player.getBoard());

        while (true) {

            if(player.getBoard().getAliveShipNumber() == 0) {
                showVictory();
                break;
            }

            if(player.getBoard().getAliveShipNumber() != 0 && player.getBoardViews().get(0).getShotsLeft() == 0) {
                showDefeat();
                break;
            }

            TerminalUtils.cls();
            player.doTurn(inputValue, RENDER_COLORS);

        }

    }

    private static void placeShipsClassic(Board board) {

        int rowNumber = board.getRowNumber();
        int columnNumber = board.getColumnNumber();

        int row;
        int column;

        while (board.getAliveShipNumber() != CLASSIC_MODE_SHIP_AMMOUNT) {

            row = (int) (Math.random()*(rowNumber));
            column = (int) (Math.random()*(columnNumber));
            board.addShip(new Ship(), row, column);

        }

    }

    private static void playAgainstCPU() {

        Player player = new Player("ENEMY", RENDER_COLORS);
        if (RENDER_COLORS) {
            if (!askUserForConfirmation("· Use default board color? [Y/N]: ")) {
                    player.getBoard().setBoardColor(choseColor());
            }
        }

        Player enemy = new Player("ENEMY", RENDER_COLORS);
        enemy.getBoard().setBoardColor(ConsoleColors.RED);

        player.addBoardView(new BoardView(enemy.getBoard(), 9999));
        enemy.addBoardView(new BoardView(player.getBoard(), 9999));

        placeShipsClassic(player.getBoard());
        placeShipsClassic(enemy.getBoard());

        while (true) {

            if(enemy.getBoard().getAliveShipNumber() == 0) {
                showVictory();
                break;
            }

            if(enemy.getBoard().getAliveShipNumber() != 0 && player.getBoardViews().get(0).getShotsLeft() == 0) {
                showDefeat();
                break;
            }

            TerminalUtils.cls();
            player.doTurn(inputValue, RENDER_COLORS);
            enemy.doTurn(inputValue, RENDER_COLORS);

        }

    }

    private static String choseColor() {
        while (true) {
            
            TerminalUtils.cls();
            System.out.println(StringUtils.surroundStringWithBox("      COLOR  TABLE      "));
            System.out.println( "    · 1: " + ConsoleColors.RED + "███" + ConsoleColors.RESET + " Red.\n"+
                                "    · 2: " + ConsoleColors.GREEN + "███" + ConsoleColors.RESET + " Green.\n"+
                                "    · 3: " + ConsoleColors.YELLOW + "███" + ConsoleColors.RESET + " Yellow.\n"+
                                "    · 4: " + ConsoleColors.BLUE + "███" + ConsoleColors.RESET + " Blue.\n"+
                                "    · 5: " + ConsoleColors.PURPLE + "███" + ConsoleColors.RESET + " Purple.\n"+
                                "    · 6: " + ConsoleColors.CYAN + "███" + ConsoleColors.RESET + " Cyan.\n"+
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
