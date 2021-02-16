//import java.util.Arrays;
import java.util.Random;

public class Model {
	
	private int dimension; 
	private String water;
	private String ship;
	private String bigShip;
	private String friendlyShip;
	private int numShips;
	private String[][] battleArea;
	
	public Model(int dimension, String water, String ship,String bigShip,String friendlyShip, int numShips) {
		setDimension(dimension);
		setWater(water);
		setShip(ship);
		setBigShip(bigShip);
		setFriendlyShip(friendlyShip);
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
	public void setBigShip(String bigShip) {
		this.bigShip = bigShip;
	}
	public String getBigShip() {
		return bigShip;
	}
	public String getFriendlyShip() {
		return friendlyShip;
	}

	public void setFriendlyShip(String friendlyShip) {
		this.friendlyShip = friendlyShip;
	}
	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
	public int getNumShips() {
		return numShips;
	}
	
	public void setBattleArea() {
		this.battleArea = setupBattleArea(getDimension(),getWater(),getShip(),getBigShip(),getFriendlyShip(),getNumShips());
	}
	
	public void setBattleArea(String[][] battleArea) {
		this.battleArea = battleArea;
	}
	
	public String[][] getBattleArea(){
		return battleArea;
	}
	
	private String[][] setupBattleArea(int dimension, String water, String ship,String bigShip,String friendlyShip, int numShips){
		String[][] area = new String[dimension][dimension];
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				area[i][j] = water;
			}
		}
		/*for(String[] row : gameBoard) {
			Arrays.fill(row, water);
		}*/
		return placeShips(area,dimension,water,ship,bigShip,friendlyShip,numShips);
	}
	
	private String[][] placeShips(String[][] area, int dimension, String water, String ship,String bigShip,String friendlyShip, int numShips){
		int shipsPlaced = 0;
		PlaceFriendlyShip(dimension, area,friendlyShip);
	    System.out.println("Our Own Ship has been placed");
		while(shipsPlaced < numShips) {
			int[] location = generateShipCoordinates(dimension);
			String shipLocation = area[location[0]][location[1]];
			
			if(shipLocation.equals(water)) {
				if(shipSizeCalculator() == 1)
				{
					area[location[0]][location[1]] = bigShip;
				}
				else {
					
				area[location[0]][location[1]] = ship;
				}
				shipsPlaced++;
			}
		    System.out.println("All the enemy ships has been placed");
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
	
	private int shipSizeCalculator() {
            int shipSize = new Random().nextInt(2);       
		return shipSize;
	}
	
	private void PlaceFriendlyShip(int dimension,String[][] area, String friendlyShip) {
		int[] coordinates = new int[2];
		for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Random().nextInt(dimension);
        }
		if(area[coordinates[0]][coordinates[1]].equals(water)) {
			area[coordinates[0]][coordinates[1]] = friendlyShip;
		}
	}


	
}

