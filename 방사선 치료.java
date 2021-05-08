import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3 {

	static ArrayList<int[]> cancers = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(in.readLine(), " ");
			int cancer_cnt = Integer.parseInt(st.nextToken()); // 종양 수
			int limit = Integer.parseInt(st.nextToken()); // 기준값 : 종양을 limit개 이하로 만들기

			// 직사각형(종양) 저장
			cancers.clear();
			// 최대 K
			int max_k = 0;
			int max_a = 300;
			int max_b = 300;
			int max_c = 0;
			int max_d = 0;
			//종양 길이(큰거) 리스트
			int cancer_len[] = new int[cancer_cnt];
			for (int i = 0; i < cancer_cnt; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int a = 0, b = 0, c = 0, d = 0;
				if (x1 < x2) {
					if (y1 < y2) {
						a = x1;
						b = y1;
						c = x2;
						d = y2;
					} else {
						a = x1;
						b = y2;
						c = x2;
						d = y1;
					}
				} else {
					if (y1 < y2) {
						a = x2;
						b = y1;
						c = x1;
						d = y2;
					} else {
						a = x2;
						b = y2;
						c = x1;
						d = y1;
					}
				}
				cancers.add(new int[] { a, b, c, d });
				
				//종양 길이
				cancer_len[i] = Math.max(c-a, d-b);

				// 최대 K 찾기
				max_a = Math.min(a, max_a);
				max_b = Math.min(b, max_b);
				max_c = Math.max(c, max_c);
				max_d = Math.max(d, max_d);
			}
			
			//최대 K 찾기
			max_k = Math.max(max_c - max_a, max_d - max_b);

			//최소 K 찾기
			Arrays.sort(cancer_len);
			int min_k = limit==0?max_k:cancer_len[limit-1];
			
			//limit = cancer_cnt-1이라면 하나만 제거하면 되니까
			if(limit == cancer_cnt-1) {
				sb.append(cancer_len[0]).append("\n");
				continue;
			}
			
			// 방문확인용
			boolean visited[] = new boolean[cancer_cnt];

			// 방사선 범위 검사
			for (int k = min_k; k <= max_k; k++) { // K의 범위
				for (int i = max_a; i <= max_c; i++) {
					int ka = i;
					int kc = i + k;
					for (int j = max_b; j <= max_d; j++) {
						int kb = j;
						int kd = j + k;
						int cur_cancer_cnt = cancer_cnt;
//						System.out.println(ka + "," + kb + "," + kc + "," + kd);
						for (int c = 0; c < cancers.size(); c++) {
							int cancer[] = cancers.get(c);
							if (!visited[c]) { // 확인 아직 안한 종양임
								int ca = cancer[0];
								int cb = cancer[1];
								int cc = cancer[2];
								int cd = cancer[3];

								if (ka <= ca && cc <= kc && kb <= cb && cd <= kd) {// K 범위안에 있다면
//									System.out.println(k+"일 때의 범위내");
									cur_cancer_cnt--;
									visited[c] = true;
								}
							}
						}
						for (int v = 0; v < cancer_cnt; v++) {
							visited[v] = false;
						}
						if (cur_cancer_cnt <= limit) {
							sb.append(k).append("\n");
							i=301;k=301;
							break;
						}
					}
				}
			}
		}
		System.out.println(sb.toString());
	}

}
