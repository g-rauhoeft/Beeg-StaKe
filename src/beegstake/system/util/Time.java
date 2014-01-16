package beegstake.system.util;

import org.lwjgl.Sys;

public class Time {
	private long lastFrame;
	
	public Time(){
		lastFrame = getTime();
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    return delta;
	}
}
