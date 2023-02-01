package BattleshipCL.Player;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;

public class CPUPlayer extends Player {

    public CPUPlayer(String name, String playerColor) {
        super(name, playerColor);
    }

    public CPUPlayer(Board board, String playerColor) {
        super(board, playerColor);
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
        // DO NOTHING.
    }

    public void showVictory() {
        // TODO Auto-generated method stub
    }

    public void showDefeat() {
        // TODO Auto-generated method stub
    }

}
