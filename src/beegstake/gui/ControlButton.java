package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import beegstake.gl.gui.util.SoundEngineHelper;

public class ControlButton extends JButton implements MouseListener  {

	private SoundEngineHelper soundEngineHelper;
	private Color darkBlue = new Color(56,152,184);
	
	public ControlButton(String text, Color color, SoundEngineHelper helper) {
		super(text);
		this.addMouseListener(this);
		setBackground(color);
		this.soundEngineHelper = helper;
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
			//TODO: Finish Button Implementation, this Code doesn't work
//			if(b.getText().equals("Key+1")){
//				soundEngineHelper.setKey(soundEngineHelper.getKey() + 1);
//			}else if(b.getText().equals("Key-1")){
//				soundEngineHelper.setKey(soundEngineHelper.getKey() + -1);
//			}
//			
//			if(b.getText().equals("Octave+1")){
//				soundEngineHelper.setOctave(soundEngineHelper.getOctave() + 1);
//			}else if(b.getText().equals("Octave-1")){
//				soundEngineHelper.setOctave(soundEngineHelper.getOctave() + -1);
//			}
		}
	}

	@Override
	public void mouseExited(MouseEvent m) {
		Object source = m.getSource();
		if (source instanceof ControlButton){
			ControlButton b = (ControlButton)source;
			if(b.getText().equals("Pitch Bend")||b.getText().equals("Other Controls")){
				b.setBackground(new Color(173,216,230));
			}
			else{
				this.setBackground(darkBlue);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
