package beegstake.gl.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;


public class ImageButton extends RectComponent {
	private Texture texture;

	public ImageButton(Point position, int width, int height) {
		super(position, width, height);
	}
	
	public ImageButton(Point position, String path){
		this(position, 0, 0);
		this.texture = ResourceManager.getTexture(path);
		this.width = texture.getTextureWidth();
		this.height = texture.getTextureHeight();
	}

	@Override
	public void render() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2i(position.getX(), position.getY());
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2i(position.getX() + width, position.getY());
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2i(position.getX() + width, position.getY() + height);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2i(position.getX(), position.getY() + height);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
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

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
