package beegstake.test;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import beegstake.audio.SoundEngine;
import beegstake.gl.gui.BeegStakeGUI;
import beegstake.gl.gui.BumpButton;
import beegstake.gl.gui.CursorEvent;
import beegstake.gl.gui.GUI;
import beegstake.gl.gui.ICursorListener;
import beegstake.gl.gui.ImageButton;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.system.Configuration;

public class GLGUITEST0 {
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
		final SoundEngine s = new SoundEngine();
		s.getAvailableInstruments().get(2).activate();
		ImageButton imgButton = new ImageButton(new Point(250, 250),
				"rsc/img/test.png");
		imgButton.add(new ICursorListener() {
			private Point enterPosition;

			@Override
			public void cursorOver(CursorEvent event) {
				enterPosition = event.getRelativePosition();
				float percentage = enterPosition.getY()
						/ (float) event.getComponent().getHeight();
				((ImageButton) event.getComponent()).setTexture(ResourceManager
						.getTexture("rsc/img/testo.png"));
				s.getAvailableInstruments().get(2)
						.playSound(65, (int) (127 * percentage));
			}

			@Override
			public void cursorOut(CursorEvent event) {
				((ImageButton) event.getComponent()).setTexture(ResourceManager
						.getTexture("rsc/img/test.png"));
				s.getAvailableInstruments().get(2).stopSound(65);
			}

			@Override
			public void cursorMoved(CursorEvent event) {
				Point p = event.getRelativePosition();
				float distanceFromEntered = (float) Math.sqrt((Math.pow(
						p.getX() - enterPosition.getX(), 2) + Math.pow(p.getY()
						- enterPosition.getY(), 2)));
				float percentage = distanceFromEntered
						/ event.getComponent().getWidth();
				s.getAvailableInstruments().get(2)
						.changeModulation(65, (int) (percentage * 127));
			}
		});
		BumpButton imgButton1 = new BumpButton(new Point(702, 250),
				"rsc/img/key_normal.png", "rsc/img/key_diffuse_black.png");
		imgButton1.add(new ICursorListener() {
			@Override
			public void cursorOver(CursorEvent event) {
				((BumpButton) event.getComponent())
						.setDiffuseMap(ResourceManager
								.getTexture("rsc/img/key_diffuse_blue.png"));
				s.getAvailableInstruments().get(2).playSound(70, 100);
			}

			@Override
			public void cursorOut(CursorEvent event) {
				((BumpButton) event.getComponent())
						.setDiffuseMap(ResourceManager
								.getTexture("rsc/img/key_diffuse_black.png"));
				s.getAvailableInstruments().get(2).stopSound(70);
			}

			@Override
			public void cursorMoved(CursorEvent event) {
			}
		});
		BumpButton imgButton2 = new BumpButton(new Point(750, 250),
				"rsc/img/key_normal.png",
				"rsc/img/key_diffuse_white.png");
		imgButton2.add(new ICursorListener() {
			@Override
			public void cursorOver(CursorEvent event) {
				s.getAvailableInstruments().get(2).playSound(75, 100);
			}

			@Override
			public void cursorOut(CursorEvent event) {
				s.getAvailableInstruments().get(2).stopSound(75);
			}

			@Override
			public void cursorMoved(CursorEvent event) {
				// TODO Auto-generated method stub

			}
		});
		gui.add(imgButton);
		gui.add(imgButton1);
		gui.add(imgButton2);
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
		new GLGUITEST0().start();
		// new BeegStakeGUI("Beeg Stake").start();
	}
}
