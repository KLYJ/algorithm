import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1992 {
	
	static int map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			String st = in.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = st.charAt(j)-'0';
			}
		}
		
		System.out.println(dfs(0, 0, N));
	}

	private static String dfs(int x, int y, int N) {
		StringBuilder sb = new StringBuilder();
		//최소 단위로 쪼갬
		if(N==1) {
			return sb.append(map[x][y]).toString();
		}
		
		String s1 = dfs(x, y, N/2);
		String s2 = dfs(x, y+N/2, N/2);
		String s3 = dfs(x+N/2, y, N/2);
		String s4 = dfs(x+N/2, y+N/2, N/2);
		
		if((s1.equals("0") || s1.equals("1")) && s1.equals(s2) && s2.equals(s3) && s3.equals(s4)) {
			return sb.append(s1).toString();
		}
		else {
			return sb.append("(").append(s1).append(s2).append(s3).append(s4).append(")").toString();
		}
		
	}

}
