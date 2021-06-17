public static int solution(int[] A) {
		int max = Integer.MIN_VALUE;
		int before = 0;
    for(int i=0;i<A.length;i++){
        if(before+A[i] <= A[i]) 
            before = A[i];
        else 
        		before += A[i];
        	
        if(before > max) max = before;
    }
      
    return max;       
}
