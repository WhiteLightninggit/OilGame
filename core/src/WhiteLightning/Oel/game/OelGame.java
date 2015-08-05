package WhiteLightning.Oel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class OelGame extends ApplicationAdapter {

	Game g;
	Control c;

	@Override
	public void create() {
		g = new Game();
		c = new Control(g);
		// g.setBatch(new SpriteBatch());
		// g.batch = new SpriteBatch();
		g.camera = new OrthographicCamera(800, 600);
		g.img = new Texture("badlogic.jpg");
		g.titleScreen = new Texture("Title.png");
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g.draw();

	}

	public void update() {

		updateCamera();
		c.processKeys(g.gameState);
		g.update();

	}

	private void updateCamera() {
		g.camera.update();
		g.batch.setProjectionMatrix(g.camera.combined);
		g.camera.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);
	}

}
