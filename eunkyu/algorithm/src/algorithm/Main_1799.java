package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//17:05
public class Main_1799 {
	
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	static int max;
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
		visited = new boolean[n][n];
		max = 0;
		int a = 0;
		dfs(0, 0, 0, 0);
		a = max;
		max = 0;
		int b = 0;
		dfs(0, 1, 1, 0);
		b = max;
		System.out.println(a + b);
	}

	private static void dfs(int y, int x, int color, int cnt) {
		if(x >= n) {
			y++;
			if(y >= n) {
				if(cnt > max) {
					max = cnt;
					//print(cnt);
				}
				return;	
			}
			x = (y + color) % 2;
		}
		if(arr[y][x] == 1 && !visited[y][x] && check(y, x)) {
			visited[y][x] = true;
			dfs(y, x + 2, color, cnt + 1);
			visited[y][x] = false;
		} 
		dfs(y, x + 2, color, cnt);

		
	}

	private static void print(int cnt) {
		System.out.println(cnt);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j])
					System.out.print("T");
				else
					System.out.print("F");
			}
			System.out.println();
		}
	}

	private static boolean check(int y, int x) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j] && Math.abs(y - i) == Math.abs(x - j))
					return false;
			}
		}
		return true;
	}
}