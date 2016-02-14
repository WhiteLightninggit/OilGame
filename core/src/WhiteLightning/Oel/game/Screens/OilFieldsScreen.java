package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

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
import WhiteLightning.Oel.game.Objects.OilField;

public class OilFieldsScreen implements IGameScreen{

	public Texture titleScreen;
	public Sprite menuArrow;
	public Texture line;
	BitmapFont cFontBlue;
	BitmapFont cFontGray;
	BitmapFont cFont;
	BitmapFont cFontRed;
	private State s = State.getInstance();
	private World world;
	private SFX sfx;
	private Logic logic;
	private int deltaY = 35;
	
	public OilFieldsScreen(SFX sfx, World world, Logic logic) {
		this.world = world;
		this.sfx = sfx;
		this.logic = logic;
		Load();
	}
	
	@Override
	public void Load() {
			titleScreen = new Texture("Images/chart6.png");
			line = new Texture("Images/line.png");
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
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		int captionOffset = 100;
		int x=300;
		int y=500;		
		drawFields(spriteBatch, world.fieldsList,captionOffset, x, y);		
	}

	@Override
	public gameStates Update(long gameTime) {
		return processKeysOilfields();		
	}

	private void drawFields(SpriteBatch batch, ArrayList<OilField> menuList, int captionOffset, int x, int y) {
		float scale = .8f;
		int priceColumnOffset = 215;
		int ownerColumnOffset = 315;
		
		batch.begin();
		batch.draw(titleScreen, 0, 0, 800, 600);
		int idx = 0;
		char fieldLetter = 'A';

		cFontBlue.setScale(.4f, .4f);
		cFontBlue.draw(batch, "Oil Field", x, y + deltaY);
		cFontBlue.draw(batch, "Price ($)", x + priceColumnOffset, y + deltaY);
		cFontBlue.draw(batch, "Owner", x + ownerColumnOffset, y + deltaY);

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

		menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - s.selectedField * deltaY, 25, 25);
		menuArrow.draw(batch);

		batch.end();
	}	

	
	public gameStates processKeysOilfields() {

		System.out.println("Mouse X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY());
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
		int menuUpperY = 100;
		
		
		if(mx>298 && mx < 590 && my > 100 && my<500){
			if((my-menuUpperY)/deltaY != s.selectedField){
					sfx.PlaySound(Sounds.MenuChange);
					s.selectedField = (my-menuUpperY)/deltaY;
					System.out.println("change: "+((my-menuUpperY) / deltaY));
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
			if (s.selectedField < world.fieldsList.size() - 1)
				s.selectedField++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			return gameStates.Game;			
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || (Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched())) {
			System.out.println(world.fieldsList.get(s.selectedField).name);
			if (!logic.buyField()) {
				sfx.PlaySound(Sounds.DenySound);				
			}
		}
		return gameStates.OilFields;
	}
	
	
	
}
