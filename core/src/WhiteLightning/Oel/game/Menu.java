package WhiteLightning.Oel.game;

import java.util.HashMap;

public class Menu implements IMenu{

	public HashMap<Integer,String> menuData;

	int currentIdx = -1;
	private int rememberedIdx = -1;
	
	public Menu(HashMap<Integer,String> ll) {
		this.menuData= new HashMap<>(ll);		
		this.currentIdx = 0;
	}
	
	public void iterateMode(){
		this.rememberedIdx = currentIdx;
		this.currentIdx = 0;
	}
	
	public void standardMode(){
		this.currentIdx = rememberedIdx;
	}
	
	
	@Override
	public Boolean setNextItem() {
		if (currentIdx < (menuData.size()-1)){
			currentIdx++; 
			return true;
		} 
		return false;
	}

	@Override
	public Boolean setPreviousItem() {
		if (currentIdx > 0 ){
			currentIdx--;	
			return true;
		}
		return false;
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
	public Object selectCurrentItem() {
		return menuData.get(currentIdx);
	}	
	
	@Override
	public int ItemsNr() {
		return menuData.size();
	}
	
	public String getItemAsString() {
		return (String) menuData.get(currentIdx);
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
	
	public boolean isCurrent() {
		return currentIdx == rememberedIdx;
	}	
}
