package WhiteLightning.Oel.game;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;


import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

public class State implements Serializable  {

	private static final long serialVersionUID = 5740086425894390405L;
	public int internalState = 0;
	public boolean animFlag = false;
	public boolean playMusic = true;
	public boolean moveUp = false;
	public int menuAnimX = 0;
	public byte players = 2;
	public byte currentPlayerIdx=0;
	public int currentYear;
	public int lastYear;
	public int endOfGame=-1; 
	public gameStates gameState = gameStates.Title;
	public FactoryType currFactoryType=FactoryType.PUMP;
	public StringBuffer sb;
	Skin skin = new Skin(Gdx.files.internal("ui/defaultskin.json")); 
	public TextField text=new TextField("Asia",skin );
	
	public String inst;
	
	public int selectedMenuItem = 0;
	public int selectedField = 0;
	public int selectedAction = 0;
	
    private static class Loader {
        static State INSTANCE = new State();
    }

    private State() {}

    public static State getInstance() {
        return Loader.INSTANCE;
    }


}