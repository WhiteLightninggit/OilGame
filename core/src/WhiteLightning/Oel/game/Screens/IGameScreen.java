package WhiteLightning.Oel.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import WhiteLightning.Oel.game.gameStates;


public interface IGameScreen {
	  void Load();

      void Draw(SpriteBatch spriteBatch);

      gameStates Update(long gameTime);
}
