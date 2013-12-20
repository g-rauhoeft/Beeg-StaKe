package beegstake.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
	
	private Color color = new Color(173,216,230);
    private ControlButton octavePlusOne = new ControlButton("Octave+1", color);
	private ControlButton keyPlusOne = new ControlButton("Key+1", color);	
	private ControlButton octaveMinusOne = new ControlButton("Octave-1", color);
	private ControlButton keyMinusOne = new ControlButton("Key-1", color);
	private ControlButton pitchBend = new ControlButton("Pitch Blend", color);
	private ControlButton otherControls = new ControlButton("Other Controls", color);
	
    private ControlButton octavePlusOne2 = new ControlButton("Octave+1", color);
	private ControlButton keyPlusOne2 = new ControlButton("Key+1", color);	
	private ControlButton octaveMinusOne2 = new ControlButton("Octave-1", color);
	private ControlButton keyMinusOne2 = new ControlButton("Key-1", color);
	private ControlButton pitchBend2 = new ControlButton("Pitch Blend", color);
	private ControlButton otherControls2 = new ControlButton("Other Controls", color);
	private SoundEngineHelper soundEngineController;
	
	
	/**
	 * GUI Constructor.
	 * @param name The name of the Application.
	 */
	public GUI(String name){
		super(name);
		Configuration.load("cfg/system.json");
		//this.soundEngineController = new SoundEngineHelper(3, 0, "Arabic");
		this.setMinimumSize(new Dimension(1280,768));//1280 x 1024 ist max für Tisch!!
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
	}
	
	public ArrayList<RadioButton> generateRadioButtons(){
		ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
		SoundEngine soundEng = new SoundEngine();
		ArrayList<Instrument> availableInstruments = soundEng.getAvailableInstruments();
		ButtonGroup group = new ButtonGroup();
		RadioButton radioBu;
		for(int i=0; i<4;i++){
			String name = availableInstruments.get(i).getInformation().getName();
			radioBu = new RadioButton(name);
			radioBu.setBorder(BorderFactory.createEmptyBorder(15, 5, 0, 0));

			radioBu.setBackground(color);
//			radioBu.setPreferredSize(new Dimension(getWidth()*6/100, 25));
			radioBu.setSoundEngineController(soundEngineController);
			group.add(radioBu);
			radioButtons.add(radioBu);	
		}
		return radioButtons;
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
				button = new KeyButton(KeyNames.getNameRevert(i+4));
			}else{				
				button = new KeyButton(KeyNames.getName(i));
			}
			//Gregs Methode zur Überprüfung
//			if (soundEngineController.isKeyBlack(i)) {
//				button.setBackground(new Color(176,176,176));
//			} else {
//				button.setBackground(new Color(255,255,255));
//			}
			
			if(button.getText()!=null && button.getText().endsWith("#")){
				button.setBackground(new Color(176,176,176));
			}else{
				button.setBackground(new Color(255,255,255));
			}
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
		final JPanel panelCenter = new JPanel(layoutCenter);
		panelCenter.setPreferredSize(new Dimension(width, height));

		panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		panelCenter.add(buttonUpsideDown(octavePlusOne, width*7/100, 100 ));
		panelCenter.add(buttonUpsideDown(keyPlusOne, width*7/100, 100));
		JPanel radioPanelTop = new JPanel();
		radioPanelTop.setLayout(new FlowLayout());
		//eigentlich 24/100
		radioPanelTop.setPreferredSize(new Dimension(width*24/100, 100));
		radioPanelTop.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		radioPanelTop.setBackground(color);
		for(RadioButton bu : generateRadioButtons()){
			bu.setFont(rotatedFont(bu, 1.0));
			bu.setHorizontalTextPosition(JRadioButton.CENTER);
			bu.setHorizontalAlignment(SwingConstants.RIGHT);
			radioPanelTop.add(bu);
		}
		panelCenter.add(radioPanelTop);
		panelCenter.add(buttonUpsideDown(pitchBend, width*2/10, 100));
		panelCenter.add(buttonUpsideDown(otherControls, width*28/100, 100));
		panelCenter.add(buttonUpsideDown(octaveMinusOne, width*7/100, 100));
		panelCenter.add(buttonUpsideDown(keyMinusOne, width*7/100, 100));
		
		
		panelCenter.add(buttonNormal(octaveMinusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(keyMinusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(otherControls2, width*28/100, 100));
		panelCenter.add(buttonNormal(pitchBend2, width*2/10, 100));
		JPanel radioPanelBottom = new JPanel();
		radioPanelBottom.setLayout(new FlowLayout());
		//bei mir: 30/100
		radioPanelBottom.setPreferredSize(new Dimension(width*24/100, 100));
		radioPanelBottom.setBorder(BorderFactory.createLineBorder(new Color(104,131,139)));
		radioPanelBottom.setBackground(color);
		for(RadioButton bu : generateRadioButtons()){
			radioPanelBottom.add(bu);
		}
		panelCenter.add(radioPanelBottom);
		panelCenter.add(buttonNormal(octavePlusOne2, width*7/100, 100));
		panelCenter.add(buttonNormal(keyPlusOne2, width*7/100, 100));
		
		
		keyPlusOne.setSoundEngineController(soundEngineController);
		octavePlusOne.setSoundEngineController(soundEngineController);
		keyPlusOne2.setSoundEngineController(soundEngineController);
		octavePlusOne2.setSoundEngineController(soundEngineController);	
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
	
//TODO: Nicht mehr nötig evtl. löschen
//	public JButton buttonOnSideTop(JButton b, int width, int height){
//		b.setPreferredSize(new Dimension(width, height));
//		b.setFont(rotatedFont(b, 0.50));
//		b.setHorizontalTextPosition(JButton.CENTER);
////		b.setBorder(BorderFactory.createEmptyBorder(0, 0, 55, 15));
//		return b;
//	}
//	
//	public JButton buttonOnSideBottom(JButton b, int width, int height){
//		b.setPreferredSize(new Dimension(width, height));
//		b.setFont(rotatedFont(b, 1.50));
//		b.setHorizontalTextPosition(JButton.CENTER);
////		b.setBorder(BorderFactory.createEmptyBorder(55, 0, 0, 0));
//		return b;
//	}
	
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
	
	
    /**
     * Creates the GUI and shows it.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        GUI frame = new GUI("Beeg Stake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.base(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        System.out.println(frame.getHeight());
        System.out.println(frame.getWidth());

    }
    
	public static void main(String [] args){
		createAndShowGUI();
	}
}

