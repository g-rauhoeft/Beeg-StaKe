package beegstake.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import beegstake.audio.Instrument;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.musictheory.KeyNames;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	protected static int KEYBUTTONS = 20;
	private GridLayout layoutTop = new GridLayout(1, 1);
	private GridLayout layoutBottom = new GridLayout(1, 1);
	private FlowLayout layoutCenter = new FlowLayout(0,0,0);
	
	private SoundEngineHelper helperTop;
	private SoundEngineHelper helperBottom;
	
	private Color defaultBlue = new Color(173,216,230);
	private Color darkBlue = new Color(56,152,184);
    private ControlButton octavePlusOne;
	private ControlButton keyPlusOne;	
	private ControlButton octaveMinusOne;
	private ControlButton keyMinusOne;
	private ControlButton pitchBend;
	private ControlButton otherControls;
	
    private ControlButton octavePlusOne2;
	private ControlButton keyPlusOne2;	
	private ControlButton octaveMinusOne2;
	private ControlButton keyMinusOne2;
	private ControlButton pitchBend2;
	private ControlButton otherControls2;

	/**
	 * GUI Constructor.
	 * @param name The name of the Application.
	 */
	public GUI(String name, SoundEngineHelper engineHelperTop, SoundEngineHelper engineHelperBottom){
		super(name);
		setHelperTop(engineHelperTop);
		setHelperBottom(engineHelperBottom);
		this.setMinimumSize(new Dimension(1280,1024));//1280 x 1024 maximum for mt table
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
		generateControlButton();
	}
	
	public void generateControlButton(){
	    octavePlusOne = new ControlButton("Octave+1", darkBlue, helperTop);
		keyPlusOne = new ControlButton("Key+1", darkBlue,helperTop);	
		octaveMinusOne = new ControlButton("Octave-1", darkBlue,helperTop);
		keyMinusOne = new ControlButton("Key-1", darkBlue,helperTop);
		pitchBend = new ControlButton("Pitch Bend", defaultBlue,helperTop);
		otherControls = new ControlButton("Other Controls", defaultBlue,helperTop);
		
	    octavePlusOne2 = new ControlButton("Octave+1", darkBlue,helperBottom);
		keyPlusOne2 = new ControlButton("Key+1", darkBlue,helperBottom);	
		octaveMinusOne2 = new ControlButton("Octave-1", darkBlue,helperBottom);
		keyMinusOne2 = new ControlButton("Key-1", darkBlue,helperBottom);
		pitchBend2 = new ControlButton("Pitch Bend", defaultBlue,helperBottom);
		otherControls2 = new ControlButton("Other Controls", defaultBlue,helperBottom);
	}
	
	public ArrayList<InstrumentSelectionButton> generateInstrumentSelectionButtons(boolean isTop){
		ArrayList<InstrumentSelectionButton> instrumentSelectionButtons = new ArrayList<InstrumentSelectionButton>();
		ArrayList<Instrument> availableInstruments = helperTop.getSoundEngine().getAvailableInstruments();
		InstrumentSelectionButton instrumentSelBu;
		for(int i=10; i<17;i++){
			String name = availableInstruments.get(i).getInformation().getName();
			if(isTop){
				instrumentSelBu = new InstrumentSelectionButton(name, helperTop);
			}else{
				instrumentSelBu = new InstrumentSelectionButton(name, helperBottom);
			}
			instrumentSelBu.setBorder(BorderFactory.createEmptyBorder(15, 5, 0, 0));
			instrumentSelBu.setBackground(new Color(126,193,216));
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
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>(20);
		KeyButton button;
		for (int i=0; i<KEYBUTTONS; i++){
			if(panel.getName().equals("top")){
				button = new KeyButton(KeyNames.getNameRevert(i+4), helperTop, i);
			}else{				
				button = new KeyButton(KeyNames.getName(i), helperBottom, i);
			}
			if(button.getText()!=null && button.getText().endsWith("#")){
				button.setBackground(new Color(176,176,176));
			}else{
				button.setBackground(new Color(255,255,255));
			}
			//Gregs method for checking the black keys
//			if (helperTop.isKeyBlack(i)) {
//				button.setBackground(new Color(176,176,176));
//			} else {
//				button.setBackground(new Color(255,255,255));
//			}
			button.setBorderPainted(true);
			buttons.add(button);
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
		int height= this.getHeight()/3;
		final JPanel panelCenter = new JPanel(layoutCenter);
		panelCenter.setPreferredSize(new Dimension(width, height));
		int test = height/3;

		//Buttons at the top
		keyPlusOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonUpsideDown(keyPlusOne, width*7/100, height/3));
		octavePlusOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonUpsideDown(octavePlusOne, width*7/100, height/3 ));
		JPanel instrSelectionPanelTop = new JPanel();
		instrSelectionPanelTop.setLayout(new FlowLayout(0,0,0));
		instrSelectionPanelTop.setPreferredSize(new Dimension(width*24/100+1, height/3));
		instrSelectionPanelTop.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		instrSelectionPanelTop.setBackground(defaultBlue);	
		for(InstrumentSelectionButton bu : generateInstrumentSelectionButtons(true)){
			bu.setFont(rotatedFont(bu, 1.0));
			bu.setPreferredSize(new Dimension((int) instrSelectionPanelTop.getPreferredSize().getWidth()/3, test/2));
			bu.setHorizontalTextPosition(JButton.CENTER);
			instrSelectionPanelTop.add(bu);
		}
		panelCenter.add(instrSelectionPanelTop);
		panelCenter.add(buttonUpsideDown(pitchBend, width*24/100+1, height/3));
		panelCenter.add(buttonUpsideDown(otherControls, width*24/100+1, height/3));
		keyMinusOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonUpsideDown(keyMinusOne, width*7/100, height/3));
		octaveMinusOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonUpsideDown(octaveMinusOne, width*7/100, height/3));

		//Buttons at the bottom.
		octaveMinusOne2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonNormal(octaveMinusOne2, width*7/100, height/3-4));
		keyMinusOne2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonNormal(keyMinusOne2, width*7/100, height/3-4));
		panelCenter.add(buttonNormal(otherControls2, width*24/100+1, height/3-4));
		panelCenter.add(buttonNormal(pitchBend2, width*24/100+1, height/3-4));
		JPanel instrSelectionPanel = new JPanel();
		instrSelectionPanel.setLayout(new FlowLayout(0,0,0));
		instrSelectionPanel.setPreferredSize(new Dimension(width*24/100+1, height/3-4));
		instrSelectionPanel.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		instrSelectionPanel.setBackground(defaultBlue);
		for(InstrumentSelectionButton bu : generateInstrumentSelectionButtons(false)){
			bu.setPreferredSize(new Dimension((int) instrSelectionPanel.getPreferredSize().getWidth()/3, test/2-2));
			instrSelectionPanel.add(bu);
		}
		panelCenter.add(instrSelectionPanel);
		octavePlusOne2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonNormal(octavePlusOne2, width*7/100, height/3-4));
		keyPlusOne2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(130,196,217), new Color(82,104,110)));
		panelCenter.add(buttonNormal(keyPlusOne2, width*7/100, height/3-4));
			
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

	private void setHelperTop(SoundEngineHelper helperTop) {
		this.helperTop = helperTop;
	}

	private void setHelperBottom(SoundEngineHelper helperBottom) {
		this.helperBottom = helperBottom;
	}
	
}

