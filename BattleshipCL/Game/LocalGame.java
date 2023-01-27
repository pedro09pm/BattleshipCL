package BattleshipCL.Game;

import java.util.ArrayList;
import BattleshipCL.Game.Primitive.Ship;

/**
 *
 * Defines local game behaviour.
 *
 * @see Game
 * 
 * @author Pedro Marín Sanchis
 * @version V.0
 * @since 22/01/2023
 *
 */
public class LocalGame extends Game{

	public LocalGame(ArrayList players, int shotNumber, Ship[] fleet) {
        super(players, shotNumber, fleet);
	}

	public void startGame() {

		gameInProgressFlag = true;
		boolean winCondition = false;

		while (!winCondition) {

			turnNumber++;

			for(int i = 0; i < players.size(); i++) {
				winCondition = checkWinCondition();
				if (players.get(i).isAlive()) {
					players.get(i).doTurn();
				} else {
					players.get(i).showDefeat();
				}
			}
		}

		showAfterGame();

	}

	protected boolean checkWinCondition() {

		int alivePlayerCount = 0;

		for(int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive()) {
				alivePlayerCount++;
			}
		}

		return (alivePlayerCount <= 1);

	}

	protected void showAfterGame() {
        for(int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive()) {
				players.get(i).showVictory();
			}
		}
    }
}
