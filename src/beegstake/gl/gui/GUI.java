package beegstake.gl.gui;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;

public class GUI implements IRenderable {
	private LinkedList<GUIComponent> components;
	private HashMap<GUIComponent, HashSet<Integer>> activeCursors;
	public GUI() {
		this.components = new LinkedList<GUIComponent>();
		this.activeCursors = new HashMap<GUIComponent, HashSet<Integer>>();
	}

	public void add(GUIComponent component) {
		components.add(component);
	}

	public void remove(GUIComponent component) {
		components.remove(component);
	}

	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		DisplayUtilities.setOrtho();
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 
		for (GUIComponent component : components) {
			component.render();
		}
	}

	public void injectCursorPosition(Point position, int id) {
		for (GUIComponent component : components) {
			if (component.within(position)) {
				if (!activeCursors.containsKey(component)) {
					activeCursors.put(component, new HashSet<Integer>());
					component.notifyCursorOver(new CursorEvent(id, component, position));
				}else{
					component.notifyCursorMoved(new CursorEvent(id, component, position));
				}
				activeCursors.get(component).add(id);
			} else {
				if (activeCursors.containsKey(component)) {
					activeCursors.get(component).remove(id);
					if(activeCursors.get(component).size() == 0){
						activeCursors.remove(component);
						component.notifyCursorOut(new CursorEvent(id, component, position));
					}
				}
			}
		}
	}

	public void removeCursor(Point position, int id) {
		for(GUIComponent component : activeCursors.keySet()){
			if(activeCursors.get(component).contains(id)){
				activeCursors.get(component).remove(id);
				if(activeCursors.get(component).size() == 0){
					component.notifyCursorOut(new CursorEvent(id, component, position));
				}
			}
		}
	}
}
