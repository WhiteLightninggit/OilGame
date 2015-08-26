package WhiteLightning.Oel.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import WhiteLightning.Oel.game.OelGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 800;
		
		config.height = 600;
		config.resizable = true;
		
		config.fullscreen = false;
		
		new LwjglApplication(new OelGame(), config);
	}
}
