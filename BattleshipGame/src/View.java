
public class View {
	
	private String[][] battleArea;
	private Model model;
	
	public View(String[][] battleArea, Model model) {
		setBattleArea(battleArea);
		setModel(model);
	}
	
	public void setBattleArea(String[][] battleArea) {
		this.battleArea = battleArea;
	}
	public String[][] getBattleArea(){
		return battleArea;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	public Model getModel() {
		return model;
	}
	
	public String getBattle() {
		return battleGrid(getBattleArea(),getModel());
	}
	
	private String battleGrid(String[][] battleArea, Model model) {
		String battle = "";
		battle += "   ";
		for(int i = 0; i < model.getDimension(); i++) {
            battle += (i + 1) + "  ";
        }
		battle += "\n";
		for(int row = 0; row < model.getDimension(); row++) {
			battle += (row + 1) + " ";
            for(int col = 0; col < model.getDimension(); col++) {
                String position = battleArea[row][col];
                if(position.equals(model.getShip())) {
                		battle += model.getWater() + "  ";
                } 
                else {
                		battle += position + "  ";
                }
            }
            battle += "\n";
        }
		battle += "\n";
		return battle;
	}
	
}
