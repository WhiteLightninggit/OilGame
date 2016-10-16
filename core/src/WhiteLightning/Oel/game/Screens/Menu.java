package WhiteLightning.Oel.game.Screens;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class Menu implements IMenu {

	//Remove ShapeRenderer later
	
	public HashMap<Integer, String> menuMap = new HashMap<>();
	public FontsPack fonts = new FontsPack();
    public int idx=0;
    boolean cyclic=true;
    public TexturesPack album = new TexturesPack();
	private int x = 500;
	private int width=200;
	private int y = 250; 
	private int menuOffsetY = 10; 
	private int menuItemHeight = 40; 
	
    private ShapeRenderer shapeRenderer;
    
	@Override
	public void setData() {
		 shapeRenderer = new ShapeRenderer();
				 
		menuMap.put(1, "Start Game");
		menuMap.put(2, "Options");
		menuMap.put(3, "Story");
		menuMap.put(4, "Credits");
		menuMap.put(5, "Exit");
	}
 
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawMenu(SpriteBatch batch) {		
		
		 if(false){		      
		       shapeRenderer.begin(ShapeType.Filled);
		       shapeRenderer.setColor(Color.RED);
		       shapeRenderer.rect(50, 50, 10, 10);
		       shapeRenderer.rect(100, 100, 10, 10);
		       shapeRenderer.rect(200, 200, 10, 10);
		       shapeRenderer.rect(300, 300, 10, 10);
		       shapeRenderer.rect(400, 400, 10, 10);		       
		       shapeRenderer.end();
		 }
		       
		batch.begin();
		
		batch.draw(album.pumpIco, x-50, y-30-(idx*menuItemHeight), 40, 40);
		batch.draw(album.pumpIco, x+180, y-30-(idx*menuItemHeight), 40, 40);		
		batch.draw(album.pumpIco, x, menuOffsetY+y-40-(idx*menuItemHeight), 170, 2);
		batch.draw(album.pumpIco, x, menuOffsetY+y-(idx*menuItemHeight), 170, 2);

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

		if(mx>x && mx<x+width && my >= (y-menuItemHeight*getMenuCount()) && my <= y  ){
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
