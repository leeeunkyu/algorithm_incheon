package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2573 {
	
	static int[][] arr;
	static int time;
	static int n;
	static int m;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]); //��
		m = Integer.parseInt(str[1]); //��
		
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		
		time = 0;
		while (true) {
			int cnt = ice();
			if(cnt >= 2) {
				break;
			}
			if(!check()) {
				time = 0;
				break;
			}
		}
		System.out.println(time);
	
	}

	private static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static int ice() {
		Queue<Point_2573> points = new LinkedList<Point_2573>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] != 0) {
					int cnt = bfs(i, j);
					points.add(new Point_2573(i, j, cnt));
					
				}
			}
		}
		
		while (!points.isEmpty()) {
			Point_2573 point = points.poll();
			int y = point.getY();
			int x = point.getX();
			int cnt = point.getCnt();
			arr[y][x] -= cnt;
			if(arr[y][x] < 0)
				arr[y][x] = 0;
		}

		visited = new boolean[n][m];
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] != 0 & !visited[i][j]) {
					bfs2(i, j);
					cnt ++;
				}
			}
		}
		time++;
		
		return cnt;
		
	}

	private static void bfs2(int i, int j) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_2573> points = new LinkedList<Point_2573>();
		points.add(new Point_2573(i, j, 0));
		visited[i][j] = true;
		
		while(!points.isEmpty()) {
			Point_2573 point = points.poll();
			int x = point.getX();
			int y = point.getY();
			for (int idx = 0; idx < dy.length; idx++) {
				int nextX = x + dx[idx];
				int nextY = y + dy[idx];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY <n
						&& !visited[nextY][nextX] && arr[nextY][nextX] != 0) {
					visited[nextY][nextX] = true;
					points.add(new Point_2573(nextY, nextX, 0));
				}
			}
		}
		
	}

	private static int bfs(int i, int j) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int cnt = 0;
		
		for (int idx = 0; idx < 4; idx++) {
			int nextX = j + dx[idx];
			int nextY = i + dy[idx];
			
			if(nextX >= 0 && nextX < m 
					&& nextY >= 0 && nextY <n && arr[nextY][nextX] == 0) {
				++cnt;
			}
		}
		
		return cnt;
	}	
}

class Point_2573 {
	int y;
	int x;
	int cnt; 
	
	public Point_2573(int y, int x, int cnt) {
		super();
		this.y = y;
		this.x = x;
		this.cnt = cnt;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
