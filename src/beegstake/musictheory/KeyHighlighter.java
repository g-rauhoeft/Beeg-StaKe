package beegstake.musictheory;

import java.util.ArrayList;

public class KeyHighlighter {
	public static boolean isBlackKey(int key, int base, ArrayList<Integer> spacings) {
		key = key % 12;
		int spacingSum = 0;
		for(Integer i : spacings){
			if(spacingSum == key){
				return false;
			}
			if(spacingSum > key){
				break;
			}
			spacingSum += i;
		}
		return true;
	}
}
