package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Game.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Control {

	private Game g;
	boolean flag1=false;
	
	
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
			processKeysOilfields();
			break;
		case Game:
			processKeysGame();
			break;

		default:
			break;
		}
	}
	
	public void processKeysTitle() {
		
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			g.gameState = gameStates.Game;			
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
			if(g.selectedMenuItem == 1){
				g.flag1 = true;
			}
			if(g.selectedMenuItem == 2){
				g.flag1 = true;				
			}
			if(g.selectedMenuItem == 3){
				g.flag1 = true;
				flag1=!flag1;
				System.out.println(flag1);
				Gdx.graphics.setDisplayMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), flag1);
			}
			
			if(g.selectedMenuItem == 0)
				g.gameState = gameStates.Game;
			
			
			
			
		}
		
		
	}
	
	public void processKeysOilfields() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
		//	g.nextInternalState();
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			g.menuChangeSound.play();
			
			if(g.selectedField > 0)
				g.selectedField--;	
			g.moveUp = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
		g.menuChangeSound.play();
			if(g.selectedField < 11)
			g.selectedField++;
			g.moveUp = false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
		g.gameState = gameStates.Game;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			g.menuChangeSound.play();
			
			System.out.println(g.world.fieldsList.get(g.selectedField).name);
			g.world.fieldsList.get(g.selectedField).setOwner(g.world.playersList.get(0));
		}
		
	}
	
	public void processKeysGame() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			//	g.nextInternalState();
			}
			if(Gdx.input.isKeyJustPressed(Keys.UP)){
				g.menuChangeSound.play();				
				if(g.selectedAction > 0)
					g.selectedAction--;	
				g.moveUp = true;
			}
			if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			g.menuChangeSound.play();
				if(g.selectedAction < g.actionsList.size())
				g.selectedAction++;
				g.moveUp = false;
			}
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				g.menuChangeSound.play();				
				System.out.println(g.actionsList.get(g.selectedAction));	
				
				if(g.selectedAction == 3) g.gameState = gameStates.Setup;				
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
			processKeysOilfields();
		} else if (g.gameState == gameStates.Game){
			processKeysGame();
		} 
		
		//Escape from app, camera etc...
		processKeysDefault();
	}
	
}
