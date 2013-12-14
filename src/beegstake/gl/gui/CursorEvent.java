package beegstake.gl.gui;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.GUIComponent;

public class CursorEvent {
	private int id;
	private GUIComponent component;
	private Point position;

	public int getId() {
		return id;
	}

	public GUIComponent getComponent() {
		return component;
	}

	public Point getPosition() {
		return position;
	}

	public Point getRelativePosition() {
		return component.getRelativePosition(position);
	}

	public CursorEvent(int id, GUIComponent component, Point position) {
		super();
		this.id = id;
		this.component = component;
		this.position = position;
	}
}
