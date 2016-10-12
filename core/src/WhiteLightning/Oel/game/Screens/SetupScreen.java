package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Config;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.oldMenu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class SetupScreen implements IGameScreen{

	private State s = State.getInstance();
	public TexturesPack album = new TexturesPack();
	private Config conf;
	private gameStates nextState = gameStates.Trend;	
	private World world;
	public SFX sfx;
	private int x = 250;
	private int y = 500;
	private int offsetX=125;
	
	private int mostLeftMenuX;

	private FontsPack fonts = new FontsPack();
	
	
	public SetupScreen(SFX sfx, Config config, World world) {
		this.sfx = sfx;
		this.conf = config;
		this.world = world;
		this.mostLeftMenuX = x+offsetX*conf.playersMax;
		Load();
	}
	
	@Override
	public void Load() {
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawSetup(spriteBatch);		
	}

	@Override
	public gameStates Update(long gameTime) {
		//System.out.println("Mouse X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY());
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
					
		if(mx>x && mx < mostLeftMenuX && my > 0 && my<525){
			if(((mx-x)/offsetX+1) != s.players){
					sfx.PlaySound(Sounds.MenuChange);
					s.players = (byte)((mx-x)/offsetX+1);
					System.out.println("change: "+((mx-x)/offsetX));
			}		
		}
		
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
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
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
			batch.begin();			
			batch.draw(album.titleScreen, 0, 0, 800, 600);			
			fonts.font.draw(batch, "Players Number:",100,500);
			
			for (int i=0; i<album.nrImages.size()-1;i++) {
				batch.draw(album.nrImages.get(i), x+i*offsetX, y-25, 50, 50);				
				if(i<s.players)
					fonts.font.draw(batch, "Player "+(i+1),x+i*offsetX,y-75);				
			}			
			batch.draw(album.nrImages.get(album.nrImages.size()-1), x+(s.players-1)*offsetX, y-25, 50, 50);
		
			/*
			s.text.setBounds(200, 200, 100, 40);
			s.text.draw(batch, 1);
			s.text.getOnscreenKeyboard();
		*/
			batch.end();
		}

}
