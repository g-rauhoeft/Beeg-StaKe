package beegstake.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import beegstake.audio.Instrument;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class RadioButton extends JRadioButton implements MouseListener {

	private SoundEngine soundEngine;

	public RadioButton(String text) {
		super(text);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent mE) {
		
	}

	@Override
	public void mouseEntered(MouseEvent mE) {
		Object source = mE.getSource();
		if (source instanceof RadioButton) {
			RadioButton bu = (RadioButton) source;
			if (!bu.isSelected()) {
				bu.setSelected(true);

//				ArrayList<Instrument> availableInstruments = getSoundEngine().getAvailableInstruments();
//				for (Instrument i : availableInstruments) {
//					if (i.isActive()) {
//						i.deactivate();
//					}
//					if (bu.getText().equals(i.getInformation().getName())) {
//						// Hier erhalten ide Tasten die T�ne vom gew�hlten
//						// Instrument
//						i.activate();
//					}
//				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent mE) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public SoundEngine getSoundEngine() {
		return soundEngine;
	}

	public void setSoundEngine(SoundEngine soundEngineController) {
		if(soundEngineController!=null){
			this.soundEngine = soundEngineController;
		}
	}

}
