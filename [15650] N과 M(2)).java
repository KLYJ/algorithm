import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//N과 M (2) - 오름차순 수열
public class BJ_15650 {
	
	static int arr[];	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input[] = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i] = i+1;
		
		perm(0, new int[M], new boolean[N]);
	}
	
	private static void perm(int toSelect, int selected[], boolean visited[]) {
		if(toSelect==M) {
			int flag = 0;
			for(int i=0;i<M-1;i++) {
				if(selected[i] > selected[i+1])
					flag=1;
			}
			if(flag==0) {
				for(int i:selected)
					System.out.print(i+" ");
				System.out.println();
			}
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				selected[toSelect] = arr[i];
				visited[i] = true;
				perm(toSelect+1, selected, visited);
				visited[i] = false;
			}
		}
	}

}
