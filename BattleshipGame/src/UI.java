import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UI extends JFrame implements ActionListener {
	
	// Swing elements.
	String title = "Battleship Game";
	final int WIDTH = 410;
	final int HEIGHT = 150;
	String x = "Row";
    String y = "Column";
    String fire = "Fire";
	JTextArea displayBattle = new JTextArea();
    JLabel xLabel = new JLabel();
    JTextField xTextField = new JTextField(5);
    JLabel yLabel = new JLabel();
    JTextField yTextField = new JTextField(5);
    JButton fireButton = new JButton();
	
	// Instantiate Model class & related elements.
	int dimension = 4;
    String water = "-";
    String ship = "s";
    int numShips = 3;
    Model model = new Model(dimension,water,ship,numShips);
    String[][] battleArea = model.getBattleArea();
    
    // Instantiate View class & related elements.
    View view = new View(battleArea,model);
    String battle = view.getBattle();
    
    // Instantiate Control class & related elements.
    int userRow;
    int userCol;
    int undetectedShips = numShips;
    String hit = "X";
    String miss = "O";
    String win = "You Win!";
    Control control = new Control();
	
	public UI() {
		// Setup java swing application.
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(WIDTH,HEIGHT);
		// Battle grid.
		add(displayBattle);
		displayBattle.setText(battle);
		displayBattle.setEditable(false);
		// User input.
		add(xLabel);
		xLabel.setText(x);
		add(xTextField);
		add(yLabel);
		yLabel.setText(y);
		add(yTextField);
		// Fire button.
		add(fireButton);
		fireButton.setText(fire);
		fireButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean formatStatus = true;
		try {
			userRow = Integer.parseInt(xTextField.getText().toString());
			userCol = Integer.parseInt(yTextField.getText().toString());
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,"Invalid input.");
			userRow = 0;
			userCol = 0;
			formatStatus = false;
		}
		if(formatStatus == true) {
			int[] userInput = {userRow,userCol};
			if(undetectedShips > 0) {
				int[] userCoordinates = control.userCoordinates(userInput);
				boolean coordinateStatus = validateUserCoordinates(userCoordinates,dimension);
				if(coordinateStatus == false) {
					JOptionPane.showMessageDialog(null, "Coordinate input is not within the bounds of the battle area.");
					throw new ArrayIndexOutOfBoundsException();
				}
				else {
					String locationTarget = control.checkAndEvaluateTarget(userCoordinates,battleArea,ship,water,hit,miss);
					if(locationTarget.equals(hit)) {
						undetectedShips--;
					}
					battleArea = control.updateBattleArea(battleArea,userCoordinates,locationTarget);
					view.setBattleArea(battleArea);
					battle = view.getBattle();
					displayBattle.setText(battle);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, win);
			}
		}
	}
	
	private boolean validateUserCoordinates(int[] userCoordinates, int dimension) {
		boolean status = true;
		for(int i = 0; i < userCoordinates.length; i++) {
			int input = userCoordinates[i];
			if(input < 0 || input > dimension) {
				status = false;
			}
		}
		return status;
	}
	
	public static void main(String[] args) {
		UI aFrame = new UI();
		aFrame.setVisible(true);
	}
	
}
