package beegstake.input;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Greg Rauhoeft 
 * 		A class to simulate mouse behavior in awt on
 *      multi-touch tables
 */
public class MouseSimulator {

	/**
	 * A Map containing all of the Components in the "pressed" state. This is so
	 * "released" events can be fired upon them.
	 */
	private static HashMap<Integer, Set<Component>> pressedMap = new HashMap<Integer, Set<Component>>();

	/**
	 * A Map containing all of the Components with a cursor hovering above them.
	 */
	private static HashMap<Integer, Set<Component>> hoveredMap = new HashMap<Integer, Set<Component>>();

	protected interface IActionHandler {
		/**
		 * A common interface for actions to be performed to simulate a mouse.
		 * 
		 * @param x
		 *            The x-coordinate of the simulated mouse pointer
		 * @param y
		 *            The y-coordinate of the simulated mouse pointer
		 * @param id
		 *            The ID of the simulated mouse
		 * @param component
		 *            The component to perform the action upon
		 */
		public void performAction(int x, int y, int id, Component component);
	}

	/**
	 * The action to be performed for the clicked event. Notifies all
	 * MouseListeners of a component that a click occurred.
	 */
	private static IActionHandler clicked = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			for (MouseListener listener : component.getMouseListeners()) {
				listener.mouseClicked(new MouseEvent(component,
						MouseEvent.MOUSE_PRESSED, 1l, 0, x, y, 1, false));
			}
		}
	};

	/**
	 * The action to be performed for the pressed event. Notifies all
	 * MouseListeners of a component that the mouse has been pressed. It also
	 * adds the component along with the id of the mouse to the Map of
	 * components in the pressed state.
	 */
	private static IActionHandler pressed = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			if (pressedMap.get(id) == null) {
				pressedMap.put(id, new HashSet<Component>());
			}
			boolean newComponent = pressedMap.get(id).add(component);
			if (newComponent) {
				for (MouseListener listener : component.getMouseListeners()) {
					listener.mouseEntered(new MouseEvent(component,
							MouseEvent.MOUSE_ENTERED, 1l, 0, x, y, 1, false));
				}
			}
			for (MouseListener listener : component.getMouseListeners()) {
				listener.mousePressed(new MouseEvent(component,
						MouseEvent.MOUSE_PRESSED, 1l, 0, x, y, 1, false));
			}
		}
	};

	private static IActionHandler released = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			for (Component pressedComponent : pressedMap.get(id)) {
				Point p = getRelativeCoordinates(x, y, component,
						pressedComponent);
				int localX = p.x, localY = p.y;
				for (MouseListener listener : pressedComponent
						.getMouseListeners()) {
					listener.mouseReleased(new MouseEvent(component,
							MouseEvent.MOUSE_RELEASED, 1l, 0, localX, localY,
							1, false));
				}
			}
			pressedMap.remove(id);
		}
	};

	private static IActionHandler moved = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			if (pressedMap.containsKey(id)) {
				LinkedList<Component> componentsToRemove = new LinkedList<Component>();
				for (Component pressedComponent : pressedMap.get(id)) {
					Point p = getRelativeCoordinates(x, y, component,
							pressedComponent);
					if (!pressedComponent.contains(p)) {
						// Exited
						for (MouseListener listener : pressedComponent
								.getMouseListeners()) {
							listener.mouseExited(new MouseEvent(pressedComponent,
									MouseEvent.MOUSE_EXITED, 1l, 0, p.x, p.y,
									1, false));
							componentsToRemove.add(pressedComponent);
						}
					} else {
						for (MouseMotionListener listener : pressedComponent
								.getMouseMotionListeners()) {
							listener.mouseMoved(new MouseEvent(component,
									MouseEvent.MOUSE_MOVED, 1l, 0, p.x, p.y, 1,
									false));
						}
					}
				}
				for (Component toRemove : componentsToRemove) {
					pressedMap.get(id).remove(toRemove);
				}
			}
		}
	};

	public static void actionHandler(int x, int y, int id, Container container,
			IActionHandler handler) {
		if (container.contains(x, y)) {
			x -= container.getX();
			y -= container.getY();
			for (Component component : container.getComponents()) {
				int adjustedX = x - component.getX(), adjustedY = y
						- component.getY();
				if (component.contains(adjustedX, adjustedY)) {
					if (component instanceof Container) {
						actionHandler(adjustedX, adjustedY, id,
								(Container) component, handler);
					} else {
						handler.performAction(adjustedX, adjustedY, id,
								component);
					}
				}
			}
			handler.performAction(x, y, id, container);
		}
	}

	public static void mouseMoved(int x, int y, int id, Container container) {
		moved.performAction(x, y, id, container);
		mousePressed(x, y, id, container);
	}

	public static void mouseClicked(int x, int y, Container container) {
		actionHandler(x, y, -1, container, clicked);
	}

	public static void mousePressed(int x, int y, int id, Container container) {
		actionHandler(x, y, id, container, pressed);
	}

	public static void mouseReleased(int x, int y, int id, Container container) {
		released.performAction(x, y, id, container);
	}

	public static Point getRelativeCoordinates(int x, int y,
			Component container, Component component) {
		int localX = x, localY = y;
		for (Component c = component; c != container; c = c.getParent()) {
			localX -= c.getX();
			localY -= c.getY();
		}
		return new Point(localX, localY);
	}
}
