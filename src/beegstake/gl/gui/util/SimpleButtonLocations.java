package beegstake.gl.gui.util;

public class SimpleButtonLocations {
	private String normalLocation;
	private String diffuseLocation;

	public SimpleButtonLocations(String normalLocation, String diffuseLocation) {
		this.normalLocation = normalLocation;
		this.diffuseLocation = diffuseLocation;
	}

	public String getNormalLocation() {
		return normalLocation;
	}

	public void setNormalLocation(String normalLocation) {
		this.normalLocation = normalLocation;
	}

	public String getDiffuseLocation() {
		return diffuseLocation;
	}

	public void setDiffuseLocation(String diffuseLocation) {
		this.diffuseLocation = diffuseLocation;
	}
}
