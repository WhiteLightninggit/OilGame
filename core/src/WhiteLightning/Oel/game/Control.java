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
	
	public Control(Game g){
		this.g = g;	
		l = g.l;		
	}	
	
	public void processKeys(gameStates gs){
		processKeysDefault();
		
		switch (s.gameState) {
		case Title:
			processKeysTitle();
			break;
		case OilFields:
			processKeysOilfields();
			break;
		case Factory:			
			switch (s.currFactoryType) {
			case DRILLS:				
				processKeysFactory(g.world.drillFactory);
				break;
			case PUMP:
				processKeysFactory(g.world.pumpsFactory);
				break;
			case WAGONS:
				processKeysFactory(g.world.wagonFactory);
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
		case Setup:
			processKeysSetup();
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
			s.gameState = gameStates.Game;			
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
				s.gameState=gameStates.Options;
			}
			if(s.selectedMenuItem == 2){
				s.flag1 = true;		
				s.gameState=gameStates.Story;
			}
			if(s.selectedMenuItem == 3){
				s.flag1 = true;
				s.flag1=!s.flag1;
				s.gameState=gameStates.Credits;
			}
			
			if(s.selectedMenuItem == 0)
				s.gameState = gameStates.Game;		
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
		s.gameState = gameStates.Game;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			System.out.println(g.world.fieldsList.get(s.selectedField).name);			
			if(!l.buyField()){
				g.denySound.play();
			}
		}		
	}
	
	
	public void processKeysFactory(ArrayList<Factory> factories) {
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
		s.gameState = gameStates.Game;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
		
			if(!l.buyFactory(s.currFactoryType)){			
				g.denySound.play();	
				if(l.getCurrentFactory().hasOwner()){
					s.gameState = gameStates.Setup;
				}
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
				
				if(s.selectedAction == 0) {
					s.gameState = gameStates.Factory;
					s.currFactoryType = FactoryType.PUMP;
				}
				if(s.selectedAction == 1){
					s.gameState = gameStates.Factory;
					s.currFactoryType = FactoryType.DRILLS;
				}
				if(s.selectedAction == 2){
					s.gameState = gameStates.Factory;
					s.currFactoryType = FactoryType.WAGONS;
				}
				if(s.selectedAction == 3) s.gameState = gameStates.OilFields;	
				if(s.selectedAction == 8) {
					l.setNextPlayer();
					System.out.println("Game state: "+s.gameState+" "+s.lastYear);
					if(l.isGameEnd()) {
						System.out.println("Game state: "+s.gameState);
						s.gameState = gameStates.End;			
					}
				}	
		//		s.internalState = s.selectedAction;
				s.selectedField=0;
			}			
		
	}
	
	public void processKeysSetup() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			s.gameState = gameStates.Game;
			s.flag1=false;
			}
	}
	
	public void processKeysOption() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			s.gameState = gameStates.Title;
			s.flag1=false;
			}
	}
	
	public void processKeysStory() {
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			s.gameState = gameStates.Title;
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
	
}
