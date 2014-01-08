package beegstake.gl.gui.font;

import org.json.JSONObject;

public class FontInformation {
	private String face;
	private int size;
	private boolean bold, italic, smooth, aa, outline;

	public FontInformation(JSONObject object) {
		this.setFace(object.getString("face"));
		this.setSize(object.getInt("size"));
		this.setBold(object.getInt("bold") != 0);
		this.setItalic(object.getInt("italic") != 0);
		this.setSmooth(object.getInt("smooth") != 0);
		this.setAa(object.getInt("aa") != 0);
		this.setOutline(object.getInt("outline") != 0);
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public boolean isSmooth() {
		return smooth;
	}

	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
	}

	public boolean isAa() {
		return aa;
	}

	public void setAa(boolean aa) {
		this.aa = aa;
	}

	public boolean isOutline() {
		return outline;
	}

	public void setOutline(boolean outline) {
		this.outline = outline;
	}
}
