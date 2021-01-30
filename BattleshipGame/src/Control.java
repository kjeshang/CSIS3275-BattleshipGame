import javax.swing.JOptionPane;

public class Control {
	
	public int[] getUserCoordinates(int row, int col) {
		int[] userCoordinates = new int[2];
		userCoordinates[0] = row-1;
		userCoordinates[1] = col-1;
		return userCoordinates;
	}
	
	public String evaluateGuessAndGetTarget(int[] guessCoordinates, String[][] gameBoard, String ship, String water, String hit, String miss) {
		String message;
		int row = guessCoordinates[0];
		int col = guessCoordinates[1];
		String target = gameBoard[row][col];
		if(target.equals(ship)) { 
            message = "Hit!";
            target = hit;
        } 
		else if(target.equals(water)) { 
            message = "Miss!";
            target = miss;
        } 
		else {
            message = "Already hit!";
        }
		JOptionPane.showMessageDialog(null, message);
		return target;
	}
	
	
}
