package WhiteLightning.Oel.game;

import java.util.Random;

public class OilField {
	
	String names[] = {"Wild Hogs","Deep Dive","Drillodrom","Oily Sands", "Drill&Pump", 
			"Oiltarium","Shake It","Oily Ground","Shalow Holes","Black Creek","Desert Storm",
			"Morning Glory"};
	
	String name;
	int price;
	int oilLeft;
	int oilDeepth;
	int oilPumped;
	int wagons;
	int drills;
	boolean hasOwner = false;
	Player owner;
	
	
	final private int MINIMAL_OIL_QUANTITY= 64000; 
	final private int MAX_OIL_QUANTITY_RANDOM = 128000; 
	final private byte MAX_DEEPTH = 20;
	final private int drillLength = 500;
	final private int minFieldPrice = 48000;
	final private int fieldPriceModifier = 24000;
	
	
	public OilField(int idx){
		name = names[idx];
		Random r = new Random();		
		oilLeft = r.nextInt(MAX_OIL_QUANTITY_RANDOM)+MINIMAL_OIL_QUANTITY;
		oilDeepth = r.nextInt(MAX_DEEPTH)*drillLength+drillLength;
		price = minFieldPrice+r.nextInt(fieldPriceModifier)+(int)(oilLeft*r.nextFloat()/10);
		
	}
	
	
	public void setOwner(Player p){
		this.hasOwner = true;
		this.owner = p;
	}
	

}
