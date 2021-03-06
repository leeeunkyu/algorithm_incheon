package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1149 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n][3];
		int[][] houses = new int[n][3];
		for (int i = 0; i < n; i++) {
			String[] houseInfo = br.readLine().split(" ");
			for (int j = 0; j < houseInfo.length; j++) {				
				houses[i][j] = Integer.parseInt(houseInfo[j]);	
			}
		}
		dp[0][0] = houses[0][0];
		dp[0][1] = houses[0][1];
		dp[0][2] = houses[0][2];
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + houses[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + houses[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + houses[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]),dp[n - 1][2]));
	}
}

