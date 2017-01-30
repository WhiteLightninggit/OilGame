package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

public class FactoriesFactory {

	static int wagonPrice=5000;
	static int pumpPrice=6000;
	static int drillPrice=1250;
	private static int defaultFactoryPrice=4444;
	
	static byte maxWagonsOrderSize = 6;
	static byte maxPumpsOrderSize = 12;
	static byte maxDrillsOrderSize = 15;
	
	public static Factory getFactory(FactoryType type) {
		return createFactory(type);				
	}
	
	public static Factory getFactory(FactoryType type,String name) {
		Factory f = createFactory(type);
		f.name = name;
		return f;		
	}
	
	public static Factory getFactory(FactoryType type,String name, int availableItems) {
		Factory f = createFactory(type);
		f.name = name;
		f.availableItems = availableItems;
		return f;		
	}
	
	public static Factory getFactory(FactoryType type,String name, int availableItems, int factoryPrice) {
		Factory f = createFactory(type);
		f.name = name;
		f.availableItems = availableItems;
		f.setPrice(factoryPrice);
		return f;		
	}
		
	private static Factory createFactory(FactoryType type){
		
		Factory f = new Factory();
		switch (type) {
		case WAGONS:
			f.type = type;
			f.itemPrice = wagonPrice;
			f.maxOrderSize = maxWagonsOrderSize;
			f.setPrice(defaultFactoryPrice);
			break;
		case PUMP:
			f.type = type;
			f.itemPrice = pumpPrice;
			f.maxOrderSize = maxPumpsOrderSize;
			f.setPrice(defaultFactoryPrice);
			break;
		case DRILLS:
			f.type = type;
			f.itemPrice = drillPrice;
			f.maxOrderSize = maxDrillsOrderSize;
			f.setPrice(defaultFactoryPrice);
			break;

		default:
			System.out.println("No such type.");
			f=null;
			break;
		}
		return f;
	}
	
}
