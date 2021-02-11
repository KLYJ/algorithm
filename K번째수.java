import java.util.Arrays;

public class K번째수 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1, 5, 2, 6, 3, 7, 4}, new int[][] {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})));
	}
	
	public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0;i<commands.length;i++) {
        	int start_idx = commands[i][0]-1;
        	int end_idx = commands[i][1];
        	int idx = commands[i][2];
        	
        	int[] arr = Arrays.copyOfRange(array, start_idx, end_idx);
        	Arrays.sort(arr);
        	answer[i] = arr[idx-1];
        }
        
        
        return answer;
    }

}
