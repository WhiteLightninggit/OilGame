package WhiteLightning.Oel.game;

public interface IMenu {

	public Boolean setNextItem();
	public Boolean setPreviousItem();
	public Object selectNextItem();
	public Object selectPreviousItem();
	public Object selectCurrentItem();
	public int getCurrentIdx();
	public int ItemsNr();
	public boolean hasNext();
	
}
