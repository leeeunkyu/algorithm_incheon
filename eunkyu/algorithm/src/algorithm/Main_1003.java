package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1003 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(fibonacci(n)+"\n");
		}
		System.out.println(sb);
	}

	private static String fibonacci(int n) {
		if(n == 0) {
			return "1 0"; 
		} else if(n == 1) {
			return "0 1";
		}
		int[] dpA = new int[n + 1];
		dpA[0] = 0;
		dpA[1] = 1;
		
		int[] dpB = new int[n + 1];
		dpB[0] = 1;
		dpB[1] = 0;
		
		for (int i = 2; i <= n; i++) {
			dpA[i] = dpA[i - 1] + dpA[i - 2];
			dpB[i] = dpB[i - 1] + dpB[i - 2];
		}

		return dpB[n]+" "+dpA[n];
	}
}
