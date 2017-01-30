package WhiteLightning.Oel.game.Control;

import WhiteLightning.Oel.game.Game;
import WhiteLightning.Oel.game.Logic;
import WhiteLightning.Oel.game.oldMenu;
import WhiteLightning.Oel.game.SoundPack;
import WhiteLightning.Oel.game.State;
import WhiteLightning.Oel.game.gameStates;
import WhiteLightning.Oel.game.Objects.Factory;
import WhiteLightning.Oel.game.Objects.Factory.FactoryType;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Control {

	private Game g;
	private State s = State.getInstance();
	private Logic l;
	private OrthographicCamera camera;
	public SoundPack sounds = new SoundPack();

	public Control(Game g, OrthographicCamera camera) {
		this.g = g;
		l = g.l;
	}

	public void processKeys(gameStates gs, oldMenu menu, oldMenu actionMenu) {

	//	processKeysDefault();

		switch (s.gameState) {
		case Title:
			processKeysTitle(menu);
			break;
		case OilFields:
			processKeysOilfields();
			break;
		case Factory:
			switch (s.currFactoryType) {
			case DRILLS:
			//	processKeysFactory(g.world.drillFactory);
				break;
			case PUMP:
		//		processKeysFactory(g.world.pumpsFactory);
				break;
			case WAGONS:
			//	processKeysFactory(g.world.wagonFactory);
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
			processKeysGame(actionMenu);
			break;

		default:
			break;
		}
	}

	public void processKeysTitle(oldMenu menu) {

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			s.gameState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sounds.menuChangeSound.play();

		
				menu.setPreviousItem();

		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sounds.menuChangeSound.play();
			menu.setNextItem();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			
		
			if (menu.getCurrentIdx() == 0){
				s.gameState = gameStates.Game;
				s.currentPlayerIdx = 0;
			}
	
			if (menu.getCurrentIdx() == 1) {
				s.gameState = gameStates.Options;
			}
			if (menu.getCurrentIdx() == 2) {
				s.gameState = gameStates.Setup;
			}
			if (menu.getCurrentIdx() == 3) {
				s.gameState = gameStates.Credits;
			}
			if (menu.getCurrentIdx() == 4)
				Gdx.app.exit();


	
		}
	}

	public void processKeysOilfields() {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sounds.menuChangeSound.play();

			if (s.selectedField > 0)
				s.selectedField--;
			s.moveUp = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sounds.menuChangeSound.play();
			if (s.selectedField < g.world.fieldsList.size() - 1)
				s.selectedField++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			s.gameState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
		
			if (!l.buyField()) {
				sounds.denySound.play();
			}
		}
	}



	public void processKeysGame(oldMenu menu) {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			sounds.menuChangeSound.play();
			menu.selectPreviousItem();
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			sounds.menuChangeSound.play();
			menu.selectNextItem();
/*			if (s.selectedAction < g.actionsList.size() - 1)
				s.selectedAction++;
*/			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			sounds.menuChangeSound.play();

			System.out.println("menu: idx "+menu.getCurrentIdx()+" action "+menu.selectCurrentItem().toString() );
			
			if (menu.getCurrentIdx() == 0) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.PUMP;
			}
			if (menu.getCurrentIdx() == 1) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.DRILLS;
			}
			if (menu.getCurrentIdx() == 2) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.WAGONS;
			}
			if (menu.getCurrentIdx() == 3)
				s.gameState = gameStates.OilFields;
			if (menu.getCurrentIdx() == 8) {
				l.setNextPlayer();
				System.out.println("Game state: " + s.gameState + " " + s.lastYear);
				if (l.isGameEnd()) {
					System.out.println("Game state: " + s.gameState);
					s.gameState = gameStates.End;
				}
			}
			s.selectedField = 0;
		}

	}

	public void processKeysSetup() {

		boolean upperCaseFlag = false;

		if (g.textEnterFlag && Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			String key = pressedKey();

			// if (Gdx.input.isKeyPressed(i)) {
			// return Keys.toString(i);
			if (key.equals("R-Shift") || key.equals("L-Shift")) {
				key = "";

			}

			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				upperCaseFlag = true;
			}

			if (key.equals("Space"))
				key = " ";

			if (key.equals("Enter"))
				key = "";
			if (key.equals("Delete")) {
				if (s.sb.length() > 0) {
					s.sb.setLength(s.sb.length() - 1);
					s.text.setText(s.sb.toString());
				}
			} else {

				if (upperCaseFlag) {
					s.sb.append(key);
				} else {
					s.sb.append(key.toLowerCase());
				}
				s.text.setText(s.sb.toString());
			}
			System.out.println(key);
		}

		if (Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
			g.textEnterFlag = !g.textEnterFlag;

			if (g.textEnterFlag) {
				s.sb = new StringBuffer();
				System.out.println("Enter text");
			} else {
				System.out.println("Entered text: " + s.sb);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			sounds.menuChangeSound.play();
			if(s.players>1)
				s.players--;
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			sounds.menuChangeSound.play();
			if(s.players<g.c.playersMax)
				s.players++;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			sounds.menuChangeSound.play();
			s.gameState = gameStates.Title;
			s.animFlag = false;
		}

	}

	public void processKeysOption() {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			s.gameState = gameStates.Title;
			s.animFlag = false;
		}
	}

	public void processKeysStory() {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			s.gameState = gameStates.Title;
			s.animFlag = false;
		}
	}



	private String pressedKey() {
		for (int i = 29; i < 128; i++) {
			if (Gdx.input.isKeyPressed(i)) {
				System.out.println(i);
				return Keys.toString(i);
			}
		}
		return "";
	}

}
