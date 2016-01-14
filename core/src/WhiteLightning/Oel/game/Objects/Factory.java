package WhiteLightning.Oel.game.Objects;

public class Factory implements RealEstateI{

	public enum FactoryType {
		WAGONS, PUMP, DRILLS
	}

	public FactoryType type;
	private int price;
	public int itemPrice;
	public int availableItems;
	public String name;
	public byte maxOrderSize;
	private boolean hasOwner=false;
	public Player owner;
	
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
