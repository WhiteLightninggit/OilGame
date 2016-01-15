package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Config;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.Menu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;

public class SetupScreen implements IGameScreen{

	private State s = State.getInstance();
	private Sprite menuArrow;
	private Texture titleScreen;
	private Logic logic;
	private Config conf;
	private long gameTime;
	private gameStates nextState = gameStates.Title;	
	private Menu myMenu;
	public SFX sfx;

	private ArrayList<Texture> nrImages;
	private Texture nr1Img;
	private Texture nr2Img;
	private Texture nr3Img;
	private Texture nr4Img;
	private Texture selectedNrImg;
	

	private BitmapFont font;
	
	
	public SetupScreen(Logic logic, SFX sfx, Config config, Menu menu) {
		this.logic=logic;		
		this.myMenu = menu;
		this.sfx = sfx;
		this.conf = config;
		Load();
	}
	
	@Override
	public void Load() {
		nrImages = new ArrayList<>();
		nrImages.add(new Texture("Images/1.png"));
		nrImages.add(new Texture("Images/2.png"));
		nrImages.add(new Texture("Images/3.png"));
		nrImages.add(new Texture("Images/4.png"));
		nrImages.add(new Texture("Images/selectedNr.png"));
		titleScreen = new Texture("Images/chart6.png");
		
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawSetup(spriteBatch);		
	}

	@Override
	public gameStates Update(long gameTime) {
		// TODO Auto-generated method stub
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			sfx.PlaySound(Sounds.MenuChange);
			if(s.players>1)
				s.players--;
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			sfx.PlaySound(Sounds.MenuChange);
			if(s.players<conf.playersMax)
				s.players++;
		}
		
		
		return gameStates.Setup;
	}
	
	private void drawSetup(SpriteBatch batch) {
		//	drawHUD();
			int x=200;
			int offsetX=50;			
			batch.begin();			
			batch.draw(titleScreen, 0, 0, 800, 600);
			
			for (int i=0; i<nrImages.size()-1;i++) {
				batch.draw(nrImages.get(i), x+i*50, 300, offsetX, 50);
			}
			
			batch.draw(nrImages.get(nrImages.size()-1), x+(s.players-1)*offsetX, 300, offsetX, 50);
			font.setScale(.9f, .9f);
			font.draw(batch, "Input: " + s.sb,500,200);
			s.text.setBounds(200, 200, 100, 40);
			s.text.draw(batch, 1);
			s.text.getOnscreenKeyboard();
			batch.end();
		}

}
