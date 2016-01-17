package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import WhiteLightning.Oel.game.Config;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;

public class TrendScreen implements IGameScreen{

	private State s = State.getInstance();
	private Texture titleScreen;
	private Config conf;
	private World world;
	private gameStates nextState = gameStates.Game;	
	public SFX sfx;
	private BitmapFont cFontGray;

	
	public TrendScreen( SFX sfx, Config config,World world) {
		this.sfx = sfx;
		this.conf = config;
		this.world = world;
		Load();
	}
	
	@Override
	public void Load() {
		titleScreen = new Texture("Images/chart6.png");
		cFontGray = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Texas.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = 30;
			parameter.characters = " -abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
			cFontGray = generator.generateFont(parameter);
			generator.dispose();		
			cFontGray.setColor(Color.GRAY);		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawTrend(spriteBatch);		
	}

	@Override
	public gameStates Update(long gameTime) {
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			sfx.PlaySound(Sounds.MenuChange);
			return nextState;
		}
				
		return gameStates.Trend;
	}
	
	private int drawTrend(SpriteBatch batch) {
		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		cFontGray.draw(batch, "This is the END or STORY screen.", 300, 450);
		batch.end();
		
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		
		 shapeRenderer.begin(ShapeType.Filled);		 
		 shapeRenderer.setColor(0, 0, 1, 1);
		 shapeRenderer.rect(100, 100, 600, 10);
		 shapeRenderer.rect(100, 100, 10, 200);
		 shapeRenderer.setColor(0, 1, 0, 1);
		 
		 int width=25;
		 
		 for(int i=0; i<world.oilPricesTrend.length;i++){
			 shapeRenderer.rect(110+i*width, 110, 20,world.oilPricesTrend[i]*20);
		 }		 
		 
		 shapeRenderer.end();
		
		return 0;
	}
}
