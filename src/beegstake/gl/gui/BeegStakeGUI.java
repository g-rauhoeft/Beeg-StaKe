package beegstake.gl.gui;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import beegstake.gl.gui.util.DisplayUtilities;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.TextPosition;
import beegstake.musictheory.KeyNames;
import beegstake.system.Configuration;


public class BeegStakeGUI extends GUI {
	
	public BeegStakeGUI(String name){
		super();
		Display.setTitle(name);
	}
	
	public void start(){
		try {
			Display.setDisplayMode(DisplayUtilities.getMaxDisplayMode());
			Display.setFullscreen(false);
			Display.create();
		} catch (Exception e) {
			System.exit(0);
		}
		Configuration.load("cfg/system.json");
		
		base();
		
		while (!Display.isCloseRequested()) {
			Display.update();
			this.injectCursorPosition(new Point(Mouse.getX(), Display.getHeight()-Mouse.getY()), 0);
			this.render();
		}
		Display.destroy();
	}

	
	public ArrayList<KeyButton> generateKeyButtons(boolean top){
		ArrayList<KeyButton> buttons = new ArrayList<KeyButton>();
		KeyButton button;
		for (int i=0; i<20; i++){
			if(top){				
				button = new KeyButton(new Point(0+i*60, Display.getY()),152,302, KeyNames.getNameRevert(i));
			}else{
				//Position ergbt sich durch Displayhöhe abzüglich der Button höhe
				button = new KeyButton(new Point(0+i*60, Display.getHeight()-302),152,302, KeyNames.getName(i));
			}
			
			if(button.getButtonText().endsWith("#")){
				button.setTexture(ResourceManager.getTexture("rsc/img/tasteDunkel.png"));
			}else{
				button.setTexture(ResourceManager.getTexture("rsc/img/tasteHell.png"));
			}
			buttons.add(button);
		}
		return buttons;
	} 
	
	public void base(){
		ArrayList<KeyButton> generateButtonsTop = generateKeyButtons(true);
		for (KeyButton b: generateButtonsTop){
			this.add(b);
		}
		
		ArrayList<KeyButton> generateButtonsBottom = generateKeyButtons(false);
		for (KeyButton b: generateButtonsBottom){
			this.add(b);
		}
	}
}
