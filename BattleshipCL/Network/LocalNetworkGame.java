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
    
}