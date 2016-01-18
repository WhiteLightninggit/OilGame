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
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;

public class SetupScreen implements IGameScreen{

	private State s = State.getInstance();
	private Texture titleScreen;
	private Config conf;
	private gameStates nextState = gameStates.Trend;	
	private World world;
	public SFX sfx;

	
	private ArrayList<Texture> nrImages;


	private BitmapFont font;
	
	
	public SetupScreen(SFX sfx, Config config, World world) {
		this.sfx = sfx;
		this.conf = config;
		this.world = world;
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
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			sfx.PlaySound(Sounds.MenuChange);
			for(int i=0;i<s.players;i++){
				world.addPlayer(conf, "Player "+(i+1),i);
				System.out.println("player added"+i);
			}
			
			
			
			return nextState;
		}
				
		return gameStates.Setup;
	}
	
	private void drawSetup(SpriteBatch batch) {
			int x=250;
			int y = 500;
			int offsetX=125;			
			batch.begin();			
			batch.draw(titleScreen, 0, 0, 800, 600);
			
			font.draw(batch, "Players Number:",100,500);
			
			for (int i=0; i<nrImages.size()-1;i++) {
				batch.draw(nrImages.get(i), x+i*offsetX, y-25, 50, 50);
				
				if(i<s.players)
					font.draw(batch, "Player "+(i+1),x+i*offsetX,y-75);
				
			}
			
			batch.draw(nrImages.get(nrImages.size()-1), x+(s.players-1)*offsetX, y-25, 50, 50);
			font.setScale(.9f, .9f);
			
		
			
			/*
			s.text.setBounds(200, 200, 100, 40);
			s.text.draw(batch, 1);
			s.text.getOnscreenKeyboard();
		*/
			batch.end();
		}

}
