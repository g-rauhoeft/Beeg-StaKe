package beegstake.gl.gui.util;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public Point() {
		this.setX(0);
		this.setY(0);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point add(Point point) {
		return new Point(this.getX() + point.getX(), this.getY() + point.getY());
	}

	public Point substract(Point point) {
		return new Point(this.getX() - point.getX(), this.getY() - point.getY());
	}

	public Point divide(float divisor) {
		return new Point((int) (this.getX() / divisor),
				(int) (this.getY() / divisor));
	}

	public Point divide(Point divisor) {
		return new Point((int) (this.getX() / divisor.getX()),
				(int) (this.getY() / divisor.getY()));
	}

	public Point multiply(float multiplicator) {
		return new Point((int) (this.getX() * multiplicator),
				(int) (this.getY() * multiplicator));
	}

	public Point multiply(Point multiplicator) {
		return new Point((int) (this.getX() * multiplicator.getX()),
				(int) (this.getY() * multiplicator.getY()));
	}

	@Override
	public boolean equals(Object o){
		return o instanceof Point && ((Point)o).getX() == this.getX() &&((Point)o).getY() == this.getY();
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
