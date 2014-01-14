package beegstake.gl.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;

public class GUI extends Panel {

	public GUI() {
		super(new Point(0, 0), Display.getWidth(), Display.getHeight());
	}

	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		for (GUIComponent component : components) {
			DisplayUtilities.setOrtho();
			component.render();
		}
	}
}
