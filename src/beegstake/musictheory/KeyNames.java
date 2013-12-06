package beegstake.musictheory;


public class KeyNames {
	private static final String[] names = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};

	public static final String getName(final int key){
		return names[key%names.length];
	}
}
