import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//N과 M (3) - 중복을 허용하는 순열
public class BJ_15651 {

	static int arr[];
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input[] = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = i + 1;

		perm(0, new int[M]);
		System.out.println(sb);
	}

	private static void perm(int toSelect, int selected[]) {
		if (toSelect == M) {
			for (int i : selected)
				sb.append(i + " ");
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			selected[toSelect] = arr[i];
			perm(toSelect + 1, selected);
		}
	}

}
