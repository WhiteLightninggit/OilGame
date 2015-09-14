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


public class Game {

	public State s = State.getInstance();
	public SpriteBatch batch;
	public Texture img;
	public Sprite menuArrow;
	public Texture titleScreen;
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

	public gameStates gameState = gameStates.Title;

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
		actionsList.add("=== OTHER POSSIBILITIES ===");
		actionsList.add("Next player");
		actionsList.add("Sabotage");
		actionsList.add("Skip turn");

	}

	public void update() {
		/*
		 * Decyzja nalezy do Ciebie: Gracz <name> $=5634653
		 * 
		 * <B>Kupowanie:</B> A - Fabryki Wiertel B - Zak³ady Pomp C - Firmy
		 * Wagonowe D - Pola Naftowe E - Wiert³a F - Pompy G - Wagony
		 * 
		 * <B>Pozostale mozliwosci: </B> H- Nastepny gracz I - Proba Sabotazu J
		 * - Przeczekanie
		 * 
		 * <B> Nacisnij dowolny Klawisz </B>
		 * 
		 */

	}

	public void nextInternalState() {
		s.internalState++;
	}

	public void draw() {
		int captionOffset = 100;
		switch (gameState) {
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
			switch (s.internalState) {
			case 0:
				drawFactory(world.drillFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				// displayDrillsFactory(20, 100, captionOffset);
				break;
			case 1:
				drawFactory(world.pumpsFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				// displayPumpsFactory(20, 100, captionOffset);
				break;
			case 2:
				drawFactory(world.wagonFactory, captionOffset, 300, Gdx.graphics.getHeight() - captionOffset);
				// displayWagonFactory(20, 100, captionOffset);
				break;
			default:
				break;
			}

			break;
		case Setup:
			// drawSetup();
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

	private void drawGameTitle() {
		batch.begin();
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
		return 0;
	}

	private int drawCredits() {
		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		cFontRed.draw(batch, "Long long time ago in galaxy far away", 300, 450);
		batch.end();
		return 0;
	}

	private int drawMenu(ArrayList<String> menuList, int selectedItem, int x, int y) {
		int deltaY = 40;

		batch.begin();

		batch.draw(titleScreen, 0, 0, 800, 600);
		cFont.setScale(1f, 1f);
		cFontRed.setScale(1f, 1f);
		cFontGray.setScale(1f, 1f);

		int idx = 0;
		char itemLetter = 'A';
		for (String string : menuList) {

			//Grey out fields, note will work only for fields in middle of menu
			if (string.charAt(0) == '=') {
				cFontGray.draw(batch, "  "+string, x, y - idx * deltaY);
				
				if(selectedItem == idx){
					if(s.moveUp){
						selectedItem --;
					} else {
						selectedItem++;
					}
				}
				
			} else {
				if(idx!=selectedItem){
					cFont.draw(batch, itemLetter+" "+string, x, y - idx * deltaY);
				}
				itemLetter++;
			}		
			idx++;
		}

		cFontRed.draw(batch, "X "+menuList.get(selectedItem), x, y - deltaY * selectedItem);

		menuArrow.setBounds(x - 35 + s.z, y - 15 - selectedItem * deltaY, 25, 25);
		menuArrow.draw(batch);

		displayStatus(20, 100);
		
		batch.end();
		return selectedItem;
	}

	private void drawFields(ArrayList<OilField> menuList, int captionOffset, int x, int y) {

		// int deltaX = 50;
		int deltaY = 25;
		float scale = .8f;

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
				cFontGray.draw(batch, oilField.owner.name, x + 330, y - idx * deltaY);

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
			cFontRed.draw(batch, menuList.get(s.selectedField).owner.name, x + 330, y - deltaY * s.selectedField);
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

	
	private void displayStatus(int x, int y){
		cFontBlue.setScale(.2f, .2f);
		cFontBlue.draw(batch, "Player: "+l.getCurrentPlayer().name+" $"+l.getCurrentPlayer().cash, x, y);		
		cFontBlue.draw(batch, "Year: "+l.getCurrentYear(), x, y-30);	
		cFontBlue.draw(batch, "State: "+gameState.toString(), x, y-60);	
	}
	
	private void drawFactory(ArrayList<Factory> menuList, int captionOffset, int x, int y) {

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
				cFontGray.draw(batch, factory.owner.name, x + 330, y - idx * deltaY);

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
			cFontRed.draw(batch, menuList.get(s.selectedField).owner.name, x + 330, y - deltaY * s.selectedField);
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

		batch.end();
	}

}
