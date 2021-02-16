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
            System.out.println("You hit the water");
        } else if (target.equals(bigShip)) {
        	message = "Big Hit!";
        	target = hit;
            System.out.println("You hit on BigShip");
		}else if (target.equals(friendlyShip)) {
			message = "You hit your own ship";
        	target = loss;
            System.out.println("You hit your the FriendlyShip");
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
