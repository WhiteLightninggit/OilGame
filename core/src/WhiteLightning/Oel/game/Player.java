package WhiteLightning.Oel.game;

public class Player {
	int cash;
	String name;
	
	public Player(Config c) {
		cash = c.starting_cash;
	}

	int pay(int amount){		
		cash=cash-amount;
		return cash;
	}

}
