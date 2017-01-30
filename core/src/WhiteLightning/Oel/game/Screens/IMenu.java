package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Control.SFX;

public interface IMenu {

	public void setData(Integer key, String value);	
	void setData(Integer key, String value, BitmapFont font);	
	void setData(Integer key, String value, BitmapFont font, BitmapFont selectionFont);
	public void setSFX(SFX sfx);
	public void display();
//	public void drawMenu();
	public int getMenuCount();
	public void setMenuCyclic();
	public void setMenuNotCyclic();
	public int getSelectedIdx();
	public void selectNext();
	public void selectPrevious();
	void drawMenu(SpriteBatch batch);
	public void processKeys(SFX sfx);
	MenuItem getItem();
	void setData(Integer key, String value, String price);


	
}
