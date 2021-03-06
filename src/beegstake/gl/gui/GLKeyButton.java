package beegstake.gl.gui;

import beegstake.gl.gui.util.KeyImageLocations;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.gl.gui.util.SoundEngineHelper;

public class GLKeyButton extends BumpButton implements ICursorListener {
	private KeyImageLocations keyImageLocations;
	private SoundEngineHelper helper;
	private int key;
	private Point enterPosition;
	private boolean up;
	private boolean over;

	public GLKeyButton(Point position, int width, int height,
			SoundEngineHelper helper, int key,
			KeyImageLocations keyImageLocations, boolean up) {
		super(position, width, height,
				keyImageLocations.getNormalMapLocation(), helper
						.isKeyBlack(key) ? keyImageLocations
						.getBlackKeyLocation() : keyImageLocations
						.getWhiteKeyLocation());
		this.helper = helper;
		this.key = key;
		this.keyImageLocations = keyImageLocations;
		this.up = up;
		this.over = false;
		this.addCursorListener(this);
	}

	@Override
	public void cursorOver(CursorEvent event) {
		enterPosition = event.getRelativePosition();
		float percentage = enterPosition.getY()
				/ (float) event.getComponent().getHeight();
		this.over = true;
		this.helper.getActiveInstrument()
				.playSound(
						helper.getLowestKey() + key,
						!up ? (int) (127 * percentage)
								: 127 - (int) (127 * percentage));
	}

	@Override
	public void cursorOut(CursorEvent event) {
		this.over = false;
		this.helper.getActiveInstrument()
				.stopSound(helper.getLowestKey() + key);
	}

	@Override
	public void cursorMoved(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		if (over) {
			this.setDiffuseMap(ResourceManager.getTexture(keyImageLocations
					.getPressedKeyLocation()));
		} else {
			if (helper.isKeyBlack(key)) {
				this.setDiffuseMap(ResourceManager.getTexture(keyImageLocations
						.getBlackKeyLocation()));
			} else {
				this.setDiffuseMap(ResourceManager.getTexture(keyImageLocations
						.getWhiteKeyLocation()));
			}
		}
		super.render();
	}
}
