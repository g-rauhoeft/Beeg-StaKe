package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class KeyButton extends JButton implements MouseListener {

	ArrayList<IKeyButtonListener> listeners = new ArrayList<IKeyButtonListener>();

	public KeyButton(String name) {
		super(name);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(new Color(255,106,106));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.getText()!=null && this.getText().endsWith("#")){
			this.setBackground(new Color(176,176,176));
		}else{
			this.setBackground(new Color(255,255,255));
		}
	}

	
	public void addListener(IKeyButtonListener listener) {
		listeners.add(listener);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int y_MousePos = e.getY();
		double maxSize = this.getSize().getHeight();
		double pw = y_MousePos / maxSize;
		for(IKeyButtonListener listener : listeners){
			listener.onSlide(pw);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
