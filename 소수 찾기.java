import java.util.ArrayList;

class Solution {
    static ArrayList<Integer> list;
	
	public static int solution(String numbers) {
		int answer = 0; //소수 개수
		
		list = new ArrayList<>(); //numbers로 만들 수 있는 수
    if(numbers.contains("2"))
			list.add(2);
		
		//numbers 조합+순열
		for(int i=1;i<=numbers.length();i++) 
			perm(numbers, i, 0, new char[i], new boolean[numbers.length()]);
		}
			
		//소수 검사
    for(int i=0;i<list.size();i++) {
     	int num = list.get(i);
      if(num<=9 && num>=0) {
      	if(num==2||num==3||num==5||num==7)
       			answer++;
        		continue;
      }
      int check = 0;
      for(int j=3;j*j<=num;j+=2) {  //에라토스테네스의 체
      	if(num%j==0) {
      			check = 1;
       			break;
	      }
      }
      if(check == 0) {
      	answer++;
      }
     }
        return answer;
	}

	private static void perm(String numbers, int cnt, int selectTo, char[] selected, boolean[] visited) {
		if(selectTo==cnt) {
			String number = new String(selected);
			int num = Integer.parseInt(number);
			if(!list.contains(num) && num%2!=0) //짝수 거르기
				list.add(num);
			return;
		}
		
		for(int i=0;i<numbers.length();i++) {
			if(!visited[i]) {
				selected[selectTo] = numbers.charAt(i);
				visited[i] = true;
				perm(numbers, cnt, selectTo+1, selected, visited);
				visited[i] = false;
			}
		}
	}
}
