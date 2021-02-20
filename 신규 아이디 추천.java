public class 신규_아이디_추천 {
	
	public static void main(String[] args) {
		System.out.println(solution2("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(solution2("z-+.^."));
		System.out.println(solution2("=.="));
		System.out.println(solution2("123_.def"));
		System.out.println(solution2("abcdefghijklmn.p"));
	}
	
  //방법2 :  이용하여 조건에 맞도록 설정
	private static String solution2(String new_id) {
		new_id = new_id.toLowerCase();
		new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
		new_id = new_id.replaceAll("[.]+", ".");
		if(new_id.charAt(0)=='.') new_id = new_id.substring(1);
		if(new_id.length()!=0 && new_id.charAt(new_id.length()-1)=='.') new_id = new_id.substring(0, new_id.length()-1);
		if(new_id.length()==0) new_id = "aaa";
		if(new_id.length()>=16) new_id = new_id.substring(0, 15);
		if(new_id.charAt(new_id.length()-1)=='.') new_id = new_id.substring(0, new_id.length()-1);
		if(new_id.length()<=2) {
			while(new_id.length()!=3)
				new_id += new_id.charAt(new_id.length()-1);
		}
		
		return new_id;
	}
  
  //방법1 : 반복문 이용하여 조건에 맞도록 설정
	private static String solution(String new_id) {
		
		//1단계
		new_id = new_id.toLowerCase();
		
		//2단계
		int len = new_id.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			char c = new_id.charAt(i);
			if (Character.isDigit(c) || Character.isLowerCase(c) || c == '-' || c == '_' || c == '.')
				sb.append(c);
		}
		new_id = sb.toString();

		//3단계
		while (new_id.contains(".."))
			new_id = new_id.replace("..", ".");

		//4단계
		if(new_id.length()!=0 && new_id.charAt(0)=='.')
			new_id = new_id.substring(1,new_id.length());
		if(new_id.length()!=0 && new_id.charAt(new_id.length()-1)=='.')
			new_id = new_id.substring(0,new_id.length()-1);
		
		//5단계
		len = new_id.length();
		if (len == 0) {
			new_id = "aaa";
			len = 3;
		}
		//6단계
		else if (len > 15) {
			new_id = new_id.substring(0, 15);
			len = 15;
			if(new_id.charAt(14)=='.') {
				new_id = new_id.substring(0,14);
				len--;
			}
		}
		//7단계
		String s = new_id.charAt(len - 1) + ""; 
		while (len < 3) {
			new_id += s;
			len++;
		}

		return new_id;
	}

}
