package beegstake.gl.gui;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;

import beegstake.gl.gui.util.Point;
import beegstake.gl.gui.util.ResourceManager;
import beegstake.input.PointerLocations;

public class BumpButton extends RectComponent{
	private Texture normalMap, diffuseMap;
	private int shader;
	private Point mousePosition;
	
	public BumpButton(Point position, int width, int height,
			String normalMapLocation, String diffuseMapLocation) {
		super(position, width, height);
		normalMap = ResourceManager.getTexture(normalMapLocation);
		diffuseMap = ResourceManager.getTexture(diffuseMapLocation);
		shader = ResourceManager.getShader("rsc/sdr/NormalMap").getProgramId();
		mousePosition = new Point(0,0);
	}

	public BumpButton(Point position, String normalMapLocation,
			String diffuseMapLocation) {
		this(position, 0, 0, normalMapLocation, diffuseMapLocation);
		this.setWidth(diffuseMap.getImageWidth());
		this.setHeight(diffuseMap.getImageHeight());
	}

	@Override
	public void render() {
		mousePosition = new Point(Mouse.getX(), Mouse.getY());
		int diffuseloc = GL20.glGetUniformLocation(shader, "colourMap");
		int normalloc = GL20.glGetUniformLocation(shader, "normalMap");
		int mouseloc = GL20.glGetUniformLocation(shader, "mouse");
		ARBShaderObjects.glUseProgramObjectARB(shader);
		GL20.glUniform1i(diffuseloc, 0);
		GL20.glUniform1i(normalloc, 1);
		Point pos = PointerLocations.getCurrentPosition();
		GL20.glUniform3f(mouseloc, pos.getX(),pos.getY(),200);
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, diffuseMap.getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + 1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, normalMap.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2i(position.getX(), position.getY());
		GL11.glTexCoord2f(diffuseMap.getWidth(), 0);
		GL11.glVertex2i(position.getX() + width, position.getY());
		GL11.glTexCoord2f(diffuseMap.getWidth(), diffuseMap.getHeight());
		GL11.glVertex2i(position.getX() + width, position.getY() + height);
		GL11.glTexCoord2f(0, diffuseMap.getHeight());
		GL11.glVertex2i(position.getX(), position.getY() + height);
		GL11.glEnd();
		ARBShaderObjects.glUseProgramObjectARB(0);
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + 1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public Texture getNormalMap() {
		return normalMap;
	}

	public void setNormalMap(Texture normalMap) {
		this.normalMap = normalMap;
	}

	public Texture getDiffuseMap() {
		return diffuseMap;
	}

	public void setDiffuseMap(Texture diffuseMap) {
		this.diffuseMap = diffuseMap;
	}
}
