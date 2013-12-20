package beegstake.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import org.json.JSONObject;

import beegstake.system.Configuration;

import com.sun.media.sound.AudioSynthesizer;
import com.sun.media.sound.SF2Soundbank;

public class SoundEngine implements ISoundEngine {
	private AudioSynthesizer synthesizer;
	private ArrayList<Instrument> instruments;
	public static final int KEYS_PER_OCTAVE = 12;

	public SoundEngine() {
		this.instruments = new ArrayList<Instrument>();
		try {
			this.synthesizer = (AudioSynthesizer) MidiSystem.getSynthesizer();
			this.synthesizer.open();
			this.synthesizer.unloadAllInstruments(synthesizer
					.getDefaultSoundbank());
			Instrument.init(synthesizer.getChannels());
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		loadInstruments();
	}

	private boolean loadInstruments() {
		try {
			SF2Soundbank soundbank = new SF2Soundbank(new FileInputStream(
					new File(getConfig().getString("SoundFont"))));
			synthesizer.loadAllInstruments(soundbank);
		} catch (IOException e) {
			return false;
		}
		for (javax.sound.midi.Instrument instrument : synthesizer
				.getLoadedInstruments()) {
			instruments.add(new Instrument(instrument.getPatch().getProgram(),
					new InstrumentInformation(instrument.getName(), "")));
		}
		return true;
	}

	private JSONObject getConfig() {
		return Configuration.getConfiguration(this.getClass());
	}

	@Override
	public final ArrayList<Instrument> getAvailableInstruments() {
		return instruments;
	}
	
	public void close(){
		this.synthesizer.close();
	}
}
