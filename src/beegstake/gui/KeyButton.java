package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class KeyButton extends JButton implements MouseListener {

	private ArrayList<IKeyButtonListener> listeners = new ArrayList<IKeyButtonListener>();
	private SoundEngineHelper soundEngineHelper;
	
	public KeyButton(String text) {
		super(text);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.getText()!=null && this.getText().endsWith("#")){
			this.setBackground(new Color(228, 150, 0));
		}else{
			this.setBackground(new Color(255, 165, 25));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.getText()!=null && this.getText().endsWith("#")){
			this.setBackground(new Color(176,176,176));
		}else{
			this.setBackground(new Color(255,255,255));
		}
//TODO: Gregs method to proofe the black key.
//		int button = e.getButton();
//		if (soundEngineHelper.isKeyBlack(button)) {
//			this.setBackground(new Color(176,176,176));
//		} else {
//			this.setBackground(new Color(255,255,255));
//		}
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
