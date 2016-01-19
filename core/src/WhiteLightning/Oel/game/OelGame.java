package WhiteLightning.Oel.game;


import java.util.ArrayList;
import java.util.HashMap;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import WhiteLightning.Oel.game.Control.SFX;
import WhiteLightning.Oel.game.Control.Control;
import WhiteLightning.Oel.game.Screens.GameScreen;
import WhiteLightning.Oel.game.Screens.IGameScreen;
import WhiteLightning.Oel.game.Screens.SetupScreen;
import WhiteLightning.Oel.game.Screens.TitleScreen;
import WhiteLightning.Oel.game.Screens.TrendScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OelGame extends ApplicationAdapter {
	
	final Logger log = LogManager.getLogger(ApplicationAdapter.class);
	private gameStates lastState=gameStates.Title;
	private HashMap<gameStates, IGameScreen> gameScreens;
	private long elapsedTime = TimeUtils.nanoTime();
	
	public World world;
	public Config config;
	public Logic l;
	public SFX sfx;
	
	public SpriteBatch spriteBatch;
	
	public OrthographicCamera camera;
	
	TitleScreen ts;
	
	Game g;
	Control control;
	public Menu mainMenu;
	public Menu actionMenu;
	ArrayList<String> menuList = new ArrayList<>();
	ArrayList<String> actionsList = new ArrayList<>();
	HashMap<Integer, String> menuMap = new HashMap<>();	
	HashMap<Integer, String> actionsMap = new HashMap<>();	
	
	@Override
	public void create() {
		log.info("Create Oel Game");
		g = new Game();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		//camera.setToOrtho(true);		
		control = new Control(g,camera);
		
		g.img = new Texture("Images/badlogic.jpg");
		g.line = new Texture("Images/lineBlue.png");
		g.titleScreen = new Texture("Images/chart6.png");
		g.menuArrow = new Sprite(new Texture("Images/marker2.png"));		
		g.menuArrow.setFlip(true, false);		
		g.menuSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/menu.mp3"));
		g.menuChangeSound = Gdx.audio.newSound(Gdx.files.internal("Sound/select.wav"));	
		g.denySound = Gdx.audio.newSound(Gdx.files.internal("Sound/deny.mp3"));
		g.denySound.setVolume(0, 0.3f);
		g.pumpImg = new Texture("Images/pump.jpg");
		g.oilFieldImg = new Texture("Images/oilfield.jpg");
		g.wagonImg = new Texture("Images/wagon.jpg");
		g.drillImg = new Texture("Images/drill.jpg");
		g.trainImg = new Texture("Images/train.jpg");
		g.pumpsImg = new Texture("Images/oilPump.png");
		g.drillsImg = new Texture("Images/oilDrills.jpg");
		g.skipImg = new Texture("Images/skip.jpg");
		g.nextImg = new Texture("Images/next.png");
		g.sabotImg = new Texture("Images/sabotage.jpg");
		
		g.nrImages = new ArrayList<>();
		g.nrImages.add(new Texture("Images/1.png"));
		g.nrImages.add(new Texture("Images/2.png"));
		g.nrImages.add(new Texture("Images/3.png"));
		g.nrImages.add(new Texture("Images/4.png"));
		g.nrImages.add(new Texture("Images/selectedNr.png"));
		
		menuList.add("Start Game");
		menuList.add("Options");
		menuList.add("Story");
		menuList.add("Credits");
		menuList.add("Exit");
		
		menuMap.put(0,"Start Game");
		menuMap.put(1,"Options");
		menuMap.put(2,"Story");
		menuMap.put(3,"Credits");
		menuMap.put(4,"Exit");
		
		mainMenu = new Menu(menuMap);
		
		actionsMap.put(0,"Drill Factories");
		actionsMap.put(1,"Pumps Factories");
		actionsMap.put(2,"Wagons factories");
		actionsMap.put(3,"Oil Fields");
		actionsMap.put(4,"Buy Drills");
		actionsMap.put(5,"Buy Pumps");
		actionsMap.put(6,"Buy Wagons");
		actionsMap.put(7,"=OTHER POSSIBILITIES");
		actionsMap.put(8,"Next player");
		actionsMap.put(9,"Sabotage");
		actionsMap.put(10,"Skip turn");
		
		actionMenu = new Menu(actionsMap);
		
		sfx = new SFX();
		
		config = new Config();
		this.world = new World(config);
		l = new Logic(this.world);
		
		gameScreens = new HashMap<>();
		gameScreens.put(gameStates.Title,new TitleScreen(l,sfx, mainMenu) );
		gameScreens.put(gameStates.Setup,new SetupScreen(sfx, config, world) );
		gameScreens.put(gameStates.Game,new GameScreen(l,sfx, config, actionMenu) );
		gameScreens.put(gameStates.Trend,new TrendScreen(sfx, config, world) );
		spriteBatch = new SpriteBatch();
		log.info("Initialised");
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				
		 gameScreens.get(lastState).Draw(spriteBatch);
	}

	public void update() {
		updateCamera();
		 lastState=gameScreens.get(lastState).Update(elapsedTime);
		 
		// System.out.println("state: "+lastState);
		 
			if (lastState == gameStates.End)
	        {
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
