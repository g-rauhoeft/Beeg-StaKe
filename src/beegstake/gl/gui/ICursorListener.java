package beegstake.gl.gui;

import beegstake.gl.gui.CursorEvent;


public interface ICursorListener {
	public void cursorOver(CursorEvent event);
	public void cursorOut(CursorEvent event);
	public void cursorMoved(CursorEvent event);
}
