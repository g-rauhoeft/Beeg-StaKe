package beegstake.musictheory.util;

import java.util.ArrayList;

import beegstake.audio.Instrument;

public class ScalePlayer {
	public static void playScale(ArrayList<Integer> spacings, int base, Instrument instrument){
		if(!instrument.isActive()){
			instrument.activate();
		}
		for(int i = 0; i < spacings.size()+1; i++){
			instrument.playSound(base, 100);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instrument.stopSound(base);
			base += spacings.get(i%spacings.size());
		}
	}
}
