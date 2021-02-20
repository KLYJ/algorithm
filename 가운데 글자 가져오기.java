public class 가운데글자가져오기 {
	
	public static void main(String[] args) {
		System.out.println(solution("abcde"));
		System.out.println(solution("abde"));
	}
	
	public static String solution(String s) {
        int len = s.length();
        //return len%2==0?""+s.charAt(len/2-1)+s.charAt(len/2):""+s.charAt(len/2);
        return len%2==0?s.substring(len/2-1,len/2+1):s.substring(len/2,len/2+1);
    }

}
