import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1463 {
	
	static int N;
	
	private static int solve(int num, Stack<Integer> arr) {
		if(num>N) {
			return arr.pop();
		}
		
		int t = arr.get(num-1)+1;
		if(num%3==0) {
			t = Math.min(arr.get(num/3)+1, t);
		}
		if(num%2==0) {
			t = Math.min(arr.get(num/2)+1, t);
		}
		arr.add(t);
		
		return solve(num+1, arr);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		if(N==1) {
			System.out.println(0);
			return;
		}
		Stack<Integer> arr = new Stack<Integer>();
		arr.add(0); arr.add(0);
		System.out.println(solve(2, arr));
		
	}

}
