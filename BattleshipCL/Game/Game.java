package BattleshipCL.Game;

import java.util.ArrayList;

import BattleshipCL.Game.Primitive.Board;
import BattleshipCL.Game.Primitive.BoardView;
import BattleshipCL.Game.Primitive.Ship;
import BattleshipCL.Player.*;

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
				players.get(i).doTurn();
				winCondition = checkWinCondition();
			}
		}

		// TODO: After game.

	}

	private boolean checkWinCondition() {

		int alivePlayerCount = 0;

		for(int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive()) {
				alivePlayerCount++;
			}
		}

		return (alivePlayerCount == 1);

	}

	public void addBoardViewsToPlayers() {

		if (gameInProgressFlag) {return;}

		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.size(); j++) {
				players.get(i).addBoardView(new BoardView(players.get(j).getBoard(), shotNumber));
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

	abstract protected void doTurn();

}
