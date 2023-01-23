package BattleshipCL.Game;

import java.util.ArrayList;

import BattleshipCL.Game.Primitive.Ship;

public class LocalGame extends Game{

    public LocalGame(ArrayList players, int shotNumber, Ship[] fleet) {
        super(players, shotNumber, fleet);
    }

    protected void doTurn() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).doTurn();
        }
    }

}
