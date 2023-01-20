package BattleshipCL;

import java.util.ArrayList;
import java.util.Map;

import BattleshipCL.Cell.CellType;
import BattleshipCL.Utils.StringUtils;

public class Board {
    
    final private int MAX_ROWS = 50;
    final private int MIN_ROWS = 8;
    final private int MAX_COLUMNS = StringUtils.ALPHABET.length;
    final private int MIN_COLUMNS = 8;

    private String boardName;
    protected String boardColor;

    protected Cell[][] cells;
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    public Board(String boardName, String boardColor) {
        this.cells = new Cell[MIN_ROWS][MIN_COLUMNS]; // Row, Column.
        this.boardName = boardName;
        this.boardColor = boardColor;
        populateCells();
    }

    public Board(int rows, int columns, String boardName, String boardColor) {
        
        if (rows >= MAX_ROWS) {rows = MAX_ROWS;}
        if (columns >= MAX_COLUMNS) {columns = MAX_COLUMNS;}

        if (rows <= MIN_ROWS) {rows = MIN_ROWS;}
        if (columns <= MIN_COLUMNS) {columns = MIN_COLUMNS;}

        this.cells = new Cell[rows][columns]; // Row, Column.
        populateCells();
        this.boardName = boardName;
        this.boardColor = boardColor;

    }

    public void populateCells() { // Fills cells array with Cell objects.
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++){
                cells[i][j] = new Cell();
            }
        } 
    }

    private String toString() {
        String[] alphabeticalIndexingArray = generateAlphabeticalIndexingArray(getColumnNumber());
        String[] numericalIndexingArray = generateNumericalIndexingArray(getRowNumber());
        String[] cellStringArray = generateCellStringArray();

        String[] combinedArrays = new String[alphabeticalIndexingArray.length + cellStringArray.length + 1]; // + 1 for Name Tag.

        for (int i = 0; i < combinedArrays.length; i++) { // Set all elements as empty String instead of NULL.
            combinedArrays[i] = "";
        }
        
        for (int i = 0; i < numericalIndexingArray.length; i++) {
            combinedArrays[i+1] = numericalIndexingArray[i];
        }

        for (int i = 0; i < combinedArrays.length - alphabeticalIndexingArray.length - 1; i++) {
            combinedArrays[i] = cellStringArray[i-1] + numericalIndexingArray[i-1];
        }

    }

    private String[] generateAlphabeticalIndexingArray(int columns) {
        String[] indexingRow = new String[1];
        indexingRow[0] = "";

        for (int i = 0; i < columns; i++) {
            indexingRow[0] = indexingRow[0] + " " + StringUtils.ALPHABET[i] + " ";
        }
        return StringUtils.surroundStringArrayWithBox(indexingRow);
    }

    private String[] generateNumericalIndexingArray(int rows) {
        String[] indexingColumn = new String[rows];

        for (int i = 0; i < rows; i++) {
            indexingColumn[i] = " " + i + " ";
        }

        return StringUtils.surroundStringArrayWithBox(indexingColumn);

    }

    private String[] generateCellStringArray() {

        String[] cellStringArray = new String[getColumnNumber()];

        for (int i = 0; i < cellStringArray.length; i++) { // Set all elements as empty String instead of NULL.
            cellStringArray[i] = "";
        }

        for (int i = 0; i < getRowNumber(); i++) { // Incorporate all cells.
            for (int j = 0; j < getColumnNumber(); j++){
                cellStringArray[i] = cellStringArray[i] + cells[i][j].toString();
            }
        }

        return StringUtils.surroundStringArrayWithBox(cellStringArray);

    }

    public boolean addShip(Ship ship, int row, int column) {
        
        boolean canAddShip = true;

        if (!ship.isPlaced()) {

            for (int i = 0; i < ship.getHeight(); i++) {
                for (int j = 0; j < ship.getWidth(); j++) {
                    if (!canPlaceShipCell(row + i, column + j)) {
                        canAddShip = false;
                    }
                }
            }
    
            if (canAddShip) {
                
                ships.add(ship);
                for (int i = 0; i < ship.getHeight(); i++) { 
                    for (int j = 0; j < ship.getWidth(); j++) {
                        cells[row + i][column + j].setCellType(Cell.CellType.SHIP);
                        addSafezone(row + i, column + j);
                    }
                }
    
                ship.setRow(row);
                ship.setColumn(column);
                ship.setPlaced(true);

                //System.out.println("Placed on board " + boardName + " ship h" + ship.getHeight() + " w " + ship.getWidth() + " row " + row + " col " + column);
    
            }
    
            return canAddShip;

        } else {

            return false;

        }
    
    }

    public void removeShip(Ship ship) {

        if (ships.contains(ship)) {

            int row = ship.getRow();
            int column = ship.getColumn();
    
            for (int i = 0; i < ship.getHeight(); i++) { 
                for (int j = 0; j < ship.getWidth(); j++) {
                    cells[row + i][column + j].setCellType(Cell.CellType.EMPTY);
                    removeSafezone(row + i, column + j);
                }
            }
    
            ships.remove(ship);
            
        }

    }

    public void addSafezone(int row, int column) {
        surroundShipWithCell(row, column, Cell.CellType.SAFETY);
    }

    public void removeSafezone(int row, int column) {
        surroundShipWithCell(row, column, Cell.CellType.EMPTY);
    }

    public void surroundShipWithCell(int row, int column, Cell.CellType cellType) { // Turns all cells around input coordinates into SAFETY or EMPTY (depending on "add boolean").
        // Move to upper left corner
    
        row--;
        column--;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellInBounds(row+i, column+j)) {
                    if (!cells[row+i][column+j].isShip()) {
                        cells[row+i][column+j].setCellType(cellType);
                    }
                }
            }
        }
    }

    public boolean isCellInBounds(int row, int column) {
        return (row < getRowNumber() && column < getColumnNumber());
    }

    public boolean canPlaceShipCell(int row, int column) {

        return (isCellInBounds(row, column) && cells[row][column].canPlaceShip());

    }

    public int getAliveShipNumber() {
        
        int shipAliveNumber = 0;

        for (int i = 0; i < ships.size(); i++) {
            if (isShipAlive(ships.get(i))) {shipAliveNumber++;}
        }

        return shipAliveNumber;
    }

    public boolean isShipAlive(Ship ship) {
        for (int i = 0; i < ship.getHeight(); i++) {
            for (int j = 0; j < ship.getWidth(); j++) {
                if (cells[ship.getRow() + i][ship.getColumn() + j].isShip()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardColor() {
        return boardColor;
    }

    public void setBoardColor(String boardColor) {
        this.boardColor = boardColor;
    }

    public int getColumnNumber() {
        return cells[0].length;
    }

    public int getRowNumber() {
        return cells.length;
    }
    
}
