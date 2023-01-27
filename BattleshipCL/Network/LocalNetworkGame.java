package BattleshipCL.Network;

import java.util.ArrayList;

import BattleshipCL.Game.Game;
import BattleshipCL.Game.Primitive.Ship;

/**
 *
 * Defines local network game behaviour.
 *
 * @see Game
 * 
 * @author Pedro Marín Sanchis
 * @version V.0
 * @since 22/01/2023
 *
 */
public class LocalNetworkGame extends Game {

    public LocalNetworkGame(ArrayList players, int shotNumber, Ship[] fleet) {
        super(players, shotNumber, fleet);
    }

    protected void doTurn() {
        

        
    }

    protected void showAfterGame() {

		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.size(); j++) {
                if (players.get(i).isAlive()) {
                    players.get(i).showVictory();
                }
			}
		}
        
    }

    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    protected boolean checkWinCondition() {
        // TODO Auto-generated method stub
        return false;
    }
    
}