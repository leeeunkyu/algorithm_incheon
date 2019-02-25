package baekjoon;
import java.math.BigInteger;
import java.util.Scanner;
public class P_4811_pill {
	public static void main(String[] args) {
		BigInteger[][] dp = new BigInteger[31][31];
		for (int i = 0; i < 31; i++) {
			dp[0][i] = BigInteger.ONE;
		}
		for (int i = 1; i < 31; i++) {
			for (int j = 0; j < 31; j++) {
				dp[i][j] = BigInteger.ZERO;
			}
		}
		for (int i = 1; i < 31; i++) {
			for (int j = i; j < 31; j++) {
				dp[i][j] = dp[i-1][j].add(dp[i][j-1]);
			}
		}
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while(t!=0) {
			System.out.println(dp[t][t]);
			t = scan.nextInt();
		}
	}
}