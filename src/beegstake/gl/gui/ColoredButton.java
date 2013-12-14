package beegstake.gl.gui;

import org.lwjgl.opengl.GL11;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.RGB;

public class ColoredButton extends RectComponent {
	private int width, height;
	private Point position;
	private RGB color;

	public ColoredButton(Point position, int width, int height, RGB color) {
		super(position, width, height);
		this.setColor(color);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public RGB getColor() {
		return color;
	}

	public void setColor(RGB color) {
		this.color = color;
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

	@Override
	public boolean within(Point point) {
		return point.getX() > this.position.getX()
				&& point.getX() < this.position.getX() + this.getWidth()
				&& point.getY() > this.position.getY()
				&& point.getY() < this.position.getY() + this.getHeight();
	}

	@Override
	public void render() {
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3i(position.getX(), position.getY(), 0);
		GL11.glVertex3i(position.getX() + width, position.getY(), 0);
		GL11.glVertex3i(position.getX() + width, position.getY() + height, 0);
		GL11.glVertex3i(position.getX(), position.getY() + height, 0);
		GL11.glEnd();
	}
}
