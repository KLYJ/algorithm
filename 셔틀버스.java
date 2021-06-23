import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Time implements Comparable<Time>{
		int hour, min;

		public Time(int hour, int min) {
			super();
			this.hour = hour;
			this.min = min;
		}

		@Override
		public int compareTo(Time o) {
			return this.hour==o.hour? this.min-o.min : this.hour-o.hour;
		}		
	}
	
	public String solution(int n, int t, int m, String[] timetable) {
		int bus[][] = new int[n][2];
		bus[0] = new int[] {9, 0};
		
		int term_hour = t==60?1:0;
		int term_min = t%60;
		
		for(int i=1;i<=n-1;i++) {
			int hour = bus[i-1][0]+term_hour;
			int min = bus[i-1][1]+term_min;
			
			if(min >= 60) {
				hour += 1;
				min -= 60;
			}
			
			bus[i][0] = hour;
			bus[i][1] = min;
		}
		
		Time[] times = new Time[timetable.length];
		for(int i=0;i<timetable.length;i++) {
			String time[] = timetable[i].split(":");
			times[i] = new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
		}
		Arrays.sort(times);
		
		Queue<Time> dq = new LinkedList<>();
		for(int i=0;i<times.length;i++)
			dq.add(times[i]);
		
		// 앞 시간대 탈 수 있는 사람들 다 빼기
		for(int i=0;i<n-1;i++) {
			int ride = m;
			int arrive_hour = bus[i][0];
			int arrive_min = bus[i][1];
			
			while(ride > 0) {
				int hour = dq.peek().hour;
				int min = dq.peek().min;
				if(arrive_hour > hour || (arrive_hour == hour && arrive_min >= min)) 
					dq.poll();
				else
					break;
				
				ride--;
			}
		}
		
		// 마지막 버스에 탈 수 있는지 체크
		int remain = dq.size();
		int last_hour = bus[n-1][0];
		int last_min = bus[n-1][1];
		if(remain >= m) {
			for(int i=0;i<m;i++) {
				int hour = dq.peek().hour;
				int min = dq.peek().min;
				if(last_hour > hour || (last_hour == hour && last_min >= min)) {
					if(i==m-1) {
						last_hour = hour;
						last_min = min-1;
					}
					dq.poll();
				}
			}
			
			if(last_min == -1) {
				last_hour -= 1;
				last_min = 59;
			}
		}
		
		// 출력 포맷 맞추기
		String ans_hour = last_hour<10?"0"+String.valueOf(last_hour):String.valueOf(last_hour);
		String ans_min = last_min<10?"0"+String.valueOf(last_min):String.valueOf(last_min);
		
        String answer = ans_hour+":"+ans_min;
        return answer;
    }
}
