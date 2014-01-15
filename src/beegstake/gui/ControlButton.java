package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import beegstake.gl.gui.util.SoundEngineHelper;

public class ControlButton extends JButton implements MouseListener  {

	private SoundEngineHelper soundEngineHelper;
	
	public ControlButton(String text, Color color, SoundEngineHelper helper) {
		super(text);
		this.addMouseListener(this);
		setBackground(color);
		setSoundEngineHelper(helper);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		this.setBackground(new Color(224,255,255));
		
		Object source = m.getSource();
		if (source instanceof ControlButton){
			ControlButton b = (ControlButton)source;
//			if(b.getText().equals("Key+1")){
//				soundEngineHelper.setKey(key);
//			}else if(b.getText().equals("Key-1")){
//				soundEngineHelper.setKey(key);
//			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBackground(new Color(173,216,230));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	public SoundEngineHelper getSoundEngineHelper() {
		return soundEngineHelper;
	}

	public void setSoundEngineHelper(SoundEngineHelper soundEngineHelper) {
		if(soundEngineHelper!=null){
			this.soundEngineHelper = soundEngineHelper;
		}
	}

}
