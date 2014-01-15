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

	private ArrayList<Instrument> availableInstruments;
	SoundEngineHelper helper;
	
	public InstrumentSelectionButton(String text, SoundEngineHelper engineHelper) {
		super(text);
		this.addMouseListener(this);
		setHelper(engineHelper);
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
			
			ArrayList<Instrument> availableInstruments = helper.getSoundEngine().getAvailableInstruments();
			int i = 0;
			for (Instrument instr : availableInstruments) {
				if (bu.getText().equals(instr.getInformation().getName())) {
					helper.setActiveInstrument(i);
				}
				i++;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBackground(new Color(126,193,216));
	}

	@Override
	public void mousePressed(MouseEvent mE) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public SoundEngineHelper getHelper() {
		return helper;
	}

	public void setHelper(SoundEngineHelper helper) {
		this.helper = helper;
	}
}
