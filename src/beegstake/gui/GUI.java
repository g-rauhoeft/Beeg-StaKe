package beegstake.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import beegstake.musictheory.KeyNames;

//GroupLayout evtl benutzen....

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	protected final int PANEL_WIDTH = 1300;
	protected final int HEIGHT = 200;
	private GridLayout layoutTop = new GridLayout(1, 1);
	private GridLayout layoutBottom = new GridLayout(1, 1);
	private GridBagLayout layoutCenter = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

    private JButton octavePlusOne = new JButton("Octave +1");
	private JButton keyPlusOne = new JButton("Key +1");	
	private JButton octaveMinusOne = new JButton("Octave -1");
	private JButton keyMinusOne = new JButton("Key -1");
	private JButton instrument = new JButton("Instrument");
	private JButton pitchBend = new JButton("Pitch Blend");
	private JButton otherControls = new JButton("Other Controls");

	/**
	 * GUI Constructor.
	 * @param name The name of the Application.
	 */
	public GUI(String name){
		super(name);
		setResizable(true);
	}
	
	
	/**
	 * The basement of the Frame. It contains three GridLayouts.
	 * @param Container The ContentPane for GUI
	 */
	public void base(Container con){		
		final JPanel panelTop = new JPanel(layoutTop);
		panelTop.setPreferredSize(new Dimension(PANEL_WIDTH, HEIGHT));
		panelTop.setName("top");
		ArrayList<KeyButton> generateButtons = generateKeyButtons(panelTop);
		for (JButton b: generateButtons){
			b.setFont(rotatedFont(b, 1.0));
			panelTop.add(b);
		}			
		
		final JPanel panelBottom = new JPanel(layoutBottom);
		panelBottom.setPreferredSize(new Dimension(PANEL_WIDTH, HEIGHT));
		panelBottom.setName("bottom");
		ArrayList<KeyButton> generateButtons2 = generateKeyButtons(panelBottom);
		for (JButton b: generateButtons2){
			panelBottom.add(b);
		}
			
		con.add(panelTop, BorderLayout.NORTH);
		con.add(controlArea(), BorderLayout.CENTER);
		con.add(panelBottom, BorderLayout.SOUTH);
	}
	
	public JPanel controlArea(){
		final JPanel panelCenter = new JPanel(layoutCenter);
		panelCenter.setPreferredSize(new Dimension(PANEL_WIDTH, HEIGHT));
	    c.fill = GridBagConstraints.FIRST_LINE_START;

		panelCenter.add(buttonOnSideTop(octavePlusOne), c);
		panelCenter.add(buttonOnSideTop(keyPlusOne), c);
		panelCenter.add(buttonUpsideDown(instrument, 300, 100), c);
		panelCenter.add(buttonUpsideDown(pitchBend, 300, 100), c);
		panelCenter.add(buttonUpsideDown(otherControls, 500, 100), c);
		panelCenter.add(buttonOnSideTop(octaveMinusOne), c);
		panelCenter.add(buttonOnSideTop(keyMinusOne), c);
	    
		c.fill = GridBagConstraints.LINE_START;
		panelCenter.add(buttonOnSideBottom(octaveMinusOne));
		panelCenter.add(buttonOnSideBottom(keyMinusOne));
		panelCenter.add(buttonNormal(instrument,  300, 100));
		panelCenter.add(buttonNormal(pitchBend, 300, 100));
		panelCenter.add(buttonNormal(otherControls, 300, 100));
		panelCenter.add(buttonOnSideBottom(octavePlusOne));
		panelCenter.add(buttonOnSideBottom(keyPlusOne));
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
			button.setPreferredSize(new Dimension(50, HEIGHT));
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
	
	public JButton buttonUpsideDown (JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		b.setFont(rotatedFont(b, 1.0));
		b.setBackground(new Color(173,216,230));
		b.setHorizontalAlignment(SwingConstants.CENTER);
		return b;
	}
	
	public JButton buttonNormal(JButton b, int width, int height){		
		b.setPreferredSize(new Dimension(width, height));
		b.setBackground(new Color(173,216,230));
		return b;
	}
	
	public JButton buttonOnSideTop(JButton b){		
		b.setPreferredSize(new Dimension(50, 100));
		b.setFont(rotatedFont(b, 0.50));
		b.setBackground(new Color(173,216,230));
		b.setHorizontalAlignment(SwingConstants.CENTER);
		return b;
	}
	
	public JButton buttonOnSideBottom(JButton b){		
		b.setPreferredSize(new Dimension(50, 100));
		b.setFont(rotatedFont(b, 1.50));
		b.setBackground(new Color(173,216,230));
		b.setHorizontalAlignment(SwingConstants.CENTER);
		return b;
	}
	
	/**
	 * Rotates the Text of a Button.
	 * @param button A JButton, d value for degree (1.0 -> 90°, 0.50 -> 45°
	 * @return Font A new font, rotated(90 degrees)
	 */
	public Font rotatedFont(JButton button, double d){
		double theta = (d) * Math.PI;
		AffineTransform rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		button.setHorizontalAlignment(SwingConstants.CENTER);
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
    
	public static void main(String [] args){
		createAndShowGUI();
	}
}

