package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
//19:00
public class Main_14502 {
	
	static int n; //세로크기
	static int m; //가로크기
	static int[][] arr;
	static int[][] copy;
	static boolean[][] visited;
	static boolean[][] visited2;
	static int safeCnt;
	static int max;
	//0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				int val = Integer.parseInt(info[j]);
				arr[i][j] = val;					
			}
		}
		visited = new boolean[n][m];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(!visited[i][j] && arr[i][j] == 0) {
					arr[i][j] = 1;
					visited[i][j] = true;
					setWall(0);
					visited[i][j] = false;
					arr[i][j] = 0;
				}
			}
		}
		System.out.println(max);
	}
	private static void setWall(int cnt) {
		if(cnt == 2) {
			copy(false);
			goVirus();
			safeCnt = 0;
			safeSection();
			if(max < safeCnt)
				max = safeCnt;
			copy(true);
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(!visited[i][j] && arr[i][j] == 0) {
					arr[i][j] = 1;
					visited[i][j] = true;
					setWall(cnt + 1);
					visited[i][j] = false;
					arr[i][j] = 0;
				}
			}
		}		
	}
	private static void copy(boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(type)
					arr[i][j] = copy[i][j];
				else
					copy[i][j] = arr[i][j];
			}
		}
	}
	private static void print() {
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void safeSection() {
		visited2 = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 0 && !visited2[i][j]) {
					bfs2(i, j);
				}
			}
		}
	}
	private static void bfs2(int y, int x) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_14502> pointq = new LinkedList<Point_14502>();
		pointq.add(new Point_14502(y, x));
		visited2[y][x] = true;
		safeCnt += 1;
		while(!pointq.isEmpty()) {
			Point_14502 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited2[nextY][nextX] && arr[nextY][nextX] == 0) {
					visited2[nextY][nextX] = true;
					pointq.add(new Point_14502(nextY, nextX));
					safeCnt += 1;
				}
			}	
		}
		
	}

	private static void goVirus() {
		visited2 = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 2 && !visited2[i][j]) {
					bfs(i, j);
				}
			}
		}
	}
	private static void bfs(int y, int x) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_14502> pointq = new LinkedList<Point_14502>();
		pointq.add(new Point_14502(y, x));
		visited2[y][x] = true;
		
		while(!pointq.isEmpty()) {
			Point_14502 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited2[nextY][nextX] && arr[nextY][nextX] != 1) {
					visited2[nextY][nextX] = true;
					arr[nextY][nextX] = 2;
					pointq.add(new Point_14502(nextY, nextX));
				}
			}	
		}
	}
}

class Point_14502 {
	int y;
	int x;
	
	public Point_14502(int y, int x) {
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
