package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2455 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = 0;
		int[] staition = new int[4];
		
		for (int i = 0; i < 4; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			size += (-a + b);
			staition[i] = size;
		}
		int max = 0;
		for (int i = 0; i < staition.length; i++) {
			int val = staition[i];
			
			if(val > max)
				max = val;
		}
		System.out.println(max);
		
	}
}
