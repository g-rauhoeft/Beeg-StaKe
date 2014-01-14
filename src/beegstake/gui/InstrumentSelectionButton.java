package beegstake.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import beegstake.audio.Instrument;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class InstrumentSelectionButton extends JButton implements MouseListener {

	private SoundEngine soundEngine;
	public InstrumentSelectionButton(String text) {
		super(text);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent mE) {
	}

	@Override
	public void mouseEntered(MouseEvent mE) {
		this.setBackground(new Color(224,255,255));
		Object source = mE.getSource();
		if (source instanceof InstrumentSelectionButton) {
			InstrumentSelectionButton bu = (InstrumentSelectionButton) source;
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
		this.setBackground(new Color(106,184,210));
	}

	@Override
	public void mousePressed(MouseEvent mE) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

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
