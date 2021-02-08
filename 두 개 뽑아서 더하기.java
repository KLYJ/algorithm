import java.util.*;

public class 두_개_뽑아서_더하기 {

	static int len;
    static List<Integer> list = new ArrayList<Integer>();
    
    public int[] solution(int[] numbers) {
        len = numbers.length;
        
        comb(0, numbers, new int[2], 0);
        Collections.sort(list);
        
        int answer[] = new int[list.size()];
        Iterator<Integer> itr = list.iterator();
        int idx=0;
        while(itr.hasNext())
            answer[idx++] = itr.next();
                                    
        return answer;
    }
    
    public void comb(int selectTo, int numbers[], int selected[], int start){
        if(selectTo==2){
            int sum = selected[0]+selected[1];
            if(!list.contains(sum))
                list.add(sum);
            return;
        }
        
        for(int i=start;i<len;i++){
            selected[selectTo] = numbers[i];
            comb(selectTo+1, numbers, selected, i+1);
        }
        
    }
    
}
