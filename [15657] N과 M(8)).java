import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

//N과 M (8) - 중복을 허용하는 비내림차순 조합(사용자 입력)
public class BJ_15657 {

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
		String str[] = in.readLine().split(" ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(str[i]);

		Arrays.sort(arr);

		comb(0, new int[M]);

		System.out.println(sb.toString());
	}

	private static void comb(int toSelect, int selected[]) {
		if (toSelect == M) {
			int flag = 0;
			for(int i=0;i<M-1;i++) {
				if(selected[i]>selected[i+1])
					flag = 1;
			}
			if(flag==0) {
				for (int i : selected)
					sb.append(i + " ");
				sb.append("\n");
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			selected[toSelect] = arr[i];
			comb(toSelect + 1, selected);
		}
	}

}
