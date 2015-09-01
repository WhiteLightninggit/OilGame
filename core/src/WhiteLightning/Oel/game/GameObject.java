package WhiteLightning.Oel.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class GameObject extends Rectangle { //todo: remove extends rectangle
	private Texture texture;	
	
	public GameObject(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}	
}
