import java.util.*;

class Solution {
  public int[] solution(int[] arr, int divisor) {
      Arrays.sort(arr);
      if(divisor == 1){
          return arr;
      }
      ArrayList<Integer> list = new ArrayList<>();
      for(int i:arr){
          if(i%divisor==0){
              list.add(i);
          }
      }
      if(list.isEmpty()){
          return new int[] {-1};
      }
      int answer[] = new int[list.size()];
      for(int i=0;i<list.size();i++){
          answer[i] = list.get(i);
      }
      return answer;
  }
}
