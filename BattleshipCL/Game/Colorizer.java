package BattleshipCL.Game;

import BattleshipCL.Game.Primitive.Cell;
import BattleshipCL.Utils.ConsoleColors;

/**
 *
 * Class used to add ASCII color codes to Strings for terminal display.
 *
 * @see ConsoleColors
 *
 * @author Pedro Marín Sanchis
 * @version V.0
 * @since 22/01/2023
 *
 */
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
