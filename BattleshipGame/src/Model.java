//import java.util.Arrays;
import java.util.Random;

public class Model {
	
	private int dimension; 
	private String water;
	private String ship;
	private int numShips;
	private String[][] battleArea;
	
	public Model(int dimension, String water, String ship, int numShips) {
		setDimension(dimension);
		setWater(water);
		setShip(ship);
		setNumShips(numShips);
		setBattleArea();
	}
	
	public Model(String[][] battleArea) {
		setBattleArea(battleArea);
	}
	
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public int getDimension() {
		return dimension;
	}
	
	public void setWater(String water) {
		this.water = water;
	}
	public String getWater() {
		return water;
	}
	
	public void setShip(String ship) {
		this.ship = ship;
	}
	public String getShip() {
		return ship;
	}
	
	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
	public int getNumShips() {
		return numShips;
	}
	
	public void setBattleArea() {
		this.battleArea = setupBattleArea(getDimension(),getWater(),getShip(),getNumShips());
	}
	
	public void setBattleArea(String[][] battleArea) {
		this.battleArea = battleArea;
	}
	
	public String[][] getBattleArea(){
		return battleArea;
	}
	
	private String[][] setupBattleArea(int dimension, String water, String ship, int numShips){
		String[][] area = new String[dimension][dimension];
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				area[i][j] = water;
			}
		}
		/*for(String[] row : gameBoard) {
			Arrays.fill(row, water);
		}*/
		return placeShips(area,dimension,water,ship,numShips);
	}
	
	private String[][] placeShips(String[][] area, int dimension, String water, String ship, int numShips){
		int shipsPlaced = 0;
		while(shipsPlaced < numShips) {
			int[] location = generateShipCoordinates(dimension);
			String shipLocation = area[location[0]][location[1]];
			if(shipLocation.equals(water)) {
				area[location[0]][location[1]] = ship;
				shipsPlaced++;
			}
		}
		return area;
	}
	
	private int[] generateShipCoordinates(int dimension) {
		int[] coordinates = new int[2];
		for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Random().nextInt(dimension);
        }
		return coordinates;
	}
	
}
