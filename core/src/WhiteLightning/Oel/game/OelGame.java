package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import WhiteLightning.Oel.game.Control.Control;
import WhiteLightning.Oel.game.Game.gameStates;

public class OelGame extends ApplicationAdapter {

	Game g;
	Control c;
	public Menu mainMenu;
	public Menu actionMenu;
	ArrayList<String> menuList = new ArrayList<>();
	ArrayList<String> actionsList = new ArrayList<>();
	HashMap<Integer, String> menuMap = new HashMap<>();	
	HashMap<Integer, String> actionsMap = new HashMap<>();	
	
	@Override
	public void create() {
		g = new Game();
		c = new Control(g);
		g.camera = new OrthographicCamera(800, 600);
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
		
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(g.s.gameState == gameStates.Game){
			g.draw(actionMenu, g.s.gameState);
		} else 
			g.draw(mainMenu, g.s.gameState);
	}

	public void update() {
		updateCamera();
		c.processKeys(g.s.gameState, mainMenu, actionMenu);
		//g.update();
	}

	private void updateCamera() {
		g.camera.update();
		g.batch.setProjectionMatrix(g.camera.combined);
		g.camera.position.set(Gdx.graphics.getWidth() / 2,
		Gdx.graphics.getHeight() / 2, 0);
	}

}
