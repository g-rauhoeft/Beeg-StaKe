package beegstake.gl.gui.font;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Font {
	private HashMap<Integer, Character> characters;
	private FontInformation fontInformation;
	private JSONObject rawObject;
	private String path;
	private String fontFileName;
	private BufferedImage fontMap;
	private int base, lineHeight, spaceWidth;
	
	public Font(String path, String filename) {
		this.path = path;
		this.rawObject = this.load(path+File.separator+filename);
		try {
			this.parse(rawObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Character getCharacter(int id){
		return characters.get(id);
	}
	
	private void parse(JSONObject object) throws IOException{
		this.characters = new HashMap<Integer, Character>();
		this.fontInformation = new FontInformation(object.getJSONObject("info"));
		JSONArray characterArray = object.getJSONObject("chars").getJSONArray("char");
		for(int i = 0; i<characterArray.length(); i++){
			JSONObject character = characterArray.getJSONObject(i);
			this.characters.put(character.getInt("id"), new Character(character));
		}
		this.fontFileName = object.getJSONArray("pages").getJSONObject(0).getString("file");
		this.fontMap = ImageIO.read(new File(this.path+File.separator+this.fontFileName));
		JSONObject commonInformation = object.getJSONObject("common");
		this.base = commonInformation.getInt("base");
		this.lineHeight = commonInformation.getInt("lineHeight");
		this.spaceWidth = this.characters.get((int)'i').getWidth();
	}
	
	public int getBase() {
		return base;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	private JSONObject load(String path){
		JSONObject fontFile = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			JSONTokener tokener = new JSONTokener(reader);
			fontFile = new JSONObject(tokener);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fontFile;
	}

	public final FontInformation getFontInformation() {
		return fontInformation;
	}

	public BufferedImage getFontMap() {
		return fontMap;
	}

	public int getSpaceWidth() {
		return spaceWidth;
	}
}
