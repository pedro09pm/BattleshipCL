package BattleshipCL.Game.Primitive;

import BattleshipCL.Utils.ConsoleColors;

/**
 *
 * The cell class is a primitive for the Board class. It has en enum called CellType that determines the state of a cell.
 *
 * @author Pedro Marín Sanchis
 * @version V.1
 * @since 13/01/2023
 *
 */
public class Cell {

	/**
	 * <p>CellType enum: Determines the state of a cell (Empty, Ship, Miss...). It's values have the attributes "cellString" and "color" that hold the text representation of the cell and the ASCII color code of the cell respectively.<p/>
	 */
    public static enum CellType {

        SHIP("███", ConsoleColors.WHITE),
        HIT("▓╬▓", ConsoleColors.WHITE),
        SAFETY(" · ",  ConsoleColors.WHITE),
        EMPTY(" · ", ConsoleColors.GREY_8),
        MISS(" X ", ConsoleColors.WHITE);

        private final String cellString;
        private final String color;

        CellType(String cellString, String color) {
            this.cellString = cellString;
            this.color = color + cellString + ConsoleColors.RESET;
        }

        public String toString() {
            return cellString;
        }

        public String getColoredCell() {
            return color;
        }

    }

    private CellType type = CellType.MISS;

    public Cell() {
        this.type = CellType.EMPTY;
    }

    public Cell(CellType type) {
        this.type = type;
    }

    public String toString() {
        return type.toString();
    }

    public void setCellType(CellType type) {
        this.type = type;
    }

    public CellType getCellType() {
        return type;
    }

    public boolean isShip() {
        return (type == CellType.SHIP);
    }

    public boolean isSea() {
        return (type == CellType.EMPTY || type == CellType.SAFETY || type == CellType.MISS);
    }

    public boolean isHitShip() {
        return (type == CellType.HIT);
    }

    public boolean isSafezone() {
        return (type == CellType.SAFETY);
    }

    public boolean canPlaceShip() {
        return (!(type == CellType.SHIP || type == CellType.SAFETY));
    }

}
