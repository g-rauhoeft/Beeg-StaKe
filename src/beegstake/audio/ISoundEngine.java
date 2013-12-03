package beegstake.audio;

import java.util.ArrayList;

public interface ISoundEngine {
	public ArrayList<Instrument> getAvailableInstruments();
	public void setActiveInstrument(Instrument instrument);
	public Instrument getActiveInstrument();
	public void playSound(int key, int volume, int pitch);
	public void changeVolume(int key, int volume);
	public void changePitch(int key, int pitch);
	public void stopSound(int key);
}
