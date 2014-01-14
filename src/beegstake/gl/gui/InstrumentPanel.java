package beegstake.gl.gui;

import beegstake.gl.gui.util.InstrumentPanelImageLocations;
import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.SoundEngineHelper;

public class InstrumentPanel extends Panel {
	private InstrumentPanelImageLocations imageLocations;
	private BumpButton background;
	private BumpButton rightButton;
	private BumpButton leftButton;
	private Label label;
	private SoundEngineHelper helper;

	private class Increment implements ICursorListener {
		@Override
		public void cursorOver(CursorEvent event) {
			helper.setActiveInstrument(helper.getActiveInstrumentId() + 1);
			setLabelText(helper.getActiveInstrument().getInformation()
					.getName());
		}

		@Override
		public void cursorOut(CursorEvent event) {
		}

		@Override
		public void cursorMoved(CursorEvent event) {
		}
	};

	private class Decrement implements ICursorListener {
		@Override
		public void cursorOver(CursorEvent event) {
			helper.setActiveInstrument(helper.getActiveInstrumentId() - 1);
			setLabelText(helper.getActiveInstrument().getInformation()
					.getName());
		}

		@Override
		public void cursorOut(CursorEvent event) {
		}

		@Override
		public void cursorMoved(CursorEvent event) {
		}
	};

	public InstrumentPanel(Point center, int height,
			final SoundEngineHelper helper,
			InstrumentPanelImageLocations locations, boolean up) {
		super(center, 0, height);
		this.helper = helper;
		this.imageLocations = locations;
		this.background = new BumpButton(new Point(0, 0),
				this.imageLocations.getBackgroundNormal(),
				this.imageLocations.getBackground());
		this.background.setProportionalHeight(height);
		this.setWidth(background.width);
		this.rightButton = new BumpButton(new Point(0, 0),
				this.imageLocations.getRightButtonNormal(),
				this.imageLocations.getRightButton());
		this.leftButton = new BumpButton(new Point(0, 0),
				this.imageLocations.getLeftButtonNormal(),
				this.imageLocations.getLeftButton());
		this.rightButton.setProportionalHeight((int) (this.height * 0.75));
		this.leftButton.setProportionalHeight((int) (this.height * 0.75));
		this.leftButton.setPosition(new Point(0, this.height / 2
				- leftButton.height / 2));
		this.rightButton.setPosition(new Point(this.width - rightButton.width,
				this.height / 2 - leftButton.height / 2));
		this.setPosition(new Point(this.getPosition().getX() - this.width / 2,
				this.getPosition().getY() - this.getHeight() / 2));
		if (up) {
			leftButton.addCursorListener(new Increment());
			rightButton.addCursorListener(new Decrement());
		} else {
			leftButton.addCursorListener(new Decrement());
			rightButton.addCursorListener(new Increment());
		}
		this.label = new Label(new Point(0, 0), "Init", up);
		this.addComponent(background);
		this.addComponent(rightButton);
		this.addComponent(leftButton);
		this.addComponent(label);
		this.setLabelText(helper.getActiveInstrument().getInformation()
				.getName());
	}

	public void setLabelText(String text) {
		this.label.setText(text);
		int halfWidth = this.background.width / 2;
		while(label.getWidth()>=halfWidth-10){
			label.setText(label.getText().substring(0, label.getText().length()-1));
		}
		int halfHeight = this.background.height / 2;
		int halfLabelHeight = this.label.getHeight() / 2;
		int halfLabelWidth = this.label.getWidth() / 2;
		this.label.setPosition(new Point(this.position.getX() + halfWidth
				- halfLabelWidth, this.position.getY() + halfHeight
				- halfLabelHeight));
	}
}
