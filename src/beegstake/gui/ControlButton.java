package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import beegstake.gl.gui.util.SoundEngineHelper;

public class ControlButton extends JButton implements MouseListener  {

	private SoundEngineHelper soundEngineController;
	
	public ControlButton(String text, Color color) {
		super(text);
		this.addMouseListener(this);
		setBackground(color);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		this.setBackground(new Color(224,255,255));
		
		Object source = m.getSource();
		if (source instanceof ControlButton){
			ControlButton b = (ControlButton)source;
//			if(b.getText().equals("Key+1")){
//				getSoundEngineController().setKey(GUI.KEYBUTTONS+1);
//			}else if(b.getText().equals("Key-1")){
//				getSoundEngineController().setKey(GUI.KEYBUTTONS-1);
//			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBackground(new Color(173,216,230));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public SoundEngineHelper getSoundEngineController() {
		return soundEngineController;
	}

	public void setSoundEngineController(SoundEngineHelper soundEngineController) {
		if(soundEngineController!=null){
			this.soundEngineController = soundEngineController;
		}
	}

}
