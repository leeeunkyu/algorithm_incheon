package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1547 {
	
	static int m;
	static int[][] order;
	static int p = 1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		order = new int[2][m];
		
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			order[0][i] = Integer.parseInt(str[0]);
			order[1][i] = Integer.parseInt(str[1]);			
		}
		
		for (int i = 0; i < m; i++) {
			int a = order[0][i];
			int b = order[1][i];
			
			if(a == p) {
				p = b;
			} else if(b == p) {
				p = a;
			}

		}
		System.out.println(p);
		
	}
}
