package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_5213 {
	static int n;
	static int m;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = 2 * n;
		int[][] arr = new int[n][m];
		int line = (n * n) - (n / 2);
		int x = 0;
		int y = 1;
		for (int i = 0; i < line; i++) {
			String[] tailInfo = br.readLine().split(" ");
			if(y % 2 != 0) {	
				for (int j = 0; j < 2; j++) {
					arr[y - 1][x] = Integer.parseInt(tailInfo[j]);
					x++;
				}
				if(x == m) {
					++y;
					x = 0;
					continue;
				}
			}
			if(y % 2 == 0){
				for (int j = 0; j < 2; j++) {
					arr[y - 1][x+1] = Integer.parseInt(tailInfo[j]);
					x++;
				}
				if(x == 2*(n-1)) {
					++y;
					x = 0;
					continue;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		boolean[][] visited = bfs(arr);
		
		System.out.println();
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < 2 * n; j++) {
				if(visited[k][j])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	private static boolean[][] bfs(int[][] arr) {
		int[] dx = {0, 0, 1};
		int[] dy = {1, -1, 0};
		
		boolean[][] visited = new boolean[n][m];
		visited[0][0] = true;
		Queue<Point_5213> points = new LinkedList<Point_5213>();
		points.add(new Point_5213(0, 0));
		
		while(!points.isEmpty()) {
			Point_5213 point = points.poll();
			int x = point.getX();
			int y = point.getY();
			for (int i = 0; i < 3; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX]) {
					if(arr[nextY][nextX] == 0)
						continue;
					if(arr[y][x] == arr[nextY][nextX]) {
						visited[nextY][nextX] = true;
						points.add(new Point_5213(nextY, nextX));
					} else if(y == nextY) {
						if((y % 2 == 0) && (nextX % 2 != 0)) {
							visited[nextY][nextX] = true;
							points.add(new Point_5213(nextY, nextX));
						} else if((y % 2 != 0) && (nextX % 2 == 0)) {
							visited[nextY][nextX] = true;
							points.add(new Point_5213(nextY, nextX));
						}
					}
				}
				
			}
		}
		return visited;
		
	}
}

class Point_5213 {
	int y;
	int x;
	int nextCnt;
	
	public Point_5213(int y, int x) {
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