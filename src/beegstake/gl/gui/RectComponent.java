package beegstake.gl.gui;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.GUIComponent;

public class RectComponent extends GUIComponent {
	public RectComponent(Point position, int width, int height) {
		super(position, width, height);
	}

	@Override
	public boolean within(Point point) {
		return point.getX() > this.position.getX()
				&& point.getX() < this.position.getX() + this.getWidth()
				&& point.getY() > this.position.getY()
				&& point.getY() < this.position.getY() + this.getHeight();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

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

}
