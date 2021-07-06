package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1543 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String document = in.readLine();
		String word = in.readLine();
		
		int cnt = 0;
		while(true) {
			if(document.contains(word)) {
				document = document.replaceFirst(word, "*");
				cnt++;
			}
			else
				break;
		}
		
		System.out.println(cnt);
	}

}
