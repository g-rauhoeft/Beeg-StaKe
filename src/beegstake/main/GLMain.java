package beegstake.main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import beegstake.audio.SoundEngine;
import beegstake.gl.gui.GLKeyButton;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class GLMain {
	public void start() {
		try {
			Display.setDisplayMode(DisplayUtilities.getMaxDisplayMode());
			Display.setFullscreen(true);
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		GUI gui = new GUI();
		GLKeyButton.init("/rsc/img/key_diffuse_black.png",
				"/rsc/img/key_diffuse_white.png",
				"/rsc/img/key_diffuse_blue.png", "/rsc/img/key_normal.png");
		SoundEngine soundEngine = new SoundEngine();
		SoundEngineHelper helper = new SoundEngineHelper(4, 0, "Major",
				soundEngine);
		helper.setActiveInstrument(0);
		int buttonWidth = Display.getWidth() / 20;
		int buttonHeight = Display.getHeight() / 3;
		for (int i = 0; i < 20; i++) {
			gui.add(new GLKeyButton(new Point(i * buttonWidth, Display.getHeight()-buttonHeight), buttonWidth,
					buttonHeight, helper, i));
		}
		while (!Display.isCloseRequested()) {
			Display.update();
			gui.injectCursorPosition(
					new Point(Mouse.getX(), Display.getHeight() - Mouse.getY()),
					0);
			gui.render();
		}
		Display.destroy();
	}

	public static void main(String[] args) {
		new GLMain().start();
	}
}
