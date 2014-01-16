package beegstake.input;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.lwjgl.opengl.Display;

import beegstake.gl.gui.Panel;
import beegstake.gl.gui.util.Point;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;

public class GLTUIOHandler implements TuioListener {
	private Panel panel;
	private int width, height;
	private ConcurrentLinkedQueue<TUIOData> addedData;

	private ConcurrentLinkedQueue<TUIOData> removedData;
	
	public class TUIOData {
		private final Point position;
		private final int id;

		public TUIOData(Point position, int id) {
			super();
			this.position = position;
			this.id = id;
		}

		public final Point getPosition() {
			return position;
		}

		public final int getId() {
			return id;
		}
	}

	public GLTUIOHandler(Panel panel, int width, int height) {
		this.panel = panel;
		this.width = width;
		this.height = height;
		addedData = new ConcurrentLinkedQueue<TUIOData>();
		removedData = new ConcurrentLinkedQueue<TUIOData>();
	}
	
	public ConcurrentLinkedQueue<TUIOData> getAddedData() {
		return addedData;
	}

	public ConcurrentLinkedQueue<TUIOData> getRemovedData() {
		return removedData;
	}
	
	@Override
	public void addTuioCursor(TuioCursor arg0) {
		Point p = new Point(arg0.getScreenX(width), Display.getHeight()
				- arg0.getScreenY(height));
		addedData.add(new TUIOData(p, arg0.getCursorID()));
	}

	@Override
	public void addTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TuioTime arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTuioCursor(TuioCursor arg0) {
		Point p = new Point(arg0.getScreenX(width), Display.getHeight()
				- arg0.getScreenY(height));
		removedData.add(new TUIOData(p, arg0.getCursorID()));
	}

	@Override
	public void removeTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTuioCursor(TuioCursor arg0) {
		Point p = new Point(arg0.getScreenX(width), Display.getHeight()
				- arg0.getScreenY(height));
		addedData.add(new TUIOData(p, arg0.getCursorID()));
	}

	@Override
	public void updateTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub

	}
}
