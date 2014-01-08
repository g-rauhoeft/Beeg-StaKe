package beegstake.test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import TUIO.TuioClient;
import beegstake.audio.SoundEngine;
import beegstake.gui.GUI;
import beegstake.gui.KeyButton;
import beegstake.input.TouchHandler;
import beegstake.system.Configuration;

public class GUIIntegrationTest {
	public static class KeyListener implements MouseListener{
		private int key;
		private SoundEngine en;
		public KeyListener(int key, SoundEngine en){
			this.key = key;
			this.en = en;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			// Only Test
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			en.getAvailableInstruments().get(0).playSound(key, 100);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			en.getAvailableInstruments().get(0).stopSound(key);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			en.getAvailableInstruments().get(0).stopSound(key);

		}
		
	}
	public static void main(String[] args){
		GUI gui = new GUI("BeegStake GUI");
//		TouchHandler touchHandler = new TouchHandler(gui.getContentPane(), gui.getWidth(), gui.getHeight());
		TuioClient client = new TuioClient();
//		client.addTuioListener(touchHandler);
//		client.connect();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
		gui.base(gui.getContentPane());
		TouchHandler touchHandler = new TouchHandler(gui.getContentPane(), gui.getWidth(), gui.getHeight());
		client.addTuioListener(touchHandler);
		client.connect();
        //Display the window.
		gui.pack();
		gui.setVisible(true);	
	
		Configuration.load("cfg/system.json");
		final SoundEngine s = new SoundEngine();
		s.getAvailableInstruments().get(0).activate();
		int i = 50;
		for(KeyButton kb : gui.getKeyButtons()){
			kb.addMouseListener(new KeyListener(i++,s));
		}
		//		s.getAvailableInstruments().get(2).playSound(65, (int) (127*percentage));
	}
}
