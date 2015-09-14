package WhiteLightning.Oel.game;

public class Factory implements RealEstateI{

	public enum FactoryType {
		WAGONS, PUMP, DRILLS
	}

	FactoryType type;
	private int price;
	int itemPrice;
	int availableItems;
	String name;
	byte maxOrderSize;
	private boolean hasOwner=false;
	Player owner;
	
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
