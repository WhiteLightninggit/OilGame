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
import WhiteLightning.Oel.game.SoundPack;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class OptionsScreen implements IGameScreen{

	public TexturesPack album = new TexturesPack();
	//private gameStates nextState = gameStates.Options;
	
	private FontsPack fonts = new FontsPack();
	private SFX sfx;
	
	
	public OptionsScreen(SFX sfx, Config config, World world) {
	this.sfx=sfx;
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
	
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			sfx.PlaySound(Sounds.MenuChange);
			return gameStates.Title;
		}
				
		return gameStates.Options;
	}
	
	private void drawSetup(SpriteBatch batch) {						
			batch.begin();			
			batch.draw(album.titleScreen, 0, 0, 800, 600);			
			fonts.font.draw(batch, "This is Option screnn...",100,500);
			batch.end();
		}

}
