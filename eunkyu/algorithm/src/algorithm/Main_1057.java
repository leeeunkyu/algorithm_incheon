package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1057 {
	
	static int n;
	static int a;
	static int b;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		a = Integer.parseInt(str[1]);
		b = Integer.parseInt(str[2]);
		int cnt = 0;
		while(a != b) {
			a = a / 2 + a % 2;
			b = b / 2 + b % 2;
			++cnt;
		}
		
		System.out.println(cnt);
		
	}
}
