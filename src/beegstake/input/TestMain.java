/**
 * TODO: Stan
 * Only for Testing ...
 * Configuration.getConfiguration(this.getClass()).getInt("FingerSensitivity");
 */
package beegstake.input;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import TUIO.*;

public class TestMain {
	private final int		window_width	= 1024; // 800;
	private final int		window_height	= 768;	// 600;
	private boolean			fullscreen		= true;

	private TouchHandler	touchHander;
	private JFrame			frame;
	private GraphicsDevice	device;

	public TestMain() {
		System.out.println("public TestMain()");

		touchHander = null;//new TouchHandler();
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setupWindow();
		showWindow();
	}

	public void machEinSyso(String text) {
		System.out.println(text);
	}

	public TuioListener getTuioListener() {
		System.out.println("public TuioListener getTuioListener()");
		return touchHander;
	}

	public void setupWindow() {
		System.out.println("public void setupWindow()");
		
		frame = new JFrame();
		//frame.add(touchHander);

		frame.setTitle("TuioDemo - Modified by SMauser");
		frame.setResizable(false);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				} else if (evt.getKeyCode() == KeyEvent.VK_F1) {
					destroyWindow();
					setupWindow();
					fullscreen = !fullscreen;
					showWindow();
				} else if (evt.getKeyCode() == KeyEvent.VK_V) {
					touchHander.verbose = !touchHander.verbose;
				}
			}
		});
	}

	public void destroyWindow() {
		frame.setVisible(false);
		if (fullscreen) {
			device.setFullScreenWindow(null);
		}
		frame = null;
	}

	public void showWindow() {
		System.out.println("public void showWindow()");
		
		if (fullscreen) {
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			touchHander.setSize(width, height);

			frame.setSize(width, height);
			frame.setUndecorated(true);
			device.setFullScreenWindow(frame);
		} else {
			int width = window_width;
			int height = window_height;
			touchHander.setSize(width, height);

			frame.pack();
			Insets insets = frame.getInsets();
			frame.setSize(width, height + insets.top);
		}

		frame.setVisible(true);
		frame.repaint();

	}

	public static void main(String[] args) {
		System.out.println("Start Main");
		TestMain touchHander = new TestMain();
		TuioClient client = null;

		System.out.println("Main => nach Objekterstellung");

		switch (args.length) { 	// Optional kann in den args Übergabewerten der
								// Port geändert werden
			case 1:
				try {
					client = new TuioClient(Integer.parseInt(args[0]));
				} catch (Exception e) {
					System.out.println("usage: java TuioDemo [port]");
					System.exit(0);
				}
				break;
			case 0:
				client = new TuioClient();
				break;
			default:
				System.out.println("usage: java TuioDemo [port]");
				System.exit(0);
				break;
		}

		if (client != null) {
			client.addTuioListener(touchHander.getTuioListener());
			client.connect();
		} else {
			System.out.println("usage: java TuioDemo [port]");
			System.exit(0);
		}
	}

}
