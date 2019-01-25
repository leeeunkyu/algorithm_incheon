package baekjoon;

import java.util.Scanner;

public class P_1697_hide_and_seek2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] dp = new int[n+k+2];
		for (int i=n-1,j=1; i>=0 ; i--,j++) {
			dp[i]=j;
		}
		
		for (int i = n+1; i < k+1; i++) {
			dp[i] = dp[i-1]+1;
			if(i%2==0) dp[i] = Math.min(dp[i], dp[i/2]+1);
			else dp[i] = Math.min(dp[i], dp[(i+1)/2]+2);
		}
		System.out.println(dp[k]);
	}
}
