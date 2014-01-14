package beegstake.gl.gui.util;

public class KeyImageLocations {
	public String blackKeyLocation, whiteKeyLocation,
	pressedKeyLocation, normalMapLocation;

	public String getBlackKeyLocation() {
		return blackKeyLocation;
	}

	public void setBlackKeyLocation(String blackKeyLocation) {
		this.blackKeyLocation = blackKeyLocation;
	}

	public String getWhiteKeyLocation() {
		return whiteKeyLocation;
	}

	public void setWhiteKeyLocation(String whiteKeyLocation) {
		this.whiteKeyLocation = whiteKeyLocation;
	}

	public String getPressedKeyLocation() {
		return pressedKeyLocation;
	}

	public void setPressedKeyLocation(String pressedKeyLocation) {
		this.pressedKeyLocation = pressedKeyLocation;
	}

	public String getNormalMapLocation() {
		return normalMapLocation;
	}

	public void setNormalMapLocation(String normalMapLocation) {
		this.normalMapLocation = normalMapLocation;
	}

	public KeyImageLocations(String blackKeyLocation, String whiteKeyLocation,
			String pressedKeyLocation, String normalMapLocation) {
		super();
		this.blackKeyLocation = blackKeyLocation;
		this.whiteKeyLocation = whiteKeyLocation;
		this.pressedKeyLocation = pressedKeyLocation;
		this.normalMapLocation = normalMapLocation;
	}
}
