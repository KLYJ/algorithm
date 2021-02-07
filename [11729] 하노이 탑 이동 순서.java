import java.util.Scanner;

public class BJ_11729 {

    static int answer = 0;
    static StringBuilder sb = new StringBuilder();
    
    private static void hanoi(int n, int from, int temp, int to){
        if(n==0) return;
        
        hanoi(n-1, from, to, temp);
        sb.append(from+" "+to+"\n");
        answer++;
        hanoi(n-1, temp, from, to);        
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        
        hanoi(n, 1, 2, 3);
        
        System.out.println(answer);
        System.out.println(sb.toString());
        
    }
	
}
