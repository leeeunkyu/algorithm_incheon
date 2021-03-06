package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

//13:45 ~ 
public class Main_14889 {
	static int n;
	static int[][] arr;
	static boolean[] visited;
	static int[] member;
	static int res;
	static HashSet<String> h;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		h = new HashSet<>();
		res = Integer.MAX_VALUE;
		for (int i = 2; i <= n / 2; i++) {
			visited = new boolean[n];
			dfs(0, 0, i);
		}
		System.out.println(res);
	}
	private static void dfs(int idx, int cnt, int rCnt) {
		if(cnt == rCnt) {
			/*for (int i = 0; i < visited.length; i++) {
				System.out.print(visited[i]+" ");
			}
			System.out.println();*/
			int a = 0;
			int b = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(visited[i] == true && visited[j] == true) {
						a += arr[i][j];
					} 
					if(visited[i] == false && visited[j] == false) {
						b += arr[i][j];
					}
				}
			}
			
			int val = Math.abs(a - b);
			if(val == 0) {
				System.out.print(0);
				System.exit(0);
			}
			if(res > val) {
				res = val;
			}
			return;
		}
		for (int i = idx; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(idx + 1, cnt + 1, rCnt);
				visited[i] = false;
			}
		}
	}
}
