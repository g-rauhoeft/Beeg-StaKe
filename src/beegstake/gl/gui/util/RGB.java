package beegstake.gl.gui.util;

public class RGB {
	private float r, g, b;

	public float getR() {
		return r;
	}

	private float clamp(float f) {
		return Math.max(0, Math.min(1, f));
	}

	public void setR(float r) {
		this.r = clamp(r);
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = clamp(g);
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = clamp(b);
	}

	public RGB(float r, float g, float b) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}
}
