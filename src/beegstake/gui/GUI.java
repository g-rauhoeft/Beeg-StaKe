package beegstake.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import beegstake.audio.Instrument;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.musictheory.KeyNames;
import beegstake.system.Configuration;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	protected static int KEYBUTTONS = 20;
	private GridLayout layoutTop = new GridLayout(1, 1);
	private GridLayout layoutBottom = new GridLayout(1, 1);
	private FlowLayout layoutCenter = new FlowLayout(0,0,0);
	
	private ArrayList<KeyButton> keyButtons;
	public ArrayList<KeyButton> getKeyButtons() {
		return keyButtons;
	}

	private SoundEngine soundEngine;
	private SoundEngineHelper helper;
	
	private Color color = new Color(173,216,230);
    private ControlButton octavePlusOne = new ControlButton("Octave+1", color, helper);
	private ControlButton keyPlusOne = new ControlButton("Key+1", color,helper);	
	private ControlButton octaveMinusOne = new ControlButton("Octave-1", color,helper);
	private ControlButton keyMinusOne = new ControlButton("Key-1", color,helper);
	private ControlButton pitchBend = new ControlButton("Pitch Blend", color,helper);
	private ControlButton otherControls = new ControlButton("Other Controls", color,helper);
	
    private ControlButton octavePlusOne2 = new ControlButton("Octave+1", color,helper);
	private ControlButton keyPlusOne2 = new ControlButton("Key+1", color,helper);	
	private ControlButton octaveMinusOne2 = new ControlButton("Octave-1", color,helper);
	private ControlButton keyMinusOne2 = new ControlButton("Key-1", color,helper);
	private ControlButton pitchBend2 = new ControlButton("Pitch Blend", color,helper);
	private ControlButton otherControls2 = new ControlButton("Other Controls", color,helper);
	
	
	/**
	 * GUI Constructor.
	 * @param name The name of the Application.
	 */
	public GUI(String name, SoundEngine s, SoundEngineHelper engineHelper){
		super(name);
		soundEngine = s;
		helper = engineHelper;
		this.setMinimumSize(new Dimension(1280,768));//1280 x 1024 maximum for mt table
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
	}
	
	public ArrayList<InstrumentSelectionButton> generateInstrumentSelectionButtons(){
		ArrayList<InstrumentSelectionButton> instrumentSelectionButtons = new ArrayList<InstrumentSelectionButton>();
		ArrayList<Instrument> availableInstruments = helper.getSoundEngine().getAvailableInstruments();
		InstrumentSelectionButton instrumentSelBu;
		for(int i=10; i<17;i++){
			String name = availableInstruments.get(i).getInformation().getName();
			instrumentSelBu = new InstrumentSelectionButton(name, helper);
			instrumentSelBu.setBorder(BorderFactory.createEmptyBorder(15, 5, 0, 0));
			instrumentSelBu.setBackground(new Color(106,184,210));
			instrumentSelBu.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
			instrumentSelectionButtons.add(instrumentSelBu);	
		}
		return instrumentSelectionButtons;
	}
	
	/**
	 * Generates KeyButtons.
	 * @param panel
	 * @return A List of KeyButtons
	 */
	public ArrayList<KeyButton> generateKeyButtons(JPanel panel){
		keyButtons = new ArrayList<KeyButton>();
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>(20);
		KeyButton button;
		for (int i=0; i<KEYBUTTONS; i++){
			if(panel.getName().equals("top")){
				button = new KeyButton(KeyNames.getNameRevert(i+4), helper, i);
			}else{				
				button = new KeyButton(KeyNames.getName(i), helper, i);
			}
			//Gregs method for checking the black keys
			if (helper.isKeyBlack(i)) {
				button.setBackground(new Color(176,176,176));
			} else {
				button.setBackground(new Color(255,255,255));
			}
			
//			if(button.getText()!=null && button.getText().endsWith("#")){
//				button.setBackground(new Color(176,176,176));
//			}else{
//				button.setBackground(new Color(255,255,255));
//			}
			button.setBorderPainted(true);
			buttons.add(button);
			keyButtons.add(button);
		}
		return buttons;
	}
	
	/**
	 * The basement of the Frame. It contains three GridLayouts.
	 * @param Container The ContentPane for GUI
	 */
	public void base(Container con){
		int width = this.getWidth();
		int height= this.getHeight()/3+28;
		System.out.println("Test" +width);
		System.out.println("Test" +height);
		final JPanel panelTop = new JPanel(layoutTop);
		panelTop.setPreferredSize(new Dimension(width, height));
		panelTop.setName("top");
		ArrayList<KeyButton> generateButtons = generateKeyButtons(panelTop);
		for (KeyButton b: generateButtons){
			b.setFont(rotatedFont(b, 1.0));
			panelTop.add(b);
		}			
		
		final JPanel panelBottom = new JPanel(layoutBottom);
		panelBottom.setPreferredSize(new Dimension(width, height));
		panelBottom.setName("bottom");
		ArrayList<KeyButton> generateButtons2 = generateKeyButtons(panelBottom);
		for (KeyButton b: generateButtons2){
			panelBottom.add(b);
		}
			
		con.add(panelTop, BorderLayout.NORTH);
		con.add(controlArea(), BorderLayout.CENTER);
		con.add(panelBottom, BorderLayout.SOUTH);
	}
	
	/**
	 * Buttons for the Control Area will be added.
	 * @return JPanel The Panel containing the ControlButtons
	 */
	public JPanel controlArea(){
		int width = this.getWidth();
		int height= this.getHeight()/3+28;
		System.out.println(width);
		System.out.println(height);
		System.out.println(this.getMinimumSize().getWidth());
		System.out.println(this.getMinimumSize().getHeight());
		final JPanel panelCenter = new JPanel(layoutCenter);
		panelCenter.setPreferredSize(new Dimension(width, height));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

		//Buttons at the top
		panelCenter.add(buttonUpsideDown(octavePlusOne, width*7/100, 100 ));
		panelCenter.add(buttonUpsideDown(keyPlusOne, width*7/100, 100));
		JPanel instrSelectionPanelTop = new JPanel();
		instrSelectionPanelTop.setLayout(new FlowLayout(0,0,0));
		instrSelectionPanelTop.setPreferredSize(new Dimension(width*24/100, 100));
		instrSelectionPanelTop.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		instrSelectionPanelTop.setBackground(color);	
		for(InstrumentSelectionButton bu : generateInstrumentSelectionButtons()){
			bu.setFont(rotatedFont(bu, 1.0));
			bu.setPreferredSize(new Dimension((int) instrSelectionPanelTop.getPreferredSize().getWidth()/3-1, 50));
			bu.setHorizontalTextPosition(JButton.CENTER);
			instrSelectionPanelTop.add(bu);
		}
		panelCenter.add(instrSelectionPanelTop);
		panelCenter.add(buttonUpsideDown(pitchBend, width*24/100, 100));
		panelCenter.add(buttonUpsideDown(otherControls, width*24/100, 100));
		panelCenter.add(buttonUpsideDown(octaveMinusOne, width*7/100, 100));
		panelCenter.add(buttonUpsideDown(keyMinusOne, width*7/100, 100));
		
		//Buttons at the bottom.
		panelCenter.add(buttonNormal(octaveMinusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(keyMinusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(otherControls2, width*24/100, 100));
		panelCenter.add(buttonNormal(pitchBend2, width*24/100, 100));
		JPanel instrSelectionPanel = new JPanel();
		instrSelectionPanel.setLayout(new FlowLayout(0,0,0));
		instrSelectionPanel.setPreferredSize(new Dimension(width*24/100, 100));
		instrSelectionPanel.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		instrSelectionPanel.setBackground(color);
		for(InstrumentSelectionButton bu : generateInstrumentSelectionButtons()){
			bu.setPreferredSize(new Dimension((int) instrSelectionPanel.getPreferredSize().getWidth()/3-1, 50));
			instrSelectionPanel.add(bu);
		}
		panelCenter.add(instrSelectionPanel);
		panelCenter.add(buttonNormal(octavePlusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(keyPlusOne2, width*7/100, 100));
			
		return panelCenter;
	}

	public JButton buttonUpsideDown (JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		b.setFont(rotatedFont(b, 1.0));
		b.setHorizontalTextPosition(JButton.CENTER);
		return b;
	}
	
	public JButton buttonNormal(JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		return b;
	}
	
	/**
	 * Rotates the Text of a Button.
	 * @param button The Button at which the text has to be rotated.
	 * @param d Value for degree (1.0 -> 180 degrees, 0.50 -> 45 degrees
	 * @return Font A new rotated font 
	 */
	public Font rotatedFont(AbstractButton button, double d){
		double theta = (d) * Math.PI;
		AffineTransform rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		return button.getFont().deriveFont(rotate);
	}
	
}

