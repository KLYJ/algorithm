import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<Integer> connect[] = new ArrayList[n+1];
        for(int i=0;i<n+1;i++)
            connect[i] = new ArrayList<Integer>();
        
        for(int i=0;i<edge.length;i++){
            connect[edge[i][0]].add(edge[i][1]);
            connect[edge[i][1]].add(edge[i][0]);
        }
        
        int minLen[] = new int[n+1];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        minLen[1] = 0;
        
        boolean visited[] = new boolean[n+1];
        
        for(int i=1;i<=n;i++){
            int min = Integer.MAX_VALUE;
            int ver = 0;
            
            for(int j=1;j<=n;j++){
                if(!visited[j] && min > minLen[j]){
                    min = minLen[j];
                    ver = j;
                }
            }
            
            visited[ver] = true;
            
            for(int j=1;j<=n;j++){
                if(!visited[j] && connect[j].contains(ver) && minLen[j] > minLen[ver]+1){
                    minLen[j] = minLen[ver]+1;
                }
            }
        }
        
        //System.out.println(Arrays.toString(minLen));
        
        int max = 0;
        int cnt = 0;
        for(int i=1;i<=n;i++){
            if(minLen[i] > max){
                max = minLen[i];
                cnt = 1;
            }
            else if(minLen[i] == max)
                cnt++;
        }

        return cnt;
    }
}
