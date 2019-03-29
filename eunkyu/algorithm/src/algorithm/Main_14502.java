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
	static int safeCnt;
	//0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		copy = new int[n][m];
		int size = 0;
		Point_14502[] points = new Point_14502[n * m];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				int val = Integer.parseInt(info[j]);
				arr[i][j] = val;
				copy[i][j] = val;
				if(val == 0) {
					points[size] = new Point_14502(i, j);
					++size;
				}
					
			}
		}
		int res = 0;
		size -= 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					setWall(i, j);
					arr[i][j] = 0;
				}
			}
		}
		
		
		//비트마스크는 30가지 경우의 수가넘어가면 인티져 범위를 초과한다.
		for (int i = 0; i < (1 << size); i++) {
			System.out.println(Integer.bitCount(i)+" i: "+i);
			if(Integer.bitCount(i) == 3) {
				System.out.println("??");
				Point_14502[] temps = new Point_14502[3];
				int temp = 0;
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						temps[temp] = points[j];
						++temp;
					}
				}
				setWall(temps);
				goVirus();
				print();
				safeCnt = 0;
				safeSection();
				print();
				setInit();
				
				if(res < safeCnt) {
					res = safeCnt;
				}
			}
		}
		System.out.println(res);
	}
	private static void setWall(int i, int j) {
		// TODO Auto-generated method stub
		
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
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 0 && !visited[i][j]) {
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
		visited[y][x] = true;
		safeCnt += 1;
		while(!pointq.isEmpty()) {
			Point_14502 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX] && arr[nextY][nextX] == 0) {
					visited[nextY][nextX] = true;
					pointq.add(new Point_14502(nextY, nextX));
					safeCnt += 1;
				}
			}	
		}
		
	}
	private static void setInit() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = copy[i][j];
			}
		}
	}
	private static void setWall(Point_14502[] temps) {
		for (int i = 0; i < temps.length; i++) {
			arr[temps[i].getY()][temps[i].getX()] = 1;	
		}
		
	}
	private static void goVirus() {
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 2 && !visited[i][j]) {
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
		visited[y][x] = true;
		
		while(!pointq.isEmpty()) {
			Point_14502 point = pointq.poll();
			int px = point.getX();
			int py = point.getY();
			
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX] && arr[nextY][nextX] != 1) {
					visited[nextY][nextX] = true;
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
