package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class KeyButton extends JButton implements MouseListener {

	ArrayList<IKeyButtonListener> listeners = new ArrayList<IKeyButtonListener>();

//	private SoundEngineHelper soundEngineController = new SoundEngineHelper(3, 0, "Arabic");
	public KeyButton(String text) {
		super(text);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(new Color(255,165,0));
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		Configuration.load("cfg/system.json");
		if(this.getText()!=null && this.getText().endsWith("#")){
			this.setBackground(new Color(176,176,176));
		}else{
			this.setBackground(new Color(255,255,255));
		}
//		int button = e.getButton();
//		if (soundEngineController.isKeyBlack(button)) {
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
