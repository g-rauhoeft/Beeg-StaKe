package beegstake.input;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import beegstake.gl.gui.util.Point;

public class PointerLocations {
	private static ConcurrentHashMap<Integer, Point> pointerLocations = new ConcurrentHashMap<Integer, Point>();
	private static Point speed = new Point();
	private static Point position = new Point();

	public static void update(long delta) {
		if (delta != 0) {
			Point direction = getCurrentPosition().substract(getAveragePoint());
			direction = direction.divide(300f).multiply((float)delta);
			speed = speed.substract(direction).multiply(0.9f);
			position = position.add(speed);
		}
	}

	public static Point getCurrentPosition() {
		return position;
	}

	public static Collection<Point> getPoints() {
		return pointerLocations.values();
	}

	public static void addPoint(int id, Point p) {
		pointerLocations.put(id, p);
	}

	public static void removePoint(int id) {
		pointerLocations.remove(id);
	}

	public static int size() {
		return pointerLocations.size();
	}

	public static Point getAveragePoint() {
		Point point = new Point();
		for (Point p : getPoints()) {
			point = point.add(p);
		}
		return point.divide(size());
	}
}
