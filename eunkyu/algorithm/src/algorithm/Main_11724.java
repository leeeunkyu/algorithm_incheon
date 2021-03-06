package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11724 {
	
	static int n;
	static int m;
	static int[][] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		graph = new int[n][n];
		visited = new boolean[n];
		
		for (int i = 0; i < m; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]) - 1;
			int b = Integer.parseInt(info[1]) - 1;
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if(!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void dfs(int i) {
		visited[i] = true;
		for (int j = 0; j < n; j++) {
			if(graph[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}
	}
}
