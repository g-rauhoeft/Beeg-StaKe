package beegstake.main.gui;

import org.lwjgl.opengl.Display;

import beegstake.gl.gui.BumpButton;
import beegstake.gl.gui.GLKeyButton;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.InstrumentPanel;
import beegstake.gl.gui.KeyShiftPanel;
import beegstake.gl.gui.Panel;
import beegstake.gl.gui.util.InstrumentPanelImageLocations;
import beegstake.gl.gui.util.KeyImageLocations;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineHelper;

public class GUICreator {
	public static final KeyImageLocations bottomLocations = new KeyImageLocations(
			"/rsc/img/key_diffuse_black.png", "/rsc/img/key_diffuse_white.png",
			"/rsc/img/key_diffuse_blue.png", "/rsc/img/key_normal.png");
	public static final KeyImageLocations topLocations = new KeyImageLocations(
			"/rsc/img/key_diffuse_black_top.png",
			"/rsc/img/key_diffuse_white_top.png",
			"/rsc/img/key_diffuse_blue_top.png", "/rsc/img/key_normal_top.png");
	public static final InstrumentPanelImageLocations instrumentPanelLocations = new InstrumentPanelImageLocations(
			"rsc/img/instrument_panel_diffuse.png",
			"rsc/img/instrument_panel_normal.png",
			"rsc/img/button_left_diffuse.png",
			"rsc/img/button_right_diffuse.png",
			"rsc/img/button_left_normal.png", "rsc/img/button_right_normal.png");
	public static int buttonWidth;
	public static int buttonHeight;

	public static void init() {
		buttonWidth = Display.getWidth() / 20;
		buttonHeight = Display.getHeight() / 3;
	}

	public static void createGUI(GUI gui, SoundEngineHelper helperTop,
			SoundEngineHelper helperBottom) {
		GUICreator.init();
		GUICreator.createTopPanel(gui, helperTop);
		GUICreator.createBottomPanel(gui, helperBottom);
		GUICreator.createTopCenterPanel(gui, helperTop);
		GUICreator.createBottomCenterPanel(gui, helperBottom);
	}

	public static void createBottomPanel(GUI g, SoundEngineHelper helper) {
		Panel bottomPanel = new Panel(new Point(0, Display.getHeight()
				- buttonHeight), Display.getWidth(), buttonHeight);
		g.addComponent(bottomPanel);
		for (int i = 0; i < 20; i++) {
			bottomPanel.addComponent(new GLKeyButton(new Point(i * buttonWidth,
					0), buttonWidth, buttonHeight, helper, i, bottomLocations,
					true));
		}
	}

	public static void createTopPanel(GUI g, SoundEngineHelper helper) {
		Panel topPanel = new Panel(new Point(0, 0), Display.getWidth(),
				buttonHeight);
		g.addComponent(topPanel);
		for (int i = 0; i < 20; i++) {
			topPanel.addComponent(new GLKeyButton(new Point(Display.getWidth()
					- (i + 1) * buttonWidth, 0), buttonWidth, buttonHeight,
					helper, i, topLocations, false));
		}
	}

	public static void createTopCenterPanel(GUI g, SoundEngineHelper helper) {
		int height = buttonHeight / 2;
		Panel panel = new Panel(new Point(0, buttonHeight), Display.getWidth(),
				height);
		BumpButton background = new BumpButton(new Point(0, 0),
				Display.getWidth(), height,
				"rsc/img/center_panel_top_normal.png",
				"rsc/img/center_panel_top_diffuse.png");
		panel.addComponent(background);
		InstrumentPanel ip = new InstrumentPanel(new Point(panel.getWidth()/2,panel.getHeight()/2),(int) (panel.getHeight()*0.75), helper, instrumentPanelLocations, true);
		panel.addComponent(ip);
		g.addComponent(panel);
	}

	public static void createBottomCenterPanel(GUI g, SoundEngineHelper helper) {
		int height = buttonHeight / 2;
		Panel panel = new Panel(new Point(0, buttonHeight + height),
				Display.getWidth(), height);
		BumpButton background = new BumpButton(new Point(0, 0),
				Display.getWidth(), height,
				"rsc/img/center_panel_bottom_normal.png",
				"rsc/img/center_panel_bottom_diffuse.png");
		panel.addComponent(background);
		InstrumentPanel ip = new InstrumentPanel(new Point(panel.getWidth()/2,panel.getHeight()/2),(int) (panel.getHeight()*0.75), helper, instrumentPanelLocations, false);
		panel.addComponent(ip);
	//	KeyShiftPanel ksp = new KeyShiftPanel(new Point(0,panel.getHeight()/2), (int) (panel.getHeight()*0.75), helper, null, false);
		g.addComponent(panel);
	}
}
