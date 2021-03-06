package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_7 {
	
	static int n;
	static int m;
	static int[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			
			arr = new int[n][n];
			int maxcnt = 0;			
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(info[j]);
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int res = go(i, j);
					if(res > maxcnt)
						maxcnt = res;
				}
			}
			
			sb.append("#"+tc+" "+maxcnt+"\n");
		}
		System.out.println(sb);
	}

	private static int go(int y, int x) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[n][n];
		
		Queue<Point_7> pointq = new LinkedList<Point_7>();
		pointq.add(new Point_7(y, x, 1));
		visited[y][x] = true;
		int h = 0;
		int pre = 1;
		if(arr[y][x] == 1) {
			++h;
		}
		int preH = h;

		while(!pointq.isEmpty()) {
			Point_7 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			int kCnt = point.getkCnt();
			if(kCnt != pre) {
				pre = kCnt;
				int val = (kCnt * kCnt) + ((kCnt - 1) * (kCnt - 1));
				if(val <= (h * m)) {
					preH = h;
				} else {
				}
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX]) {
					if(arr[nextY][nextX] == 1)
						++h;
					visited[nextY][nextX] = true;
					pointq.add(new Point_7(nextY, nextX, kCnt + 1));
				}
			}
		}
		return preH;
	}

	private static void print(int pre, int kCnt, int preH, int h, boolean[][] visited) {
		System.out.println("이전 k값: "+pre+" 바뀔k값: "+kCnt+" 이전 집 수: "+preH+" 지금 집 수: "+h);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.println();
		}
	}
}

class Point_7 {
	int y;
	int x;
	int hCnt;
	int kCnt;
	
	public Point_7(int y, int x, int kCnt) {
		super();
		this.y = y;
		this.x = x;
		this.kCnt = kCnt;
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
	public int gethCnt() {
		return hCnt;
	}
	public void sethCnt(int hCnt) {
		this.hCnt = hCnt;
	}
	public int getkCnt() {
		return kCnt;
	}
	public void setkCnt(int kCnt) {
		this.kCnt = kCnt;
	}
}
