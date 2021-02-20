package Kakao;

import java.util.*;

public class 크레인_인형뽑기_게임 {
	//방법1 : 2차원 배열을 스택처럼 사용
	static int len;
    static int stack[][];
    static int index[];
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        len = board[0].length;
        stack = new int[len][len];
        index = new int[len];
        
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(board[i][j]!=0){
                    stack[j][index[j]++] = board[i][j];
                }
            }
        }
        
        index = new int[len];
        Stack<Integer> basket = new Stack<Integer>();
        for(int i:moves){
            if(index[i-1]<len&&stack[i-1][index[i-1]]!=0){
                if(!basket.isEmpty() && basket.peek() == stack[i-1][index[i-1]]){
                    basket.pop();
                    answer += 2;
                }
                else{
                    basket.push(stack[i-1][index[i-1]]);   
                }
                index[i-1]++;
            }
        }
        
        return answer;
    }
    
    
    //방법2 : Stack 배열 사용
    public static int solution2(int[][] board, int[] moves) {
    	int answer = 0;
    	int len = board[0].length;
    	Stack<Integer>[] map = new Stack[len];
    	Stack<Integer> basket = new Stack<>();
    	
    	for(int i=0;i<len;i++)
    		map[i] = new Stack<>();
    	
    	for(int i=len-1;i>=0;i--) {
    		for(int j=len-1;j>=0;j--) {
    			if(board[i][j] != 0)
    				map[j].push(board[i][j]);
    		}
    	}
    	
    	for(int i=0;i<len;i++)
    		System.out.println(map[i]);
    	
    	for(int i:moves) {
    		System.out.println(basket);
    		if(!map[i-1].isEmpty()) {
    			int pick = map[i-1].pop();
    			if(!basket.isEmpty() && pick == basket.peek()) {
    				basket.pop();
    				answer += 2;
    			}
    			else {
    				basket.push(pick);
    			}
    				
    		}
    	}

    	return answer;
    }
    
    public static void main(String[] args) {
    	int test[][] = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
    	int move[] = {1,5,3,5,1,2,1,4};
		System.out.println(solution2(test, move));
	}

}
