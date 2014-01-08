package beegstake.main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import beegstake.audio.SoundEngine;
import beegstake.gl.gui.GLKeyButton;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.KeyImageLocations;
import beegstake.gl.gui.Label;
import beegstake.gl.gui.font.Font;
import beegstake.gl.gui.font.FontRenderer;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineHelper;
import beegstake.system.Configuration;

public class GLMain {
	public void start() {
		try {
			Display.setDisplayModeAndFullscreen(DisplayUtilities.getMaxDisplayMode());
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		GUI gui = new GUI();
		KeyImageLocations bottomLocations = new KeyImageLocations(
				"/rsc/img/key_diffuse_black.png",
				"/rsc/img/key_diffuse_white.png",
				"/rsc/img/key_diffuse_blue.png", 
				"/rsc/img/key_normal.png");
		KeyImageLocations topLocations = new KeyImageLocations(
				"/rsc/img/key_diffuse_black_top.png",
				"/rsc/img/key_diffuse_white_top.png",
				"/rsc/img/key_diffuse_blue_top.png",
				"/rsc/img/key_normal_top.png");
		SoundEngine soundEngine = new SoundEngine();
		SoundEngineHelper helperBottom = new SoundEngineHelper(4, 0, "Major",
				soundEngine);
		SoundEngineHelper helperTop = new SoundEngineHelper(5, 0, "Major",
				soundEngine);
		helperBottom.setActiveInstrument(5);
		helperTop.setActiveInstrument(44);
		int buttonWidth = Display.getWidth() / 20;
		int buttonHeight = Display.getHeight() / 3;
		for (int i = 0; i < 20; i++) {
			gui.add(new GLKeyButton(new Point(i * buttonWidth, Display
					.getHeight() - buttonHeight), buttonWidth, buttonHeight,
					helperBottom, i, bottomLocations, true));
			gui.add(new GLKeyButton(new Point(Display.getWidth() - (i + 1)
					* buttonWidth, 0), buttonWidth, buttonHeight, helperTop,
					i, topLocations, false));
		}
//		Font font = ResourceManager.getFont("rsc/fnt/comfortaa","comfortaa.json");
//		FontRenderer fr = new FontRenderer("This is a test!", font, new Point(Display.getWidth()/2, Display.getHeight()/2));
//		File outputfile = new File("saved.png");
//	    try {
//			ImageIO.write(fr.getImage(), "png", outputfile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Label l = new Label(new Point(Display.getWidth()/2, Display.getHeight()/2), "Test", true);
		Label l1 = new Label(new Point(Display.getWidth()/3, Display.getHeight()/2), "Test1", false);
		gui.add(l);
		gui.add(l1);
		while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
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
