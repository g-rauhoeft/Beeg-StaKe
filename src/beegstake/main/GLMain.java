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
import beegstake.input.GLTUIOHandler.TUIOData;
import beegstake.input.PointerLocations;
import beegstake.main.gui.GUICreator;
import beegstake.system.Configuration;
import beegstake.system.util.Time;

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
		GLTUIOHandler handler = new GLTUIOHandler(gui, Display.getWidth(),
				Display.getHeight());
		client.addTuioListener(handler);
		client.connect();
		Time time = new Time();
		while (!Display.isCloseRequested()
				&& !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.update();
			int delta = time.getDelta();
			PointerLocations.update(delta);
			Point mousePosition = new Point(Mouse.getX(), Mouse.getY());
			gui.injectCursorPosition(new Point(Mouse.getX(), Display.getHeight()-Mouse.getY()),-1);
			PointerLocations.addPoint(-1, mousePosition);
			for (TUIOData data = handler.getAddedData().poll(); data != null; data = handler
					.getAddedData().poll()) {
				gui.injectCursorPosition(data.getPosition(), data.getId());
			}
			for (TUIOData data = handler.getRemovedData().poll(); data != null; data = handler
					.getRemovedData().poll()) {
				gui.removeCursor(data.getPosition(), data.getId());
			}
			gui.render();
		}
		client.disconnect();
		Display.destroy();
	}

	public static void main(String[] args) {
		new GLMain().start();
	}
}
