package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_5656 {

	static int n;
	static int width;
	static int hight;
	static int[][] arr;
	static int min;
	//0은 빈 공간 그 외의 숫자는 벽돌
	//상 하 좌 우로 숫자 -1 만큼 제거
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			
			n = Integer.parseInt(str[0]);
			width = Integer.parseInt(str[1]);
			hight = Integer.parseInt(str[2]);
			
			arr = new int[hight][width];
			
			for (int i = 0; i < hight; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < width; j++) {
					arr[i][j] = Integer.parseInt(info[j]);
				}
			}
			min = Integer.MAX_VALUE;
			goGame(0);
			sb.append("#"+tc+" "+min+"\n");
		}
		System.out.println(sb);
	}
	private static void goGame(int cnt) {
		if(cnt == n) {
			int blockCnt = 0;
			for (int i = 0; i < hight; i++) {
				for (int j = 0; j < width; j++) {
					if(arr[i][j] != 0) {
						blockCnt += 1;
					}
				}
			}
			if(min > blockCnt) {
				min = blockCnt;
			}
			return;
		}
		for (int i = 0; i < width; i++) {
			int[][] copyMap = new int[hight][width];

			copy(true, copyMap);
			dropBall(i);
			
			goGame(cnt + 1);
			copy(false, copyMap);
		}
		
	}
	private static void copy(boolean type, int[][] copyMap) {
		if (type) {
			for (int i = 0; i < hight; i++) {
				for (int j = 0; j < width; j++) {
					copyMap[i][j] = arr[i][j];
				}
			}
		} else {
			for (int i = 0; i < hight; i++) {
				for (int j = 0; j < width; j++) {
					arr[i][j] = copyMap[i][j];
				}
			}
		}
	}
	private static void dropBall(int idx) {
		int h = 0;
		int val = 0;
		for (int i = 0; i < hight; i++) {
			val = arr[i][idx];
			if(val != 0) { 
				arr[i][idx] = 0;
				h = i;
				break;
			}
		}
		
		if(val > 1) {
			boom(val, h, idx);
		}
/*		System.out.println("reset");
		for (int j = 0; j < hight; j++) {
			for (int j2 = 0; j2 < width; j2++) {
				System.out.print(arr[j][j2]+" ");
			}
			System.out.println();
		}
		System.out.println();*/
		reset();
	/*	System.out.println("reset 끝");
		for (int j = 0; j < hight; j++) {
			for (int j2 = 0; j2 < width; j2++) {
				System.out.print(arr[j][j2]+" ");
			}
			System.out.println();
		}
		System.out.println();*/
	}
	private static void reset() {
		for (int x = 0; x < width; x++) {
			Queue<Integer> q = new LinkedList<Integer>();
			for (int y = hight - 1; y >= 0; y--) {
				int val = arr[y][x];
				if(val != 0) {
					q.add(val);
					arr[y][x] = 0;
				}
			}
			int tempY = hight - 1;
			while(!q.isEmpty()) {
				arr[tempY][x] = q.poll();
				tempY--;
			}
		}
	}
	private static void boom(int val, int h, int idx) {
		int y = h;
		int x = idx;
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_5656> points = new LinkedList<>();
		points.add(new Point_5656(y, x, val));
		while (!points.isEmpty()) {
			Point_5656 point = points.poll();
			int px = point.getX();
			int py = point.getY();
			int pval = point.getVal();
			for (int v = 0; v < pval - 1; v++) {			
				for (int i = 0; i < 4; i++) {
					
					int nextX = px + dx[i];
					int nextY = py + dy[i];
					
					for (int j = 0; j < v; j++) {
						nextX += dx[i];
						nextY += dy[i];
					}
					
					if(nextX >= 0 && nextX < width && nextY >= 0 && nextY < hight) {
						if(arr[nextY][nextX] > 1) {
							//boom(arr[nextY][nextX], nextY, nextX);
							points.add(new Point_5656(nextY, nextX, arr[nextY][nextX]));
							arr[nextY][nextX] = 0;
						} else {
							arr[nextY][nextX] = 0;
						}
					}
				}	
			}	
		}
	}
	
}

class Point_5656 {
	int y;
	int x;
	int val;
	
	public Point_5656(int y, int x, int val) {
		super();
		this.y = y;
		this.x = x;
		this.val = val;
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
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}	
}
