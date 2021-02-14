import javax.swing.JOptionPane;

public class Control {
	
	public int[] userCoordinates(int[] userInput) {
		int[] userCoordinates = new int[2];
		for(int i = 0; i < userCoordinates.length; i++) {
			userCoordinates[i] = userInput[i] - 1;
		}
		return userCoordinates;
	}
	
	public String checkAndEvaluateTarget(int[] userCoordinates, String[][] battleArea,String ship,String bigShip, String friendlyShip, String water, String hit, String miss, String loss) {
		String message;
		int row = userCoordinates[0];
		int col = userCoordinates[1];
		String target = battleArea[row][col];
		if(target.equals(ship)) { 
            message = "Hit!";
            target = hit;
            System.out.println("You hit a ship");
        } 
		else if(target.equals(water)) { 
            message = "Miss!";
            target = miss;
            System.out.println("You hit on water");
        } else if (target.equals(bigShip)) {
        	message = "BigHit";
        	target = hit;
            System.out.println("You hit on BigShip");
		}else if (target.equals(friendlyShip)) {
			message = "You Hit Friendly Ship";
        	target = loss;
            System.out.println("You hit on friendly Ship");
		}
		else {
            message = "Already hit!";
        }
		JOptionPane.showMessageDialog(null, message);
		return target;
	}
	
	public String[][] updateBattleArea(String[][] battleArea, int[] userCoordinates, String locationTarget) {
		int row = userCoordinates[0]; 
        int col = userCoordinates[1]; 
        battleArea[row][col] = locationTarget;
        System.out.println("BattleGround has been upddated after the round");
        return battleArea;
	}
}
