package beegstake.gl.gui.util;

import java.util.ArrayList;

import beegstake.audio.SoundEngine;
import beegstake.musictheory.KeyHighlighter;
import beegstake.musictheory.Scales;
import beegstake.system.Configuration;

public class SoundEngineController {
	private int octave;
	private int baseNote;
	private String scale;
	private Scales scales;
	
	public SoundEngineController(int octave, int key, String scale){
		scales = new Scales(Configuration.getConfiguration().getJSONArray("Scale"));
		this.setOctave(octave);
		this.setKey(key);
		this.setScale(scale);
	}
	
	public int getLowestKey(){
		return SoundEngine.KEYS_PER_OCTAVE * octave + baseNote;
	}
	
	public void setKey(int key){
		this.baseNote = key % SoundEngine.KEYS_PER_OCTAVE;
	}
	
	public void setOctave(int octave){
		this.octave = octave;
	}

	public ArrayList<Integer> getScaleSpacings(){
		return scales.getSpacings(scale);
	}
	
	public boolean isKeyBlack(int key){
		return KeyHighlighter.isBlackKey(key, baseNote, getScaleSpacings());
	}
	
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
}
