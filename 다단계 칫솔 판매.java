import java.util.*;

class Solution {
    
    static class Info {
        String parent;
        int total_money;
        int last_money;
        
        public Info(String p, int m, int l){
            this.parent = p;
            this.total_money = m;
            this.last_money = l;
        }
        
    }
    
    static Map<String, Info> map = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for(int i=0;i<enroll.length;i++){
            map.put(enroll[i], new Info(referral[i], 0, 0));
            
        }
        
        for(int i=0;i<seller.length;i++){
            map.get(seller[i]).last_money = amount[i]*100;
            dfs(seller[i]);
        }
        
        int[] answer = new int[enroll.length];
        for(int i=0;i<answer.length;i++){
            answer[i] = map.get(enroll[i]).total_money;
        }
        return answer;
    }
    
    public void dfs(String seller){        
        String parent = map.get(seller).parent;
        int total_money = map.get(seller).total_money;
        int last_money = map.get(seller).last_money;          
        
        if(last_money < 10){
            map.get(seller).total_money += last_money;
            return;
        }
        else{
            map.get(seller).total_money += last_money - (int) (last_money*0.1);
            
            if(parent.equals("-"))
                return;
        
            map.get(parent).last_money = (int) (last_money*0.1);
            dfs(parent);
        }

    }
}
