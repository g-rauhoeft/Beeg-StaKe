package beegstake.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
//GridBagLayout
//GroupLayout
public class GUI extends JFrame{
	//private GridLayout baseLayout = new GridLayout(3,1);
	private GridLayout baseOfKeysTop = new GridLayout(1, 1);
	private GridLayout baseOfKeysBottom = new GridLayout(1, 1);
	private GridBagLayout control = new GridBagLayout();
	
	public ArrayList<KeyButton> testGenerateButtons(JPanel panel){
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>(20);
		for (int i=0; i<20; i++){
			KeyButton test = new KeyButton("Test");
			test.setPreferredSize(new Dimension(50, 200));
			buttons.add(test);
		}
		return buttons;
	}
	
	public GUI(String name){
		super(name);
		setResizable(true);
	}
	
	/**
	 * The basement of the Frame. It contains two GridLayouts and an GridBagLayout.
	 * @param Container The GUI Container.
	 */
	public void base(Container con){		
		final JPanel panelTop = new JPanel(baseOfKeysTop);
		panelTop.setPreferredSize(new Dimension(1000, 200));
		ArrayList<KeyButton> testGenerateButtons = testGenerateButtons(panelTop);
		for (JButton b: testGenerateButtons){
			panelTop.add(b);
		}			
        
		final JPanel panelCenter = new JPanel(control);
		panelCenter.setPreferredSize(new Dimension(1000, 200));
		panelCenter.add(new JButton("lol"));
		panelCenter.add(new JButton("lol"));
		
		final JPanel panelBottom = new JPanel(baseOfKeysBottom);
		panelBottom.setPreferredSize(new Dimension(1000, 200));
		ArrayList<KeyButton> testGenerateButtons2 = testGenerateButtons(panelBottom);
		for (JButton b: testGenerateButtons2){
			panelBottom.add(b);
		}
		
		con.add(panelTop, BorderLayout.NORTH);
		con.add(panelCenter, BorderLayout.CENTER);
		con.add(panelBottom, BorderLayout.SOUTH);
	}
	
	
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
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

