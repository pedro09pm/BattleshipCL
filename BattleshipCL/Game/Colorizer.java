package BattleshipCL.Game;

import BattleshipCL.Game.Primitive.Cell;
import BattleshipCL.Utils.ConsoleColors;

public class Colorizer {

    public static String colorizeBoard(String playerColor, String board) {

        board = playerColor + board;

        for (int i = 0; i < Cell.CellType.values().length; i++) {
            Cell.CellType cell = Cell.CellType.values()[i];
            board.replaceAll(cell.toString(), cell.getCellColor() + cell.toString() + playerColor);
        }

        return board + ConsoleColors.RESET;

    }

    public static String colorize(String string, String color) {
        return color + string + ConsoleColors.RESET;
    }

}
