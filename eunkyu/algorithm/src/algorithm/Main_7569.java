package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//18:30 ~ 
public class Main_7569 {
	
	static int m;	//상자칸의 가로수
	static int n;	//상자칸의 세로수
	static int h;	//높이
	static int[][][] arr;
	static boolean[][][] visited;
	static int hole;
	static Queue<Point_7569> points;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
	
		m = Integer.parseInt(str[0]);
		n = Integer.parseInt(str[1]);
		h = Integer.parseInt(str[2]);
		
		arr = new int[h][n][m];
		visited = new boolean[h][n][m];
		
		boolean c = false;	//전부 1인가
		boolean c2 = false;	//1이 하나도 없는가
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				String[] info = br.readLine().split(" ");
				for (int k = 0; k < m; k++) {
					int val = Integer.parseInt(info[k]);
					arr[i][j][k] = val;
					if(val == 0) {
						c = true;
					}
					if(val == 1) {
						c2 = true;
					}
					if(val == -1) {
						hole ++;
					}
				}
			}
		}
		
		if(!c) {
			System.out.println(0);
			return;
		}
		
		if(!c2) {
			System.out.println(-1);
			return;
		}
		
		init();		
	}

	private static void init() {
		points = new LinkedList<Point_7569>();
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(arr[i][j][k] == 1) {
						visited[i][j][k] = true;
						tomatoBfs(i, j, k, 0);
					}
				}
			}
		}
/*		print();
*/
		bfs();		
	}

	private static void bfs() {
		int time = 0;
		while(!points.isEmpty()) {
			Point_7569 point = points.poll();
			int pl = point.getL();
			int py = point.getY();
			int px = point.getX();
			int pt = point.getT();
			time = pt;
			tomatoBfs(pl, py, px, pt);
		}
/*		print();
*/		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(arr[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(time);
		
		
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					System.out.print(arr[i][j][k]+" ");
				}
				System.out.println();
			}
		}
	}

	private static void tomatoBfs(int pl, int py, int px, int pt) {
		int[] dx = {0, 0, 0, 0, 1, -1};
		int[] dy = {0, 0, 1, -1, 0, 0};
		int[] dl = {1, -1, 0, 0, 0, 0};
		
		
		for (int idx = 0; idx < 6; idx++) {
			int nextX = px + dx[idx];
			int nextY = py + dy[idx];
			int nextL = pl + dl[idx];
			
			if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
				&& nextL >= 0 && nextL < h && (arr[nextL][nextY][nextX] == 0 || arr[nextL][nextY][nextX] == 2)
				&& !visited[nextL][nextY][nextX]) {
				visited[nextL][nextY][nextX] = true;
				points.add(new Point_7569(nextL, nextY, nextX, pt + 1));
				arr[nextL][nextY][nextX] = 2;
			}
		}
		
	}
}

class Point_7569 {
	int y;
	int x;
	int l;
	int t;
	
	public Point_7569(int l, int y, int x, int t) {
		super();
		this.y = y;
		this.x = x;
		this.l = l;
		this.t = t;
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
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}
}
