import java.util.*;

class Solution {
    static Set<BitSet> answer = new HashSet<>();
    static int len;
    static ArrayList<Integer> list[];
    
    public int solution(String[] user_id, String[] banned_id) {
        len = banned_id.length;
        
        list = new ArrayList[banned_id.length];
        
        for(int i=0;i<banned_id.length;i++){
            list[i] = new ArrayList<Integer>();
            String ban = banned_id[i];
            
            for(int j=0;j<user_id.length;j++){
                String user = user_id[j];
                
                if(ban.length() != user.length())
                    continue;
                
                boolean isBanned = true;
                for(int k=0;k<ban.length();k++){
                    if(ban.charAt(k) != '*' && ban.charAt(k) != user.charAt(k)){
                        isBanned = false;
                        break;
                    }
                }
                
                if(isBanned){
                    list[i].add(j);
                }
            }
            
        }
        
        Stack<Integer> ans = new Stack<>();
        comb(ans, 0);
        
        return answer.size();
    }
    
    public void comb(Stack<Integer> ans, int selectTo){
        for(int i : list[selectTo]){            
            if(!ans.contains(i))
                ans.push(i);
            else
                continue;
            
            if(selectTo == len-1) {
                BitSet bs = new BitSet(8);
                for(int j : ans){
                    bs.set(j);
                }
                
                if(!answer.contains(bs))
                    answer.add(bs);
            }
            else
                comb(ans, selectTo+1);
            
            ans.pop();
        }
        
    }
}
