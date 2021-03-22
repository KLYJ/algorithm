package Level2;

public class 타겟넘버 {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,1,1,1,1}, 3));
	}
	
	static int num[], T, answer = 0;
	
	public static int solution(int[] numbers, int target) {
		num = numbers;
		T = target;
		
		dfs(0, 0);
		
        return answer;
    }

	private static void dfs(int cnt, int sum) {
		if(cnt==num.length) {
			if(sum==T) {
				answer++;
			}
			return;
		}
		
		dfs(cnt+1, sum+num[cnt]);
		dfs(cnt+1, sum-num[cnt]);
	}
	
}
