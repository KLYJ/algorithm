import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
		char last = words[0].charAt(words[0].length()-1);
		Set<String> map = new HashSet<>();
		map.add(words[0]);
		
		int[] answer = {0, 0};
        for(int i=1;i<words.length;i++){
            if(last != words[i].charAt(0) || map.contains(words[i])) {
            	answer[0] = (i%n)+1;
            	answer[1] = i/n+1;
            	break;
            }
            
            last = words[i].charAt(words[i].length()-1);
            map.add(words[i]);
        }
        
        
        return answer;
    }
}
