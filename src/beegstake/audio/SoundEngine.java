package beegstake.audio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import beegstake.system.Configuration;

public class SoundEngine implements ISoundEngine {

	public SoundEngine() {

	}

	public boolean loadInstruments() {
		
		return true;
	}
	
	public String[] getListOfInstruments(){
		File folder = new File(Configuration.getConfiguration(this.getClass())
				.getString("SoundFontFolder"));
		String[] list = folder.list(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.endsWith(".sf");
			}
		});
		return list;
	}

	@Override
	public ArrayList<IInstrument> getAvailableInstruments() {
		// TODO Auto-generated method stub
		return null;
	}
}
