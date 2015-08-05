package WhiteLightning.Oel.game;

public class Factory {

	public enum FactoryType {
		WAGONS, PUMP, DRILLS
	}

	FactoryType type;
	int itemPrice;
	int availableItems;
	String name;
	byte maxOrderSize;
	boolean hasOwner=false;
	Player owner;
	

	


	

}
