package beegstake.test;

import javax.swing.JFrame;

import TUIO.TuioClient;
import beegstake.audio.SoundEngine;
import beegstake.gui.GUI;
import beegstake.input.TouchHandler;
import beegstake.system.Configuration;

public class GUIIntegrationTest {
	public static void main(String[] args){
		GUI gui = new GUI("Hello World");
		TouchHandler touchHandler = new TouchHandler(gui.getContentPane(), gui.getWidth(), gui.getHeight());
		TuioClient client = new TuioClient();
		client.addTuioListener(touchHandler);
		client.connect();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
		gui.base(gui.getContentPane());
        //Display the window.
		gui.pack();
		gui.setVisible(true);	
	
//		Configuration.load("cfg/system.json");
//		final SoundEngine s = new SoundEngine();
//		s.getAvailableInstruments().get(2).activate();
//		
//		float percentage = 100;
//
//		s.getAvailableInstruments().get(2).playSound(65, (int) (127*percentage));
	}
}
