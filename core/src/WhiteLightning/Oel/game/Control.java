package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Factory.FactoryType;
import WhiteLightning.Oel.game.Game.gameStates;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Control {

	private Game g;
	private State s = State.getInstance();
	private Logic l;
	boolean flag1=false;	
	
	public Control(Game g){
		this.g = g;	
		l = g.l;
		
	}	
	
	public void processKeys(gameStates gs){
		processKeysDefault();
		
		switch (gs) {
		case Title:
			processKeysTitle();
			break;
		case OilFields:
			processKeysOilfields();
			break;
		case Factory:			
			switch (s.internalState) {
			case 0:
				processKeysFactory(g.world.drillFactory, FactoryType.DRILLS);
				break;
			case 1:
				processKeysFactory(g.world.pumpsFactory, FactoryType.PUMP);
				break;
			case 2:
				processKeysFactory(g.world.wagonFactory, FactoryType.WAGONS);
				break;
			default:
				break;
			}
			
			break;
		case Options:
			processKeysOption();
			break;
		case Story:
			processKeysStory();
			break;
		case Credits:
			processKeysStory();
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
			
			if(s.selectedMenuItem > 0)
				s.selectedMenuItem--;	
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
		g.menuChangeSound.play();
			if(s.selectedMenuItem < 4)
			s.selectedMenuItem++;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			if(s.selectedMenuItem == 4)
				Gdx.app.exit();
			if(s.selectedMenuItem == 1){
				s.flag1 = true;
				g.gameState=gameStates.Options;
			}
			if(s.selectedMenuItem == 2){
				s.flag1 = true;		
				g.gameState=gameStates.Story;
			}
			if(s.selectedMenuItem == 3){
				s.flag1 = true;
				flag1=!flag1;
				System.out.println(flag1);
				g.gameState=gameStates.Credits;
			//	Gdx.graphics.setDisplayMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), flag1);
			}
			
			if(s.selectedMenuItem == 0)
				g.gameState = gameStates.Game;		
		}		
	}
	

	public void processKeysOilfields() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
		//	g.nextInternalState();
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			g.menuChangeSound.play();
			
			if(s.selectedField > 0)
				s.selectedField--;	
			s.moveUp = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
		g.menuChangeSound.play();
			if(s.selectedField < g.world.fieldsList.size()-1)
			s.selectedField++;
			s.moveUp = false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
		g.gameState = gameStates.Game;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			System.out.println(g.world.fieldsList.get(s.selectedField).name);			
			if(!l.buyField()){
				g.denySound.play();
			}
		}		
	}
	
	
	public void processKeysFactory(ArrayList<Factory> factories, FactoryType type) {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
		//	g.nextInternalState();
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			g.menuChangeSound.play();
			
			if(s.selectedField > 0)
				s.selectedField--;	
			s.moveUp = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
		g.menuChangeSound.play();
			if(s.selectedField < factories.size() -1)
			s.selectedField++;
			s.moveUp = false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
		g.gameState = gameStates.Game;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
		
			if(!l.buyFactory(type)){			
				g.menuChangeSound.play();
			}
			System.out.println(factories.get(s.selectedField).name);
		}		
	}	
	
	public void processKeysGame() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			//	g.nextInternalState();
			}
			if(Gdx.input.isKeyJustPressed(Keys.UP)){
				g.menuChangeSound.play();				
				if(s.selectedAction > 0)
					s.selectedAction--;	
				s.moveUp = true;
			}
			if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			g.menuChangeSound.play();
				if(s.selectedAction < g.actionsList.size()-1)
				s.selectedAction++;
				s.moveUp = false;
			}
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				g.menuChangeSound.play();				
				System.out.println(g.actionsList.get(s.selectedAction)+" "+s.selectedAction);	
				
				if(s.selectedAction == 0) g.gameState = gameStates.Factory;
				if(s.selectedAction == 1) g.gameState = gameStates.Factory;	
				if(s.selectedAction == 2) g.gameState = gameStates.Factory;	
				if(s.selectedAction == 3) g.gameState = gameStates.OilFields;	
				if(s.selectedAction == 8) {
					l.setNextPlayer();
					System.out.println("Game state: "+g.gameState+" "+s.lastYear);
					if(l.isGameEnd()) {
						System.out.println("Game state: "+g.gameState);
						g.gameState = gameStates.End;			
					}
				}	
				s.internalState = s.selectedAction;
				s.selectedField=0;
			}			
		
	}
	
	public void processKeysOption() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			g.gameState = gameStates.Title;
			s.flag1=false;
			}
	}
	
	public void processKeysStory() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			g.gameState = gameStates.Title;
			s.flag1=false;
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
