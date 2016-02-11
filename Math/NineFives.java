package Math;
/*Combining nine 5s with any number of the operators +, -, *, /, (, ), what is the smallest positive integer that cannot be expressed?

Hints:

1)The answer isn't zero. You can express zero like this:

(5 - 5) * (5 + 5 + 5 + 5 + 5 + 5 + 5)

Also, zero isn't a positive integer.

2) The answer isn't one. You can express one like this:

5 - (5 * 5 - 5)/5 + 5 - 5 + 5 – 5 */

import java.util.HashMap;
	
class NineFives {
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
			i++;
		}
		
		System.out.println(i); // 63
	}
}
