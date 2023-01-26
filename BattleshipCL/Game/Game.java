package BattleshipCL.Game;

import java.util.ArrayList;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;
import BattleshipCL.Game.Primitive.Ship;
import BattleshipCL.Player.*;

/**
 *
 * Abstract class that defines game behaviour.
 *
 * @author Pedro Marín Sanchis
 * @version V.0
 * @since 22/01/2023
 *
 */
abstract public class Game {

	protected ArrayList<Player> players = new ArrayList<>();
	private Ship[] fleet;
	private int turnNumber = 0;
	private int shotNumber = 0;
	private boolean gameInProgressFlag = false;

	public Game(ArrayList players, int shotNumber, Ship[] fleet) {
		this.players = players;
		this.shotNumber = shotNumber;
		this.fleet = fleet;
		addBoardViewsToPlayers();
		addShipsToPlayers();
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

	private boolean checkWinCondition() {

		int alivePlayerCount = 0;

		for(int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive()) {
				alivePlayerCount++;
			}
		}

		return (alivePlayerCount <= 1);

	}

	private void showAfterGame() {
        for(int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive()) {
				players.get(i).showVictory();
			}
		}
    }

	public void addBoardViewsToPlayers() {

		if (gameInProgressFlag) {return;}

		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.size(); j++) {
				if (!(players.get(j).getBoard().equals(players.get(i).getBoard()))) {

					players.get(i).addBoardView(new BoardView(players.get(j).getBoard(), shotNumber, players.get(i).getPlayerColor()));

				}
			}
		}
	}

	private void addShipsToPlayers() {
		for(int i = 0; i < players.size(); i++) {
			placeShips(players.get(i).getBoard(), fleet);
		}
	}

	private static void placeShips(Board board, Ship[] ships) {
		int rowNumber = board.getRowNumber();
		int columnNumber = board.getColumnNumber();
		int row;
		int column;
		boolean rotate;

		int allowedTries = 100;

		ships = Ship.cloneShipArray(ships);

		boolean allShipsPlaced = false;

		while (!allShipsPlaced) {

			allShipsPlaced = true;
			System.gc();

			for (Ship i : ships) { // Reset for next try.
				i.setPlaced(false);
				board.removeShip(i);
			}

			for (Ship i : ships) {
				for (int j = 0; j < allowedTries; j++) {

					rotate = (Math.random()) > 0.5; // Boolean condition to rotate ships.

					if (rotate) {
						i.rotate();
					}

					row = (int) (Math.random() * (rowNumber));
					column = (int) (Math.random() * (columnNumber));
					board.addShip(i, row, column);

				}
			}

			for (Ship i : ships) {

				if (!i.isPlaced()) {

					allShipsPlaced = false;

				}

			}
		}
	}
}
