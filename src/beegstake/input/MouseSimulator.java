package beegstake.input;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MouseSimulator {

	private static HashMap<Integer, Set<Component>> pressedMap = new HashMap<Integer, Set<Component>>();

	protected interface IActionHandler {
		public void performAction(int x, int y, int id, Component component);
	}

	private static IActionHandler clicked = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			for (MouseListener listener : component.getMouseListeners()) {
				listener.mouseClicked(new MouseEvent(component,
						MouseEvent.MOUSE_PRESSED, 1l, 0, x, y, 1, false));
			}
		}
	};
	private static IActionHandler pressed = new IActionHandler() {
		@Override
		public void performAction(int x, int y, int id, Component component) {
			if(pressedMap.get(id) == null){
				pressedMap.put(id, new HashSet<Component>());
			}
			pressedMap.get(id).add(component);
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
				int localX = x, localY = y;
				for (Component c = pressedComponent; c != component; c = c
						.getParent()) {
					localX = localX - c.getX();
					localY = localY - c.getY();
				}
				for (MouseListener listener : pressedComponent
						.getMouseListeners()) {
					listener.mouseReleased(new MouseEvent(component,
							MouseEvent.MOUSE_RELEASED, 1l, 0, localX, localY,
							1, false));
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

	public static void mouseClicked(int x, int y, Container container) {
		actionHandler(x, y, -1, container, clicked);
	}

	public static void mousePressed(int x, int y, int id, Container container) {
		actionHandler(x, y, id, container, pressed);
	}

	public static void mouseReleased(int x, int y, int id, Container container) {
		released.performAction(x, y, id, container);
	}
}
