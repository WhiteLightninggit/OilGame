package WhiteLightning.Oel.game.Screens;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.Menu;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class TitleScreen implements IGameScreen{

	private State s = State.getInstance();
	private long gameTime;
	private gameStates nextState = gameStates.Title;
public FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();
	private Menu myMenu;


	private SFX sfx;
	
	private int menuItemHeight = 40;
	private int screenWidth = 800;
	private int screenHeight = 600;
	private int titleX = 300;
	private int titleY = 450;
	private int cursorWidth = 25;
	private int cursorHeight = 25;
	private int cursorXOffset = 37;
	private int cursorYOffset = 22;
	private String title = "OEL - P";
	private int menuX = 500;
	private int menuY = 250;
	
	
	public TitleScreen(Logic logic, SFX sfx, Menu menu) {
		this.myMenu = menu;
		this.sfx = sfx;
		Load();
	}
	
	@Override
	public void Load() {	
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);		
		numbers.forEach( value -> value+=2);		
		this.gameTime = TimeUtils.nanoTime();
	}
	
	private int drawMenuNew(Menu menu, int x, int y,SpriteBatch batch) {		
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, screenWidth, screenHeight);
		
		char itemLetter = 'A'; //First menu object index letter
		menu.iterateMode();		
		while(menu.hasNext()){						
				if (menu.isCurrent()) {
					fonts.cFontRed.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());
				} else {
					fonts.cFont.draw(batch, itemLetter + " " + menu.getItemAsString(), x, y - menuItemHeight * menu.getCurrentIdx());					
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
		album.menuArrow.setBounds(x - cursorXOffset + s.menuAnimX, y - cursorYOffset - menu.getCurrentIdx() * menuItemHeight, cursorWidth, cursorHeight);
		album.menuArrow.draw(batch);
		batch.end();
		return menu.getCurrentIdx();
	}
	
	
	private int drawMenuNews(Menu menu, int x, int y,SpriteBatch batch) {		
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, screenWidth, screenHeight);
		batch.end();
		return menu.getCurrentIdx();
	}
	
	private void drawGameTitle(SpriteBatch batch) {
		batch.begin();
		fonts.bigFontBlue.draw(batch, title, titleX, titleY);
		batch.end();
	}	

	@Override
	public void Draw(SpriteBatch spriteBatch) {
			drawMenuNew(myMenu, menuX, menuY,spriteBatch);
			drawGameTitle(spriteBatch); 
	}

	@Override
	public gameStates Update(long gameTime) {
		this.gameTime = gameTime;
		processKeysTitle(myMenu);		
		return nextState;
	}	
	
	private void processKeysTitle(Menu menu) {
	
	//	System.out.println("Mouse X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY());
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
		
		if(mx>500 && mx < 700 && my > 350 && my < 540 ){
			if((my-350)/40 != menu.currentIdx){
					sfx.PlaySound(Sounds.MenuChange);
					menu.currentIdx = (my-350)/40;
					System.out.println("change: "+((my-350) / 40));
			}		
		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			nextState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sfx.PlaySound(Sounds.MenuChange);
		    menu.setPreviousItem();
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sfx.PlaySound(Sounds.MenuChange);
			menu.setNextItem();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isButtonPressed(Input.Buttons.LEFT) ) {			
			System.out.println(menu.selectCurrentItem() + " time: "+gameTime);  //remove xxx

			if (menu.getCurrentIdx() == 0){
				nextState = gameStates.Setup;
				s.currentPlayerIdx = 0;
			}
	
			if (menu.getCurrentIdx() == 1) {
				nextState = gameStates.Options;
			}
			if (menu.getCurrentIdx() == 2) {
				nextState = gameStates.Setup;
			}
			if (menu.getCurrentIdx() == 3) {
				nextState = gameStates.Credits;
			}
			if (menu.getCurrentIdx() == 4)
				nextState = gameStates.End;
		}
	}


}
