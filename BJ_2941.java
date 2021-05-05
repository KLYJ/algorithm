package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2941 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String word = in.readLine();
		String cro_alpha[] = {"c=","c-","dz=","d-","lj","nj","s=","z="};
		
		for(String alpha:cro_alpha) {
			word = word.replace(alpha, "#");
		}
		
		System.out.println(word.length());
	}

}
