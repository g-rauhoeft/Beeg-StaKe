package beegstake.gui;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
