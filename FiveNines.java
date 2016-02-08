import java.util.HashMap;

class FiveNines {
	static void buildMap(int n, int k, HashMap<String, Float> map){
		HashMap<String, Float> tmp = new HashMap<String, Float>();
		for(String m : map.keySet()){
			Float value = map.get(m); 
			tmp.put("(" + m + "+5)", value + 5);
			tmp.put("(" + m + "-5)", value - 5);
			if(value != 0){
				tmp.put( "(" + m+ "/5)", value / 5);
			}
			tmp.put(m + "*5", value * 5);
		}
		if(n+1 == k){
			map.clear();//we no longer need in-between resuts
			map.putAll(tmp);
			return;
		}
		map.putAll(tmp);
		buildMap(n+1, k, map);
	}
	
	static String[] minPosInt(HashMap<String, Float> map){
		String[] res = new String[map.size()]; 

		for(String m : map.keySet()){
			Float value = map.get(m); 
			if(value >= 0 && value % 1 < 0.0000001 && value < res.length){
				res[Math.round(value)] = m;
			}
		}
		return res;
	}
	
	public static void main(String args[]){
		int n = 9;
		HashMap<String, Float> map = new HashMap<String, Float>();
		map.put("(5+5)", (float) 5+5);
		map.put("(5-5)", (float) 5-5);
		map.put("(5/5)", (float) 5/5);
		map.put("5*5", (float) 5*5);
		buildMap(2, n, map);

		
		String[] res = minPosInt(map);		
		int i = 0;
		while (i < res.length){
			if(res[i]==null){
				break;
			}
			//System.out.println(i + " " + res[i]);
			i++;
		}
		
		System.out.println(i);
	}
}
