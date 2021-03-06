package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_4811 {
	
	static BigInteger[][] dp;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int n;
		//W 한개짜리 H는 반개짜리
		
		while(true) {
			//약을꺼내서 한개짜리면 바로먹고 
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			if(n <= 2) {
				sb.append(n+"\n");
				continue;
			}
			//00000

			dp = new BigInteger[n + 1][n + 1];
		
			eatMedicine(n, 0);
			
			sb.append(dp[n][0]+"\n");

		}
		System.out.println(sb);
	}
	private static BigInteger eatMedicine(int w, int h) {
		if (w > 0 && dp[w][h] != null) {
			//한개짜리가 0보다 크고, dp에 이미 값이 있다면
			return dp[w][h];
		}
		if(w == 0) {
			dp[w][h] = BigInteger.ONE;
			return BigInteger.ONE;
		} else {
			BigInteger sum = BigInteger.ZERO;
			sum = sum.add(eatMedicine(w - 1, h + 1));
			//한개짜리 -1 반개짜리 +1
			if(h > 0) {
				sum = sum.add(eatMedicine(w, h - 1));
				//반개짜리만먹기
			}
			dp[w][h] = sum;
			return sum;
		}
	}
}
