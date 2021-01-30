
public class View {
	
	public String printGameBoard(String[][] gameBoard, int gameBoardLength,String water, String ship) {
		String finalGameBoard = "";
		finalGameBoard += "   ";
		for(int i = 0; i < gameBoardLength; i++) {
            finalGameBoard += (i + 1) + "  ";
        }
		finalGameBoard += "\n";
		for(int row = 0; row < gameBoardLength; row++) {
			finalGameBoard += (row + 1) + " ";
            for(int col = 0; col < gameBoardLength; col++) {
                String position = gameBoard[row][col];
                if(position.equals(ship)) {
                		finalGameBoard += water + "  ";
                } 
                else {
                		finalGameBoard += position + "  ";
                }
            }
            finalGameBoard += "\n";
        }
		finalGameBoard += "\n";
		return finalGameBoard;
	}
	
	public String[][] updateGameBoard(String[][] gameBoard, int[] guessCoordinates, String locationViewUpdate) {
		int row = guessCoordinates[0]; 
        int col = guessCoordinates[1]; 
        gameBoard[row][col] = locationViewUpdate; 
        return gameBoard;
	}
}
