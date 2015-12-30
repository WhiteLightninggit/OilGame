package WhiteLightning.Oel.game;

import WhiteLightning.Oel.game.Factory.FactoryType;
import WhiteLightning.Oel.game.Game.gameStates;

import java.util.ArrayList;

import javax.swing.text.html.StyleSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class Control {

	private Game g;
	private State s = State.getInstance();
	private Logic l;

	public Control(Game g) {
		this.g = g;
		l = g.l;
	}

	public void processKeys(gameStates gs, Menu menu) {

		processKeysDefault();

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

	public void processKeysTitle(Menu menu) {

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			s.gameState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			g.menuChangeSound.play();

		
				menu.setPreviousItem();

		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			g.menuChangeSound.play();
			menu.setNextItem();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			
			System.out.println(menu.selectCurrentItem());
			
			if (menu.getCurrentIdx() == 4)
				Gdx.app.exit();

	
			if (menu.getCurrentIdx() == 1) {
				s.gameState = gameStates.Options;
			}
			if (menu.getCurrentIdx() == 2) {
				s.gameState = gameStates.Setup;
			}
			if (menu.getCurrentIdx() == 3) {
				s.gameState = gameStates.Credits;
			}

			if (menu.getCurrentIdx() == 0)
				s.gameState = gameStates.Game;
	
		}
	}

	public void processKeysOilfields() {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			g.menuChangeSound.play();

			if (s.selectedField > 0)
				s.selectedField--;
			s.moveUp = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			g.menuChangeSound.play();
			if (s.selectedField < g.world.fieldsList.size() - 1)
				s.selectedField++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			s.gameState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			s.animFlag = true;
			s.menuAnimX = 0;
			System.out.println(g.world.fieldsList.get(s.selectedField).name);
			if (!l.buyField()) {
				g.denySound.play();
			}
		}
	}

	public void processKeysFactory(ArrayList<Factory> factories) {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			g.menuChangeSound.play();

			if (s.selectedField > 0)
				s.selectedField--;
			s.moveUp = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			g.menuChangeSound.play();
			if (s.selectedField < factories.size() - 1)
				s.selectedField++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			s.gameState = gameStates.Game;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {

			if (!l.buyFactory(s.currFactoryType)) {
				g.denySound.play();
				if (l.getCurrentFactory().hasOwner()) {
					s.gameState = gameStates.Setup;
				}
			}
			System.out.println(factories.get(s.selectedField).name);
		}
	}

	public void processKeysGame() {
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			// g.nextInternalState();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			g.menuChangeSound.play();
			if (s.selectedAction > 0)
				s.selectedAction--;
			s.moveUp = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			g.menuChangeSound.play();
			if (s.selectedAction < g.actionsList.size() - 1)
				s.selectedAction++;
			s.moveUp = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			g.menuChangeSound.play();
			System.out.println(g.actionsList.get(s.selectedAction) + " " + s.selectedAction);

			if (s.selectedAction == 0) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.PUMP;
			}
			if (s.selectedAction == 1) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.DRILLS;
			}
			if (s.selectedAction == 2) {
				s.gameState = gameStates.Factory;
				s.currFactoryType = FactoryType.WAGONS;
			}
			if (s.selectedAction == 3)
				s.gameState = gameStates.OilFields;
			if (s.selectedAction == 8) {
				l.setNextPlayer();
				System.out.println("Game state: " + s.gameState + " " + s.lastYear);
				if (l.isGameEnd()) {
					System.out.println("Game state: " + s.gameState);
					s.gameState = gameStates.End;
				}
			}
			// s.internalState = s.selectedAction;
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

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
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

	public void processKeysDefault() {
		if (!g.textEnterFlag) {
			if (Gdx.input.isKeyPressed(Keys.X)) {
				g.camera.rotate(0.8f);
			}

			if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				Gdx.app.exit();
			}

			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				g.camera.zoom += 0.02;
			}

			if (Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				g.camera.zoom -= 0.02;
			}
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
