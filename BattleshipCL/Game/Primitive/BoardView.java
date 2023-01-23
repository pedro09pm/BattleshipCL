/* 13/01/2023 - Pedro Marín Sanchis

BoardView stores knowledge of another board. The whole point is to be able to have many instaces of a viewer
for a single board (Suppose a 3v3. One player shouldn't know the other's shots, since fog of war is the whole
point of the game.)

*/

package BattleshipCL.Game.Primitive;

import BattleshipCL.Utils.StringUtils;

public class BoardView extends Board {

    protected Board originalBoard;
    
    private int shotsMade;
    private int shotsHit;
    private int shotsLeft;

    public BoardView(Board oriniginalBoard, int shotNumber) {

        super(oriniginalBoard.getRowNumber(), oriniginalBoard.getColumnNumber(), oriniginalBoard.getBoardName());
        this.originalBoard = oriniginalBoard;
        this.shotsLeft = shotNumber;

    }

    public boolean revealCell(int row, int column) { // Updates view and updates original if it is populated (With a SHIP cell)

        shotsLeft--;

        if (!originalBoard.isCellInBounds(row, column)) {
            return false;
        }

        if (originalBoard.isShipCell(row, column)) {

            cells[row][column].setCellType(Cell.CellType.HIT); // Set this cell to HIT cell.
            originalBoard.cells[row][column].setCellType(Cell.CellType.HIT); // Set original board cell to HIT cell.

            shotsMade++;
            shotsHit++;
            return true;

        }
        
        if (cells[row][column].isHitShip()) {
            cells[row][column].setCellType(Cell.CellType.HIT);
            shotsMade++;
            return false;
        }

        if (cells[row][column].isSea()) {
            cells[row][column].setCellType(Cell.CellType.MISS);
            originalBoard.cells[row][column].setCellType(Cell.CellType.MISS);
            shotsMade++;
        }

        return false;

    }

    public void revealAll() { // Reveals all cells and ruins the fun.

        for (int i = 0; i < getRowNumber(); i++) {
            for (int j = 0; j < getColumnNumber(); j++) {
                cells[i][j].setCellType(originalBoard.cells[i][j].getCellType());
            }
        }

    }

    public String formatBoard(String[] message, Boolean addStats) {

        String boardString = super.toString();
        String messageString = StringUtils.stringArrayToString(formatMessageToBoardSize(message));

        if (addStats) {boardString = addStatisticsToBoardString(boardString);}

        return boardString + "\n" + messageString;

    }

    public String[] formatMessageToBoardSize(String[] string) {
        
        string = StringUtils.padToSameLength(string, ' ');
        int boardLength = super.getBoardToStringLength();

        if (string[0].length() < boardLength) {
            StringUtils.appendToStringArray(string, " ".repeat(boardLength - string[0].length()));
        }

        return StringUtils.surroundStringArrayWithBox(string);

    }

    private String addStatisticsToBoardString(String boardString) {

        String[] boardStringArray = boardString.split("\n");
        boardStringArray[5] = boardStringArray[5] + " > Ships left: " + (originalBoard.getAliveShipNumber());
        boardStringArray[6] = boardStringArray[6] + " > Shots made: " + getShotsMade();
        boardStringArray[7] = boardStringArray[7] + " > Shots hit: " + getShotsHit();

        return StringUtils.stringArrayToString(StringUtils.padToSameLength(boardStringArray, ' '));

    }

    public int getShotsMade() {
        return shotsMade;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }

    
}
