package beegstake.test;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import beegstake.audio.SoundEngine;
import beegstake.gl.gui.BeegStakeGUI;
import beegstake.gl.gui.CursorEvent;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.ICursorListener;
import beegstake.gl.gui.ImageButton;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.system.Configuration;

public class GLGUITEST {
	public void start() {
		try {
			Display.setDisplayMode(DisplayUtilities.getMaxDisplayMode());
			Display.setFullscreen(false);
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		GUI gui = new GUI();
		ImageButton imgButton = new ImageButton(new Point(250, 250),"rsc/img/test.png");
		imgButton.add(new ICursorListener(){
			private Point enterPosition;
			@Override
			public void cursorOver(CursorEvent event) {
			}
			@Override
			public void cursorOut(CursorEvent event) {
			}
			@Override
			public void cursorMoved(CursorEvent event) {
			}
		});
		ImageButton imgButton1 = new ImageButton(new Point(500, 250),"rsc/img/test.png");
		imgButton1.add(new ICursorListener(){
			@Override
			public void cursorOver(CursorEvent event) {
			}
			@Override
			public void cursorOut(CursorEvent event) {
			}
			@Override
			public void cursorMoved(CursorEvent event) {
			}
		});
		gui.add(imgButton);
		gui.add(imgButton1);
	
		while (!Display.isCloseRequested()) {
			Display.update();
			gui.injectCursorPosition(new Point(Mouse.getX(), Display.getHeight()-Mouse.getY()), 0);
			gui.render();
		}
		Display.destroy();
	}

	public static void main(String[] args) {
//		new GLGUITEST().start();
		BeegStakeGUI test = new BeegStakeGUI("BeegStake");
		test.start();
		System.out.println(Display.getHeight());
		System.out.println(Display.getY());

	}
}
