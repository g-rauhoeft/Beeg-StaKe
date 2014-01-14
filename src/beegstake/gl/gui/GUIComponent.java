package beegstake.gl.gui;

import java.util.LinkedList;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.CursorEvent;
import beegstake.gl.gui.IBounds;
import beegstake.gl.gui.ICursorListener;
import beegstake.gl.gui.IRenderable;

public abstract class GUIComponent implements IBounds, IRenderable {
	private LinkedList<ICursorListener> cursorListeners;

	protected Point position;
	protected int width, height;

	public GUIComponent(Point position, int width, int height) {
		this.setPosition(position);
		this.setWidth(width);
		this.setHeight(height);
		this.cursorListeners = new LinkedList<ICursorListener>();
	}

	public Point getRelativePosition(Point absolutePosition) {
		return new Point(absolutePosition.getX() - position.getX(),
				absolutePosition.getY() - position.getY());
	}
	
	public Point getAbsolutePosition(Point relativePosition) {
		return new Point(relativePosition.getX() + position.getX(),
				relativePosition.getY() - position.getY());
	}

	public void addCursorListener(ICursorListener listener) {
		this.cursorListeners.add(listener);
	}

	public void removeCursorListener(ICursorListener listener) {
		this.cursorListeners.remove(listener);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void notifyCursorOver(CursorEvent event) {
		for (ICursorListener listener : cursorListeners) {
			listener.cursorOver(event);
		}
	}

	public void notifyCursorOut(CursorEvent event) {
		for (ICursorListener listener : cursorListeners) {
			listener.cursorOut(event);
		}
	}

	public void notifyCursorMoved(CursorEvent event) {
		for (ICursorListener listener : cursorListeners) {
			listener.cursorMoved(event);
		}
	}
}
