package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.World;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.Factory;


public class FactoryScreen implements IGameScreen{

	public Texture titleScreen;
	BitmapFont cFontBlue;
	BitmapFont cFontGray;
	BitmapFont cFont;
	BitmapFont cFontRed;
	public Sprite menuArrow;
	private State s = State.getInstance();
	private World world;
	private SFX sfx;
	private Logic logic;
	final Logger log = LogManager.getLogger(GameScreen.class);
	private int factoriesNr;
	private int deltaY = 25;
	private int menuLeftX = 450;
	private int menuUpperY = Gdx.graphics.getHeight() - 200;
	
	public FactoryScreen(SFX sfx, World world, Logic logic) {
		this.world = world;
		this.sfx = sfx;
		this.logic = logic;
		Load();
		log.info("constructed");
	}
	
	@Override
	public void Load() {
		titleScreen = new Texture("Images/chart6.png");
		menuArrow = new Sprite(new Texture("Images/marker2.png"));		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Texas.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 30;
		parameter.characters = " -abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
		cFont = generator.generateFont(parameter);
		cFontRed = generator.generateFont(parameter);
		cFontGray = generator.generateFont(parameter);
		parameter.size = 80;
		cFontBlue = generator.generateFont(parameter);
		generator.dispose();
		cFont.setColor(Color.BLACK);
		cFontRed.setColor(Color.RED);
		cFontBlue.setColor(Color.BLUE);
		cFontGray.setColor(Color.GRAY);		
		log.trace("loaded");
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		log.trace("draw");
	//	drawFactory(batch, menuList, captionOffset, x, y);
		switch (s.currFactoryType) {
		case DRILLS:
			drawFactory(spriteBatch,world.drillFactory, menuLeftX, menuUpperY);
			factoriesNr = world.drillFactory.size();
			break;
		case PUMP:
			drawFactory(spriteBatch,world.pumpsFactory, menuLeftX, menuUpperY);
			factoriesNr = world.pumpsFactory.size();
			break;
		case WAGONS:
			drawFactory(spriteBatch,world.wagonFactory, menuLeftX, menuUpperY);
			factoriesNr = world.wagonFactory.size();
			break;
		default:
			break;
		}
	}

	@Override
	public gameStates Update(long gameTime) {
		log.trace("update");
		return processKeysFactoryScreen();
	}

	private void drawFactory(SpriteBatch batch, ArrayList<Factory> menuList, int x, int y) {
		log.trace("Draw Factory");
		float scale = .8f;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		int idx = 0;
		char fieldLetter = 'A';

		cFontBlue.setScale(.4f, .4f);


		
		for (Factory factory : menuList) {
			if (factory.hasOwner()) {
				cFontGray.setScale(scale, scale);
				cFontGray.draw(batch, fieldLetter + " " + factory.name, x, y - idx * deltaY);
				cFontGray.draw(batch, factory.owner.name, x + 230, y - idx * deltaY);

			} else {
				cFont.setScale(scale, scale);
				cFont.draw(batch, fieldLetter + " " + factory.name, x, y - idx * deltaY);
				cFont.draw(batch, String.valueOf(factory.getPrice()), x + 230, y - idx * deltaY);
			}
			idx++;
			fieldLetter++;
		}

		cFontRed.setScale(scale, scale);
		fieldLetter = (char) ('A' + s.selectedField);
		cFontRed.draw(batch, fieldLetter + " " + menuList.get(s.selectedField).name, x, y - deltaY * s.selectedField);
		if (menuList.get(s.selectedField).hasOwner()) {
			cFontRed.draw(batch, menuList.get(s.selectedField).owner.name, x + 230, y - deltaY * s.selectedField);
		} else {

			cFontRed.draw(batch, String.valueOf(menuList.get(s.selectedField).getPrice()), x + 230,
					y - deltaY * s.selectedField);
		}


		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - s.selectedField * deltaY, 25, 25);
		menuArrow.draw(batch);
		batch.end();
	}
	
	public gameStates processKeysFactoryScreen() {

		System.out.println("Mouse X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY());
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
	//	int menuUpperY = 100;
		
		System.out.println("MY: "+menuUpperY);
		
		
		if(mx>menuLeftX && mx < 590 && my > 200 && my<(200+deltaY*factoriesNr)){
			if((my-200)/deltaY != s.selectedField){
					sfx.PlaySound(Sounds.MenuChange);
					s.selectedField = (my-200)/deltaY;
					System.out.println("change: "+((my-200) / deltaY));
			}		
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sfx.PlaySound(Sounds.MenuChange);

			if (s.selectedField > 0)
				s.selectedField--;
			s.moveUp = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sfx.PlaySound(Sounds.MenuChange);
			if (s.selectedField < factoriesNr-1)
				s.selectedField++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			return gameStates.Game;
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			if (!logic.buyFactory(s.currFactoryType)) {
				sfx.PlaySound(Sounds.DenySound);
			}
		}
		return gameStates.Factory;
	}
	
	
	
	/*
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
	*/
}
