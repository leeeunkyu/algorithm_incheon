package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//10:30 ~ 11:30
public class Main_16234 {
	static int r;	
	static int l;	
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] copyMap;
	static boolean[][] notChange;
	static Queue<Integer> secq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		l = Integer.parseInt(str[1]);
		r = Integer.parseInt(str[2]);
		
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		int cnt = 0;
		secq = new LinkedList<Integer>();
		boolean conGame = goMoveGame();
		while (conGame) {
/*			System.out.println("game");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}*/
			cnt++;
			conGame = goMoveGame();
		}
		System.out.println(cnt);
		
	}
	private static boolean goMoveGame() {
		int openCnt = 1;
		boolean isOpen = true;
		copyMap = new int[n][n];
		visited = new boolean[n][n];
		notChange = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					if(bfs(i, j, openCnt)) {
/*						System.out.println("?? i: "+i+" j: "+j);
*/						openCnt++;
						isOpen = true;
					} else {
/*						System.out.println("i: "+i+" j: "+j);
*/						notChange[i][j] = true;
						isOpen = false;
					}
				}
			}
		}
		if(openCnt > 1) {
			int[] peopleCnt = new int[openCnt + 1];
			for (int i = 1; i < openCnt; i++) {
				peopleCnt[i] = secq.poll();
			}
			/*for (int i = 0; i < peopleCnt.length; i++) {
				System.out.print("i: "+i+" val: "+peopleCnt[i]+" ");
			}
			System.out.println();
			for (int i = 0; i < copyMap.length; i++) {
				for (int j = 0; j < copyMap.length; j++) {
					System.out.print(copyMap[i][j]+" ");
				}
				System.out.println();
			}*/
			for (int k = 0; k < n; k++) {
				for (int k2 = 0; k2 < n; k2++) {
					if(!notChange[k][k2])
						arr[k][k2] = peopleCnt[copyMap[k][k2]];
				}
			}
			return true;
		}
		return false;
	}

	private static boolean bfs(int i, int j, int openCnt) {
/*		System.out.println("bfs");
*/		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int sum = arr[i][j];
		int num = 1;
		boolean isOpen = false;
		visited[i][j] = true;
		Queue<Point_16234> points = new LinkedList<Point_16234>();
		points.add(new Point_16234(i, j));
		
		while(!points.isEmpty()) {
			Point_16234 point = points.poll();
			int x = point.getX();
			int y = point.getY();
			for (int idx = 0; idx < 4; idx++) {
				int nextX = x + dx[idx];
				int nextY = y + dy[idx];
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && 
						!visited[nextY][nextX] 
								&& l <= Math.abs(arr[nextY][nextX] - arr[y][x])
								&& r >= Math.abs(arr[nextY][nextX] - arr[y][x])) {
					visited[nextY][nextX] = true;
					copyMap[nextY][nextX] = openCnt;
					sum += arr[nextY][nextX];
					++num;
					points.add(new Point_16234(nextY, nextX));
					isOpen = true;
				}
			}
		}
		
		int val = sum/num;
		if(isOpen) {
			copyMap[i][j] = openCnt;
			secq.add(val);
		}
		return isOpen;
	}
}

class Point_16234 {
	int y;
	int x;
	
	public Point_16234(int y, int x) {
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