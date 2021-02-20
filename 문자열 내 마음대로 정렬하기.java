package Level1;

import java.util.Arrays;
import java.util.Comparator;

public class 문자열내마음대로정렬하기 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] {"abce", "abcd", "cdx"}, 2)));
	}

	public static String[] solution(String[] strings, int n) {
		//방법1 : anonymous 클래스 생성
		Arrays.sort(strings, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int diff = o1.charAt(n)-o2.charAt(n);
//				//str1.compareTo(str2) : str1이 str2보다 사전적으로 앞이라면 음수 , 같으면 0, 뒤라면 양수 리턴 
				return diff==0?o1.compareTo(o2):diff;
			}
			
		});
		
		//방법2 : lambda 이용
		Arrays.sort(strings, (o1, o2) -> (o1.charAt(n)==o1.charAt(n)?o1.compareTo(o2):o1.charAt(n)-o2.charAt(n)));
		return strings;
	}
}
