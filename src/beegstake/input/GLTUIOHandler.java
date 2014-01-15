package beegstake.input;

import beegstake.gl.gui.Panel;
import beegstake.gl.gui.util.Point;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;

public class GLTUIOHandler implements TuioListener {
	private Panel panel;

	public GLTUIOHandler(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void addTuioCursor(TuioCursor arg0) {
		TuioPoint tp = arg0.getPosition();
		Point p = new Point((int) tp.getX(), (int) tp.getY());
		panel.injectCursorPosition(p, arg0.getCursorID());
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
		TuioPoint tp = arg0.getPosition();
		Point p = new Point((int) tp.getX(), (int) tp.getY());
		panel.removeCursor(p, arg0.getCursorID());
	}

	@Override
	public void removeTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTuioCursor(TuioCursor arg0) {
		TuioPoint tp = arg0.getPosition();
		Point p = new Point((int) tp.getX(), (int) tp.getY());
		panel.injectCursorPosition(p, arg0.getCursorID());
	}

	@Override
	public void updateTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub

	}
}
