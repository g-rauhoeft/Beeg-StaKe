package beegstake.input;

import java.awt.*;
import java.util.*;
import java.awt.event.MouseEvent;
//import swt.events.TouchEvent;

import java.lang.Object;

import javax.swing.*;

import beegstake.gui.ITouchInterface;
import beegstake.gui.KeyButton;
import TUIO.*;

public class TouchHandler extends JComponent implements TuioListener {
	private Hashtable<Long, TObject>	objectList					= new Hashtable<Long, TObject>();
	private Hashtable<Long, TuioCursor>	cursorList					= new Hashtable<Long, TuioCursor>();

	public static final int				finger_size					= 15;
	public static final int				object_size					= 60;
	public static final int				table_size					= 760;

	public static int					width, height;
	private float						scale						= 1.0f;
	public boolean						verbose						= false;

	private static final boolean		DEBUG_AND_EVALUATION_MODE	= true;
	private static final boolean		DEBUG_WITH_SYSO				= true;

	private ITouchInterface				touchInterface;
	
//	 private TouchPoint _touchPoint;
	 
//	 private Touch touch;

	// **********************************************************************
	// **********************************************************************
	public void setSize(int w, int h) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void setSize(int w, int h) ");
		}

		super.setSize(w, h);
		width = w;
		height = h;
		scale = height / (float) TouchHandler.table_size;
	}

	@Override
	public void addTuioCursor(TuioCursor tcur) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void addTuioCursor(TuioCursor tcur)");
		}

		if (!cursorList.containsKey(tcur.getSessionID())) {
			cursorList.put(tcur.getSessionID(), tcur);
			repaint();
		}

		if (verbose) {
			System.out.println("add cur " + tcur.getCursorID() + " (" + tcur.getSessionID() + ") " + tcur.getX() + " "
					+ tcur.getY());
		}
	}

	@Override
	public void addTuioObject(TuioObject tobj) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("addTuioObject --> public void addTuioObject(TuioObject tobj) ");
		}

		TObject demo = new TObject(tobj);
		objectList.put(tobj.getSessionID(), demo);

		if (verbose) {
			System.out.println("add obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " "
					+ tobj.getY() + " " + tobj.getAngle());
		}
	}

	@Override
	public void refresh(TuioTime frameTime) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void refresh(TuioTime frameTime)");
		}

		repaint();
	}

	@Override
	public void removeTuioCursor(TuioCursor tcur) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void removeTuioCursor(TuioCursor tcur)");
		}

		cursorList.remove(tcur.getSessionID());
		repaint();

		if (verbose) {
			System.out.println("del cur " + tcur.getCursorID() + " (" + tcur.getSessionID() + ")");
		}
	}

	@Override
	public void removeTuioObject(TuioObject tobj) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void removeTuioObject(TuioObject tobj)");
		}

		objectList.remove(tobj.getSessionID());

		if (verbose) {
			System.out.println("del obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ")");
		}
	}

	@Override
	public void updateTuioCursor(TuioCursor tcur) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void updateTuioCursor(TuioCursor tcur)");
		}

		repaint();

		if (verbose) {
			System.out.println("set cur " + tcur.getCursorID() + " ("
					+ tcur.getSessionID() + ") " + tcur.getX() + " "
					+ tcur.getY() + " " + tcur.getMotionSpeed() + " "
					+ tcur.getMotionAccel());
		}
	}

	@Override
	public void updateTuioObject(TuioObject tobj) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void updateTuioCursor(TuioCursor tcur)");
		}

		TObject demo = (TObject) objectList.get(tobj.getSessionID());
		demo.update(tobj);

		if (verbose) {
			System.out.println("set obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " "
					+ tobj.getY() + " " + tobj.getAngle() + " " + tobj.getMotionSpeed() + " " + tobj.getRotationSpeed()
					+ " " + tobj.getMotionAccel() + " " + tobj.getRotationAccel());
		}
	}

	public void paint(Graphics g) {
		if (DEBUG_WITH_SYSO) {
			System.out.println("public void updateTuioCursor(TuioCursor tcur)");
		}

		update(g);
	}
	
	public Hashtable<Long, TuioCursor> getCursorList(){
		return this.cursorList;
	}

	// TODO Hier das Interface von BeKe aufrufen und die Koordinaten übergeben
	public void update(Graphics finger) {
		Graphics2D fingerObject2D = (Graphics2D) finger;
		fingerObject2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		fingerObject2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		fingerObject2D.setColor(Color.white);
		fingerObject2D.fillRect(0, 0, width, height);

		int w = (int) Math.round(width - scale * finger_size / 2.0f);
		int h = (int) Math.round(height - scale * finger_size / 2.0f);

		Enumeration<TuioCursor> cursors = cursorList.elements();
		while (cursors.hasMoreElements()) {
			TuioCursor tcur = cursors.nextElement();
			if (tcur == null) {
				continue;
			}
			Vector<TuioPoint> path = tcur.getPath();
			TuioPoint current_point = path.elementAt(0);
			if (current_point != null) {
				// draw the cursor path
				fingerObject2D.setPaint(Color.blue);
				for (int i = 0; i < path.size(); i++) {
					TuioPoint next_point = path.elementAt(i);
					fingerObject2D.drawLine(current_point.getScreenX(w), current_point.getScreenY(h),
							next_point.getScreenX(w), next_point.getScreenY(h));
					current_point = next_point;
				}
			}

			// draw the finger tip
			fingerObject2D.setPaint(Color.red); // lightGray
			int s = (int) (scale * finger_size);
			fingerObject2D.fillOval(current_point.getScreenX(w - s / 2), current_point.getScreenY(h - s / 2), s, s);
			fingerObject2D.setPaint(Color.black);

			if (DEBUG_AND_EVALUATION_MODE) {
				fingerObject2D.drawString(tcur.getCursorID() + "   |    X: " + current_point.getScreenX(w) + "   Y: "
						+ current_point.getScreenY(h), current_point.getScreenX(w), current_point.getScreenY(h));
			} else {
				fingerObject2D.drawString(tcur.getCursorID() + " ", current_point.getScreenX(w),
						current_point.getScreenY(h));
			}
		}

		// draw the objects
		Enumeration<TObject> objects = objectList.elements();
		while (objects.hasMoreElements()) {
			TObject tobj = objects.nextElement();
			if (tobj != null) {
				tobj.paint(fingerObject2D, width, height);
			}
		}
	}
}