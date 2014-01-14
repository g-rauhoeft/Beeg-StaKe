package beegstake.gl.gui.util;

import java.util.ArrayList;

import beegstake.audio.Instrument;
import beegstake.audio.SoundEngine;
import beegstake.musictheory.KeyHighlighter;
import beegstake.musictheory.Scales;
import beegstake.system.Configuration;

public class SoundEngineHelper {
	private int octave;
	private int baseNote;
	private String scale;
	private Scales scales;
	private int activeInstrument;
	private SoundEngine soundEngine;

	public SoundEngineHelper(int octave, int key, String scale,
			SoundEngine soundEngine) {
		scales = new Scales(Configuration.getConfiguration().getJSONArray(
				"Scale"));
		this.setOctave(octave);
		this.setKey(key);
		this.setScale(scale);
		this.soundEngine = soundEngine;
		this.activeInstrument = -1;
	}

	public int getLowestKey() {
		return SoundEngine.KEYS_PER_OCTAVE * octave + baseNote;
	}

	public void setKey(int key) {
		this.baseNote = key % SoundEngine.KEYS_PER_OCTAVE;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	public ArrayList<Integer> getScaleSpacings() {
		return scales.getSpacings(scale);
	}

	public boolean isKeyBlack(int key) {
		return KeyHighlighter.isBlackKey(key, baseNote, getScaleSpacings());
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public SoundEngine getSoundEngine() {
		return soundEngine;
	}

	public void setSoundEngine(SoundEngine soundEngine) {
		this.soundEngine = soundEngine;
	}

	public Instrument getActiveInstrument() {
		return this.soundEngine.getAvailableInstruments().get(activeInstrument);
	}
	
	public int getActiveInstrumentId(){
		return this.activeInstrument;
	}

	public void setActiveInstrument(int activeInstrument) {
		if (this.activeInstrument!=-1) {
			this.soundEngine.getAvailableInstruments()
					.get(this.activeInstrument).deactivate();
		}
		int numberOfInstruments=this.soundEngine.getAvailableInstruments().size();
		this.activeInstrument = (activeInstrument+numberOfInstruments)%numberOfInstruments;
		this.soundEngine.getAvailableInstruments().get(this.activeInstrument)
				.activate();
	}
}
