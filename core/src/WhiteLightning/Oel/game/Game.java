package WhiteLightning.Oel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Game {

	public boolean textEnterFlag = false;	
	public State s = State.getInstance();
	public SpriteBatch batch;
	public World world;
	public Config c;
	public Logic l;

	public OrthographicCamera camera;

	public Game() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.setToOrtho(true);

		batch = new SpriteBatch();	
		c = new Config();
		this.world = new World(c);
		l = new Logic(world);
	}

	public void update() {}

	public void draw(oldMenu menu, gameStates gs) {}	


}
