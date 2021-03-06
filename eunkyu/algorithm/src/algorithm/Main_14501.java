package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14501 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		int t[] = new int[cnt];
		int p[] = new int[cnt];
		int dp[] = new int[cnt];
		String temp[];
		for (int i = 0; i < cnt; i++) {
			temp = br.readLine().split(" ");
			t[i] = Integer.parseInt(temp[0]);
			p[i] = Integer.parseInt(temp[1]);
			dp[i] = p[i];
		}
		//dp[i] = p[i]+dp[..];
		for (int i = 1; i < cnt; i++) {
			for (int j = 0; j < i; j++ ) {
				if(i - j >= t[j]) {
					dp[i] = Math.max(p[i] + dp[j], dp[i]);
				}
			}
		}
		
		//퇴사 직전까지 일 할 수 있는 값중 최대 i 구하기
		int max = 0;
		for (int i = 0; i < cnt; i++) {
			if(i + t[i] <= cnt) {
				if(max < dp[i]) {
					max = dp[i];
				}
			}
		}
		System.out.println(max);
		
	}
}
