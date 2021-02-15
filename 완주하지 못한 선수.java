import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {
	
	public static void main(String[] args) {
		System.out.println(solution2(new String[] {"leo", "kiki", "eden"}, new String[] {"eden", "kiki"}));
	}
	
        //방법1 : HashMap 이용 (빠름)
	public String solution(String[] participant, String[] completion) {
        //answer에 <참가자 이름, 수> 저장
        Map<String, Integer> answer = new HashMap<>();
        
        for(int i=0;i<participant.length;i++){
            String name = participant[i];
            int people = 1;
            if(answer.containsKey(name)){
                people += answer.get(name);
            }
            answer.put(name, people);
        }
        
        //참가자와 완주자 비교
        for(String name:completion){
            int people = answer.get(name);
            if(people == 1){
                answer.remove(name);
            }
            else{
                answer.put(name, people-1);
            }
        }
        
        //완주하지 못한 선수(=answer에 남은 선수) 리턴 
        return answer.keySet().iterator().next();
    }
	
        // 방법2 : 배열 탐색 이용
	public static String solution2(String[] participant, String[] completion) {
		Arrays.sort(participant);
		Arrays.sort(completion);
		String answer = "";
		
		for(int i=0;i<completion.length;i++) {
			if(!participant[i].equals(completion[i])) {
				return participant[i];
			}
		}

		return participant[participant.length-1];

	}
	
}
