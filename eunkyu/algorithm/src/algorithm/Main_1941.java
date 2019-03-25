package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
//13:20
import java.util.Queue;

public class Main_1941 {
	static int n = 5;
	static int size = 7;
	static int cy = 0;
	static int cs = 0;
	static char[][] arr;
	static int max = 0;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			arr[i] = str.toCharArray();
		}
	/*	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}*/
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = true;
				if(arr[i][j] == 'Y')
					++cy;
				if(arr[i][j] == 'S')
					++cs;
				dfs(i, j, 1);
				if(arr[i][j] == 'Y')
					--cy;
				if(arr[i][j] == 'S')
					--cs;
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}
	private static void dfs(int i, int j, int cnt) {
		if(cnt == size) {
			if(cs > cy) {
/*				System.out.println("cs: "+cs+" cy: "+cy);
*/				++max;
			}
			return;
		}
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_1941> points = new LinkedList<Point_1941>();
		
		for (int idx = 0; idx < 4; idx++) {
			int x = j + dx[idx];
			int y = i + dy[idx];
			if(x >= 0 && x < n && y > 0 && y < n
					&& !visited[y][x]) {
				visited[y][x] = true;
				if(arr[y][x] == 'Y')
					++cy;
				if(arr[y][x] == 'S')
					++cs;
				dfs(y, x, cnt + 1);
				if(arr[y][x] == 'Y')
					--cy;
				if(arr[y][x] == 'S')
					--cs;
				visited[y][x] = false;

			}
		}
	}
}

class Point_1941{
	int y;
	int x;
	
	public Point_1941(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
}