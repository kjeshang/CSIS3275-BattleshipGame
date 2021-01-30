import java.util.Arrays;
import java.util.Random;

public class Model {
	
    public String[][] createGameBoard(int gameBoardLength, String water, String ship, int numShips) {
    		String[][] gameBoard = new String[gameBoardLength][gameBoardLength];
    		for(String[] row : gameBoard) {
    			Arrays.fill(row, water);
    		}
    		return placeShips(gameBoard,gameBoardLength,water,ship,numShips);
    }
    
    public String[][] placeShips(String[][] gameBoard, int gameBoardLength, String water, String ship, int numShips) {
    		int shipsPlaced = 0;
    		while(shipsPlaced < numShips) {
    			int[] location = generateShipCoordinates(gameBoardLength);
    			String possiblePlacement = gameBoard[location[0]][location[1]];
    			if(possiblePlacement.equals(water)) {
    				gameBoard[location[0]][location[1]] = ship;
    				shipsPlaced++;
    			}
    		}
    		return gameBoard;
    }

	private int[] generateShipCoordinates(int gameBoardLength) {
		int[] coordinates = new int[2];
		for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Random().nextInt(gameBoardLength);
        }
		return coordinates;
	}
    
}
