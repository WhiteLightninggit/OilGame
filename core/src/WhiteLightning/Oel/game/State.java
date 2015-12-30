package WhiteLightning.Oel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import WhiteLightning.Oel.game.Factory.FactoryType;
import WhiteLightning.Oel.game.Game.gameStates;

public class State {

	public int internalState = 0;
	public boolean animFlag = false;
	public boolean playMusic = true;
	public boolean moveUp = false;
	public int menuAnimX = 0;
	public byte currentPlayerIdx=0;
	public int currentYear;
	int lastYear;
	public int endOfGame=-1; 
	public gameStates gameState = gameStates.Title;
	public FactoryType currFactoryType=FactoryType.PUMP;
	public StringBuffer sb;
	Skin skin = new Skin(Gdx.files.internal("ui/defaultskin.json")); 
	TextField text=new TextField("Asia",skin );
	
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