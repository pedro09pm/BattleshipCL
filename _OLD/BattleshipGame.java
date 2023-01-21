package _OLD;
// 13/01/2023 - Pedro Marín Sanchis

// BATTLESHIP COMMAND-LINE EDITION V0

import java.util.Scanner;
import BattleshipCL.Utils.*;
import BattleshipCL.Game.*;
import BattleshipCL.Game.Primitive.Ship;

public class BattleshipGame {

    private static Scanner inputValue = new Scanner(System.in);

    public static void main(String[] args) {
        TerminalUtils.hideCursor();
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
        StringUtils.revealString(Cons.MENU_TEXT, 10);
        inputValue.nextLine();
    }

    private static void showVictory() {
        TerminalUtils.moveCursorToEnd();
        if (Cons.RENDER_COLORS) {System.out.print(ConsoleColors.GREEN);}
        StringUtils.revealString(Cons.VICTORY_TEXT, 5);
        if (Cons.RENDER_COLORS) {System.out.print(ConsoleColors.RESET);}
        enterToContinue();
    }

    private static void showDefeat() {
        TerminalUtils.moveCursorToEnd();
        if (Cons.RENDER_COLORS) {System.out.print(ConsoleColors.RED);}
        StringUtils.revealString(Cons.DEFEAT_TEXT, 5);
        if (Cons.RENDER_COLORS) {System.out.print(ConsoleColors.RESET);}
        enterToContinue();
    }

    private static void showLegend() {
        TerminalUtils.cls();
        StringUtils.revealString("   LEGEND\n\n███  SHIP \n\n▓╬▓  HIT  \n\n X   MISS \n\n ·   EMPTY\n\n", 50);
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
            switch(inputValue.nextLine()) {
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
                " 1.- Play Classic | 2.- Play Against CPU | 3.- ??? | 4.- Quit Game "));
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

        if (Cons.RENDER_COLORS && !classicMode) {
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

        /*Player player = new Player(new Board(boardSize, boardSize, "YOUR FLEET", Constants.RENDER_COLORS, playerColor), Constants.RENDER_COLORS);
        EnemyAI enemy = new EnemyAI(new Board(boardSize, boardSize, "ENEMY FLEET", Constants.RENDER_COLORS, enemyColor), Constants.RENDER_COLORS);*/
        enemy.getBoard().setBoardColor(ConsoleColors.RED);

        player.addBoardView(new BoardView(enemy.getBoard(), shotNumber));
        enemy.addBoardView(new BoardView(player.getBoard(), shotNumber));

        placeShips(player.getBoard(), fleet);
        placeShips(enemy.getBoard(), fleet);

        while (true) {

            if(enemy.getBoard().getAliveShipNumber() == 0) {
                showVictory();
                break;
            }

            if(player.getBoardViews().get(0).getShotsLeft() == 0) {
                showDefeat();
                break;
            }

            TerminalUtils.cls();
            if (classicMode) {
                player.doTurn(inputValue, Cons.RENDER_COLORS, true);
            } else {
                player.showBoard(inputValue, Cons.RENDER_COLORS);
                player.doTurn(inputValue, Cons.RENDER_COLORS, false);
                enemy.doTurn();
            }

        }

    }

    private static void placeShips(Board board, Ship[] ships) {
        int rowNumber = board.getRowNumber();
        int columnNumber = board.getColumnNumber();
        int row;
        int column;
        boolean rotate;

        int allowedTries = 100;

        ships = Ship.cloneShipArray(ships);
        
        boolean allShipsPlaced = false;

        while (!allShipsPlaced) {

            allShipsPlaced = true;
            System.gc();

            for (Ship i: ships) { // Reset for next try.
                i.setPlaced(false);
                board.removeShip(i);
            }

            for (Ship i: ships) {
                for(int j = 0; j < allowedTries; j++) {

                    rotate = (Math.random()) > 0.5; // Boolean condition to rotate ships.

                    if (rotate) {i.rotate();}

                    row = (int) (Math.random()*(rowNumber));
                    column = (int) (Math.random()*(columnNumber));
                    board.addShip(i, row, column);

                }
            }

            for (Ship i: ships) {
                
                if (!i.isPlaced()) {

                    allShipsPlaced = false;

                }

            }
        }
    }

    private static String chooseColor() {
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
