package WhiteLightning.Oel.game.Screens;

import java.util.function.LongUnaryOperator;

import com.badlogic.gdx.Gdx;
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
import WhiteLightning.Oel.game.Menu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;
import WhiteLightning.Oel.game.Objects.Player;

public class ActionScreen implements IGameScreen{

	private State s = State.getInstance();
	private Sprite menuArrow;
	private Texture titleScreen;
	private Logic logic;
	private long gameTime;
	private gameStates nextState = gameStates.Title;
	private Config conf;
	
	private Menu myMenu;

	private BitmapFont font;
	private BitmapFont redFont;
	private BitmapFont cFont;
	private BitmapFont cFontRed;
	private BitmapFont cFontGreen;
	private BitmapFont cFontYellow;
	private BitmapFont cFontBlue;
	private BitmapFont cFontGray;
	private SFX sfx;
	
	public Texture img;
	public Texture oilFieldImg;
	public Texture pumpImg;
	public Texture wagonImg;
	public Texture drillImg;
	public Texture trainImg;
	public Texture pumpsImg;
	public Texture drillsImg;
	public Texture skipImg;
	public Texture nextImg;
	public Texture sabotImg;
	
	private int menuItemHeight = 40;
	private int screenWidth = 800;
	private int screenHeight = 600;
	private float fontScaleX = 1f;
	private float fontScaleY = 1f;
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
	
	
	public ActionScreen(Logic logic, SFX sfx, Config config, Menu menu) {
		this.logic=logic;		
		this.myMenu = menu;
		this.sfx = sfx;
		this.conf = config;
		Load();
	}
	
	
	private int drawMenuNew(Menu menu, int x, int y,SpriteBatch batch) {		
		batch.begin();
		batch.draw(titleScreen, 0, 0, screenWidth, screenHeight);
		cFont.setScale(fontScaleX, fontScaleY);
		cFontRed.setScale(fontScaleX, fontScaleY);
		cFontGray.setScale(fontScaleX, fontScaleY);
		
		char itemLetter = 'A'; //First menu object index letter
		menu.iterateMode();		
		while(menu.hasNext()){						
				if (menu.isCurrent()) {
					cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());
				} else {
					cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * menuItemHeight);					
				}
				itemLetter++;			
				menu.selectNextItem();
		}		
		//Displaying last item from menu
		if (menu.isCurrent()) {
			cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());
		} else {
			cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * menuItemHeight);					
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
		this.gameTime = TimeUtils.nanoTime();
		titleScreen = new Texture("Images/chart6.png");
		menuArrow = new Sprite(new Texture("Images/marker2.png"));
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		redFont = new BitmapFont();
		redFont.setColor(Color.RED);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Texas.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 30;
		parameter.characters = " -abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
		cFont = generator.generateFont(parameter);
		cFontRed = generator.generateFont(parameter);
		cFontGreen = generator.generateFont(parameter);
		cFontGray = generator.generateFont(parameter);
		parameter.size = 80;
		cFontYellow = generator.generateFont(parameter);
		cFontBlue = generator.generateFont(parameter);
		generator.dispose();
		cFont.setColor(Color.BLACK);
		cFontRed.setColor(Color.RED);
		cFontGreen.setColor(Color.GREEN);
		cFontYellow.setColor(Color.YELLOW);
		cFontBlue.setColor(Color.BLUE);
		cFontGray.setColor(Color.GRAY);		
		
		pumpImg = new Texture("Images/pump.jpg");
		oilFieldImg = new Texture("Images/oilfield.jpg");
		wagonImg = new Texture("Images/wagon.jpg");
		drillImg = new Texture("Images/drill.jpg");
		trainImg = new Texture("Images/train.jpg");
		pumpsImg = new Texture("Images/oilPump.png");
		drillsImg = new Texture("Images/oilDrills.jpg");
		skipImg = new Texture("Images/skip.jpg");
		nextImg = new Texture("Images/next.png");
		sabotImg = new Texture("Images/sabotage.jpg");
		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		drawMenuNew(myMenu, 450, 500, spriteBatch);
		drawImg(spriteBatch);
		
	}

	@Override
	public gameStates Update(long gameTime) {
		
		processKeysGame(myMenu);
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			sfx.PlaySound(Sounds.MenuChange);
			return gameStates.End;
		}
		
		return gameStates.Game;
	}

	private void displayStatus(SpriteBatch batch, int x, int y) {
		
		font.setScale(0.9f*fontScaleX, 0.9f*fontScaleY);
	//	font.draw(batch, "Player: " + logic.getCurrentPlayer().name + " $" + logic.getCurrentPlayer().cash, x, y);
	//	logic.w = 
		Player p = logic.getCurrentPlayer();
		font.draw(batch, "Player: " + logic.getCurrentPlayer().name, x, y);
		font.draw(batch, "Year: " + logic.getCurrentYear(), x, y - 30);
		font.draw(batch, "State: " + s.gameState.toString(), x, y - 60);
	}
	
	private void drawImg(SpriteBatch batch) {

batch.begin();

			int x = 100;
			int y = 300;
			int width = 220;
			int height = 220;

			if (s.selectedAction == 0) {
				batch.draw(drillImg, x, y, width, height);
			}
			if (s.selectedAction == 1) {
				batch.draw(pumpImg, x, y, width, height);
			}
			if (s.selectedAction == 2) {
				batch.draw(wagonImg, x, y, width, height);
			}
			if (s.selectedAction == 3) {
				batch.draw(oilFieldImg, x, y, width, height);
			}
			if (s.selectedAction == 4) {
				batch.draw(drillsImg, x, y, width, height);
			}
			if (s.selectedAction == 5) {
				batch.draw(pumpsImg, x, y, width, height);
			}
			if (s.selectedAction == 6) {
				batch.draw(trainImg, x, y, width, height);
			}
			if (s.selectedAction == 8) {
				batch.draw(skipImg, x, y, width, height);
			}
			if (s.selectedAction == 9) {
				batch.draw(sabotImg, x, y, width, height);
			}
			if (s.selectedAction == 10) {
				batch.draw(nextImg, x, y, width, height);
			}
	batch.end();
	}
	
	
	
	public void processKeysGame(Menu menu) {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
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
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			sfx.PlaySound(Sounds.MenuChange);

			System.out.println("menu: idx "+menu.getCurrentIdx()+" action "+menu.selectCurrentItem().toString() );
			
			if (menu.getCurrentIdx() == 0) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.PUMP;
			}
			if (menu.getCurrentIdx() == 1) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.DRILLS;
			}
			if (menu.getCurrentIdx() == 2) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.WAGONS;
			}
			if (menu.getCurrentIdx() == 3)
				s.gameState = gameStates.OilFields;
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

	}
	
	
	
}
