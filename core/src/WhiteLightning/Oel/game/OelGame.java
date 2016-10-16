package WhiteLightning.Oel.game;


import java.util.ArrayList;
import java.util.HashMap;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Control;
import WhiteLightning.Oel.game.Screens.BuyDrillsScreen;
import WhiteLightning.Oel.game.Screens.FactoryScreen;
import WhiteLightning.Oel.game.Screens.GameScreen;
import WhiteLightning.Oel.game.Screens.IGameScreen;
import WhiteLightning.Oel.game.Screens.OilFieldsScreen;
import WhiteLightning.Oel.game.Screens.OptionsScreen;
import WhiteLightning.Oel.game.Screens.SetupScreen;
import WhiteLightning.Oel.game.Screens.TitleScreen;
import WhiteLightning.Oel.game.Screens.TrendScreen;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OelGame extends ApplicationAdapter {
	
	final Logger log = LogManager.getLogger(ApplicationAdapter.class);
	private gameStates lastState=gameStates.Game;
	private HashMap<gameStates, IGameScreen> gameScreens;
	private long elapsedTime = TimeUtils.nanoTime();
	
	public World world;
	public Config config;
	public Logic logic;
	public SFX sfx;	
	
	public SpriteBatch spriteBatch;
	
	public OrthographicCamera camera;
	
	TitleScreen ts;
	
	Game g;
	Control control;
	public oldMenu mainMenu;
	public oldMenu actionMenu;
	ArrayList<String> menuList = new ArrayList<>();
	ArrayList<String> actionsList = new ArrayList<>();
	HashMap<Integer, String> menuMap = new HashMap<>();	
	HashMap<Integer, String> actionsMap = new HashMap<>();
	public SoundPack sounds = new SoundPack();
	
	@Override
	public void create() {	
		
		log.info("Create Oel Game");
		g = new Game();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		control = new Control(g,camera);
	
		sounds.menuSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/menu.mp3"));
		sounds.menuChangeSound = Gdx.audio.newSound(Gdx.files.internal("Sound/select.wav"));	
		sounds.denySound = Gdx.audio.newSound(Gdx.files.internal("Sound/deny.mp3"));
		sounds.denySound.setVolume(0, 0.3f);
		
		sfx = new SFX();
		
		config = new Config();
		this.world = new World(config);
		logic = new Logic(this.world);
		
		gameScreens = new HashMap<>();
		gameScreens.put(gameStates.Title,new TitleScreen(logic,sfx) );
		gameScreens.put(gameStates.Setup,new SetupScreen(sfx, config, world) );
		gameScreens.put(gameStates.Options,new OptionsScreen(sfx, config, world) );
		gameScreens.put(gameStates.Game,new GameScreen(world,logic,sfx, config) );
		gameScreens.put(gameStates.Trend,new TrendScreen(sfx, config, world) );
		gameScreens.put(gameStates.OilFields,new OilFieldsScreen(sfx, world, logic) );
		gameScreens.put(gameStates.Factory,new FactoryScreen(sfx, world, logic) );
		gameScreens.put(gameStates.BuyDrills,new BuyDrillsScreen(sfx, world, logic) );
		spriteBatch = new SpriteBatch();
		log.info("Initialised");
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		if(lastState != gameStates.End){
			gameScreens.get(lastState).Draw(spriteBatch);
		}
	}

	public void update() {
		updateCamera();
		lastState=gameScreens.get(lastState).Update(elapsedTime);		 
		// System.out.println("state: "+lastState);
		
		if (lastState == gameStates.End){
			Gdx.app.exit();
	    }
		processKeysDefault();
	}

	private void updateCamera() {
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);		
	}

	
	private void processKeysDefault() {
		
		if (!g.textEnterFlag) {
			if (Gdx.input.isKeyPressed(Keys.X)) {
				camera.rotate(0.8f);
			}
			
			if (Gdx.input.isKeyPressed(Keys.F)) {
				Gdx.graphics.setDisplayMode(800, 600, !Gdx.graphics.isFullscreen());
			}

			if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				Gdx.app.exit();
			}

			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				camera.zoom += 0.02;
			}

			if (Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				camera.zoom -= 0.02;
			}
		}
	}
	
}
