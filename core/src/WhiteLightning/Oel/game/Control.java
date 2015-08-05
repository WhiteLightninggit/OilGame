package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Game.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Control {

	private Game g;
	
	public Control(Game g){
		this.g = g;
	}
	
	
	public void processKeys(gameStates gs){
		
		processKeysDefault();
		
		switch (gs) {
		case Title:
			processKeysTitle();
			break;
		case Setup:
			processKeysSetup();
			break;

		default:
			break;
		}
	}
	
	public void processKeysTitle() {
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			g.gameState = gameStates.Setup;			
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			g.menuChangeSound.play();
			if(g.selectedMenuItem > 0)
				g.selectedMenuItem--;	
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
		g.menuChangeSound.play();
			if(g.selectedMenuItem < 4)
			g.selectedMenuItem++;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			if(g.selectedMenuItem == 4)
				Gdx.app.exit();
			if(g.selectedMenuItem == 0)
				g.gameState = gameStates.Setup;
			
		}
		
		
	}
	
	public void processKeysSetup() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			g.nextInternalState();
		}
		
	}
	
	public void processKeysGame() {
		if(Gdx.input.isKeyPressed(Keys.W)){
//			gameObject.y +=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
//			gameObject.y -=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
//			gameObject.x -=speed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
//			gameObject.x +=speed * Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyPressed(Keys.M)){
//				music.play();
		}
		
		if(Gdx.input.isKeyPressed(Keys.N)){
//			scream.play(0.2f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.V)){
			
		}
		
		
	}
	
	
	public void processKeysDefault(){
		if(Gdx.input.isKeyPressed(Keys.X)){
			g.camera.rotate(0.8f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){			
			g.camera.zoom += 0.02;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)){
			g.camera.zoom -= 0.02;
		}
	}
	
	
	
	
	public void processKeys() {

		if(g.gameState == gameStates.Title){
			processKeysTitle();
		} else if (g.gameState == gameStates.Setup){
			processKeysSetup();
		} else if (g.gameState == gameStates.Game){
			processKeysGame();
		} 
		
		//Escape from app, camera etc...
		processKeysDefault();
	}
	
}
