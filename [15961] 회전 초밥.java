import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int sushi[] = new int[N+k-1];
		for(int i=0;i<N;i++) {
			sushi[i] = Integer.parseInt(in.readLine());
		}
		for(int i=N;i<N+k-1;i++) {
			sushi[i] = sushi[i-N];
		}
		
		int visited[] = new int[d+1];
		
		int max = 0;
		for(int i=0;i<k;i++) {
			if(visited[sushi[i]] == 0) {
				max++;
			}
			visited[sushi[i]]++;
		}
		System.out.println(max);
		
		int temp = max;
		for(int i=1;i<N;i++) {
			if(visited[sushi[i-1]] == 1)
				temp--;
			visited[sushi[i-1]]--;
			
			if(visited[sushi[i+k-1]] == 0) {
					temp++;
			}
			visited[sushi[i+k-1]]++;
			
			if(visited[c] == 0) {
				max = Math.max(max, temp+1);
				System.out.println(temp+1);
			}
			else {
				max = Math.max(max, temp);
				System.out.println(temp);
			}
			
		}
		
		System.out.println(max);
		
	}

}
