package WhiteLightning.Oel.game.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TexturesPack {
	public Texture titleScreen;
	public Texture img;
	public Texture oilFieldImg;
	public Texture pumpImg;
	public Texture wagonImg;
	public Texture drillImg;
	public Texture trainImg;
	public Texture pumpsImg;
	public Texture drillsImg;
	public Texture pumpIco;
	public Texture skipImg;
	public Texture nextImg;
	public Texture sabotImg;
	public Texture line;
	public Sprite menuArrow;
	public Texture checked;
	public Texture unchecked;
	public ArrayList<Texture> nrImages;

	public TexturesPack() {
		this.pumpImg = new Texture("Images/pump.jpg");
		this.oilFieldImg = new Texture("Images/oilfield.jpg");
		this.wagonImg = new Texture("Images/wagon.jpg");
		this.drillImg = new Texture("Images/drill.jpg");
		this.trainImg = new Texture("Images/train.jpg");
		this.pumpsImg = new Texture("Images/oilPump.png");
		this.drillsImg = new Texture("Images/oilDrills.jpg");
		this.pumpIco = new Texture("Images/OilPumpIco.png");
		this.skipImg = new Texture("Images/skip.jpg");
		this.nextImg = new Texture("Images/next.png");
		this.sabotImg = new Texture("Images/sabotage.jpg");
		this.line = new Texture("Images/line.png");
		this.menuArrow = new Sprite(new Texture("Images/marker2.png"));
		this.checked = new Texture("Images/checked.png");
		this.unchecked = new Texture("Images/unchecked.png");
		this.titleScreen = new Texture("Images/chart6.png");
		this.menuArrow = new Sprite(new Texture("Images/marker2.png"));	
		
		this.nrImages = new ArrayList<>();
		this.nrImages.add(new Texture("Images/1.png"));
		this.nrImages.add(new Texture("Images/2.png"));
		this.nrImages.add(new Texture("Images/3.png"));
		this.nrImages.add(new Texture("Images/4.png"));
		this.nrImages.add(new Texture("Images/selectedNr.png"));
	}
}