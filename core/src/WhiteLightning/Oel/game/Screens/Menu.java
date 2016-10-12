package WhiteLightning.Oel.game.Screens;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Sounds;
import WhiteLightning.Oel.game.Objects.FontsPack;

public class Menu extends Actor implements IMenu {

	public HashMap<Integer, String> menuMap = new HashMap<>();
	public FontsPack fonts = new FontsPack();
    public int idx=0;
    boolean cyclic=true;
	 
	@Override
	public void setData() {
		menuMap.put(0, "Start Game");
		menuMap.put(1, "Options");
		menuMap.put(2, "Story");
		menuMap.put(3, "Credits");
		menuMap.put(4, "Exit");
	}
 
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawMenu(SpriteBatch batch) {

		int x = 500;
		int y = 250;
		batch.begin();
		AtomicInteger i = new AtomicInteger(0);
		menuMap.forEach((k, v) -> {
			fonts.cFont.draw(batch, k.toString() + " " + v.toString(), x, (y - 40 * i.get()));

			if (idx == i.get()) {
				fonts.cFontRed.draw(batch, k.toString() + " " + v.toString(), x, (y - 40 * i.get()));
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
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sfx.PlaySound(Sounds.MenuChange);
		    this.selectPrevious();
		    System.out.println(idx);
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sfx.PlaySound(Sounds.MenuChange);
			this.selectNext();
			System.out.println(idx);
		}	
	}

	@Override
	public void setSFX(SFX sfx) {
	}



}
