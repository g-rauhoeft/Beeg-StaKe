package beegstake.main;

import javax.swing.JFrame;
import TUIO.TuioClient;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.gui.GUI;
import beegstake.input.TouchHandler;
import beegstake.system.Configuration;

public class AWTMain {

	public static void main(String[] args){
		Configuration.load("cfg/system.json");
		final SoundEngine s = new SoundEngine();
		SoundEngineHelper helperTop = new SoundEngineHelper(5, 0, "Major",s);
		SoundEngineHelper helperBottom = new SoundEngineHelper(5, 0, "Major",s);

		helperTop.setActiveInstrument(0);
		helperBottom.setActiveInstrument(0);
		GUI gui = new GUI("BeegStake GUI", helperTop, helperBottom);
		TuioClient client = new TuioClient();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
		gui.base(gui.getContentPane());
		TouchHandler touchHandler = new TouchHandler(gui.getContentPane(), gui.getWidth(), gui.getHeight());
		client.addTuioListener(touchHandler);
		client.connect();
        //Display the window.
		gui.pack();
		gui.setVisible(true);	
	}
}
