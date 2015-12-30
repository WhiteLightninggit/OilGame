package WhiteLightning.Oel.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Menu implements IMenu{

	public HashMap<Integer,String> menuData;

	int currentIdx = -1;
	int tmpIdx = -1;
	
	public Menu(HashMap<Integer,String> ll) {
		this.menuData= new HashMap<>(ll);		
		this.currentIdx = 0;
		this.tmpIdx = 0;
	}
	
	
	@Override
	public void setNextItem() {
		if (currentIdx < (menuData.size()-1))
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
		return menuData.get(currentIdx);
	}

	@Override
	public Object selectPreviousItem() {
		this.setPreviousItem();
		return menuData.get(currentIdx);
	}

	@Override
	public int ItemsNr() {
		return menuData.size();
	}


	@Override
	public Object selectCurrentItem() {
		return menuData.get(currentIdx);
	}


	public Object getCurrentItem2() {
		
		System.out.println("a: "+tmpIdx+" b "+currentIdx);
		
		return menuData.get(tmpIdx);
	}
	
	public String getCurrentItemAsString() {
		return (String) menuData.get(tmpIdx);
	}
	

	@Override
	public int getNextCurrentIdx() {
		if (currentIdx < (menuData.size()-1))
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
		if (currentIdx<(menuData.size()-1)){			
			return true;
		} else {
			return false;
		}
	}
	

	public void setNextTmpIdx() {
		if (tmpIdx < (menuData.size()))
			tmpIdx++;		
	}


	public void setPreviousTmpIdx() {
		if (tmpIdx > 0 )
			tmpIdx--;		
	}
	
	public boolean hasNextIdx() {
		if (tmpIdx<(menuData.size())){			
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
