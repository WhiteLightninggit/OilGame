package WhiteLightning.Oel.game.Objects;

import WhiteLightning.Oel.game.Config;

public class Player {
	public int cash;
	public String name;
	
	public Player(Config c) {
		cash = c.starting_cash;
	}

	public int pay(int amount){		
		cash=cash-amount;
		return cash;
	}

}
