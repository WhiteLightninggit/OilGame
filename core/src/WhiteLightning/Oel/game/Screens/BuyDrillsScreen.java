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
import WhiteLightning.Oel.game.Objects.FontsPack;
import WhiteLightning.Oel.game.Objects.OilField;

public class BuyDrillsScreen implements IGameScreen{
	
	private State s = State.getInstance();
	private World world;
	private SFX sfx;
	private Logic logic;
	private int deltaY = 35;
	private FontsPack fonts = new FontsPack();
	public TexturesPack album = new TexturesPack();
	
	public BuyDrillsScreen(SFX sfx, World world, Logic logic) {
		this.world = world;
		this.sfx = sfx;
		this.logic = logic;
		Load();
	}
	
	@Override
	public void Load() {
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

		int priceColumnOffset = 215;
		int ownerColumnOffset = 315;
		
		batch.begin();
		batch.draw(album.titleScreen, 0, 0, 800, 600);
		int idx = 0;
		char fieldLetter = 'A';

		fonts.cFontBlue.draw(batch, "Oil Field", x, y + deltaY);
		fonts.cFontBlue.draw(batch, "Price ($)", x + priceColumnOffset, y + deltaY);
		fonts.cFontBlue.draw(batch, "Owner", x + ownerColumnOffset, y + deltaY);

		for (OilField oilField : menuList) {
			if (oilField.hasOwner) {
				fonts.cFontGray.draw(batch, fieldLetter + " " + oilField.name, x, y - idx * deltaY);
				fonts.cFontGray.draw(batch, oilField.owner.name, x + 230, y - idx * deltaY);
			} else {
				fonts.cFont.draw(batch, fieldLetter + " " + oilField.name, x, y - idx * deltaY);
				fonts.cFont.draw(batch, String.valueOf(oilField.getPrice()), x + 230, y - idx * deltaY);
			}
			idx++;
			fieldLetter++;
		}


		fieldLetter = (char) ('A' + s.selectedField);
		fonts.cFontRed.draw(batch, fieldLetter + " " + menuList.get(s.selectedField).name, x, y - deltaY * s.selectedField);
		if (menuList.get(s.selectedField).hasOwner) {
			fonts.cFontRed.draw(batch, menuList.get(s.selectedField).owner.name, x + 230, y - deltaY * s.selectedField);
		} else {

			fonts.cFontRed.draw(batch, String.valueOf(menuList.get(s.selectedField).getPrice()), x + 230,
					y - deltaY * s.selectedField);
		}		

		album.menuArrow.setBounds(x - 35 + s.menuAnimX, y - 15 - s.selectedField * deltaY, 25, 25);
		album.menuArrow.draw(batch);

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
		return gameStates.BuyDrills;
	}
	
	
	
}
