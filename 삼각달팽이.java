package Level2;

import java.util.*;

class 삼각달팽이 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5)));
	}
	
    static int dx[] = {1,0,-1};
    static int dy[] = {0,1,-1};
    
    public static int[] solution(int n) {
        int arr[][] = new int[n+1][n+1];
        
        int r = 1, c = 1, cnt = 1, d = 0;
        int check = 0;
        while(true){          
            if(r >= 1 && r <= n && c >= 1 && c <= n && arr[r][c] == 0){
                arr[r][c] = cnt++;
                check = 0;
            }
            else{
            	r -= dx[d];
            	c -= dy[d];
                d = (d+1)%3;
                
                check++;
            }
            
            if(check == 2)
                break;
            
            r += dx[d];
            c += dy[d];
        }
        
        ArrayList<Integer> list = new ArrayList<>();   
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                list.add(arr[i][j]);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++)
            answer[i] = list.get(i);
        return answer;
    }
}