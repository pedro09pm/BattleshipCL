import java.util.Scanner;

public class EnemyAI extends Player{
    
    public EnemyAI(String name, boolean RENDER_COLORS) {
        super(name, RENDER_COLORS);
    }

    @Override
    public void doTurn(Scanner inputValue, boolean RENDER_COLORS) {

        for (int turnNumber = 0; turnNumber < super.boardViews.size(); turnNumber++) {

            shoot(boardViews.get(turnNumber));

        }

    }

    @Override
    public static Boolean shoot(BoardView board) {

        

    }
}
