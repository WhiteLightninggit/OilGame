package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.OilField;
import WhiteLightning.Oel.game.Objects.Player;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

public class World {

	State s =State.getInstance();
	byte players;	
	public float oilPricesTrend[];	
	private final byte oilFieldsNr=12; 
	private final byte wagonFactoriesNr=4; 
	private final byte drillFactoriesNr=3; 
	private final byte pumpFactoriesNr=2; 
	
	public ArrayList<OilField> fieldsList;
	public ArrayList<Factory> wagonFactory;
	public ArrayList<Factory> drillFactory;
	public ArrayList<Factory> pumpsFactory;
	public HashMap<Integer, Player> playersMap;
	
	public World(Config c) {

		Random rand = new Random();
		players = s.players;

		oilPricesTrend = new float[c.gameLength];

		for (byte i = 0; i < c.gameLength; i++) {
			oilPricesTrend[i] = rand.nextFloat() * c.max_oil_price-c.min_oil_price + c.min_oil_price;
		}

		s.currentYear = c.starting_year;
		s.lastYear = c.starting_year + c.gameLength;
		
		createOilFields();
		createWagonFactories();
		createDrillFactories();
		createPumpFactories();	
		playersMap = new HashMap<>();

	}

	public boolean addPlayer(Config config, String playerName, Integer playerNr){
	
		
		if(s.players<=config.playersMax){
			Player p = new Player(config);
			p.name = playerName;
			this.playersMap.put(playerNr, p);
			System.out.println("player "+playerName+" "+playerNr+" was added.");
			return true;
		}
		return false;		
	}
	
	public Player getPlayer(Integer playerNr){
		return (Player) playersMap.get(playerNr);		
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
	
	float getCurrentOilPrice(){
		return oilPricesTrend[s.currentYear];
	}

}
