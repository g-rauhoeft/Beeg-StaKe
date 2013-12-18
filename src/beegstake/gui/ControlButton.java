package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ControlButton extends JButton implements MouseListener  {

	public ControlButton(String name, Color color) {
		super(name);
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
			if(b.getText().equals("Octave +1")){
				
			}else if(b.getText().equals("Octave -1")){
				
			}
			
			
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

}
