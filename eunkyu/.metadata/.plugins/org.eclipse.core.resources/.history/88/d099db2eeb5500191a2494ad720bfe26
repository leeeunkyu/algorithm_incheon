package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//14:00 ~
public class Main_14502_2 {
	
	static int n;	//세로
	static int m;	//가로
	static int[][] arr;
	static int[][] copy;
	static Point_14502_2[] map;
	static Queue<Point_14502_2[]> pointq;
	static int cnt;
	static boolean[][] visited;
	static boolean[][] visited2;
	static int safeCnt;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		copy = new int[n][m];
		map = new Point_14502_2[n * m];
		cnt = 0;
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				int val = Integer.parseInt(info[j]);
				arr[i][j] = val;
				if(val == 0) {
					map[cnt] = new Point_14502_2(i, j);
					++cnt;
				}
			}
		}
		
		pointq = new LinkedList<Point_14502_2[]>();
		
		int r = 3;
		int[] combArr = new int[r];
		doCombnation(combArr, r, 0, 0);
		
		while(!pointq.isEmpty()) {
			Point_14502_2[] points = pointq.poll();
			copy(false);
			for (int i = 0; i < 3; i++) {
				Point_14502_2 point = points[i];
				arr[point.getY()][point.getX()] = 1;
			}
/*			print();
*/			goVirus();
			safeCnt = 0;
			safeSection();
			if(max < safeCnt)
				max = safeCnt;
			//System.out.println(safeCnt);
/*			for (int i = 0; i < 3; i++) {
				Point_14502_2 point = points[i];
				arr[point.getY()][point.getX()] = 0;
			}*/
			copy(true);

/*			print();
*/		}
		System.out.println(max);
	}
	private static void doCombnation(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			Point_14502_2[] points = new Point_14502_2[3];
			for (int i = 0; i < index; i++) {
				points[i] = map[combArr[i]];
			}
			pointq.add(points);
		} else if(target == cnt) {
			return;
		} else {
			combArr[index] = target;
			doCombnation(combArr, r - 1, index + 1, target + 1);
			doCombnation(combArr, r, index, target + 1);
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
		Queue<Point_14502_2> pointq = new LinkedList<Point_14502_2>();
		pointq.add(new Point_14502_2(y, x));
		visited2[y][x] = true;
		safeCnt += 1;
		while(!pointq.isEmpty()) {
			Point_14502_2 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited2[nextY][nextX] && arr[nextY][nextX] == 0) {
					visited2[nextY][nextX] = true;
					pointq.add(new Point_14502_2(nextY, nextX));
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
		Queue<Point_14502_2> pointq = new LinkedList<Point_14502_2>();
		pointq.add(new Point_14502_2(y, x));
		visited2[y][x] = true;
		
		while(!pointq.isEmpty()) {
			Point_14502_2 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited2[nextY][nextX] && arr[nextY][nextX] != 1) {
					visited2[nextY][nextX] = true;
					arr[nextY][nextX] = 2;
					pointq.add(new Point_14502_2(nextY, nextX));
				}
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
}


class Point_14502_2 {
	int y;
	int x;
	
	public Point_14502_2(int y, int x) {
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