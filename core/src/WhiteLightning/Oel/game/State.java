package WhiteLightning.Oel.game;

public class State {

	public int internalState = 0;
	public boolean flag1 = false;
	public boolean playMusic = true;
	public boolean moveUp = false;
	public int z = 0;
	public byte currentPlayerIdx=0;
	public int currentYear;
	int lastYear;
	public int endOfGame=-1; 
	
	public String inst;
	
	public int selectedMenuItem = 0;
	int selectedField = 0;
	public int selectedAction = 0;
	
    private static class Loader {
        static State INSTANCE = new State();
    }

    private State() {}

    public static State getInstance() {
        return Loader.INSTANCE;
    }
}