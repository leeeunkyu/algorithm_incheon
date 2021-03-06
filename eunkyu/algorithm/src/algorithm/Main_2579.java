package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579 {
	
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	
		int[] stair = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n + 1];
		dp[1] = stair[1];
		if(n >= 2)
			dp[2] = stair[1] + stair[2];
		
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(stair[i] + dp[i - 2], dp[i - 3] + stair[i - 1] + stair[i]);			
		}

	/*	for (int i = 0; i < dp.length; i++) {
			System.out.println(dp[i]+" ");
		}*/
		System.out.println(dp[n]);
	
	}
}
