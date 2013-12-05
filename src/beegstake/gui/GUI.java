package beegstake.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//GridBagLayout
//GroupLayout
public class GUI extends JFrame{
	private GridLayout baseLayout = new GridLayout(3,1);
	private GridLayout baseOfKeysTop = new GridLayout(1, 1);
	private final JPanel panelOfKeysTop = new JPanel(baseOfKeysTop);
	private GridLayout baseOfKeysBottom = new GridLayout(1, 1);
	private final JPanel panelOfKeysBottom = new JPanel(baseOfKeysBottom);
	private GridBagLayout control = new GridBagLayout();
	private final JPanel controlPanel = new JPanel(control);
	//40 Tasten
	ArrayList<KeyButton> keyButtons = new ArrayList<KeyButton>(39);

	
	public GUI(String name){
		super(name);
		setResizable(true);
	}
	
	/**
	 * The basement of the Frame. It contains two GridLayouts and an GridBagLayout.
	 * @param Container The GUI Container.
	 */
	public void base(Container con){
		final JPanel panel = new JPanel(baseLayout);
		panel.setPreferredSize(new Dimension(1000, 600));
		panel.add(panelOfKeysTop);
		panel.add(panelOfKeysBottom);
		panel.add(controlPanel);
		this.add(panel);
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

