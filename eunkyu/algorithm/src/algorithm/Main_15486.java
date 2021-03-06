package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15486 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int T[] = new int[N+1];
		int P[] = new int[N+1];
		int dp[] = new int[N+50];
		
		for (int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0 ; i < N; i++) {
			dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}
		System.out.println(dp[N]);
	}
}