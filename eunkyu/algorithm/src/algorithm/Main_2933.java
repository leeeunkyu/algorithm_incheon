package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


//13시30분
public class Main_2933 {
	
	static int r;
	static int c;
	static char[][] arr;
	static int n;
	static int[] height;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			String info = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = info.charAt(j);
			}
		}
		
		n = Integer.parseInt(br.readLine());
		height = new int[n];
		String[] hInfo = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(hInfo[i]);
		}
		
		goGame();
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		
	}
	private static void goGame() {
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int i = 0; i < n; i++) {
			int h = height[i];
			int x = shootStick(r - h, i % 2);
			
		/*	System.out.println();
			for (int idx = 0; idx < r; idx++) {
				for (int j = 0; j < c; j++) {
					System.out.print(arr[idx][j]);
				}
				System.out.println();
			}*/
			
			
		/*	for (int j = 0; j < r; j++) {
				for (int j2 = 0; j2 < c; j2++) {
					System.out.print(arr[j][j2]+" ");
				}
				System.out.println();
			}
			System.out.println();*/
			int rx = x;
			int ry = r - h;
			for (int idx = 0; idx < 4; idx++) {
				visited = new boolean[r][c];

				int nextX = rx + dx[idx];
				int nextY = ry + dy[idx];
				if(nextX >= 0 && nextX < c && nextY >= 0 && nextY < r
						&& arr[nextY][nextX] == 'x') {
					if(!isDropStone(nextX, nextY)) {
/*						System.out.println("FALSE");
*/						dropStone();
					/*System.out.println();
					for (int idx2 = 0; idx2 < r; idx2++) {
						for (int j = 0; j < c; j++) {
							System.out.print(arr[idx2][j]);
						}
						System.out.println();
					}
					System.out.println();*/
					} else {
/*						System.out.println("TRUE");
*/					}
				}
			}
			
			
			
		}
	}
	private static void dropStone() {
		
/*		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(visited[i][j])
					System.out.print(1+" ");
				else
					System.out.print(0+" ");
			}
			System.out.println();
		}*/
		int tempH = 0;
		loop:
		for (int downH = 1; downH < r; downH++) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if(visited[i][j]) {
/*						System.out.println("i: "+i+" j: "+j);
*/						if(arr[i + downH][j] == 'x' && !visited[i + downH][j]) {
							tempH = downH - 1;
							break loop;
						}
						if(i + downH == r - 1) {
							tempH = downH;
							break loop;
						}
					}
				}
			}	
		}
		
/*		char[][] copy = new char[r][c];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(visited[i][j]) {
					arr[i][j] = '.';
				}
			}
		}*/
		for (int i = r - 1; i >= 0; i--) {
			for (int j = 0; j < c; j++) {
				if(visited[i][j] && arr[i][j] == 'x') {
					arr[i + tempH][j] = 'x';
					arr[i][j] = '.';
				}
			}
		}
		
	}
	private static boolean isDropStone(int x, int h) {
/*		System.out.println("x: "+x+" y: "+h+" arr: "+arr[h][x]);
*/		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		visited[h][x] = true;
		Queue<Point_2933> points  = new LinkedList<Point_2933>();
		points.add(new Point_2933(h, x));
		while(!points.isEmpty()) {
			Point_2933 point = points.poll();
			int px = point.getX();
			int py = point.getY();
			for (int i = 0; i < 4; i++) {
				int nextX = px + dx[i];
				int nextY = py + dy[i];
				if(nextY == r - 1) {
					return true;
				}
				if(nextX >= 0 && nextX < c && nextY >= 0 && nextY < r
						&& arr[nextY][nextX] == 'x' && !visited[nextY][nextX]) {
					points.add(new Point_2933(nextY, nextX));
					visited[nextY][nextX] = true;
				}
			}	
		}
		
		return false;
	}
	private static int shootStick(int h, int i) {
		int dx = 0;
		int x;
		if(i == 0) {
			x = -1;
			dx = 1;
			int nextX = x + dx;
			while(nextX >= 0 && nextX < c && arr[h][nextX] != 'x') {
				nextX += dx;
			}
			if(nextX >= 0 && nextX < c && arr[h][nextX] == 'x') {
				arr[h][nextX] = '.';
				x = nextX;
			}
			
			//i 가 0이면 왼쪽
		} else {
			x = c;
			dx = -1;
			int nextX = x + dx;
			while(nextX >= 0 && nextX < c && arr[h][nextX] != 'x') {
				nextX += dx;
			}
			if(nextX >= 0 && nextX < c && arr[h][nextX] == 'x') {
				arr[h][nextX] = '.';
				x = nextX;
			}
		}
		return x;
	}
}

class Point_2933 {
	int y;
	int x;
	
	public Point_2933(int y, int x) {
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
		
