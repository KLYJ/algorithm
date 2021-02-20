import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sol_0203_2_SWEA1873 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		// 위, 아래, 오른쪽, 왼쪽
		int dx[] = { -1, 1, 0, 0 }; // height
		int dy[] = { 0, 0, 1, -1 }; // width
		List<Character> dir_input = new ArrayList<Character>();
		dir_input.add('U');
		dir_input.add('D');
		dir_input.add('R');
		dir_input.add('L');
		List<Character> dir_map = new ArrayList<Character>();
		dir_map.add('^');
		dir_map.add('v');
		dir_map.add('>');
		dir_map.add('<');

		for (int i = 1; i <= T; i++) {
			String hw[] = in.readLine().split(" ");
			int h = Integer.parseInt(hw[0]);
			int w = Integer.parseInt(hw[1]);
			char map[][] = new char[h][w];
			int x = 0;
			int y = 0;
			int dir = 0;
			for (int j = 0; j < h; j++) {
				String line = in.readLine();
				map[j] = line.toCharArray();
				for (int k = 0; k < w; k++) {
					if (dir_map.contains(map[j][k])) {
						dir = dir_map.indexOf(map[j][k]);
						x = j;
						y = k;
						map[x][y] = '.';
						break;
					}
				}
			}

			int len = Integer.parseInt(in.readLine());
			String action = in.readLine();
			for (int j = 0; j < len; j++) {
				char s = action.charAt(j);
				if (s == 'S') {
					// 슈팅(이동X)
					int shoot_x = x;
					int shoot_y = y;
					shoot_x += dx[dir];
					shoot_y += dy[dir];
					while (shoot_x > -1 && shoot_x < h && shoot_y > -1 && shoot_y < w) {
						if (map[shoot_x][shoot_y] == '#')
							break;
						else if (map[shoot_x][shoot_y] == '*') {
							map[shoot_x][shoot_y] = '.';
							break;
						}
						shoot_x += dx[dir];
						shoot_y += dy[dir];
					}
				} else {
					// 이동
					dir = dir_input.indexOf(s);
					// 한 칸만 이동!!
					int new_x = x + dx[dir];
					int new_y = y + dy[dir];
					if (new_x > -1 && new_x < h && new_y > -1 && new_y < w && map[new_x][new_y] == '.') {
						x = new_x;
						y = new_y;
					}
				}
			}

			System.out.print("#" + i + " ");
			map[x][y] = dir_map.get(dir);

			for (int k = 0; k < h; k++) {
				System.out.println(String.valueOf(map[k]));
			}

		}
	}
}
