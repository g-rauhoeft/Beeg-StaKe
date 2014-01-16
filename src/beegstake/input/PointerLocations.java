package beegstake.input;

import java.util.Collection;
import java.util.HashMap;

import beegstake.gl.gui.util.Point;

public class PointerLocations {
	private static HashMap<Integer, Point> pointerLocations = new HashMap<Integer, Point>();
	
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
}
