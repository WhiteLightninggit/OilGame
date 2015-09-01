package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.Random;

import WhiteLightning.Oel.game.Factory.FactoryType;

public class World {

	byte players;
	private byte currentPlayer;
	float oilPricesTrend[];
	private int currentYear;
	int lastYear;
	private final int endOfGame=-1; 
	
	private final byte oilFieldsNr=12; 
	private final byte wagonFactoriesNr=4; 
	private final byte drillFactoriesNr=3; 
	private final byte pumpFactoriesNr=2; 
	
	ArrayList<OilField> fieldsList;
	ArrayList<Factory> wagonFactory;
	ArrayList<Factory> drillFactory;
	ArrayList<Factory> pumpsFactory;
	ArrayList<Player> playersList = new ArrayList<Player>();
	
	public World(Config c) {

		Random rand = new Random();
		players = c.players;

		oilPricesTrend = new float[c.gameLength];

		for (byte i = 0; i < c.gameLength; i++) {
			oilPricesTrend[i] = rand.nextFloat() * c.max_oil_price-c.min_oil_price + c.min_oil_price;
		}

		this.currentYear = c.starting_year;
		this.lastYear = c.starting_year + c.gameLength;
		
		createOilFields();
		createWagonFactories();
		createDrillFactories();
		createPumpFactories();	
		
		Player p = new Player();
		p.name = "James";
		
		Player p2 = new Player();
		p2.name = "Bond";
		
		playersList.add(p);
		playersList.add(p2);
		
		fieldsList.get(5).setOwner(p);
		fieldsList.get(3).setOwner(p2);
		fieldsList.get(0).setOwner(p2);
		fieldsList.get(11).setOwner(p);
	}

	private void createOilFields(){
		fieldsList = new ArrayList<OilField>(oilFieldsNr);
		for(byte i=0;i<oilFieldsNr;i++){			
			fieldsList.add(new OilField(i));
		}		
	}
	
	private void createWagonFactories(){
		wagonFactory = new ArrayList<Factory>(wagonFactoriesNr);		
		wagonFactory.add(FactoriesFactory.getFactory(FactoryType.WAGONS, "Pesa", 30));
		wagonFactory.add(FactoriesFactory.getFactory(FactoryType.WAGONS, "Newag", 42));
		wagonFactory.add(FactoriesFactory.getFactory(FactoryType.WAGONS, "Bombardier", 33));
		wagonFactory.add(FactoriesFactory.getFactory(FactoryType.WAGONS, "Konstal", 39));		
	}

	private void createDrillFactories(){
		drillFactory = new ArrayList<Factory>(drillFactoriesNr);		
		drillFactory.add(FactoriesFactory.getFactory(FactoryType.DRILLS, "TurboDrill", 30));
		drillFactory.add(FactoriesFactory.getFactory(FactoryType.DRILLS, "ChineseDrills", 42));
		drillFactory.add(FactoriesFactory.getFactory(FactoryType.DRILLS, "Piepeline", 33));
	}
	
	private void createPumpFactories(){
		pumpsFactory = new ArrayList<Factory>(pumpFactoriesNr);		
		pumpsFactory.add(FactoriesFactory.getFactory(FactoryType.PUMP, "PumperNickel", 30));
		pumpsFactory.add(FactoriesFactory.getFactory(FactoryType.PUMP, "Pump&Load", 42));
	}	
	
	byte getCurrentPlayer() {
		return currentPlayer;
	}

	byte getNextPlayer() {
		if (++currentPlayer < players) {
			return currentPlayer;
		} else {
			currentYear++;
			return 0;
		}
	}
	
	float getCurrentOilPrice(){
		return oilPricesTrend[currentYear];
	}
	
	int getCurrentYear(){
		return currentYear;
	}
	
	int getNextYear(){
		if(++currentYear<lastYear){
			return currentYear++;
		} else return endOfGame;
	}

}
