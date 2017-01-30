package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuItem {

	public String text;
	public BitmapFont font;
	public BitmapFont selectionFont;
	public String name;
	public String price;
	
	public MenuItem(String text, BitmapFont font, BitmapFont selectionFont) {
		this.text = text;
		this.font = font;
		this.selectionFont = selectionFont;
	}
	
	public MenuItem(String name, String price, BitmapFont font, BitmapFont selectionFont) {
		this.name = name;
		this.price = price;
		this.text = name+ "  "+price;
		this.font = font;
		this.selectionFont = selectionFont;
	}
	

	
}
