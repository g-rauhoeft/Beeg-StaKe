package beegstake.gl.gui.util;

public class InstrumentPanelImageLocations {
	private String background, backgroundNormal,
	leftButton, rightButton, leftButtonNormal, rightButtonNormal;

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBackgroundNormal() {
		return backgroundNormal;
	}

	public void setBackgroundNormal(String backgroundNormal) {
		this.backgroundNormal = backgroundNormal;
	}

	public String getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(String leftButton) {
		this.leftButton = leftButton;
	}

	public String getRightButton() {
		return rightButton;
	}

	public void setRightButton(String rightButton) {
		this.rightButton = rightButton;
	}

	public String getLeftButtonNormal() {
		return leftButtonNormal;
	}

	public void setLeftButtonNormal(String leftButtonNormal) {
		this.leftButtonNormal = leftButtonNormal;
	}

	public String getRightButtonNormal() {
		return rightButtonNormal;
	}

	public void setRightButtonNormal(String rightButtonNormal) {
		this.rightButtonNormal = rightButtonNormal;
	}

	public InstrumentPanelImageLocations(String background,
			String backgroundNormal, String leftButton, String rightButton,
			String leftButtonNormal, String rightButtonNormal) {
		this.background = background;
		this.backgroundNormal = backgroundNormal;
		this.leftButton = leftButton;
		this.rightButton = rightButton;
		this.leftButtonNormal = leftButtonNormal;
		this.rightButtonNormal = rightButtonNormal;
	}
	
}
