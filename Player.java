// 13/01/2023 - Pedro Marín Sanchis

import java.util.ArrayList;
import java.util.Scanner;
import Utils.StringUtils;
import Utils.TerminalUtils;

public class Player {
    private Board board;
    protected ArrayList<BoardView> boardViews = new ArrayList<>();
    private String name;
    
    public Player(String name, boolean RENDER_COLORS) {
        this.board = new Board(name, RENDER_COLORS);
        this.name = name;
    }

    public Player(Board board, ArrayList<BoardView> boardViews) {
        this.board = board;
        this.boardViews = boardViews;
        this.name = board.getBoardName();
    }

    public ArrayList<BoardView> getBoardViews() {
        return boardViews;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public void addBoardView(BoardView boardView) {
        boardViews.add(boardView);
    }

    public void doTurn(Scanner inputValue, boolean RENDER_COLORS) {

        String boardString = "";

        for (int turnNumber = 0; turnNumber < boardViews.size(); turnNumber++) {
            boardString = boardViews.get(turnNumber).formatBoard(" ENTER FIRE COORDINATES:\n SHOTS LEFT: " + boardViews.get(turnNumber).getShotsLeft(), RENDER_COLORS);
            System.out.println(boardString);
            TerminalUtils.moveCursorUp(3);
            TerminalUtils.moveCursorForward(26);
            shoot(boardViews.get(turnNumber), inputValue);

            TerminalUtils.cls();

            boardString = boardViews.get(turnNumber).formatBoard(" ENTER TO CONITNUE: \n ", RENDER_COLORS);
            System.out.println(boardString);
            inputValue.nextLine();

            TerminalUtils.cls();
        }
    }

    public static Boolean shoot(BoardView board, Scanner inputValue) {
        
        String userInput = "";
        int row = 0;
        int column = 0;

        userInput = inputValue.nextLine();

        if (userInput.equalsIgnoreCase("dev_r")) { // Ruins the fun for the players. Makes life easy for developers...
            board.revealAllCheat();
            return false;
        }

        if (userInput.length() >= 2) {

            column  = StringUtils.ALPHABET_STRING.indexOf(StringUtils.removeNonLetters(userInput).toUpperCase());
            row = Integer.parseInt("0" + StringUtils.removeNonNumbers(userInput));

            if (column < 0) {column = 0;} // indexOf method returns -1 on "no match found" condition.
            if (row < 0) {row = 0;} // Just to be safe...
    
            if (row < board.getRowNumber() && column < board.getColumnNumber()) {
                return board.revealCell(row, column);
            }
        }
        
        return false;

    }

}
