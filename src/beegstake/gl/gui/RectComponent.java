package beegstake.gl.gui;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.GUIComponent;

public abstract class RectComponent extends GUIComponent {
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

	public int getWidth() {
		return width;
	}
	
	public void setProportionalHeight(int height){
		float proportion = (float)height / this.height;
		this.height = height;
		this.width = (int)(proportion*width);
		System.out.println("Width "+width+" Height "+height );
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setProportionalWidth(int width){
		float proportion = (float)width / this.width;
		this.width = width;
		this.height = (int)(proportion*height);
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

}
