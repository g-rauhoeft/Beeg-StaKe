package beegstake.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import TUIO.TuioClient;
import beegstake.audio.SoundEngine;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.input.GLTUIOHandler;
import beegstake.main.gui.GUICreator;
import beegstake.system.Configuration;

public class GLMain {
	public void start() {
		try {
			Display.setDisplayModeAndFullscreen(DisplayUtilities
					.getMaxDisplayMode());
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		GUI gui = new GUI();
		SoundEngine soundEngine = new SoundEngine();
		SoundEngineHelper helperBottom = new SoundEngineHelper(5, 0, "Major",
				soundEngine);
		SoundEngineHelper helperTop = new SoundEngineHelper(5, 0, "Major",
				soundEngine);
		helperBottom.setActiveInstrument(0);
		helperTop.setActiveInstrument(2);
		GUICreator.createGUI(gui, helperTop, helperBottom);
		TuioClient client = new TuioClient();
		GLTUIOHandler handler = new GLTUIOHandler(gui);
		client.addTuioListener(handler);
		client.connect();
		while (!Display.isCloseRequested()
				&& !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
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
