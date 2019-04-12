package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16948 {
	
	static int n;
	static int[][] arr;
	static int r1;
	static int c1;
	static int r2;
	static int c2;
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		String[] str = br.readLine().split(" ");
		r1 = Integer.parseInt(str[0]);
		c1 = Integer.parseInt(str[1]);
		r2 = Integer.parseInt(str[2]);
		c2 = Integer.parseInt(str[3]);
		res = -1;
		bfs();
		System.out.println(res);
	
	}
	private static void bfs() {
		boolean[][] visited = new boolean[n][n];
		int[] dx = {-1, 1, -2, 2, -1, 1};
		int[] dy = {-2, -2, 0, 0, 2, 2};
		
		Queue<Point_16948> pq = new LinkedList<Point_16948>();
		pq.add(new Point_16948(r1, c1, 0));
		
		while(!pq.isEmpty()) {
			Point_16948 p = pq.poll();
			int px = p.getX();
			int py = p.getY();
			int step = p.getStep();
			if(px == c2 && py == r2) {
				res = step;
				break;
			}
			for (int dir = 0; dir < 6; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					pq.add(new Point_16948(nextY, nextX, step + 1));
				}
			}
		}
	}
}

class Point_16948{
	int y;
	int x;
	int step;
	
	public Point_16948(int y, int x, int step) {
		super();
		this.y = y;
		this.x = x;
		this.step = step;
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
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
}
