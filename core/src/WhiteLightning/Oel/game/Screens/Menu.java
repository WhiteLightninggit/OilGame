package WhiteLightning.Oel.game.Screens;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class Menu implements IMenu {
	
	public HashMap<Integer, String> menuMap = new HashMap<>();
	public FontsPack fonts = new FontsPack();
    public int idx=0;
    boolean cyclic=true;
    public TexturesPack album = new TexturesPack();
	
    public int x = 500;    
	public int y = 250; 
	public int menuCaptionWidth = 180;
	
	private int menuOffsetY = 10; 
	private int menuOffsetX = 10;	
	private int menuItemHeight = 40; 
	private int menuIconWidth=40;
	private int lineSize=2;
	    	
	@Override
	public void setData(Integer key, String value) {
       this.menuMap.put(key, value);	 		 
	}
 
	@Override
	public void display() {
	}

	@Override
	public void drawMenu(SpriteBatch batch) {		
 
		batch.begin();
		
		batch.draw(album.pumpIco, x-menuIconWidth-menuOffsetX, y-menuIconWidth+menuOffsetX-(idx*menuItemHeight), menuIconWidth, menuItemHeight);
		batch.draw(album.pumpIco, x+menuCaptionWidth, y-menuIconWidth+menuOffsetX-(idx*menuItemHeight), menuIconWidth, menuItemHeight);		
		batch.draw(album.pumpIco, x, menuOffsetY+y-menuItemHeight-(idx*menuItemHeight), menuCaptionWidth-menuOffsetX, lineSize);
		batch.draw(album.pumpIco, x, menuOffsetY+y-(idx*menuItemHeight), menuCaptionWidth-menuOffsetX, lineSize);

		AtomicInteger i = new AtomicInteger(0);
		menuMap.forEach((k, v) -> {
				fonts.cFont.draw(batch, k.toString() + " " + v.toString(), x, (y - menuItemHeight * i.get()));
				if (idx == i.get()) {
					fonts.cFontRed.draw(batch, k.toString() + " " + v.toString(), x, (y - menuItemHeight * i.get()));
				}
				i.getAndIncrement();			
			}
		);
	
		batch.end();
	}

	@Override
	public void setMenuCyclic() {
		cyclic=true;
	}
	
	@Override
	public void setMenuNotCyclic() {
		cyclic=false;
	}

	@Override
	public int getSelectedIdx() {
		return idx;
	}

	@Override
	public void selectNext() {
		if(idx<(menuMap.size()-1)){
			idx++;
		} else if(cyclic){
			idx=0;
		}
	}

	@Override
	public void selectPrevious() {
		if(idx>0){
			idx--;
		} else if (cyclic){
			idx = menuMap.size()-1;
		}
	}

	@Override
	public void processKeys(SFX sfx) {
		int mx = Gdx.input.getX();
		int my = Gdx.graphics.getHeight() - Gdx.input.getY();
	//	System.out.println("Mouse X: "+mx+" Y: "+my);

		if(mx>x && mx<x+menuCaptionWidth+menuOffsetX && my >= (y-menuItemHeight*getMenuCount()) && my <= y  ){
			int menuItemUnderCursor = (int)((menuOffsetY+y-my )/menuItemHeight);
			
			if(menuItemUnderCursor != idx && menuItemUnderCursor < this.getMenuCount()){
				idx=menuItemUnderCursor;
				sfx.PlaySound(Sounds.MenuChange);
			}
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.H)) {
			sfx.PlaySound(Sounds.MenuChange);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sfx.PlaySound(Sounds.MenuChange);
		    this.selectPrevious();
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sfx.PlaySound(Sounds.MenuChange);
			this.selectNext();
		}	
	}

	@Override
	public void setSFX(SFX sfx) {
	}

	@Override
	public int getMenuCount() {
		return this.menuMap.size();
	}
	
}
