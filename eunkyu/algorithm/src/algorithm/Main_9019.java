package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9019 {
	
	static int a;
	static int b;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testCase; tc++) {
			String[] str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[1]);
			
			
		}
		
	}
	/*
	 * if(D) {
	 *  n = 2n
	 *  if(n > 9999) {
	 *  	n %= 10000
	 *  }
	 * }
	 * if(S) {
	 * 	n -= 1;
	 *  if(n == -1) {
	 *    n = 9999
	 *   }
	 * }
	 */
}
