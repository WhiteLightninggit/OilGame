package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.OilField;
import WhiteLightning.Oel.game.Objects.Player;
import WhiteLightning.Oel.game.Objects.RealEstateI;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

public class Logic {

	World w;
	State s = State.getInstance();
	byte players;	
	
	public Logic(Game g){
		this.w = g.world;
		this.players = w.players;
	}
	
	public boolean buyField(){
		System.out.println("Current player: "+s.currentPlayerIdx);
		return buyField(getCurrentPlayer(), w.fieldsList.get(s.selectedField));
	}
	
	private boolean buyField(Player p, OilField field ){
		if(p.cash > field.getPrice() && !field.hasOwner){
			field.setOwner(p);
			p.pay(field.getPrice());
			return true;
		} 		
		return false;
	}
	
	public boolean buyFactory(FactoryType type){
		System.out.println("Current player: "+s.currentPlayerIdx);
		
		switch (type) {
		case DRILLS:
			return buyRealEstate(getCurrentPlayer(), w.drillFactory.get(s.selectedField));
		case PUMP:
			return buyRealEstate(getCurrentPlayer(), w.pumpsFactory.get(s.selectedField));
		case WAGONS:
			return buyRealEstate(getCurrentPlayer(), w.wagonFactory.get(s.selectedField));
		default:
			return false;			
		}		
	}
	
	private boolean buyRealEstate(Player p, RealEstateI estate ){
		if(p.cash > estate.getPrice() && !estate.hasOwner()){
			estate.setOwner(p);
			p.pay(estate.getPrice());
			return true;
		} 		
		return false;
	}
		
	
	byte getCurrentPlayerNr() {
		return s.currentPlayerIdx;
	}
	
	Player getCurrentPlayer(){		
		return w.playersList.get(s.currentPlayerIdx);
	}
	
	public Factory getCurrentFactory(){		
		switch (s.currFactoryType) {
		case PUMP:
			return w.pumpsFactory.get(s.selectedField);
		case WAGONS:
			return w.wagonFactory.get(s.selectedField);
		case DRILLS:
			return w.drillFactory.get(s.selectedField);
			
		default:
			return null;
			
		}
	}

	Player setAndGetNextPlayer(){
		setNextPlayer();
		return getCurrentPlayer();
	}
	
	public byte setNextPlayer() {
		
		System.out.println("Set next player: "+s.currentPlayerIdx);
		
		if (++s.currentPlayerIdx < players) {			
			return s.currentPlayerIdx;
		} else {
			s.currentYear++;
			if(s.currentYear == s.lastYear){
				s.endOfGame = 1;
				System.out.println("End of game");
			}
			s.currentPlayerIdx=0;
			return 0;
		}
	}
	
	int getCurrentYear(){
		return s.currentYear;
	}
	
	int getNextYear(){
		if(++s.currentYear<s.lastYear){
			return s.currentYear++;
		} else return s.endOfGame;
	}
	
	public boolean isGameEnd(){
		if (s.endOfGame > 0){
			return true;
		}		
		return false;
	}	
	
	
}
