package WhiteLightning.Oel.game.Objects;

import java.util.Random;

public class OilField implements RealEstateI{
	
	String names[] = {"Wild Hogs","Deep Dive","Drillodrom","Oily Sands", "Drill&Pump", 
			"Oiltarium","Shake It","Oily Ground","Shalow Holes","Black Creek","Desert Storm",
			"Morning Glory"};
	
	public String name;
	private int price;
	int oilLeft;
	int oilDeepth;
	int oilPumped;
	int wagons;
	int drills;
	public boolean hasOwner = false;
	public Player owner;	
	
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
	
	@Override
	public boolean setOwner(Player p){
		if(this.hasOwner){
			return false;
		}
		this.hasOwner = true;
		this.owner = p;
		return true;
	}

	@Override
	public int getPrice() {		
		return this.price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;		
	}
	
	@Override
	public boolean hasOwner() {
		return this.hasOwner;
	}

}
