package Level2;

public class 큰수만들기 {
  
	public static String solution(String number, int k) {
		char[] num = number.toCharArray();
				
		for(int i=0;i<num.length;i++) {
			int now = num[i];
			for(int j=i-1;j>=0;j--) {
				if(num[j]!='#' && num[j]<now) {
					num[j]='#';
					k--;
				}
				if(num[j]=='9')
					break;
				if(k==0) {
					i=num.length;
					break;
				}
			}
		}
		
		int end = num.length;
		if(k!=0) {
			end -=k;
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i=0;i<end;i++) {
			if(num[i]!='#')
				answer.append(num[i]);
		}
		return answer.toString();
	}
}
