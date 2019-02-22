package baekjoon;

import java.util.Scanner;

public class P_15486_quit_two {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] dp = new int[n+1+50];  // dp정의 -> dp[n]은 n일 전까지의 가장 높은 수익
		for (int i = 1; i < n+1; i++) {
			int t = scan.nextInt();
			int p = scan.nextInt();
			if(dp[i]<dp[i-1]) dp[i] = dp[i-1];
			if(dp[i+t]<dp[i]+p) dp[i+t] = dp[i]+p;
		}
		System.out.println(dp[n]>dp[n+1]?dp[n]:dp[n+1]);
	}

}
