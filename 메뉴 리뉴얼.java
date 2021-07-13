import java.util.*;

class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static Set<String> answer = new HashSet<>();
	
	public static String[] solution(String[] orders, int[] course) {
        for(int i:course) {
        	for(String order:orders) {
        		comb(i, order, 0, 0, new char[i]);
        	}
        }
        
        ArrayList<String> temp = new ArrayList<>();
        
        for(int i:course) {
        	int max_cnt = 0;
        	ArrayList<String> max_ans = new ArrayList<>();
        	for(String order:map.keySet()) {
        		if(order.length() == i && map.get(order) >= 2) {
        			if(map.get(order) > max_cnt) {
        				max_ans.clear();
        				max_ans.add(order);
        				max_cnt = map.get(order);
        			}
        			else if(map.get(order) == max_cnt)
        				max_ans.add(order);
        		}
        	}
        	temp.addAll(max_ans);
        }
        
        Collections.sort(temp);
        
        String[] ans = new String[temp.size()];
        for(int i=0;i<temp.size();i++)
        	ans[i] = temp.get(i);
        
        return ans;
    }

	private static void comb(int N, String order, int selectTo, int start, char selected[]) {
		if(selectTo == N) {
			char temp[] = selected.clone();
			Arrays.sort(temp);
			String str = new String(temp);
			
			map.put(str, map.getOrDefault(str, 0)+1);
			
			return;
		}
		
		for(int i=start;i<order.length();i++) {
			selected[selectTo] = order.charAt(i);
			comb(N, order, selectTo+1, i+1, selected);
		}
	}
}
