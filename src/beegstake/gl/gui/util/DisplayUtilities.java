package beegstake.gl.gui.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayUtilities {
	public static DisplayMode getMaxDisplayMode(){
		DisplayMode displayMode = Display.getDisplayMode();
		try {
			for(DisplayMode mode : Display.getAvailableDisplayModes()){
				if(mode.isFullscreenCapable() && mode.getWidth()>displayMode.getWidth()){
					displayMode = mode;
				}
			}
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		System.out.println(displayMode);
		return displayMode;
	} 
	
	public static void setOrtho(){
		int width = Display.getWidth(), height = Display.getHeight();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
	}
}
