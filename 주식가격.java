import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        Stack<int[]> stack = new Stack<>();
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] > prices[i]) {
                answer[stack.peek()[1]] = i - stack.peek()[1];
                stack.pop();
            }
            stack.push(new int[] { prices[i], i });
        }

        for (int i = prices.length - 1; i >= 0; i--) {
            if (answer[i] == 0) {
                answer[i] = prices.length - 1 - i;
            }
        }
        
        return answer;
    }
}
