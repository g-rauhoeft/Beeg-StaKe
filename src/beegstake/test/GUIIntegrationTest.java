package beegstake.test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import TUIO.TuioClient;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.gui.GUI;
import beegstake.gui.KeyButton;
import beegstake.input.TouchHandler;
import beegstake.system.Configuration;

public class GUIIntegrationTest {

	public static void main(String[] args){
		Configuration.load("cfg/system.json");
		final SoundEngine s = new SoundEngine();
		SoundEngineHelper helper = new SoundEngineHelper(4, 0, "Major",s);
		helper.setActiveInstrument(0);
		GUI gui = new GUI("BeegStake GUI", s, helper);
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
