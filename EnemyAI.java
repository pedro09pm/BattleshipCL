// 18/01/2023 Pedro Marín Sanchis

public class EnemyAI extends Player{
    
    public EnemyAI(String name, boolean RENDER_COLORS) {
        super(name, RENDER_COLORS);
    }

    public EnemyAI(Board board, boolean RENDER_COLORS) {
        super(board, RENDER_COLORS);
    }

    public void doTurn() {

        for (int turnNumber = 0; turnNumber < super.boardViews.size(); turnNumber++) {
            
            if (boardViews.get(turnNumber).getShotsLeft() > 0) {
            
                shoot(boardViews.get(turnNumber));
            
            }

        }

    }

    public static Boolean shoot(BoardView board) {

        int row = (int) (Math.random()*(board.getRowNumber()));
        int column = (int) (Math.random()*(board.getColumnNumber()));
        return board.revealCell(row, column);

        /*
         * 
         * 
         *  TODO: Real Enemy AI
         * 
         * 
         */

    }
}
