package beegstake.gl.gui.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileHandler {
	public static String read(String filename) {
		StringBuilder builder = new StringBuilder();
		try {
			FileInputStream inputStream = new FileInputStream(filename);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader
					.readLine()) {
				builder.append(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			return null;
		}
		return builder.toString();
	}
}
