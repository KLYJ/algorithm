import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;

//N과 M (12) - 중복을 허용하는 비내림차순 중복(사용자 입력 - 중복 허용)
public class BJ_15666 {

	static int arr[];
	static int N;
	static int M;
	static LinkedHashSet<String> temp = new LinkedHashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input[] = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		arr = new int[N];
		String str[] = in.readLine().split(" ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(str[i]);

		Arrays.sort(arr);

		comb(0, new int[M]);

		for (String s : temp)
			System.out.println(s);
	}

	private static void comb(int toSelect, int selected[]) {
		if (toSelect == M) {
			StringBuilder sb = new StringBuilder();
			int flag = 0;
			for (int i = 0; i < M - 1; i++) {
				if (selected[i] > selected[i + 1])
					flag = 1;
			}
			if (flag == 0) {
				for (int i : selected)
					sb.append(i + " ");
				temp.add(sb.toString());
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			selected[toSelect] = arr[i];
			comb(toSelect + 1, selected);
		}
	}

}
