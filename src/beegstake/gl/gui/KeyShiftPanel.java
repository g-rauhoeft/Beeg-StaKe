package beegstake.gl.gui;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.SimpleButtonLocations;
import beegstake.gl.gui.util.SoundEngineHelper;

public class KeyShiftPanel extends Panel {
	private boolean up;
	private SoundEngineHelper helper;
	private BumpButton octaveShift;
	private BumpButton keyShift;

	private class ShiftOctave implements ICursorListener {
		private boolean up;

		public ShiftOctave(boolean up) {
			this.up = up;
		}

		@Override
		public void cursorOver(CursorEvent event) {
			helper.setOctave(helper.getOctave() + (up ? 1 : -1));
		}

		@Override
		public void cursorOut(CursorEvent event) {
		}

		@Override
		public void cursorMoved(CursorEvent event) {
		}
	}

	private class ShiftKey implements ICursorListener {
		private boolean up;

		public ShiftKey(boolean up) {
			this.up = up;
		}

		@Override
		public void cursorOver(CursorEvent event) {
			helper.setKey(helper.getKey() + (up ? 1 : -1));
		}

		@Override
		public void cursorOut(CursorEvent event) {

		}

		@Override
		public void cursorMoved(CursorEvent event) {

		}
	}

	public KeyShiftPanel(Point position, int width, int height,
			final SoundEngineHelper helper, SimpleButtonLocations locations,
			boolean upShift, boolean up) {
		super(position, width, height);
		this.helper = helper;
		this.setPosition(new Point(this.getPosition().getX(), this
				.getPosition().getY() - this.getHeight() / 2));
		this.octaveShift = new BumpButton(new Point(), width / 2, height,
				locations.getNormalLocation(), locations.getDiffuseLocation());
		this.keyShift = new BumpButton(new Point(width / 2, 0), width / 2,
				height, locations.getNormalLocation(),
				locations.getDiffuseLocation());
		Label octaveShiftLabel = new Label(new Point(), "O"
				+ (upShift ? "+" : "-") + "1", up);
		Label keyShiftLabel = new Label(new Point(), "K"
				+ (upShift ? "+" : "-") + "1", up);
		this.centerLabel(octaveShiftLabel, octaveShift);
		this.centerLabel(keyShiftLabel, keyShift);
		this.octaveShift.addCursorListener(new ShiftOctave(upShift));
		this.keyShift.addCursorListener(new ShiftKey(upShift));
		this.addComponent(octaveShift);
		this.addComponent(keyShift);
		this.addComponent(octaveShiftLabel);
		this.addComponent(keyShiftLabel);
	}

	public void centerLabel(Label label, BumpButton button) {
		int halfWidth = button.width / 2;
		int halfHeight = button.height / 2;
		int halfLabelHeight = label.getHeight() / 2;
		int halfLabelWidth = label.getWidth() / 2;
		label.setPosition(new Point(button.position.getX() + halfWidth
				- halfLabelWidth, button.position.getY() + halfHeight
				- halfLabelHeight));
	}
}
