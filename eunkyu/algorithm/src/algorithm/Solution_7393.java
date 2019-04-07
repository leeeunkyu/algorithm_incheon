package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//11:40
public class Solution_7393 {
	
	static int n;
	static boolean visied[];
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			res = 0;
			for (int i = 1; i <= 9; i++) {
				visied = new boolean[10];
				visied[i] = true;
				dfs(i, 1, ""+i, 1);
				visied[i] = false;
			}
			
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int idx, int cnt, String str, int tCnt) {
		if(cnt == n) {
			for (int i = 0; i < 10; i++) {
				if(!visied[i]) {
					return;
				}
			}
			//System.out.println(str);
			if(res == 1000000000) {
				res = 0;
			}
			++res;
			return;
		} else {
			if(idx + 1 < 10 && 10 - tCnt < n - cnt) {
				if(!visied[idx + 1]) {
					visied[idx + 1] = true;
					dfs(idx + 1, cnt + 1, str + " "+(idx + 1), tCnt + 1);
				} else {
					dfs(idx + 1, cnt + 1, str + " "+(idx + 1), tCnt);
				}
				visied[idx + 1] = false;
			}
			if(idx - 1 >= 0) {
				if(!visied[idx - 1]) {
					visied[idx - 1] = true;
					dfs(idx - 1, cnt+ 1, str + " "+(idx - 1), tCnt + 1);
				} else {
					dfs(idx - 1, cnt+ 1, str + " "+(idx - 1), tCnt);
				}
				visied[idx - 1] = false;
			}
		}
	}
}