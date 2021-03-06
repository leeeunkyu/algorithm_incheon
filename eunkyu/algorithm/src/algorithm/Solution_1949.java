package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//10:00 ~ 10:50
public class Solution_1949 {
	
	static int n;
	static int k;
	static int[][] arr;
	static Queue<Point_1949> points;
	static int maxCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			k = Integer.parseInt(str[1]);
			arr = new int[n][n];
			int max = 0;
			points = new LinkedList<Point_1949>();
			
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int val = Integer.parseInt(info[j]);
					arr[i][j] = val;
					if(val == max)
						points.add(new Point_1949(i, j));
					if(val > max) {
						points.clear();
						max = val;
						points.add(new Point_1949(i, j));
					}
					
				}
			}
			maxCnt = 0;
			//System.out.println(points);
			goWalk(max);
			
			/*points.clear();
			points.add(new Point_1949(2, 4));
			test();*/
			sb.append("#"+tc+" "+maxCnt+"\n");	
		}
		System.out.println(sb);
	}

	private static void test() {
		while(!points.isEmpty()) {
	 		Point_1949 point = points.poll();
			arr[2][3] -= 1;
			print();
			dfs(2, 4, 1);
			arr[2][3] += 1;
		}
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

	private static void goWalk(int max) {
		while(!points.isEmpty()) {
	 		Point_1949 point = points.poll();
	 		for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(point.getX() == j && point.getY() == i) {
						continue;
					}
					for (int j2 = 0; j2 <= k; j2++) {
						arr[i][j] -= j2;
						dfs(point.getY(), point.getX(), 1);
						arr[i][j] += j2;
					}
				}
			}
		}
	}

	private static void dfs(int y, int x, int cnt) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int visited[][] = new int[n][n];
		
		if(maxCnt < cnt) {
			maxCnt = cnt;
		}
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
					&& (visited[nextY][nextX] == 0 || visited[nextY][nextX] > cnt + 1)
					&& arr[nextY][nextX] < arr[y][x]) {
				visited[nextY][nextX] = cnt + 1;
				dfs(nextY, nextX, cnt + 1);
			}
		}
	}

	private static void bfs(Point_1949 point) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean visited[][] = new boolean[n][n];
		Queue<Point_1949> points = new LinkedList<>();
		points.add(point);
		visited[point.getY()][point.getX()] = true;
		while(!points.isEmpty()) {
			Point_1949 p = points.poll();
			int x = p.getX();
			int y = p.getY();
			int cnt = p.getCnt();
			if(maxCnt < cnt) {
				maxCnt = cnt;
			}
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX] && arr[nextY][nextX] < arr[y][x]) {
					visited[nextY][nextX] = true;
					points.add(new Point_1949(nextY, nextX, cnt + 1));
				}
			}
		}
	}
}	

class Point_1949 {
	int y;
	int x;
	int cnt = 1;
	
	public Point_1949(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
	
	public Point_1949(int y, int x, int cnt) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point_1949 [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", cnt=");
		builder.append(cnt);
		builder.append("]");
		return builder.toString();
	}
	
	
}