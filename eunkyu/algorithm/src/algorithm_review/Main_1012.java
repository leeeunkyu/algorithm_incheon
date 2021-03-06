package algorithm_review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1012 {
	
	static int m;
	static int n;
	static int k;
	static int[][] arr;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testCase; tc++) {
			String[] info = br.readLine().split(" ");
			m = Integer.parseInt(info[0]);//가로
			n = Integer.parseInt(info[1]);//세로
			k = Integer.parseInt(info[2]);//배추 심어져있는 위치 갯수
			arr = new int[n][m];
			for (int i = 0; i < k; i++) {
				String[] kInfo = br.readLine().split(" ");
				int x = Integer.parseInt(kInfo[0]);
				int y = Integer.parseInt(kInfo[1]);
				arr[y][x] = 1;
			}
			int cnt = 0;
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(arr[i][j] == 1 && !visited[i][j]) {
						cnt++;
						bfs(i, j);
					}
				}
			}
			
			System.out.println(cnt);
			
		}
	}

	private static void bfs(int i, int j) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		visited[i][j] = true;
		Queue<Point_1012> pointq = new LinkedList<Point_1012>();
		pointq.add(new Point_1012(i, j));
		
		
		while (!pointq.isEmpty()) {
			Point_1012 point = pointq.poll();
			
			int x = point.getX();
			int y = point.getY();
			
			for (int k = 0; k < 4; k++) {
				int nextX = x + dx[k];
				int nextY = y + dy[k];
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n 
						&& !visited[nextY][nextX] && arr[nextY][nextX] == 1) {
					visited[nextY][nextX] = true;
					pointq.add(new Point_1012(nextY, nextX));
				}
			}	
		}
	}
}
class Point_1012 {
	int y;
	int x;
	
	public Point_1012(int y, int x) {
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
