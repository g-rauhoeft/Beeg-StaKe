package beegstake.musictheory;


public class KeyNames {
	private static final String[] names = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
	private static final String[] namesRevert = {"H","A#","A","G#","G","F#","F","E","D#","D","C#","C"};

	public static final String getName(final int key){
		return names[key%names.length];
	}
	
	public static final String getNameRevert(final int key){
		return namesRevert[key%namesRevert.length];
	}
}
