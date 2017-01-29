package WhiteLightning.Oel.game.Control;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SFX {

	private HashMap<Sounds, Sound> playlist;
	
	public SFX(){
		playlist = new HashMap<>();
		LoadSounds();
	}
	
	private void LoadSounds(){
		addToPlaylist(Sounds.MenuChange, "Sound/select.wav");
		addToPlaylist(Sounds.MenuSound, "Sound/menu.mp3");
		addToPlaylist(Sounds.DenySound, "Sound/deny.mp3");		
	}
	
	private void addToPlaylist(Sounds key, String path){
		this.playlist.put(key,  Gdx.audio.newSound(Gdx.files.internal(path)));
	}
	
	public void PlaySound(Sounds sound){
	//	this.playlist.get(sound).play();
	}
	
}
