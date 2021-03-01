import java.util.HashMap;
import java.util.Iterator;

public class Solution {

    public static int solution(String[][] clothes) {
		HashMap<String, Integer> hash = new HashMap<>();
		for(int i=0;i<clothes.length;i++) {
			String kinds = clothes[i][1];
			if(hash.containsKey(kinds))
				hash.put(kinds, hash.get(kinds)+1);
			else
				hash.put(kinds, 2);
		}
		
		int answer = 1;
		Iterator<String> iter = hash.keySet().iterator();
		while(iter.hasNext()) {
			answer *= hash.get(iter.next());
		}
		return answer-1;
    }

}
