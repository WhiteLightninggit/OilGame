package WhiteLightning.Oel.game;

import java.util.ArrayList;

public class Menu implements IMenu{

	public ArrayList<String> menuList;

	int currentIdx = -1;
	int tmpIdx = -1;
	
	public Menu(ArrayList<String> ll) {
		this.menuList = new ArrayList<>(ll);
		this.currentIdx = 0;
		this.tmpIdx = 0;
	}
	
	
	@Override
	public void setNextItem() {
		if (currentIdx < (menuList.size()-1))
			currentIdx++;		
	}

	@Override
	public void setPreviousItem() {
		if (currentIdx > 0 )
			currentIdx--;		
	}

	@Override
	public Object selectNextItem() {
		this.setNextItem();
		return menuList.get(currentIdx);
	}

	@Override
	public Object selectPreviousItem() {
		this.setPreviousItem();
		return menuList.get(currentIdx);
	}

	@Override
	public int ItemsNr() {
		return menuList.size();
	}


	@Override
	public Object selectCurrentItem() {
		return menuList.get(currentIdx);
	}


	public Object getCurrentItem() {
		return menuList.get(tmpIdx);
	}
	
	public String getCurrentItemAsString() {
		return (String) menuList.get(tmpIdx);
	}
	

	@Override
	public int getNextCurrentIdx() {
		if (currentIdx < (menuList.size()-1))
			currentIdx++;
		return currentIdx;
	}


	@Override
	public int getPreviousCurrentIdx() {
		if(currentIdx>0)
			currentIdx--;
		
		return currentIdx;
		
	}


	@Override
	public int getCurrentIdx() {
		return currentIdx;
	}


	@Override
	public boolean hasNext() {
		if (currentIdx<(menuList.size()-1)){			
			return true;
		} else {
			return false;
		}
	}
	

	public void setNextTmpIdx() {
		if (tmpIdx < (menuList.size()))
			tmpIdx++;		
	}


	public void setPreviousTmpIdx() {
		if (tmpIdx > 0 )
			tmpIdx--;		
	}
	
	public boolean hasNextIdx() {
		if (tmpIdx<(menuList.size())){			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCurrent() {
		return currentIdx == tmpIdx;
	}
	
	public void resetTmpIdx(){
		tmpIdx=0;
	}
	
	
}
