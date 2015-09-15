package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Factory.FactoryType;
import WhiteLightning.Oel.game.Game.gameStates;

public class State {

	public int internalState = 0;
	public boolean flag1 = false;
	public boolean playMusic = true;
	public boolean moveUp = false;
	public int z = 0;
	public byte currentPlayerIdx=0;
	public int currentYear;
	int lastYear;
	public int endOfGame=-1; 
	public gameStates gameState = gameStates.Title;
	public FactoryType currFactoryType=FactoryType.PUMP;
	
	public String inst;
	
	public int selectedMenuItem = 0;
	int selectedField = 0;
	public int selectedAction = 0;
	
    private static class Loader {
        static State INSTANCE = new State();
    }

    private State() {}

    public static State getInstance() {
        return Loader.INSTANCE;
    }
}