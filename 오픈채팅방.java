import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> pk = new HashMap<>();
        ArrayList<Integer> alarms = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();
        
        for(int i=0;i<record.length;i++){
            StringTokenizer st = new StringTokenizer(record[i], " ");
            String alarm = st.nextToken();
            String id = st.nextToken();
            
            if(!alarm.equals("Change"))
                ids.add(id);
            
            if(alarm.equals("Enter"))
                alarms.add(1);
            else if(alarm.equals("Leave")){
                alarms.add(2);
                continue;
            }
            
            String nickname = st.nextToken();
            pk.put(id, nickname);
        }
        
        
        String[] answer = new String[alarms.size()];
        for(int i=0;i<alarms.size();i++){
            StringBuilder sb = new StringBuilder();
            sb.append(pk.get(ids.get(i))).append("님이 ");
            if(alarms.get(i) == 1)
                sb.append("들어왔습니다.");
            else
                sb.append("나갔습니다.");
            answer[i] = sb.toString();
        }
        return answer;
    }
}
