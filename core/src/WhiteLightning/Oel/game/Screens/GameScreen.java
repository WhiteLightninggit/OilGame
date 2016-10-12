package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.TimeUtils;

import WhiteLightning.Oel.game.Config;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.oldMenu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;
import WhiteLightning.Oel.game.Objects.FontsPack;
import WhiteLightning.Oel.game.Objects.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameScreen implements IGameScreen{

	final Logger log = LogManager.getLogger(GameScreen.class);
	private State s = State.getInstance();
	private Sprite menuArrow;
	private Logic logic;
	private long gameTime;
	private gameStates nextState = gameStates.Title;
	private Config conf;
	private FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();
	
	private oldMenu myMenu;


	private SFX sfx;
	
	
	private int menuItemHeight = 40;
	private int screenWidth = 800;
	private int screenHeight = 600;
	private int statusX = 20;
	private int statusY = 100;
	private int titleX = 300;
	private int titleY = 450;
	private int cursorWidth = 25;
	private int cursorHeight = 25;
	private int cursorXOffset = 37;
	private int cursorYOffset = 22;
	private String title = "OEL - P";
	private int menuX = 500;
	private int menuY = 250;
	
	private World world;
	
	public GameScreen(World world, Logic logic, SFX sfx, Config config, oldMenu menu) {
		log.info("GameScreen");
		this.logic=logic;		
		this.myMenu = menu;
		this.sfx = sfx;
		this.conf = config;
		this.world=world;
		
		for(int i=0;i<s.players;i++){
			world.addPlayer(conf, "Player "+(i+1),i);
			System.out.println("player added"+i);
		}
		
		Load();
	}
	
	
	private int drawMenuNew(oldMenu menu, int x, int y,SpriteBatch batch) {		
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, screenWidth, screenHeight);
		
		char itemLetter = 'A'; //First menu object index letter
		menu.iterateMode();		
		while(menu.hasNext()){						
				if (menu.isCurrent()) {
					fonts.cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());
				} else {
					fonts.cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * menuItemHeight);					
				}
				itemLetter++;			
				menu.selectNextItem();
		}		
		//Displaying last item from menu
		if (menu.isCurrent()) {
			fonts.cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());
		} else {
			fonts.cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * menuItemHeight);					
		}
		
		menu.standardMode();		
		menuArrow.setBounds(x - cursorXOffset + s.menuAnimX, y - cursorYOffset - menu.getCurrentIdx() * menuItemHeight, cursorWidth, cursorHeight);
		menuArrow.draw(batch);
		displayStatus(batch, statusX, statusY);	
		batch.end();
		return menu.getCurrentIdx();
	}
	
	
	@Override
	public void Load() {
		log.trace("Load()");
		this.gameTime = TimeUtils.nanoTime();
		album.titleScreen = new Texture("Images/chart6.png");
		menuArrow = new Sprite(new Texture("Images/marker2.png"));
		
		

		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawMenuNew(myMenu, 450, 500, spriteBatch);
		drawImg(spriteBatch);
		
	}

	@Override
	public gameStates Update(long gameTime) {
		
		
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			sfx.PlaySound(Sounds.MenuChange);
			return gameStates.End;
		}
		
		return processKeysGame(myMenu);
	}

	private void displayStatus(SpriteBatch batch, int x, int y) {
		fonts.font.draw(batch, "Player: " + logic.getCurrentPlayer().name, x, y);
		fonts.font.draw(batch, "Year: " + logic.getCurrentYear(), x, y - 30);
		fonts.font.draw(batch, "State: " + s.gameState.toString(), x, y - 60);
	}
	
	private void drawImg(SpriteBatch batch) {
			batch.begin();

			int x = 100;
			int y = 300;
			int width = 220;
			int height = 220;

			if (s.selectedAction == 0) {
				batch.draw(album.drillImg, x, y, width, height);
			}
			if (s.selectedAction == 1) {
				batch.draw(album.pumpImg, x, y, width, height);
			}
			if (s.selectedAction == 2) {
				batch.draw(album.wagonImg, x, y, width, height);
			}
			if (s.selectedAction == 3) {
				batch.draw(album.oilFieldImg, x, y, width, height);
			}
			if (s.selectedAction == 4) {
				batch.draw(album.drillsImg, x, y, width, height);
			}
			if (s.selectedAction == 5) {
				batch.draw(album.pumpsImg, x, y, width, height);
			}
			if (s.selectedAction == 6) {
				batch.draw(album.trainImg, x, y, width, height);
			}
			if (s.selectedAction == 8) {
				batch.draw(album.skipImg, x, y, width, height);
			}
			if (s.selectedAction == 9) {
				batch.draw(album.sabotImg, x, y, width, height);
			}
			if (s.selectedAction == 10) {
				batch.draw(album.nextImg, x, y, width, height);
			}
	batch.end();
	}
	
	
	
	public gameStates processKeysGame(oldMenu menu) {

		//System.out.println("Mouse X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY());
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
		int menuUpperY = 100;
		
		
		if(mx>500 && mx < 700 && my > 100 && my<525){
			if((my-menuUpperY)/40 != menu.currentIdx){
					sfx.PlaySound(Sounds.MenuChange);
					menu.currentIdx = (my-menuUpperY)/40;
					System.out.println("change: "+((my-menuUpperY) / 40));
			}		
		}

		
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sfx.PlaySound(Sounds.MenuChange);
			menu.selectPreviousItem();
			s.selectedAction--;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sfx.PlaySound(Sounds.MenuChange);
			menu.selectNextItem();
			s.selectedAction++;
/*			if (s.selectedAction < g.actionsList.size() - 1)
				s.selectedAction++;
*/			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			sfx.PlaySound(Sounds.MenuChange);
			
			if (menu.getCurrentIdx() == 0) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.DRILLS;
				return gameStates.Factory;
			}
			if (menu.getCurrentIdx() == 1) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.PUMP;
				return gameStates.Factory;
			}
			if (menu.getCurrentIdx() == 2) {
				s.selectedField=0;
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.WAGONS;
				return gameStates.Factory;
			}
			if (menu.getCurrentIdx() == 3){
				s.gameState = gameStates.OilFields;
				return gameStates.OilFields;
			}
			if (menu.getCurrentIdx() == 4){
				s.gameState = gameStates.BuyDrills;
				return gameStates.BuyDrills;
			}
			if (menu.getCurrentIdx() == 8) {
				logic.setNextPlayer();
				System.out.println("Game state: " + s.gameState + " " + s.lastYear);
			
				if (logic.isGameEnd()) {
					System.out.println("Game state: " + s.gameState);
					s.gameState = gameStates.End;
				}
			}
			s.selectedField = 0;
		}

		
		return gameStates.Game;
		
	}
	
	
	
}
