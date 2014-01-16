package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class KeyButton extends JButton implements MouseListener {

	private SoundEngineHelper soundEngineHelper;
	private int key;

	public KeyButton(String text, SoundEngineHelper helper, int key) {
		super(text);
		this.addMouseListener(this);
		this.soundEngineHelper = helper;
		this.key = key;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.soundEngineHelper.getActiveInstrument().playSound(soundEngineHelper.getLowestKey() + key, 100);
		if (this.getText() != null && this.getText().endsWith("#")) {
			this.setBackground(new Color(198, 228, 0 ));
		} else {
			this.setBackground(new Color( 221, 255, 0 ));
 }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.soundEngineHelper.getActiveInstrument().stopSound(soundEngineHelper.getLowestKey() + key);

		if (this.getText() != null && this.getText().endsWith("#")) {
			this.setBackground(new Color(176, 176, 176));
		} else {
			this.setBackground(new Color(255, 255, 255));
		}
		// TODO: Gregs method to proofe the black key.
//		for (int i = 0; i < GUI.KEYBUTTONS; i++) {
//
//			if (soundEngineHelper.isKeyBlack(i)) {
//				this.setBackground(new Color(176, 176, 176));
//			} else {
//				this.setBackground(new Color(255, 255, 255));
//			}
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

}
