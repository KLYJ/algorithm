import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16563 {
	
	static char ch[];
	static int len, cnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		len = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		
		ch = new char[cnt];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0;i<cnt;i++) {
			ch[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(ch);
		
		perm(0, new char[len], 0, 0, 0);
		
		System.out.println(sb.toString());
	}

	private static void perm(int selectTo, char[] selected, int start, int ja, int mo) {
		if(selectTo == len) {
			if(ja >= 2 && mo >= 1)
				sb.append(new String(selected)).append("\n");
			return;
		}
		
		for(int i=start;i<cnt;i++) {
			selected[selectTo] = ch[i];
			if(ch[i] == 'a' || ch[i] == 'e' || ch[i] == 'i' || ch[i] == 'o' || ch[i] == 'u') { //모음
				perm(selectTo+1, selected, i+1, ja, mo+1);
			}
			else { //자음
				perm(selectTo+1, selected, i+1, ja+1, mo);
			}
		}
	}

}
