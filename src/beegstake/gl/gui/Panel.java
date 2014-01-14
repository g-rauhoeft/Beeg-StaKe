package beegstake.gl.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;

public class Panel extends RectComponent implements IRenderable {
	protected LinkedList<GUIComponent> components;
	private HashMap<GUIComponent, HashSet<Integer>> activeCursors;

	public Panel(Point position, int width, int height) {
		super(position, width, height);
		this.components = new LinkedList<GUIComponent>();
		this.activeCursors = new HashMap<GUIComponent, HashSet<Integer>>();
	}

	public void addComponent(GUIComponent component) {
		component.setPosition(component.getPosition().add(this.getPosition()));
		this.components.add(component);
	}

	public void removeComponent(GUIComponent component) {
		boolean removed = this.components.remove(component);
		if (removed)
			component.getPosition().substract(this.getPosition());
	}

	@Override
	public void setPosition(Point position) {
		if (components != null) {
			for (GUIComponent component : components) {
				component.setPosition(component.getPosition().add(
						position.substract(this.position)));
			}
		}
		this.position = position;
	}

	public void injectCursorPosition(Point position, int id) {
		for (GUIComponent component : components) {
			if (component instanceof Panel) {
				((Panel) component).injectCursorPosition(position, id);
			}
			if (component.within(position)) {
				if (!activeCursors.containsKey(component)) {
					activeCursors.put(component, new HashSet<Integer>());
					component.notifyCursorOver(new CursorEvent(id, component,
							position));
				} else {
					component.notifyCursorMoved(new CursorEvent(id, component,
							position));
				}
				activeCursors.get(component).add(id);
			} else {
				if (activeCursors.containsKey(component)) {
					activeCursors.get(component).remove(id);
					if (activeCursors.get(component).size() == 0) {
						activeCursors.remove(component);
						component.notifyCursorOut(new CursorEvent(id,
								component, position));
					}
				}
			}
		}
	}

	public void removeCursor(Point position, int id) {
		for (GUIComponent component : activeCursors.keySet()) {
			if (activeCursors.get(component).contains(id)) {
				activeCursors.get(component).remove(id);
				if (activeCursors.get(component).size() == 0) {
					component.notifyCursorOut(new CursorEvent(id, component,
							position));
				}
			}
		}
	}

	@Override
	public void render() {
		for (GUIComponent component : components) {
			DisplayUtilities.setOrtho();
			component.render();
		}
	}

}
