package BattleshipCL;

public class Ship {
    
    private boolean placed = false;
    private int height;
    private int width;

    private int row;
    private int column;

    public Ship() {
        this.height = 1;
        this.width = 1;
    }

    public Ship(int height, int width) {
        if (height <= 0) {height = 1;}
        if (width <= 0) {width = 1;}

        this.height = height;
        this.width = width;
    }

    public Ship clone() {
        return new Ship(height, width);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void rotate() {
        if (!placed) {
            int flip;
            flip = height;
            height = width;
            width = flip;
        }
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}

