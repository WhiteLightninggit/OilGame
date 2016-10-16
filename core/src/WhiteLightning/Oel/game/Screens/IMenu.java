package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Control.SFX;

public interface IMenu {

	public void setData();	
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
	
	
	
	
}
