package WhiteLightning.Oel.game;

public interface IMenu {

	public void setNextItem();
	public void setPreviousItem();
	public Object selectNextItem();
	public Object selectPreviousItem();
	public Object selectCurrentItem();
	public int getNextCurrentIdx();
	public int getPreviousCurrentIdx();
	public int getCurrentIdx();
	public int ItemsNr();
	public boolean hasNext();
	
}
