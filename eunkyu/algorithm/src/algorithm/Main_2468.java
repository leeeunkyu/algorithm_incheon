package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//22:00
public class Main_2468 {
	
	static int n;
	static int[][] arr;
	static int[][] copy;
	static int res;
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		copy = new int[n][n];
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(max < val) {
					max = val;
				}
			}
		}
		
		res = 1;
		
		for (int i = 1; i < max; i++) {
			copy(true);
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					if(arr[j][j2] <= i) {
						arr[j][j2] = -1;
					}
				}
			}
			go();
			copy(false);
		}
		System.out.println(res);
		
		
	}

	private static void go() {
		visited = new boolean[n][n];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visited[i][j] && arr[i][j] != -1) {
					visited[i][j] = true;
					bfs(i, j);
					++cnt;
				}
			}
		}
		if(res < cnt) {
			res = cnt;
		}
		
	}

	private static void bfs(int y, int x) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		Queue<Point_2648> pointq = new LinkedList<Point_2648>();
		pointq.add(new Point_2648(y, x));
		
		while(!pointq.isEmpty()) {
			Point_2648 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& arr[nextY][nextX] != -1 && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					pointq.add(new Point_2648(nextY, nextX));
				}
			}
		}
		
	}

	private static void copy(boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(type)
					copy[i][j] = arr[i][j];
				else
					arr[i][j] = copy[i][j];
			}
		}
	}
}

class Point_2648 {
	int y;
	int x;

	public Point_2648(int y, int x) {
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
