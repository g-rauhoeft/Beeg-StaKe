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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import beegstake.musictheory.KeyNames;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private GridLayout layoutTop = new GridLayout(1, 1);
	private GridLayout layoutBottom = new GridLayout(1, 1);
	private FlowLayout layoutCenter = new FlowLayout(0,0,0);
 
	private ArrayList<KeyButton> keyButtons;
	public ArrayList<KeyButton> getKeyButtons() {
		return keyButtons;
	}

	private Color color = new Color(173,216,230);
    private ControlButton octavePlusOne = new ControlButton("Octave +1", color);
	private ControlButton keyPlusOne = new ControlButton("Key +1", color);	
	private ControlButton octaveMinusOne = new ControlButton("Octave -1", color);
	private ControlButton keyMinusOne = new ControlButton("Key -1", color);
	private ControlButton instrument = new ControlButton("Instrument", color);
	private ControlButton pitchBend = new ControlButton("Pitch Blend", color);
	private ControlButton otherControls = new ControlButton("Other Controls", color);
	
    private ControlButton octavePlusOne2 = new ControlButton("Octave +1", color);
	private ControlButton keyPlusOne2 = new ControlButton("Key +1", color);	
	private ControlButton octaveMinusOne2 = new ControlButton("Octave -1", color);
	private ControlButton keyMinusOne2 = new ControlButton("Key -1", color);
	private ControlButton instrument2 = new ControlButton("Instrument", color);
	private ControlButton pitchBend2 = new ControlButton("Pitch Blend", color);
	private ControlButton otherControls2 = new ControlButton("Other Controls", color);
	
	/**
	 * GUI Constructor.
	 * @param name The name of the Application.
	 */
	public GUI(String name){
		super(name);
		this.keyButtons = new ArrayList<KeyButton>();
		this.setMinimumSize(new Dimension(1280,1024));//1280 x 1024 ist max für Tisch!!
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
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
		for (JButton b: generateButtons){
			b.setFont(rotatedFont(b, 1.0));
			panelTop.add(b);
		}			
		
		final JPanel panelBottom = new JPanel(layoutBottom);
		panelBottom.setPreferredSize(new Dimension(width, height));
		panelBottom.setName("bottom");
		ArrayList<KeyButton> generateButtons2 = generateKeyButtons(panelBottom);
		for (JButton b: generateButtons2){
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

		panelCenter.add(buttonOnSideTop(octavePlusOne, getWidth()*7/100, 100 ));
		panelCenter.add(buttonOnSideTop(keyPlusOne, getWidth()*7/100, 100));
		panelCenter.add(buttonUpsideDown(instrument, getWidth()*2/10, 100));
		panelCenter.add(buttonUpsideDown(pitchBend, getWidth()*2/10, 100));
		panelCenter.add(buttonUpsideDown(otherControls, getWidth()*28/100, 100));
		panelCenter.add(buttonOnSideTop(octaveMinusOne, getWidth()*7/100, 100));
		panelCenter.add(buttonOnSideTop(keyMinusOne, getWidth()*7/100, 100));

		panelCenter.add(buttonOnSideBottom(octavePlusOne2, getWidth()*7/100, 100));
		panelCenter.add(buttonOnSideBottom(keyPlusOne2, getWidth()*7/100, 100));
		panelCenter.add(buttonNormal(instrument2, getWidth()*2/10, 100));
		panelCenter.add(buttonNormal(pitchBend2, getWidth()*2/10, 100));
		panelCenter.add(buttonNormal(otherControls2, getWidth()*28/100, 100));
		panelCenter.add(buttonOnSideBottom(octaveMinusOne2, getWidth()*7/100, 100));
		panelCenter.add(buttonOnSideBottom(keyMinusOne2, getWidth()*7/100, 100));
	    
		return panelCenter;
	}

	/**
	 * Generates KeyButtons.
	 * @param panel
	 * @return A List of KeyButtons
	 */
	public ArrayList<KeyButton> generateKeyButtons(JPanel panel){
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>(20);
		KeyButton button;
		for (int i=0; i<20; i++){
			if(panel.getName().equals("top")){
				button = new KeyButton(KeyNames.getNameRevert(i+4));
			}else{				
				button = new KeyButton(KeyNames.getName(i));
			}
			if(button.getText()!=null && button.getText().endsWith("#")){
				button.setBackground(new Color(176,176,176));
			}else{
				button.setBackground(new Color(255,255,255));
			}
			button.setBorderPainted(true);
			buttons.add(button);
			keyButtons.add(button);
		}
		return buttons;
	}
	
	public JButton buttonUpsideDown (JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		b.setFont(rotatedFont(b, 1.0));
		b.setAlignmentX(LEFT_ALIGNMENT);
//		b.setHorizontalAlignment(SwingConstants.CENTER);
		return b;
	}
	
	public JButton buttonNormal(JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		return b;
	}
	
	public JButton buttonOnSideTop(JButton b, int width, int height){
		b.setPreferredSize(new Dimension(width, height));
		b.setFont(rotatedFont(b, 0.50));
//		b.setHorizontalAlignment(SwingConstants.LEFT);
		return b;
	}
	
	public JButton buttonOnSideBottom(JButton b, int width, int height){
		b.setPreferredSize(new Dimension(width, height));
		b.setFont(rotatedFont(b, 1.50));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		return b;
	}
	
	/**
	 * Rotates the Text of a Button.
	 * @param button A JButton,
	 * @param d value for degree (1.0 -> 90�, 0.50 -> 45�
	 * @return Font A new font, rotated(90 degrees)
	 */
	public Font rotatedFont(JButton button, double d){
		double theta = (d) * Math.PI;
		AffineTransform rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
//		button.setHorizontalAlignment(SwingConstants.CENTER);
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
    }
    
//	public static void main(String [] args){
//		createAndShowGUI();
//	}
}

