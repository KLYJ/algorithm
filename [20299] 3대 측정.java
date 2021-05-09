import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input[] = in.readLine().split(" ");
		int apply = Integer.parseInt(input[0]);  //신청 동아리 수
		int team = Integer.parseInt(input[1]);   //팀원 합 능력 조건
		int person = Integer.parseInt(input[2]); //개인 능력 조건
		
		int success_cnt = 0;                         //스마트 클럽에 가입된 동아리의 수
		Queue<Integer> success = new LinkedList<>(); //스마트 클럽에 가입된 학생들의 능력치
		
		for(int i=0;i<apply;i++) {
			String club[] = in.readLine().split(" "); //동아리원 능력치
			int sum = 0;                              //팀원 3명 능력 합
			int flag = 0;                             //탈락한 동아리원 있는지 여부(0:없음, 1:있음)
			for(int j=0;j<3;j++) {
				int p = Integer.parseInt(club[j]);
				if(p<person) {						  //동아리원 능력치 < 개인 능력 조건
					flag = 1;						  //탈락한 동아리원 발생
					break;							  //stop
				}
				else {								  //동아리원 능력치 >= 개인 능력 조건
					sum += p;						  //팀원 능력 합 변수(sum)에 개인 능력 저장 
				}
			}
			if(flag==0) {           			      //모든 동아리원이 개인 능력 조건을 통과
				if(sum>=team) {     				  //팀원 합 능력 조건도 통과
					success_cnt++;  				  //스마트 클럽에 가입한 동아리 수++
					for(int j=0;j<3;j++) {
						success.offer(Integer.parseInt(club[j]));  //스마트 클럽에 가입한 동아리원의 능력 저장
					}
				}
			}
		}
		
		System.out.println(success_cnt);
		StringBuilder sb = new StringBuilder();
		while(!success.isEmpty()) {
			sb.append(success.poll()).append(" ");
		}
		System.out.println(sb.toString());
		
	}

}
