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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.sun.javafx.scene.control.skin.LabelSkin;

public class Game {

	public boolean textEnterFlag = false;
	
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
	
	public Texture line;
	public World world;
	Config c;
	Logic l;
	public Music menuSound;
	public Sound menuChangeSound;
	public Sound denySound;
	ArrayList<String> menuList = new ArrayList<>();
	ArrayList<String> actionsList = new ArrayList<>();

	public OrthographicCamera camera;

	public enum gameStates {
		Test, Title, Game, Options, Setup, OilFields, Factory, Credits, Story, End
	};

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
		// FreeTypeFontGenerator generator = new
		// FreeTypeFontGenerator(Gdx.files.internal("Fonts/cfont.ttf"));
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

		actionsList.add("Drill Factories");
		actionsList.add("Pumps Factories");
		actionsList.add("Wagons factories");
		actionsList.add("Oil Fields");
		actionsList.add("Buy Drills");
		actionsList.add("Buy Pumps");
		actionsList.add("Buy Wagons");
		actionsList.add("=OTHER POSSIBILITIES");
		actionsList.add("Next player");
		actionsList.add("Sabotage");
		actionsList.add("Skip turn");

	}

	public void update() {
	}

	public void draw() {
		int captionOffset = 100;
		switch (s.gameState) {
		case Test:
			// drawTest();
			break;
		case Game:
			s.selectedAction = drawMenu(actionsList, s.selectedAction, 300, 500);
			break;
		case Title:
			s.selectedMenuItem = drawMenu(menuList, s.selectedMenuItem, 350, 250);
			drawGameTitle();
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

	private void drawSetup() {


		
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
		

		
	//	TextInput listener = new TextInput();
	//	Gdx.input.getTextInput(listener, "Ala", "ma", "kota");
		
		
		
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

	private int drawMenu(ArrayList<String> menuList, int selectedItem, int x, int y) {
		int deltaY = 40;
		x = x + 150;

		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		drawImg();

		cFont.setScale(1f, 1f);
		cFontRed.setScale(1f, 1f);
		cFontGray.setScale(1f, 1f);

		int idx = 0;
		char itemLetter = 'A';
		for (String string : menuList) {
			// Grey out fields, note will work only for fields in middle of menu
			if (string.charAt(0) == '=') {
				cFontGray.draw(batch, "  " + string, x, y - idx * deltaY);
				if (selectedItem == idx) {
					if (s.moveUp) {
						selectedItem--;
					} else {
						selectedItem++;
					}
				}
			} else {
				if (idx != selectedItem) {
					cFont.draw(batch, itemLetter + " " + string, x, y - idx * deltaY);
				} else {
					cFontRed.draw(batch, itemLetter + " " + menuList.get(selectedItem), x, y - deltaY * selectedItem);
				}
				itemLetter++;
			}
			idx++;
		}

		menuArrow.setBounds(x - 35 + s.z, y - 15 - selectedItem * deltaY, 25, 25);
		menuArrow.draw(batch);

		displayStatus(20, 100);

		batch.end();
		return selectedItem;
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
		if (s.flag1) {
			s.z = s.z + 3;
			System.out.println("z: " + s.z);
			if (s.z >= 150) {
				s.z = 1;
				s.flag1 = false;
			}

			batch.draw(line, x - 12, y - 20 - s.selectedField * deltaY, s.z, 6);
		}

		menuArrow.setBounds(x - 35 + s.z, y - 15 - s.selectedField * deltaY, 25, 25);
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
		if (s.flag1) {
			s.z = s.z + 3;
			System.out.println("z: " + s.z);
			if (s.z >= 150) {
				s.z = 1;
				s.flag1 = false;
			}

			batch.draw(line, x - 12, y - 20 - s.selectedField * deltaY, s.z, 6);
		}

		menuArrow.setBounds(x - 35 + s.z, y - 15 - s.selectedField * deltaY, 25, 25);
		menuArrow.draw(batch);
		displayStatus(20, 100);
		batch.end();
	}

}
