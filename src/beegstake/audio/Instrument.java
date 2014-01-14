package beegstake.audio;

import javax.sound.midi.MidiChannel;

import beegstake.audio.exception.AllChannelsReservedException;
import beegstake.audio.exception.InstrumentNotInitializedException;

public class Instrument {
	private static MidiChannel[] channels;
	private static Boolean[] channelsReserved;
	private InstrumentInformation information;
	private int program, channel;
	private static boolean initialized = false;

	public static void init(MidiChannel[] channels) {
		Instrument.channels = channels;
		Instrument.channelsReserved = new Boolean[channels.length];
		for (int i = 0; i < channelsReserved.length; i++)
			channelsReserved[i] = false;
		Instrument.initialized = true;
	}

	public Instrument(int program, InstrumentInformation information) {
		if (Instrument.initialized) {
			this.setInformation(information);
			this.program = program;
			this.channel = -1;
		} else {
			throw new InstrumentNotInitializedException(
					"The instrument class hasn't been initialized.");
		}
	}

	public void activate() {
		for (int i = 0; i < channels.length; i++) {
			if (!channelsReserved[i]) {
				this.channel = i;
				channelsReserved[i] = true;
				channels[i].programChange(program);
				break;
			}
		}
		if (!this.isActive()) {
			throw new AllChannelsReservedException(
					"All Channels are reserved. Deactivate an instrument to activate this one.");
		}
	}

	public boolean isActive() {
		return this.channel != -1;
	}

	public void deactivate() {
		if (isActive()) {
			channelsReserved[this.channel] = false;
			this.channel = -1;
		}
	}

	public void playSound(int key, int volume) {
		if (channel != -1)
			channels[channel].noteOn(key, volume);
	}

	public void changeModulation(int key, int volume) {
		if (channel != -1)
			channels[channel].setPolyPressure(key, volume);
	}

	public void changePitch(int pitch) {
		if (channel != -1)
			channels[channel].setPitchBend(pitch);
	}

	public void stopSound(int key) {
		if (channel != -1)
			channels[channel].noteOff(key);
	}

	public InstrumentInformation getInformation() {
		return information;
	}

	public void setInformation(InstrumentInformation information) {
		this.information = information;
	}
}
