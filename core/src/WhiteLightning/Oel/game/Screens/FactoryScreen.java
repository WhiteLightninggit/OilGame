package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.gameStates;

public class FactoryScreen implements IGameScreen{

	@Override
	public void Load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(SpriteBatch spriteBatch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public gameStates Update(long gameTime) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	private void drawFactoryHUD(SpriteBatch batch) {
		int x = 20;
		int y = 500;
		batch.begin();
		font.setScale(.9f, .9f);
		font.draw(batch, "Factory: " + logic.getCurrentFactory().name, x, y);
		if (logic.getCurrentFactory().hasOwner()) {
			font.draw(batch, "Owner: " + logic.getCurrentFactory().owner.name, x, y - 30);
		} else {
			font.draw(batch, "Owner: " + "FREE", x, y - 30);
		}
		font.draw(batch, "ItemPrice: " + logic.getCurrentFactory().itemPrice, x, y - 60);
		batch.end();
	}
	*/
}
