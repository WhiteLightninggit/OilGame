package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.Menu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;

public class TitleScreen implements IGameScreen{

	public State s = State.getInstance();
	public Sprite menuArrow;
	public Texture titleScreen;
	private Logic logic;
	
	public Menu myMenu;

	BitmapFont font;
	BitmapFont redFont;
	BitmapFont cFont;
	BitmapFont cFontRed;
	BitmapFont cFontGreen;
	BitmapFont cFontYellow;
	BitmapFont cFontBlue;
	BitmapFont cFontGray;

	public TitleScreen(Logic logic, Menu menu) {
		this.logic=logic;		
		this.myMenu = menu;

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
	}
	
	private int drawMenuNew(Menu menu, int x, int y,SpriteBatch batch) {
		int deltaY = 40;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		cFont.setScale(1f, 1f);
		cFontRed.setScale(1f, 1f);
		cFontGray.setScale(1f, 1f);

		char itemLetter = 'A';
		menu.rememberIdx();
		menu.reset();
		
		while(menu.hasNext()){						
				if (menu.isCurrent()) {
					cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - deltaY * menu.getCurrentIdx());
				} else {
					cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * deltaY);					
				}
				itemLetter++;			
				menu.selectNextItem();
		}
		
		//Displaying last item from menu
		if (menu.isCurrent()) {
			cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - deltaY * menu.getCurrentIdx());
		} else {
			cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menu.getCurrentIdx() * deltaY);					
		}
		
		menu.restore();
		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - menu.getCurrentIdx() * deltaY, 25, 25);
		menuArrow.draw(batch);

		displayStatus(batch, 20, 100);	
		batch.end();
		return menu.getCurrentIdx();
	}
	
	
	private void drawFactoryHUD(SpriteBatch batch) {
		int x = 20;
		int y = 500;
		batch.begin();
		font.setScale(.9f, .9f);
		font.draw(batch, "Factory: " + logic.getCurrentFactory().name, x, y);
		if (logic.getCurrentFactory().hasOwner()) {
			font.draw(batch, "Owner: " + logic.getCurrentFactory().owner.name, x, y - 30);
		} else {
			font.draw(batch, "Owner: " + "FREE", x, y - 30);
		}
		font.draw(batch, "ItemPrice: " + logic.getCurrentFactory().itemPrice, x, y - 60);
		batch.end();
	}
	
	private void drawGameTitle(SpriteBatch batch) {
		batch.begin();
		cFontBlue.setScale(1f, 1f);
		cFontBlue.draw(batch, "Oel - P", 300, 450);
		batch.end();
	}	
	
	private void displayStatus(SpriteBatch batch, int x, int y) {
		font.setScale(.9f, .9f);
		font.draw(batch, "Player: " + logic.getCurrentPlayer().name + " $" + logic.getCurrentPlayer().cash, x, y);
		font.draw(batch, "Year: " + logic.getCurrentYear(), x, y - 30);
		font.draw(batch, "State: " + s.gameState.toString(), x, y - 60);
	}

	
	@Override
	public void Load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
			System.out.println("ts draw");
			drawMenuNew(myMenu, 500, 250,spriteBatch);
			drawGameTitle(spriteBatch); 
		//	drawFactoryHUD(spriteBatch);
	}

	@Override
	public gameStates Update(long gameTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
