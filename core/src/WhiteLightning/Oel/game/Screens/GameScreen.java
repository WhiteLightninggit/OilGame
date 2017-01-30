package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import WhiteLightning.Oel.game.Config;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;
import WhiteLightning.Oel.game.Objects.FontsPack;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameScreen implements IGameScreen{

	final Logger log = LogManager.getLogger(GameScreen.class);
	private State s = State.getInstance();
	private Logic logic;
	private Config conf;
	private FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();	
	
	private Menu menu = new Menu();
	private SFX sfx;

	private int screenWidth = 800;
	private int screenHeight = 600;	
	
	public GameScreen(World world, Logic logic, SFX sfx, Config config) {
		log.info("GameScreen");
		this.logic=logic;		
		this.sfx = sfx;
		this.conf = config;

		for(int i=0;i<s.players;i++){
			world.addPlayer(conf, "Player "+(i+1),i);
			System.out.println("player added"+i);
		}
		
		Load();
	}	
	
	@Override
	public void Load() {
		log.trace("Load()");
		
		menu.setData(0,"Drill Factories");
		menu.setData(1,"Pumps Factories");
		menu.setData(2,"Wagons factories");
		menu.setData(3,"Oil Fields");
		menu.setData(4,"Buy Drills");
		menu.setData(5,"Buy Pumps");
		menu.setData(6,"Buy Wagons");
		//menu.setData(7,"=OTHER POSSIBILITIES");
		menu.setData(7,"Next player");
		menu.setData(8,"Sabotage");
		menu.setData(9,"Skip turn");
		menu.setData(10,"Exit");
		menu.setData(5,"Buy Pumps");
	
		menu.setSFX(sfx);

		menu.y=500;
		menu.x=450;
		menu.menuCaptionWidth = 300;		
		menu.zzz();
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawBackground(spriteBatch);
		menu.drawMenu(spriteBatch);
		drawImg(spriteBatch);
		displayStatus(spriteBatch, 100, 200);		
	}

	@Override
	public gameStates Update(long gameTime) {			
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			sfx.PlaySound(Sounds.MenuChange);
			return gameStates.End;
		}		
		return processKeysGame();
	}

	private void displayStatus(SpriteBatch batch, int x, int y) {
		batch.begin();
		fonts.font.draw(batch, "Player: " + logic.getCurrentPlayer().name, x, y);
		fonts.font.draw(batch, "Year: " + logic.getCurrentYear(), x, y - 30);
		fonts.font.draw(batch, "State: " + s.gameState.toString(), x, y - 60);
		batch.end();
	}
	
	
	private void drawBackground(SpriteBatch batch) {
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, screenWidth, screenHeight);
		batch.end();
	}
	
	private void drawImg(SpriteBatch batch) {
		int x = 100;
		int y = 300;
		int width = 220;
		int height = 220;
		ArrayList<Texture> picsList = album.getMenuPicsList();
		
		batch.begin();
			batch.draw(picsList.get(menu.getSelectedIdx()), x, y, width, height);
		batch.end();
	}
		
	public gameStates processKeysGame() {
		menu.processKeys(sfx);
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			sfx.PlaySound(Sounds.MenuChange);
			
			if (menu.getSelectedIdx() == 0) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.DRILLS;
				return gameStates.Factory;
			}
			if (menu.getSelectedIdx() == 1) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.PUMP;
				return gameStates.Factory;
			}
			if (menu.getSelectedIdx() == 2) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.WAGONS;
				return gameStates.Factory;
			}
			if (menu.getSelectedIdx() == 3){
				s.gameState = gameStates.OilFields;
				return gameStates.OilFields;
			}
			if (menu.getSelectedIdx() == 4){
				s.gameState = gameStates.BuyDrills;
				return gameStates.BuyDrills;
			}
			if (menu.getSelectedIdx() == 8) {
				logic.setNextPlayer();
				System.out.println("Game state: " + s.gameState + " " + s.lastYear);
			
				if (logic.isGameEnd()) {
					System.out.println("Game state: " + s.gameState);
					s.gameState = gameStates.End;
				}
			}
			if (menu.getSelectedIdx() == 10){
				s.gameState = gameStates.End;
				return gameStates.End;
			}
			s.selectedField = 0;
		}
		
		return gameStates.Game;
		
	}	
}
