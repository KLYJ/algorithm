import java.util.*;

class Solution { 
    class Pick{
		String gem;
		int location;
		
		public Pick(String gem, int location) {
			super();
			this.gem = gem;
			this.location = location;
		}
	}
	
	public int[] solution(String[] gems) {
		Map<String, Queue<Integer>> map = new HashMap<String, Queue<Integer>>();
		for(int i=0;i<gems.length;i++) {
			if(map.get(gems[i]) != null)
				map.get(gems[i]).offer(i+1);
			else {
				Queue<Integer> q = new LinkedList<>();
				q.offer(i+1);
				map.put(gems[i], q);
			}
		}
		
		PriorityQueue<Pick> pq = new PriorityQueue<>(new Comparator<Pick>() {
			@Override
			public int compare(Pick o1, Pick o2) {
				return o1.location-o2.location;
			}
		});
		int max = 0;
		
		for(String key:map.keySet()) {
			int location = map.get(key).poll();
			max = Math.max(max, location);
			pq.add(new Pick(key, location));
		}
		
		int len = Integer.MAX_VALUE;
		int start = 0;
		int ans_start = 0;
		int ans_end = 0;
		while(true) {
			start = pq.peek().location;
			if(max-start+1 < len) {
				ans_start = start;
				ans_end = max;
				len = max-start+1;
				
				if(len == map.size())
					break;
			}
			
			String next = pq.poll().gem;
			if(map.get(next).isEmpty())
				break;
			
			int location = map.get(next).poll();
			max = Math.max(max, location);
			pq.add(new Pick(next, location));
		}
		
		return new int[] {ans_start, ans_end};
	}
}
