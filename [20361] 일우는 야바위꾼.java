import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String str[] = in.readLine().split(" ");
		int cup = Integer.parseInt(str[0]); // 종이컵 수
		String location = str[1]; 			// 간식있는 위치
		int cnt = Integer.parseInt(str[2]); // 바꾸는 횟수
		
		for(int i=0;i<cnt;i++) {
			String change[] = in.readLine().split(" "); //바꾸는 컵들(A, B)
			if(change[0].equals(location)) {            //A가 간식있는 위치라면
				location = change[1];					//간식있는 위치는 B로 업데이트
			}
			else if(change[1].equals(location)) {       //B가 간식있는 위치라면
				location = change[0];				    //간식있는 위치는 A로 업데이트
			}
			else {										//간식있는 컵이 아닌 컵들을 바꾸는거면
				continue;								//continue
			}
        }

		System.out.println(location);
	}
}
