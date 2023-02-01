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
	protected Ship[] fleet;
	protected int turnNumber = 0;
	protected int shotNumber = 0;
	protected boolean gameInProgressFlag = false;

	public Game(ArrayList players, int shotNumber, Ship[] fleet) {
		this.players = players;
		this.shotNumber = shotNumber;
		this.fleet = fleet;
		addBoardViewsToPlayers();
		addShipsToPlayers();
	}

	abstract public void startGame();
	abstract protected boolean checkWinCondition();
	abstract protected void showAfterGame();

	public void addBoardViewsToPlayers() {
		if (gameInProgressFlag) {return;}
		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.size(); j++) {
				if (!(players.get(j).getBoard().equals(players.get(i).getBoard()))) {
					players.get(i).addBoardView(new BoardView(players.get(j).getBoard(), shotNumber, players.get(j).getPlayerColor()));
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
		boolean allShipsPlaced = false;
		ships = Ship.cloneShipArray(ships);
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
