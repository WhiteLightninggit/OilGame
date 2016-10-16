package WhiteLightning.Oel.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import WhiteLightning.Oel.game.OelGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesktopLauncher {
	public static void main (String[] arg) {		
		final Logger log = LogManager.getLogger(DesktopLauncher.class);
		
		log.info("Program started. Java: "+System.getProperty("java.version"));				
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;		
		config.height = 600;
		config.resizable = true;	
		config.fullscreen = false;
		log.trace("Creating new app");
		
		new LwjglApplication(new OelGame(), config);
		log.info("Bye bye...");
	}
}
