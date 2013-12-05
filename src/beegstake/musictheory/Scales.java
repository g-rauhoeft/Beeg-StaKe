package beegstake.musictheory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class Scales {
	private HashMap<String, ArrayList<Integer>> spacings;
	
	public Scales(JSONArray scales){
		spacings = new HashMap<String, ArrayList<Integer>>();
		for(int i = 0; i<scales.length(); i++){
			JSONObject object = scales.getJSONObject(i);
			String name = object.getString("name");
			JSONArray spacingJSON = object.getJSONArray("spacings");
			ArrayList<Integer> spacingArray = new ArrayList<Integer>();
			for(int j = 0; j<spacingJSON.length(); j++){
				spacingArray.add(spacingJSON.getInt(j));
			}
			spacings.put(name, spacingArray);
		}
	}
	
	public ArrayList<Integer> getSpacings(String scale){
		return spacings.get(scale);
	}
	
	public Set<String> getScales(){
		return spacings.keySet();
	}
}
