package beegstake.system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Configuration {
	private static JSONObject configuration;

	private Configuration() {
	}

	public static void load(String path) {
		if (configuration == null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				JSONTokener tokener = new JSONTokener(reader);
				configuration = new JSONObject(tokener);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static JSONObject getConfiguration() {
		return configuration;
	}

	public static JSONObject getConfiguration(Class cls) {
		return configuration.getJSONObject(cls.getSimpleName());
	}
	
	public static JSONObject getConfiguration(String key) {
		return configuration.getJSONObject(key);
	}
}
