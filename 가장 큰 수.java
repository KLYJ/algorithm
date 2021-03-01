package Level2;

import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수 {
	
	public static String solution(int[] numbers) {
		String num[] = new String[numbers.length];
        for(int i=0;i<numbers.length;i++) {
            num[i] = String.valueOf(numbers[i]);
        }
		
        Arrays.sort(num, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int diff = o2.charAt(0)-o1.charAt(0);
                if(diff==0) {
                    String compare1 = o1+o2;
                    String compare2 = o2+o1;
                    return compare2.compareTo(compare1);
                }
                else {
                    return diff;
                }
            }
			
        });
		
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numbers.length;i++){
            sb.append(num[i]);
        }
        if(sb.charAt(0)=='0') {
            sb.setLength(0);
            sb.append(0);
        }
        return sb.toString();
    }

}
