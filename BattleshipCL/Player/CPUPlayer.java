package BattleshipCL.Player;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

public class CPUPlayer extends Player {

    public CPUPlayer(String name, String boardColor) {
        super(name, boardColor);
    }

    public CPUPlayer(Board board, String boardColor) {
        super(board, boardColor);
    }

    public int[] getNextShot(BoardView board) {

        int[] coordinates = new int[]{0,0};

        coordinates[0] = (int) (Math.random()*(board.getRowNumber()));
        coordinates[1] = (int) (Math.random()*(board.getColumnNumber()));

        /*
         * 
         * 
         * TODO: Real Enemy AI
         * 
         * 
         */

        return coordinates;
        
    }

    public void showBoard() {
        
        
    
    }
    
}
