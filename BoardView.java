/* 13/01/2023 - Pedro Marín Sanchis

BoardView stores knowledge of another board. The whole point is to be able to have many instaces of a viewer
for a single board (Suppose a 3v3. One player shouldn't know the other's shots, since fog of war is the whole
point of the game.)

*/

import Utils.StringUtils;
import Utils.ConsoleColors;

public class BoardView extends Board {

    protected Board originalBoard;
    
    private int shotsMade;
    private int shotsHit;
    private int shotsLeft;

    public BoardView(Board oriniginalBoard, int shotNumber) {
        super(oriniginalBoard.getRowNumber(), oriniginalBoard.getColumnNumber(), oriniginalBoard.getBoardName(),
                oriniginalBoard.colorizeCells, oriniginalBoard.boardColor);
        this.originalBoard = oriniginalBoard;
        this.shotsLeft = shotNumber;
    }

    public boolean revealCell(int row, int column) { // Updates view and updates original if it is populated (With a SHIP cell)

        shotsLeft--;

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

    public int getShotsMade() {
        return shotsMade;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }

    public String formatBoard(String message, boolean RENDER_COLORS) {

        String boardString = super.toString(false); // Important to keep ASCII colors out of the equation.
        int boardStringLength = boardString.split("\n")[0].length();
        int fireStringPadding = boardStringLength - StringUtils.getLongestString(message.split("\n")).length() - 2; // Set up this way in case of multi line messages. Used to make text box the same size as the board.

        // The following abomination takes fireString and adds a box around it while adding fireStringPadding.

        String fireString = StringUtils.stringArrayToString(     //  _  _             _ _    _       _         _                    _         
            StringUtils.surroundStringArrayWithBox(             //  | || |___ _ _ _ _(_) |__| |___  | |__ _  _| |_  __ __ _____ _ _| |__ ___  
                StringUtils.appendToStringArray(               //   | __ / _ \ '_| '_| | '_ \ / -_) | '_ \ || |  _| \ V  V / _ \ '_| / /(_-<_ 
                    StringUtils.padToSameLength(              //    |_||_\___/_| |_| |_|_.__/_\___| |_.__/\_,_|\__|  \_/\_/\___/_| |_\_\/__(_)
                        (message).split("\n"), ' '), " ".repeat(fireStringPadding))));

        boardString = boardString + "\n" + fireString;

        if (RENDER_COLORS) { // Colorize
            boardString = colorize(originalBoard.boardColor + boardString + ConsoleColors.RESET);
        }

        return boardString;

    }

    public String formatBoardWithStatistics(String message, boolean RENDER_COLORS) {

        return addStatisticsToBoardString(formatBoard(message, RENDER_COLORS));
    
    }

    private String addStatisticsToBoardString(String boardString) {
        String[] boardStringArray = boardString.split("\n");
        boardStringArray[5] = boardStringArray[5] + " > Shots made: " + getShotsMade();
        boardStringArray[6] = boardStringArray[6] + " > Shots hit: " + getShotsHit();
        boardStringArray[7] = boardStringArray[7] + " > Ships left: " + (originalBoard.getAliveShipNumber());
        return StringUtils.stringArrayToString(StringUtils.padToSameLength(boardStringArray, ' '));
    }

    
}
