package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class TitleScreen implements IGameScreen {

	private State s = State.getInstance();
	private long gameTime;
	private gameStates nextState = gameStates.Title;
	public FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();

	private Menu menu = new Menu();

	private SFX sfx;
	
	private int screenWidth = 800;
	private int screenHeight = 600;
	private int titleX = 300;
	private int titleY = 450;
		
	private String title = "OEL - P";

	public TitleScreen(Logic logic, SFX sfx) {
		this.sfx = sfx;
		Load();
	}

	@Override
	public void Load() {
		menu.setData(1,"Start Game");
		menu.setData(2,"Options");
		menu.setData(3,"Story");
		menu.setData(4,"Credits");
		menu.setData(5,"Exit");
		
		menu.setSFX(sfx);
		this.gameTime = TimeUtils.nanoTime();
	}

	private void drawGameTitle(SpriteBatch batch) {
		batch.begin();
		fonts.bigFontBlue.draw(batch, title, titleX, titleY);
		batch.end();
	}

	private void drawBackground(SpriteBatch batch) {
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, screenWidth, screenHeight);
		batch.end();
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawBackground(spriteBatch);
		menu.drawMenu(spriteBatch);
		drawGameTitle(spriteBatch);
	}

	@Override
	public gameStates Update(long gameTime) {
		this.gameTime = gameTime;
		processKeysTitle(menu);
		return nextState;
	}

	private void processKeysTitle(Menu menu) {
		nextState = gameStates.Title;
		menu.processKeys(sfx);

		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			System.out.println(menu.getSelectedIdx() + " time: " + gameTime); 

			if (menu.getSelectedIdx() == 0) {
				nextState = gameStates.Setup;
				s.currentPlayerIdx = 0;
			}

			if (menu.getSelectedIdx() == 1) {
				nextState = gameStates.Options;
			}
			if (menu.getSelectedIdx() == 2) {
				nextState = gameStates.Setup;
			}
			if (menu.getSelectedIdx() == 3) {
				nextState = gameStates.Credits;
			}
			if (menu.getSelectedIdx() == 4)
				nextState = gameStates.End;
		}
	}

}
