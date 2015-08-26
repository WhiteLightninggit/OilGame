package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Game {

	public SpriteBatch batch;
	public Texture img;
	// public Sprite s;
	public Sprite menuArrow;
	public Texture titleScreen;
	public Texture line;
	private int internalState = 0;
	public World world;
	Config c;
	public Music menuSound;
	public Sound menuChangeSound;
	ArrayList<String> menuList = new ArrayList<String>();
	int selectedField=0;

	boolean flag1 = false;
	boolean playMusic = true;
	boolean moveUp = false;
	int z = 0;

	public int selectedMenuItem = 0;

	public OrthographicCamera camera;

	public enum gameStates {
		Test, Title, Setup, OilFields, Game, End
	};

	public gameStates gameState = gameStates.Setup;

	BitmapFont font, redFont, cFont, cFontRed, cFontGreen, cFontYellow, cFontBlue, cFontGray;

	public Game() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.setToOrtho(true);

		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.GREEN);
		redFont = new BitmapFont();
		redFont.setColor(Color.RED);
		c = new Config();
		world = new World(c);
		
	//	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/cfont.ttf"));
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
		
		menuList.add("Start Game");
		menuList.add("Options");
		menuList.add("Story");
		menuList.add("Credits");
		menuList.add("Exit");
		
	}

	public void update() {

	}

	public void nextInternalState() {
		internalState++;
	}

	public void draw() {

		switch (gameState) {
		case Test:
			// drawTest();
			break;

		case Title:
			drawTitle2(menuList);

			break;

		case Setup:
			drawSetup();
			break;
		case OilFields:
			break;
		default:
			break;
		}

	}

	
	
	private void drawTitle2(ArrayList<String> menuList) {
		int x=350;
		int y=250;
		int deltaX = 50;
		int deltaY = 40;
		int xOffset;
		int yOffset;
		//	menuSound.setLooping(true);
		//	menuSound.play();		
		batch.begin();
	//	batch.draw(img, 200, 200);
		batch.draw(titleScreen, 0, 0,800,600);		
		cFontBlue.draw(batch,"Oel - P", 300, 450);		
		int idx=0;		
		for (String string : menuList) {
			cFont.draw(batch, string, x, y-idx*deltaY);
			idx++;
		}			
	//	 Color c = batch.getColor();
      //   batch.setColor(c.r, c.g, c.b, 0.5f); 			
		cFontRed.draw(batch, menuList.get(selectedMenuItem), x, y-deltaY*selectedMenuItem);	
		
		if(flag1){
			z=z+3;
			System.out.println("z: "+z);			
			if (z>=150) {
				z=1;
				flag1=false;
			}
			
			batch.draw(line,x-12,y-20-selectedMenuItem*deltaY,z,6);
		}         		
		
		//batch.draw(menuArrow,x-30,y-16-selectedMenuItem*deltaY,20,20);
		menuArrow.setBounds(x-35+z, y-15-selectedMenuItem*deltaY, 25, 25);
		menuArrow.draw(batch);
		
	//	 c = batch.getColor();
     //    batch.setColor(c.r, c.g, c.b, 0.5f); 	
		batch.end();
	}
	
	
	
	
	private void drawFields(ArrayList<OilField> menuList, int captionOffset) {
		int x=300;
		int y= Gdx.graphics.getHeight() - captionOffset;
		int deltaX = 50;
		int deltaY = 25;
		int xOffset;
		int yOffset;
		float scale=.8f;
		
		
		//	menuSound.setLooping(true);
		//	menuSound.play();		
		batch.begin();
	//	batch.draw(img, 200, 200);
		batch.draw(titleScreen, 0, 0,800,600);		
	//	cFontBlue.draw(batch,"Oel - P", 300, 450);		
		int idx=0;		
		char fieldLetter = 'A';
		
		cFontBlue.setScale(.4f,.4f);
		cFontBlue.draw(batch, "Oil Field", x, y+deltaY+10);
		cFontBlue.draw(batch, "Price ($)", x+215, y+deltaY+10);
		cFontBlue.draw(batch, "Owner", x+315, y+deltaY+10);
		
		for (OilField oilField : menuList) {
			if(oilField.hasOwner){			
				 cFontGray.setScale( scale,scale);
				cFontGray.draw(batch, fieldLetter+" "+oilField.name, x, y-idx*deltaY);
				cFontGray.draw(batch, oilField.owner.name, x+330, y-idx*deltaY);
				
			} else {
				cFont.setScale(scale, scale);
				cFont.draw(batch, fieldLetter+" "+oilField.name, x, y-idx*deltaY);
				cFont.draw(batch, String.valueOf(oilField.price), x+230, y-idx*deltaY);
			}
			idx++;
			fieldLetter++;
		}			
		
		cFontRed.setScale( scale,scale);
		fieldLetter = (char) ('A' + selectedField);
		cFontRed.draw(batch, fieldLetter+" "+menuList.get(selectedField).name, x, y-deltaY*selectedField);
		if(menuList.get(selectedField).hasOwner){
			cFontRed.draw(batch, menuList.get(selectedField).owner.name, x+330, y-deltaY*selectedField);
		} else {
				
			cFontRed.draw(batch, String.valueOf(menuList.get(selectedField).price), x+230, y-deltaY*selectedField);
		}
		if(flag1){
			z=z+3;
			System.out.println("z: "+z);			
			if (z>=150) {
				z=1;
				flag1=false;
			}
			
			batch.draw(line,x-12,y-20-selectedField*deltaY,z,6);
		}         		
		
		menuArrow.setBounds(x-35+z, y-15-selectedField*deltaY, 25, 25);
		menuArrow.draw(batch);
		

		batch.end();
	}


	private void drawSetup() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		int x = 300;
		int captionHeight = 20;
		int captionOffset = 100;

		if (internalState == 0) {
		//	displayOilFields(x, captionHeight, captionOffset);
			drawFields(world.fieldsList, captionOffset);
		}

		if (internalState == 1) {
			displayWagonFactory(x, captionHeight, captionOffset);

		}

		if (internalState == 2) {
			displayPumpsFactory(x, captionHeight, captionOffset);
		}

		if (internalState == 3) {
			displayDrillsFactory(x, captionHeight, captionOffset);
		}

		if (internalState == 4) {
			Gdx.app.exit();
		}

	}

	private void displayDrillsFactory(int x, int captionHeight, int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Drill factories to sale:", 20, Gdx.graphics.getHeight() - captionOffset);

		char menuItemLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Drill Price ($)", x + 100, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Drills nr.", x + 220, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight() - captionOffset + 20);

		for (int i = 0; i < world.drillFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);
			font.draw(batch, world.drillFactory.get(i).name, x, y);
			font.draw(batch, Integer.toString(world.drillFactory.get(i).itemPrice), x + 100, y);
			font.draw(batch, Integer.toString(world.drillFactory.get(i).availableItems), x + 220, y);
			/*
			 * font.draw(batch,
			 * Integer.toString(world.drillFactory.get(i).maxOrderSize), x +
			 * 320, y);
			 */
			if (world.drillFactory.get(i).hasOwner) {
				font.draw(batch, world.drillFactory.get(i).owner.name, x + 320, y);
			}

		}
		batch.end();
	}

	private void displayPumpsFactory(int x, int captionHeight, int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Pumps factories to sale:", 20, Gdx.graphics.getHeight() - captionOffset);

		char fieldLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Pump Price ($)", x + 100, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Pumps nr.", x + 220, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight() - captionOffset + 20);

		for (int i = 0; i < world.pumpsFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);
			font.draw(batch, world.pumpsFactory.get(i).name, x, y);
			font.draw(batch, Integer.toString(world.pumpsFactory.get(i).itemPrice), x + 100, y);
			font.draw(batch, Integer.toString(world.pumpsFactory.get(i).availableItems), x + 220, y);
			/*
			 * font.draw(batch,
			 * Integer.toString(world.pumpsFactory.get(i).maxOrderSize), x +
			 * 320, y);
			 */
			if (world.pumpsFactory.get(i).hasOwner) {
				font.draw(batch, world.pumpsFactory.get(i).owner.name, x + 320, y);
			}

		}
		batch.end();
	}

	private void displayWagonFactory(int x, int captionHeight, int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Wagons factories to sale:", 20, Gdx.graphics.getHeight() - captionOffset);

		char fieldLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Wagon Price ($)", x + 100, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Wagons nr.", x + 220, Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight() - captionOffset + 20);

		for (int i = 0; i < world.wagonFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);

			font.draw(batch, fieldLetter + ". " + world.wagonFactory.get(i).name, x, y);
			font.draw(batch, Integer.toString(world.wagonFactory.get(i).itemPrice), x + 100, y);
			font.draw(batch, Integer.toString(world.wagonFactory.get(i).availableItems), x + 220, y);
			/*
			 * font.draw(batch,
			 * Integer.toString(world.wagonFactory.get(i).maxOrderSize), x +
			 * 200, y);
			 */
			if (world.wagonFactory.get(i).hasOwner) {
				font.draw(batch, world.wagonFactory.get(i).owner.name, x + 320, y);
			}
			fieldLetter++;

		}

		batch.end();
	}
	
}

//	 c = batch.getColor();
//    batch.setColor(c.r, c.g, c.b, 0.5f); 	
