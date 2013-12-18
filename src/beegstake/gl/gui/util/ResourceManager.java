package beegstake.gl.gui.util;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
	private static HashMap<String, Texture>	textures	= new HashMap<String, Texture>();
	private static HashMap<String, Shader>	shaders		= new HashMap<String, Shader>();

	public static Texture getTexture(String path) {
		if (!textures.containsKey(path)) {
			Texture texture;
			try {
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
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
			fragCode ="uniform sampler2D colourMap; uniform sampler2D normalMap; uniform vec3 mouse; void main() { vec3 normal = normalize(texture2D(normalMap, gl_TexCoord[0].st).rgb * 2.0 - 1.0); vec3 lightPosition = normalize(mouse-gl_FragCoord.xyz); float diffuse = max(dot(normal, lightPosition), 0); gl_FragColor = vec4(diffuse * texture2D(colourMap, gl_TexCoord[0].st).rgb, 1.0); }"; //FileHandler.read(path + ".frag");
			vertCode = "void main() { gl_TexCoord[0] = gl_MultiTexCoord0; gl_Position = ftransform(); }"; //FileHandler.read(path + ".vert");
			shaders.put(path, new Shader(fragCode, vertCode));
		}
		return shaders.get(path);
	}
}
