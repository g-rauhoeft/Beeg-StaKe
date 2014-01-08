package beegstake.gl.gui.font;

import org.json.JSONObject;

public class Character {
	public int id;
	public int x, y;
	public int width, height;
	public int xOffset, yOffset;
	public int xAdvance;
	
	public Character(JSONObject object){
		this.setId(object.getInt("id"));
		this.setX(object.getInt("x"));
		this.setY(object.getInt("y"));
		this.setWidth(object.getInt("width"));
		this.setHeight(object.getInt("height"));
		this.setxOffset(object.getInt("xoffset"));
		this.setyOffset(object.getInt("yoffset"));
		this.setxAdvance(object.getInt("xadvance"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xoffset) {
		this.xOffset = xoffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yoffset) {
		this.yOffset = yoffset;
	}

	public int getxAdvance() {
		return xAdvance;
	}

	public void setxAdvance(int xAdvance) {
		this.xAdvance = xAdvance;
	}
	
	public String getString(){
		return String.valueOf((char)id);
	}
}
