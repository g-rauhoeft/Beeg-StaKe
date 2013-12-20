package beegstake.gl.gui;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineHelper;

public class GLKeyButton extends BumpButton implements ICursorListener {
	public static String blackKeyLocation, whiteKeyLocation,
			pressedKeyLocation, normalMapLocation;

	public static void init(String blackKeyLocation, String whiteKeyLocation,
			String pressedKeyLocation, String normalMapLocation) {
		GLKeyButton.blackKeyLocation = blackKeyLocation;
		GLKeyButton.whiteKeyLocation = whiteKeyLocation;
		GLKeyButton.pressedKeyLocation = pressedKeyLocation;
		GLKeyButton.normalMapLocation = normalMapLocation;
	}

	private SoundEngineHelper helper;
	private int key;

	public GLKeyButton(Point position, int width, int height,
	SoundEngineHelper helper, int key) {
		super(position, width, height, normalMapLocation, helper.isKeyBlack(key)?blackKeyLocation:whiteKeyLocation);
		this.helper = helper;
		this.key = key;
		this.add(this);
	}

	@Override
	public void cursorOver(CursorEvent event) {
		this.setDiffuseMap(ResourceManager.getTexture(pressedKeyLocation));
		this.helper.getActiveInstrument().playSound(helper.getLowestKey()+key, 100);
	}

	@Override
	public void cursorOut(CursorEvent event) {
		if(helper.isKeyBlack(key)){
			this.setDiffuseMap(ResourceManager.getTexture(blackKeyLocation));
		}else{
			this.setDiffuseMap(ResourceManager.getTexture(whiteKeyLocation));
		}
		this.helper.getActiveInstrument().stopSound(helper.getLowestKey()+key);
	}

	@Override
	public void cursorMoved(CursorEvent event) {
		// TODO Auto-generated method stub

	}

}
