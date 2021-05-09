import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class hw0201_bj_2164 {
	
	//방법1 : Queue 이용
	private static int solve(Queue<Integer> cards) {
		if(cards.size()==1) return cards.poll();
		cards.poll();
		cards.add(cards.poll());
		return solve(cards);
	}
	
	//방법2 : 규칙 이용
	private static int solve2(int input) {
		//1. 2의 제곱수 찾기
		int i=1;
		while(i<input)
			i *= 2;

		return i-(i-input)*2;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(in.readLine());
		
		//방법1 : Queue 이용
		Queue<Integer> cards = new LinkedList<Integer>();
		for(int i=1;i<=input;i++) {
			cards.add(i);
		}
		System.out.println(solve(cards));
		
		//방법2 : 규칙 이용
		System.out.println(solve2(input));
	}

}
