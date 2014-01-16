package beegstake.input;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import beegstake.gl.gui.util.Point;

public class PointerLocations {
	private static ConcurrentHashMap<Integer, Point> pointerLocations = new ConcurrentHashMap<Integer, Point>();
	
	public static Collection<Point> getPoints(){
		return pointerLocations.values();
	}
	
	public static void addPoint(int id, Point p){
		pointerLocations.put(id, p);
	}
	
	public static void removePoint(int id){
		pointerLocations.remove(id);
	}
	
	public static int size(){
		return pointerLocations.size();
	}
	
	public static Point getAveragePoint(){
		Point point = new Point();
		for(Point p : getPoints()){
			point = point.add(p);
		}
		return point.divide(size());
	}
}
