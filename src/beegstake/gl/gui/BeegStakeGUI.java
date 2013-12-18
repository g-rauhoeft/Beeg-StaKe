package beegstake.gl.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineController;
import beegstake.musictheory.KeyNames;
import beegstake.system.Configuration;

public class BeegStakeGUI extends GUI {
	private static int panelSize = Display.getHeight() / 3;
	protected final static int START_POS_TOP = Display.getY();
	protected final static int START_POS_CENTER_1 = getPanelSize();
	protected final static int START_POS_CENTER_2 = getPanelSize() / 2;
	protected final static int START_POS_END = Display.getHeight()
			- getPanelSize();
	private SoundEngineController soundEngineController;

	public BeegStakeGUI(String name) {
		super();
		Display.setTitle(name);
	}

	public void start() {
		try {
			Display.setDisplayMode(DisplayUtilities.getMaxDisplayMode());
			Display.setFullscreen(false);
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		this.soundEngineController = new SoundEngineController(3, 0, "Arabic");

		base();

		while (!Display.isCloseRequested()) {
			Display.update();
			this.injectCursorPosition(
					new Point(Mouse.getX(), Display.getHeight() - Mouse.getY()),
					0);
			this.render();
		}
		Display.destroy();
	}

	public ArrayList<KeyButton> generateKeyButtons(boolean top) {
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>();
		KeyButton button;
		for (int i = 0; i < 20; i++) {
			if (top) {
				button = new KeyButton(new Point(0 + i * 60,
						BeegStakeGUI.START_POS_TOP), 90, 253,
						KeyNames.getNameRevert(i));
			} else {
				// Position ergbt sich durch Displayh�he abz�glich der Button
				// h�he
				button = new KeyButton(new Point(0 + i * 60,
						BeegStakeGUI.START_POS_END), 90, 253,
						KeyNames.getName(i));
			}

			if (soundEngineController.isKeyBlack(i)) {
				button.setTexture(ResourceManager
						.getTexture("rsc/img/tasteDunkel.png"));
			} else {
				button.setTexture(ResourceManager
						.getTexture("rsc/img/tasteHell.png"));
			}
			buttons.add(button);
		}
		return buttons;
	}

	public void base() {
		ArrayList<KeyButton> generateButtonsTop = generateKeyButtons(true);
		for (KeyButton b : generateButtonsTop) {
			this.add(b);
		}

		ArrayList<KeyButton> generateButtonsBottom = generateKeyButtons(false);
		for (KeyButton b : generateButtonsBottom) {
			this.add(b);
		}
	}

	public static int getPanelSize() {
		return panelSize;
	}
}
