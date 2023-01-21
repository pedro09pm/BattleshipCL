package BattleshipCL.Game.Primitive;

import BattleshipCL.Utils.ConsoleColors;

public class Cell {

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

        public String getCellColor() {
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