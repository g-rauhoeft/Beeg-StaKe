package beegstake.gl.gui.util;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();

	public static Texture getTexture(String path) {
		if (!textures.containsKey(path)) {
			Texture texture;
			try {
				if(path.endsWith(".png")){
					texture = TextureLoader.getTexture("PNG",
							ResourceLoader.getResourceAsStream(path));
				}else{
					texture = TextureLoader.getTexture("JPG",
							ResourceLoader.getResourceAsStream(path));
				}
				textures.put(path, texture);
			} catch (IOException e) {
				return null;
			}
		}
		return textures.get(path);
	}
}
