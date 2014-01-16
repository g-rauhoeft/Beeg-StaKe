package beegstake.input;

import java.awt.Container;
import java.util.Hashtable;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

//import swt.events.TouchEvent;

public class TouchHandler implements TuioListener {

	private Hashtable<Long, TuioCursor> cursorList = new Hashtable<Long, TuioCursor>();
	private int width, height;
	private Container container;

	public TouchHandler(Container container, int width, int height) {
		this.container = container;
		this.width = width;
		this.height = height;
	}

	@Override
	public void addTuioCursor(TuioCursor tcur) {
		if (!cursorList.containsKey(tcur.getSessionID())) {
			cursorList.put(tcur.getSessionID(), tcur);
		}
	}

	@Override
	public void addTuioObject(TuioObject tobj) {

	}

	@Override
	public void refresh(TuioTime frameTime) {

	}

	@Override
	public void removeTuioCursor(TuioCursor tcur) {
		cursorList.remove(tcur.getSessionID());
		MouseSimulator.mouseReleased(tcur.getScreenX(width),
				tcur.getScreenY(height), tcur.getCursorID(), container);
	}

	@Override
	public void removeTuioObject(TuioObject tobj) {

	}

	@Override
	public void updateTuioCursor(TuioCursor tcur) {
		MouseSimulator.mouseMoved(tcur.getScreenX(width),
				tcur.getScreenY(height), tcur.getCursorID(), container);
	}

	@Override
	public void updateTuioObject(TuioObject tobj) {

	}
}