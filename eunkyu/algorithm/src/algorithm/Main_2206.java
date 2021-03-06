package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2206 {
	
	static int n;	//����
	static int m;	//����
	static int[][] arr;
	static boolean[][][] visited;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		Queue<Point_2206> pointq = new LinkedList<Point_2206>();
		for (int i = 0; i < n; i++) {
			String info = br.readLine();
			for (int j = 0; j < m; j++) {
				int val = (int)(info.charAt(j) - '0');
				arr[i][j] = val;
				if(val != 0) {
					pointq.add(new Point_2206(i, j, 0, false));
				}
			}
		}
		
		
		res = -1;
		visited = new boolean[n][m][2];
		bfs();
		System.out.println(res);
	}
	private static void print() {
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void bfs() {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		Queue<Point_2206> pointq = new LinkedList<>();
		pointq.add(new Point_2206(0, 0, 1, false));
		visited[0][0][0] = true;
		
		while(!pointq.isEmpty()) {
			Point_2206 point = pointq.poll();
			int cnt = point.getCnt();
			boolean isCrush = point.isCrush();
			if(point.getX() == (m - 1) && point.getY() == (n - 1)) {
				res = cnt;
				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nextX = point.getX() + dx[dir];
				int nextY = point.getY() + dy[dir];				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
					if(arr[nextY][nextX] == 0) {
						if(!isCrush && !visited[nextY][nextX][0]) {
							visited[nextY][nextX][0] = true;
							pointq.add(new Point_2206(nextY, nextX, cnt + 1, isCrush));
						}
						else if(isCrush && !visited[nextY][nextX][1]) {
							visited[nextY][nextX][1] = true;
							pointq.add(new Point_2206(nextY, nextX, cnt + 1, isCrush));
						}
					} else if(arr[nextY][nextX] == 1){
						if(!isCrush) {
							visited[nextY][nextX][1] = true;
							pointq.add(new Point_2206(nextY, nextX, cnt + 1, true));
						}
					}
				}
			}	
		}
		
		
	}
}

class Point_2206 {
	int y;
	int x;
	int cnt;
	boolean isCrush;
	
	public Point_2206(int y, int x, int cnt, boolean isCrush) {
		super();
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.isCrush = isCrush;
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

	public boolean isCrush() {
		return isCrush;
	}

	public void setCrush(boolean isCrush) {
		this.isCrush = isCrush;
	}
	
}
