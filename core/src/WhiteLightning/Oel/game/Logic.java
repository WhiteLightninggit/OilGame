package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.OilField;
import WhiteLightning.Oel.game.Objects.Player;
import WhiteLightning.Oel.game.Objects.RealEstateI;
import WhiteLightning.Oel.game.Screens.IGameScreen;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

public class Logic {

	private boolean actionPerformed = false;
	public World w;
	State s = State.getInstance();
//	byte players;	
	
	
	public Logic(World w){
		this.w = w;
	//this.players = w.players;
	}
	
	public boolean actionPerformed(){
		return actionPerformed;
	}
	
	public boolean buyField(){
		return buyField(getCurrentPlayer(), w.fieldsList.get(s.selectedField));
	}
	
	private boolean buyField(Player p, OilField field ){
		if(p.cash > field.getPrice() && !field.hasOwner && !actionPerformed){
			field.setOwner(p);
			p.pay(field.getPrice());
			actionPerformed = true;
			return true;
		} 		
		return false;
	}
	
	public boolean buyFactory(FactoryType type){
		
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
	
	public boolean buyFactory(FactoryType type, int idx){
		
		switch (type) {
		case DRILLS:
			return buyRealEstate(getCurrentPlayer(), w.drillFactory.get(idx));
		case PUMP:
			return buyRealEstate(getCurrentPlayer(), w.pumpsFactory.get(idx));
		case WAGONS:
			return buyRealEstate(getCurrentPlayer(), w.wagonFactory.get(idx));
		default:
			return false;			
		}		
	}
	
	private boolean buyRealEstate(Player p, RealEstateI estate ){
		if(p.cash > estate.getPrice() && !estate.hasOwner() && !actionPerformed){
			estate.setOwner(p);
			p.pay(estate.getPrice());
			actionPerformed = true;
			return true;
		} 		
		return false;
	}
		
	
	byte getCurrentPlayerNr() {
		return s.currentPlayerIdx;
	}
	
	public Player getCurrentPlayer(){		
		return (Player) w.getPlayer((int) s.currentPlayerIdx);
	}
	
	public Factory getCurrentFactory(int idx){		
		switch (s.currFactoryType) {
		case PUMP:
			return w.pumpsFactory.get(idx);
		case WAGONS:
			return w.wagonFactory.get(idx);
		case DRILLS:
			return w.drillFactory.get(idx);
			
		default:
			return null;
			
		}
	}

	Player setAndGetNextPlayer(){
		setNextPlayer();
		return getCurrentPlayer();
	}
	
	public byte setNextPlayer() {
		
		actionPerformed = false;
		if (++s.currentPlayerIdx < s.players) {				
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
	
	public int getCurrentYear(){
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
