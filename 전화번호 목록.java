import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean solution(String[] phone_book) {
		Arrays.sort(phone_book, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		});
		
		for(int i=0;i<phone_book.length;i++) {
			String pre = phone_book[i];
			for(int j=i+1;j<phone_book.length;j++) {
				if(pre.equals(phone_book[j].substring(0, pre.length()))) {
					return false;
				}
			}
		}
		
		return true;
    }
}
