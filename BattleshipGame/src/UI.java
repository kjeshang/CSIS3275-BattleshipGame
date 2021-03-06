import java.awt.FlowLayout;
import java.awt.Font;
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
	final int WIDTH = 810;
	final int HEIGHT = 810;
	String x = "Row";
    String y = "Column";
    String fire = "Fire";
    String insTitle = "Instructions";
    String insDetail = instructions();
	JTextArea displayBattle = new JTextArea();
    JLabel xLabel = new JLabel();
    JTextField xTextField = new JTextField(5);
    JLabel yLabel = new JLabel();
    JTextField yTextField = new JTextField(5);
    JButton fireButton = new JButton();
    JButton insButton =new JButton();
    JTextArea instructionsArea = new JTextArea();
	
	// Instantiate Model class & related elements.
	int dimension = 9;
    String water = "~";
    String ship = "s";
    String bigShip = "ss";
    String friendlyShip = "FS";
    int numShips = 5;
    boolean gameover = false;
    Model model = new Model(dimension,water,ship,bigShip,friendlyShip,numShips);
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
    String loss = "!";
    String win = "You Win!";
    String lost = "You Lost";
    Control control = new Control();
	
	public UI() {
		// Setup java swing application.
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(WIDTH,HEIGHT);
		// Instructions.
		add(insButton);
		insButton.setText(insTitle);
		insButton.addActionListener(this);
		add(instructionsArea);
		instructionsArea.setText(insDetail);
		instructionsArea.setVisible(false);
		// Battle grid.
		add(displayBattle);
		Font font = new Font("SansSerif",Font.PLAIN, 20);
		//displayBattle.setMargin(new Insets(100, 100, 100,100));
		displayBattle.setText(battle);
		displayBattle.setEditable(false);
		displayBattle.setFont(font);
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
	    System.out.println("All the Jframe elements have been created");
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		// Instruction button pressed:
		if(e.getSource() == insButton) {
			instructionsArea.setVisible(true);
			JOptionPane.showMessageDialog(null, instructionsArea,"BattleShip Game: CSIS 3275-001 (Team 8)",JOptionPane.INFORMATION_MESSAGE);
		}
		// Fire button pressed:
		if(e.getSource() == fireButton) {
			boolean formatStatus = true;
			try {
				userRow = Integer.parseInt(xTextField.getText().toString());
				userCol = Integer.parseInt(yTextField.getText().toString());
			    System.out.println("User entry has been verfied as Integer");
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
						String locationTarget = control.checkAndEvaluateTarget(userCoordinates,battleArea,ship,bigShip,friendlyShip,water,hit,miss,loss);
					    System.out.println("You hit the target");
						if(locationTarget.equals(hit)) {
							undetectedShips--;
						    System.out.println("Its a hit");
						}
						if(locationTarget.equals(loss)) {
							gameover = true;
							undetectedShips = 0;
						    System.out.println("You Lost the game because you hit friendly ship");
						}
						battleArea = control.updateBattleArea(battleArea,userCoordinates,locationTarget);
						view.setBattleArea(battleArea);
						battle = view.getBattle();
						displayBattle.setText(battle);
					    System.out.println("The battle ground has been updated");
					}
				}
				else {
					if(gameover == true) {
						JOptionPane.showMessageDialog(null, lost);
						System.out.println("Game Over! You Lost");
					}
					else
					{
						JOptionPane.showMessageDialog(null, win);
					    System.out.println("Game Over! You Won");
					}
				}
			}
		}
	}
	
	private boolean validateUserCoordinates(int[] userCoordinates, int dimension) {
		boolean status = true;
		for(int i = 0; i < userCoordinates.length; i++) {
			int input = userCoordinates[i];
			if(input < 0 || input > dimension) {
			    System.out.println("The validation of the user coordinates have falied");
				status = false;
			}
		}
		return status;
	}
	
	private String instructions() {
		String message = "";
		message += "Welcome to the BattleShip Game. Here are the rules:\n\n" + 
				   "You need to enter coordinates into the Row and Column text fields to fire a missile to destroy an enemy ship in the battle area.\n" +
				   "There are two types of enemy ships: Regular Ships & Big Ships\n" +
				   "You need to destroy all enemy ships to win the battle.\n" +
				   "In the entire battle area, there are 5 ships; one of those ships is yours. If you fire a missile on your own ship, it is an instant game over.\n\n" +
				   "Legend:\n" +
				   "      ~ = Water\n" +
				   "      s = Regular Enemy Ship (encompasses 1 coordinate in battle area)\n" +
				   "     ss = Big Enemy Ship (encompasses 2 coordinates in battle area)\n" +
				   "     FS = Your Ship (encompasses 1 coordinate in battle area)\n" +
				   "      X = Enemy Ship hit\n" +
				   "      O = Enemy Ship miss\n" +
				   "      ! = Own Ship hit\n\n" +
				   "-------------------------------------------------------------------------\n" +
				   "Team members:\n" +
				   "    Heena Kashyap (300305305)\n"+
				   "    Sukhleen Kaur (300310052)\n" +
				   "    Sehajpreet Singh Kingra (300304180)\n" +
				   "    Kunal Ajaykumar Jeshang (300328339)";
		return message;
	}
	
	public static void main(String[] args) {
		UI aFrame = new UI();
		aFrame.setVisible(true);
	}
	
}
