package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.FontsPack;


public class FactoryScreen implements IGameScreen{


	FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();
	private State s = State.getInstance();
	private World world;
	private SFX sfx;
	private Logic logic;
	final Logger log = LogManager.getLogger(GameScreen.class);
	//private int deltaY = 25;
	private int menuLeftX = 450;
	private int menuUpperY = Gdx.graphics.getHeight() - 200;
	private Menu drillMenu;
	private Menu pumpMenu;
	private Menu wagonMenu;
	private Menu currentMenu;
	
	public FactoryScreen(SFX sfx, World world, Logic logic) {
		this.world = world;
		this.sfx = sfx;
		this.logic = logic;
		Load();
		log.info("constructed");
	}
	
	@Override
	public void Load() {
		drillMenu = createFactoryMenu(world.drillFactory);
		pumpMenu = createFactoryMenu(world.pumpsFactory);
		wagonMenu = createFactoryMenu(world.wagonFactory);		
		log.trace("loaded");
	}

	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		log.trace("draw");
	
		switch (s.currFactoryType) {
		case DRILLS:
			currentMenu = drillMenu;
			break;
		case PUMP:
			currentMenu = pumpMenu;
			break;
		case WAGONS:
			currentMenu = wagonMenu;
			break;
		default:
			break;
		}
		
		drawFactory(spriteBatch,world.drillFactory, menuLeftX, menuUpperY);
		drawImg(spriteBatch);
		//drawFactoryHUD(spriteBatch);
	}

	@Override
	public gameStates Update(long gameTime) {
		log.trace("update");
		return processKeysFactoryScreen();
	}

	private void drawFactory(SpriteBatch batch, ArrayList<Factory> menuList, int x, int y) {
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, 800, 600);
		batch.end();		
		currentMenu.drawMenu(batch);
	}	
	
	public gameStates processKeysFactoryScreen() {
		currentMenu.processKeys(sfx);

		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			return gameStates.Game;			
		}
	
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			if (!logic.buyFactory(s.currFactoryType,currentMenu.getSelectedIdx())) {
				sfx.PlaySound(Sounds.DenySound);			
			} else {
				currentMenu.changeFontForCurrentItem(fonts.cFontGray);
				currentMenu.getItem().text=currentMenu.getItem().name +" "+ logic.getCurrentPlayer().name;
			}
		}	
	
		return gameStates.Factory;
	}
	
	//To Delete
	private void drawFactoryHUD(SpriteBatch batch) {
		int x = 20;
		int y = 500;
		batch.begin();
	/*	fonts.cFontRed.draw(batch, "Factory: " + logic.getCurrentFactory().name, x, y);
		if (logic.getCurrentFactory().hasOwner()) {
			fonts.cFontRed.draw(batch, "Owner: " + logic.getCurrentFactory().owner.name, x, y - 30);
		} else {
			fonts.cFontRed.draw(batch, "Owner: " + "FREE", x, y - 30);
		}
		fonts.cFontRed.draw(batch, "ItemPrice: " + logic.getCurrentFactory().itemPrice, x, y - 60);
	
		if(logic.actionPerformed()){
			batch.draw(album.unchecked, 450, 100, 50, 50);
			fonts.cFontBlue.draw(batch, "- End turn.", 520, 130);
		}
		*/
		batch.end();
	}
	
	private void drawImg(SpriteBatch batch) {
		int x = 100;
		int y = 300;
		int width = 220;
		int height = 220;
		ArrayList<Texture> picsList = album.getMenuPicsList();
		
		batch.begin();
			batch.draw(picsList.get(currentMenu.getSelectedIdx()), x, y, width, height);
		batch.end();
	}
	
	private Menu createFactoryMenu(ArrayList<Factory> factory){
		Menu menu = new Menu();
		menu.y=500;
		menu.x=400;
		menu.menuCaptionWidth = 300;
		
		for(int i=0; i<factory.size(); i++ ){
			menu.setData(i,factory.get(i).name, String.valueOf(factory.get(i).getPrice()) );
		}	
		
		return menu;
	}
	
}
