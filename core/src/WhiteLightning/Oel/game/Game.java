package WhiteLightning.Oel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

	public SpriteBatch batch;
	public Texture img;
	public Sprite s;
	public Texture titleScreen;
	private int internalState = 0;
	private World world;
	Config c;

	public OrthographicCamera camera;

	public enum gameStates {
		Test, Title, Setup, Game, End
	};

	public gameStates gameState = gameStates.Setup;

	BitmapFont font, redFont;

	public Game() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f,
				camera.viewportHeight / 2f, 0);
		camera.setToOrtho(true);

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		redFont = new BitmapFont();
		redFont.setColor(Color.RED);
		c = new Config();
		world = new World(c);

	}

	public void update() {

	}

	public void nextInternalState() {
		internalState++;
	}

	public void draw() {

		switch (gameState) {
		case Title:
			drawTitle();
			break;

		case Setup:
			drawSetup();
			break;
		default:
			break;
		}

	}

	private void drawTitle() {
		batch.begin();
		batch.draw(img, 200, 200);
		batch.draw(titleScreen, 0, 0);
		batch.end();
	}

	private void drawSetup() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		int x = 300;
		int captionHeight = 20;
		int captionOffset = 100;

		if (internalState == 0) {
			displayOilFields(x, captionHeight, captionOffset);
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

	private void displayDrillsFactory(int x, int captionHeight,
			int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Drill factories to sale:", 20,
				Gdx.graphics.getHeight() - captionOffset);

		char menuItemLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Drill Price ($)", x + 100,
				Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Drills nr.", x + 220, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight()
				- captionOffset + 20);

		for (int i = 0; i < world.drillFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);
			font.draw(batch, world.drillFactory.get(i).name, x, y);
			font.draw(batch,
					Integer.toString(world.drillFactory.get(i).itemPrice),
					x + 100, y);
			font.draw(batch,
					Integer.toString(world.drillFactory.get(i).availableItems),
					x + 220, y);
		/*	font.draw(batch,
					Integer.toString(world.drillFactory.get(i).maxOrderSize),
					x + 320, y);
					*/
			if(world.drillFactory.get(i).hasOwner){
				font.draw(batch, world.drillFactory.get(i).owner.name, x + 320,
						y);
			}
			
			
		}
		batch.end();
	}

	private void displayPumpsFactory(int x, int captionHeight, int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Pumps factories to sale:", 20,
				Gdx.graphics.getHeight() - captionOffset);

		char fieldLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Pump Price ($)", x + 100, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Pumps nr.", x + 220, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight()
				- captionOffset + 20);

		for (int i = 0; i < world.pumpsFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);
			font.draw(batch, world.pumpsFactory.get(i).name, x, y);
			font.draw(batch,
					Integer.toString(world.pumpsFactory.get(i).itemPrice),
					x + 100, y);
			font.draw(batch,
					Integer.toString(world.pumpsFactory.get(i).availableItems),
					x + 220, y);
			/*
			 * font.draw(batch,
			 * Integer.toString(world.pumpsFactory.get(i).maxOrderSize), x +
			 * 320, y);
			 */
			if (world.pumpsFactory.get(i).hasOwner) {
				font.draw(batch, world.pumpsFactory.get(i).owner.name, x + 320,
						y);
			}

		}
		batch.end();
	}

	private void displayWagonFactory(int x, int captionHeight, int captionOffset) {
		batch.begin();

		redFont.draw(batch, "Wagons factories to sale:", 20,
				Gdx.graphics.getHeight() - captionOffset);

		char fieldLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Factory", x, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Wagon Price ($)", x + 100,
				Gdx.graphics.getHeight() - captionOffset + 20);
		redFont.draw(batch, "Wagons nr.", x + 220, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Owner", x + 320, Gdx.graphics.getHeight()
				- captionOffset + 20);

		for (int i = 0; i < world.wagonFactory.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);

			font.draw(batch, fieldLetter + ". "
					+ world.wagonFactory.get(i).name, x, y);
			font.draw(batch,
					Integer.toString(world.wagonFactory.get(i).itemPrice),
					x + 100, y);
			font.draw(batch,
					Integer.toString(world.wagonFactory.get(i).availableItems),
					x + 220, y);
			/*
			 * font.draw(batch,
			 * Integer.toString(world.wagonFactory.get(i).maxOrderSize), x +
			 * 200, y);
			 */
			if (world.wagonFactory.get(i).hasOwner) {
				font.draw(batch, world.wagonFactory.get(i).owner.name, x + 320,
						y);
			}
			fieldLetter++;

		}

		batch.end();
	}

	private void displayOilFields(int x, int captionHeight, int captionOffset) {

		batch.begin();

		redFont.draw(batch, "Oil Fields to sale:", 20, Gdx.graphics.getHeight()
				- captionOffset);

		char fieldLetter = 'A';
		int y = 0;

		redFont.draw(batch, "Field name", x, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Price ($)", x + 130, Gdx.graphics.getHeight()
				- captionOffset + 20);
		redFont.draw(batch, "Owner", x + 220, Gdx.graphics.getHeight()
				- captionOffset + 20);

		for (int i = 0; i < world.fieldsList.size(); i++) {
			y = Gdx.graphics.getHeight() - (20 * i + captionOffset);
			
if(world.fieldsList.get(i).hasOwner){
	font.setColor(Color.GRAY);
} else {
	font.setColor(Color.GREEN);
}
			
			

font.draw(batch, fieldLetter + ". " + world.fieldsList.get(i).name,
		x, y);
			font.draw(batch, Integer.toString(world.fieldsList.get(i).price),
					x + 130, y);

			if (world.fieldsList.get(i).hasOwner) {
				font.draw(batch, world.fieldsList.get(i).owner.name, x + 220, y);
			}

			/*
			 * font.draw(batch,
			 * Integer.toString(world.fieldsList.get(i).oilDeepth), x + 190, y);
			 * font.draw(batch,
			 * Integer.toString(world.fieldsList.get(i).oilLeft), x + 250, y);
			 */
			fieldLetter++;
		}
		batch.end();
	}

}
