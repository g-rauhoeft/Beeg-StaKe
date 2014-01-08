package beegstake.gl.gui.font;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import beegstake.gl.gui.IRenderable;
import beegstake.gl.gui.util.Point;

public class FontRenderer implements IRenderable {

	private String text;
	private Font font;
	private int textWidth;
	private BufferedImage image;
	private int textureId;
	private Point position;
	private boolean flipped;

	public FontRenderer(String text, Font font, Point position, boolean flipped) {
		this.textureId = GL11.glGenTextures();
		this.setFont(font);
		this.setText(text);
		this.setPosition(position);
		this.setFlipped(flipped);
	}

	@Override
	public void render() {
		if (flipped) {
			GL11.glTranslatef(position.getX() + image.getWidth() / 2,
					position.getY() + image.getHeight() / 2, 0);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glTranslatef(-(position.getX() + image.getWidth() / 2),
					-(position.getY() + image.getHeight() / 2), 0);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2i(position.getX(), position.getY());
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2i(position.getX() + image.getWidth(), position.getY());
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2i(position.getX() + image.getWidth(), position.getY()
				+ image.getHeight());
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2i(position.getX(), position.getY() + image.getHeight());
		GL11.glEnd();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	public int calculateTextWidth(String text) {
		int width = 0;
		char[] characters = text.toCharArray();
		for (char c : characters) {
			if (c == ' ') {
				width += font.getSpaceWidth();
			} else {
				Character character = font.getCharacter(c);
				width += character.getWidth();
			}
		}
		return width;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.textWidth = calculateTextWidth(text);
		this.generateImage();
		this.generateTexture();
	}

	private void generateImage() {
		BufferedImage image = new BufferedImage(textWidth,
				font.getLineHeight(), font.getFontMap().getType());
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		char[] characters = text.toCharArray();
		int currentX = 0;
		for (char c : characters) {
			if (c == ' ') {
				currentX += font.getSpaceWidth();
			} else {
				Character ch = font.getCharacter(c);
				BufferedImage characterImage = font.getFontMap().getSubimage(
						ch.x, ch.y, ch.width, ch.height);
				graphics.drawImage(characterImage, currentX, ch.yOffset, null);
				currentX += ch.width;
			}
		}
		this.image = image;
	}

	private void generateTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureId);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
				GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
				GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
				image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, this.convert(image));
	}

	public ByteBuffer convert(BufferedImage image) {
		ByteBuffer byteBuffer = null;
		WritableRaster raster = Raster.createInterleavedRaster(
				DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 4,
				null);
		BufferedImage textureImage = new BufferedImage(getGLColorModel(),
				raster, false, new Hashtable<Object, Object>());
		textureImage.getGraphics().drawImage(image, 0, 0, null);
		byte[] data = ((DataBufferByte) textureImage.getRaster()
				.getDataBuffer()).getData();
		byteBuffer = ByteBuffer.allocateDirect(data.length);
		byteBuffer.order(ByteOrder.nativeOrder());
		byteBuffer.put(data);
		byteBuffer.flip();
		return byteBuffer;
	}

	private ColorModel getGLColorModel() {
		return new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB), true, false,
				ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
	}

	public BufferedImage getImage() {
		return image;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}
}
