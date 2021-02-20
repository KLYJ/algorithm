import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1244 {
	
	static String convert[] = {"1","0"};

	private static String[] boy(int num, String[] switches) {
		for(int i = 0;i<switches.length;i++) {
			if((i+1)%num==0)
				switches[i] = convert[Integer.parseInt(switches[i])];
		}
		return switches;
	}

	private static String[] girl(int left, int right, String[] switches) {
		if(left <0 || right>switches.length-1 || !switches[left].equals(switches[right])) return switches;
		if(switches[left].equals(switches[right])) {
			switches[left] = convert[Integer.parseInt(switches[left])];
			switches[right] = convert[Integer.parseInt(switches[right])];
		}
		return girl(left-1, right+1, switches);
	}
	
	private static void printS(String[] switches) {
		StringBuilder sb = new StringBuilder();
		for(String s:switches)
			sb.append(s+" ");
		if(sb.length()<=40)
			System.out.println(sb.toString());
		else {
			for(int i=0;i<sb.length();i+=40) {
				if(sb.length()-i>=40)
					System.out.println(sb.substring(i, i+40));
				else
					System.out.println(sb.substring(i));
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int switch_cnt = Integer.parseInt(in.readLine());		
		String switches[] = in.readLine().split(" ");
		
		int student_cnt = Integer.parseInt(in.readLine());
		int students[][] = new int[student_cnt][2];
		
		for(int i=0;i<student_cnt;i++) {
			String student[] = in.readLine().split(" ");
			int num = Integer.parseInt(student[1]);
			if(student[0].equals("1")) {
				switches = boy(num, switches);
			}
			else {
				switches[num-1] = convert[Integer.parseInt(switches[num-1])];
				switches = girl(num-2, num, switches);
			}
		}
		printS(switches);
	}
}
