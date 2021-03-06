package beegstake.gl.gui.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import beegstake.gl.gui.font.Font;

public class ResourceManager {
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static HashMap<String, Shader> shaders = new HashMap<String, Shader>();
	private static HashMap<String, Font> fonts = new HashMap<String, Font>();

	public static Texture getTexture(String path) {
		if (!textures.containsKey(path)) {
			Texture texture;
			try {
				texture = TextureLoader.getTexture("PNG",
						ResourceLoader.getResourceAsStream(path));
				textures.put(path, texture);
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return null;
			}
		}
		return textures.get(path);
	}

	public static Shader getShader(String path) {
		if (!shaders.containsKey(path)) {
			String fragCode, vertCode;
			fragCode = FileHandler.read(path + ".frag");
			vertCode = FileHandler.read(path + ".vert");
			shaders.put(path, new Shader(fragCode, vertCode));
		}
		return shaders.get(path);
	}

	public static Font getFont(String path, String filename) {
		if (!fonts.containsKey(path)) {
			fonts.put(path, new Font(path, filename));
		}
		return fonts.get(path);
	}
}
