package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class OelGame extends ApplicationAdapter {

	Game g;
	Control c;
	public Menu mainMenu;
	ArrayList<String> menuList = new ArrayList<>();
	HashMap<Integer, String> menuMap = new HashMap<>();
	
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
		
		
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		g.draw(mainMenu, g.s.gameState);
	}

	public void update() {
		updateCamera();
		c.processKeys(g.s.gameState, mainMenu);
		g.update();
	}

	private void updateCamera() {
		g.camera.update();
		g.batch.setProjectionMatrix(g.camera.combined);
		g.camera.position.set(Gdx.graphics.getWidth() / 2,
		Gdx.graphics.getHeight() / 2, 0);
	}

}
