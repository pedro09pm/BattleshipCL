package BattleshipCL.Network;

import java.util.ArrayList;

import BattleshipCL.Game.Game;
import BattleshipCL.Game.Primitive.Ship;

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
    
}