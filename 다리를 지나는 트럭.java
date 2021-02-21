import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        for(int i=0;i<bridge_length-1;i++) {        
        	bridge.offer(0);
        }
        int time = 1;                                                        	
        bridge.offer(truck_weights[0]);     		
        int b_weight = weight-truck_weights[0];      
        int next_truck = 1;                 		
        int truck_num = truck_weights.length; 		
        
        while(b_weight!=weight) {                	
        	b_weight += bridge.poll();              
        	if(next_truck<truck_num && b_weight>=truck_weights[next_truck]) {
        		bridge.offer(truck_weights[next_truck]);
        		b_weight -= truck_weights[next_truck++];
        	}
        	else {            
        		bridge.offer(0);
        	}
        	time++;
        }
             
        return time;
    }
}
