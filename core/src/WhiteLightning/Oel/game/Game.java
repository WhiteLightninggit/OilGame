package WhiteLightning.Oel.game;

import java.util.ArrayList;

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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.OilField;


public class Game {

	public boolean textEnterFlag = false;
	
	Skin localSkin = new Skin();
	
	public State s = State.getInstance();
	public SpriteBatch batch;
	public Texture img;
	public Sprite menuArrow;
	public Texture titleScreen;
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
	
	public ArrayList<Texture> nrImages;
	public Texture nr1Img;
	public Texture nr2Img;
	public Texture nr3Img;
	public Texture nr4Img;
	public Texture selectedNrImg;
	
	public Texture line;
	public World world;
	public Config c;
	public Logic l;
	public Music menuSound;
	public Sound menuChangeSound;
	public Sound denySound;
	

	public OrthographicCamera camera;



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
		this.world = new World(c);
		l = new Logic(this);
	//	FreeTypeFontGenerator generator = new
	//	FreeTypeFontGenerator(Gdx.files.internal("Fonts/cfont.ttf"));
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Texas.ttf"));
	//	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/a1.ttf"));
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

	public void update() {}

	public void draw(Menu menu, gameStates gs) {
		int captionOffset = 100;
		switch (gs) {
		case Test:
			// drawTest();
			break;
		case Game:
			s.selectedAction = drawMenuNew(menu, 450, 500);
			break;
		case Title:
			//s.selectedMenuItem
			drawMenuNew(menu, 500, 250);
			drawGameTitle(); // s.selectedMenuItem
			break;
		case Options:
			drawOptions();
			break;
		case Story:
			drawStory();
			break;
		case Credits:
			drawCredits();
			break;
		case Factory:
			switch (s.currFactoryType) {
			case DRILLS:
				drawFactory(world.drillFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				break;
			case PUMP:
				drawFactory(world.pumpsFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				break;
			case WAGONS:
				drawFactory(world.wagonFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				break;
			default:
				break;
			}

			break;
		case Setup:
			drawSetup();
			break;
		case OilFields:
			// int x = 300;
			// int captionHeight = 20;
			drawFields(world.fieldsList, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
			break;
		default:
			break;
		}
	}
	
	private int drawMenuNew(Menu menu, int x, int y) {
		int deltaY = 40;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		drawImg();

		cFont.setScale(1f, 1f);
		cFontRed.setScale(1f, 1f);
		cFontGray.setScale(1f, 1f);

		char itemLetter = 'A';
		menu.iterateMode();
		
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
		
		menu.standardMode();
		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - menu.getCurrentIdx() * deltaY, 25, 25);
		menuArrow.draw(batch);

		displayStatus(20, 100);		
		batch.end();
		return menu.getCurrentIdx();
	}
	
	private void drawSetup() {
	//	drawHUD();
		int x=200;
		int offsetX=50;
		
		batch.begin();
		
		batch.draw(titleScreen, 0, 0, 800, 600);
		
		for (int i=0; i<nrImages.size()-1;i++) {
			batch.draw(nrImages.get(i), x+i*50, 300, offsetX, 50);
		}
		
		batch.draw(nrImages.get(nrImages.size()-1), x+(s.players-1)*offsetX, 300, offsetX, 50);
		
		font.setScale(.9f, .9f);
		font.draw(batch, "Input: " + s.sb,500,200);
		s.text.setBounds(200, 200, 100, 40);
		s.text.draw(batch, 1);
		s.text.getOnscreenKeyboard();
		batch.end();
	}
	
	private void drawHUD() {

		int x = 20;
		int y = 500;
		batch.begin();
		font.setScale(.9f, .9f);
		batch.draw(titleScreen, 0, 0, 800, 600);
		font.draw(batch, "Factory: " + l.getCurrentFactory().name, x, y);
		if (l.getCurrentFactory().hasOwner()) {
			font.draw(batch, "Owner: " + l.getCurrentFactory().owner.name, x, y - 30);
		} else {
			font.draw(batch, "Owner: " + "FREE", x, y - 30);
		}

		font.draw(batch, "ItemPrice: " + l.getCurrentFactory().itemPrice, x, y - 60);
		batch.end();
	}
	
	private void drawGameTitle() {
		batch.begin();
		cFontBlue.setScale(1f, 1f);
		cFontBlue.draw(batch, "Oel - P", 300, 450);
		batch.end();
	}

	private int drawOptions() {
		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		cFontGray.draw(batch, "This is option screen.", 300, 450);
		batch.end();
		return 0;
	}

	private int drawStory() {
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

	private int drawCredits() {
		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		cFontRed.draw(batch, "Long long time ago in galaxy far away", 300, 450);
		batch.end();
		return 0;
	}

	private void drawImg() {

		if (s.gameState == gameStates.Game) {

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

		}
	}


	private void drawFields(ArrayList<OilField> menuList, int captionOffset, int x, int y) {
		int deltaY = 25;
		float scale = .8f;
		x = x + 150;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		int idx = 0;
		char fieldLetter = 'A';

		cFontBlue.setScale(.4f, .4f);
		cFontBlue.draw(batch, "Oil Field", x, y + deltaY + 10);
		cFontBlue.draw(batch, "Price ($)", x + 215, y + deltaY + 10);
		cFontBlue.draw(batch, "Owner", x + 315, y + deltaY + 10);

		for (OilField oilField : menuList) {
			if (oilField.hasOwner) {
				cFontGray.setScale(scale, scale);
				cFontGray.draw(batch, fieldLetter + " " + oilField.name, x, y - idx * deltaY);
				cFontGray.draw(batch, oilField.owner.name, x + 230, y - idx * deltaY);

			} else {
				cFont.setScale(scale, scale);
				cFont.draw(batch, fieldLetter + " " + oilField.name, x, y - idx * deltaY);
				cFont.draw(batch, String.valueOf(oilField.getPrice()), x + 230, y - idx * deltaY);
			}
			idx++;
			fieldLetter++;
		}

		cFontRed.setScale(scale, scale);
		fieldLetter = (char) ('A' + s.selectedField);
		cFontRed.draw(batch, fieldLetter + " " + menuList.get(s.selectedField).name, x, y - deltaY * s.selectedField);
		if (menuList.get(s.selectedField).hasOwner) {
			cFontRed.draw(batch, menuList.get(s.selectedField).owner.name, x + 230, y - deltaY * s.selectedField);
		} else {

			cFontRed.draw(batch, String.valueOf(menuList.get(s.selectedField).getPrice()), x + 230,
					y - deltaY * s.selectedField);
		}
		
		
		
		if (s.animFlag) {
			s.menuAnimX = s.menuAnimX + 3;
			System.out.println("z: " + s.menuAnimX);
			if (s.menuAnimX >= 150) {
				s.menuAnimX = 1;
				s.animFlag = false;
			}

			batch.draw(line, x - 12, y - 20 - s.selectedField * deltaY, s.menuAnimX, 6);
		}

		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - s.selectedField * deltaY, 25, 25);
		menuArrow.draw(batch);

		displayStatus(20, 100);

		batch.end();
	}

	private void displayStatus(int x, int y) {
		font.setScale(.9f, .9f);
		font.draw(batch, "Player: " + l.getCurrentPlayer().name + " $" + l.getCurrentPlayer().cash, x, y);
		font.draw(batch, "Year: " + l.getCurrentYear(), x, y - 30);
		font.draw(batch, "State: " + s.gameState.toString(), x, y - 60);
	}

	private void drawFactory(ArrayList<Factory> menuList, int captionOffset, int x, int y) {
		x = x + 150;
		// int deltaX = 50;
		int deltaY = 25;
		float scale = .8f;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		int idx = 0;
		char fieldLetter = 'A';

		cFontBlue.setScale(.4f, .4f);
		// cFontBlue.draw(batch, "Oil Field", x, y + deltaY + 10);
		// cFontBlue.draw(batch, "Price ($)", x + 215, y + deltaY + 10);
		// cFontBlue.draw(batch, "Owner", x + 315, y + deltaY + 10);

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
		if (s.animFlag) {
			s.menuAnimX = s.menuAnimX + 3;
			System.out.println("z: " + s.menuAnimX);
			if (s.menuAnimX >= 150) {
				s.menuAnimX = 1;
				s.animFlag = false;
			}

			batch.draw(line, x - 12, y - 20 - s.selectedField * deltaY, s.menuAnimX, 6);
		}

		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - s.selectedField * deltaY, 25, 25);
		menuArrow.draw(batch);
		displayStatus(20, 100);
		batch.end();
	}

}
