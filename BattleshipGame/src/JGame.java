import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class JGame extends JFrame implements ActionListener {
	
	String title = "Battleship Game";
	final int WIDTH = 410;
	final int HEIGHT = 150;
	
	int gameBoardLength = 4;
    String water = "-";
    String ship = "s";
    int numShips = 3;
    
    String x = "Row";
    String y = "Column";
    String fire = "Fire";
    
    Model model = new Model();
    String[][] gameBoard = model.createGameBoard(gameBoardLength, water, ship, numShips);
    View view = new View();
    String printGameBoard = view.printGameBoard(gameBoard,gameBoardLength,water,ship);
    JTextArea showGameBoard = new JTextArea();
    JLabel xLabel = new JLabel();
    JTextField xTextField = new JTextField(5);
    JLabel yLabel = new JLabel();
    JTextField yTextField = new JTextField(5);
    JButton fireButton = new JButton();
	
    int userRow;
    int userCol;
    int numShipsUndetected = numShips;
    String hit = "X";
    String miss = "O";
    Control control = new Control();
    String win = "You Win!";
    
	public JGame() {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(WIDTH,HEIGHT);
		add(showGameBoard);
		showGameBoard.setText(printGameBoard);
		showGameBoard.setEditable(false);
		add(xLabel);
		xLabel.setText(x);
		add(xTextField);
		add(yLabel);
		yLabel.setText(y);
		add(yTextField);
		add(fireButton);
		fireButton.setText(fire);
		fireButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		userRow = Integer.parseInt(xTextField.getText().toString());
		userCol = Integer.parseInt(yTextField.getText().toString());
        if(numShipsUndetected > 0) {
        		int[] guessCoordinates = control.getUserCoordinates(userRow,userCol);
        		String locationViewUpdate = control.evaluateGuessAndGetTarget(guessCoordinates, gameBoard, ship, water, hit, miss);
        		if(locationViewUpdate.equals(hit)) {
        			numShipsUndetected--;
        		}
        		gameBoard = view.updateGameBoard(gameBoard, guessCoordinates, locationViewUpdate);
        		printGameBoard = view.printGameBoard(gameBoard,gameBoardLength,water,ship);
        		showGameBoard.setText(printGameBoard);
        }
        else {
        		JOptionPane.showMessageDialog(null, win);
        }
	}
	
	public static void main(String[] args) {
		JGame aFrame = new JGame();
		aFrame.setVisible(true);
	}

}
