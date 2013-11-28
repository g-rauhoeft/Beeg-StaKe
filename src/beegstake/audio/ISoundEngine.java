package beegstake.audio;

public interface ISoundEngine {
	public void playSound(int key, int volume, int pitch);
	public void changeVolume(int key, int volume);
	public void changePitch(int key, int pitch);
	public void stopSound(int key);
}
