package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_13460 {
	
	static int n;
	static int m;
	static char[][] arr;
	static boolean[][][][] visited;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new char[n][m];
		visited = new boolean[n][m][n][m];
		Ball_13460 red = null;
		Ball_13460 blue = null;
		for (int i = 0; i < n; i++) {
			String info = br.readLine();
			for (int j = 0; j < m; j++) {
				char val = info.charAt(j);
				arr[i][j] = val;
				if(val == 'R') {
					red = new Ball_13460(i, j, true, 0);
				}
				if(val == 'B') {
					blue = new Ball_13460(i, j, false, 0);
				}
			}
		}
		
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}*/
		res = -1;
		goGame(red, blue);
		System.out.println(res);
	}

	private static void goGame(Ball_13460 red, Ball_13460 blue) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Ball_13460[]> ballList = new LinkedList<Ball_13460[]>();
		Ball_13460[] balls = new Ball_13460[2];
		balls[0] = red;
		balls[1] = blue;
		ballList.add(balls);
		visited[red.getY()][red.getX()][blue.getY()][blue.getX()] = true;
		while(!ballList.isEmpty()) {
			balls = ballList.poll();
			int rx = balls[0].getX();
			int ry = balls[0].getY();
			int bx = balls[1].getX();
			int by = balls[1].getY();
			int rCnt = balls[0].getCnt();
			int bCnt = balls[1].getCnt();
			
			if(arr[ry][rx] == 'O' && arr[by][bx] != 'O') {
/*				System.out.println("====== ry: "+ry+" rx:"+rx +" rCnt: "+rCnt+"=====");
*/				res = rCnt;
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nextRX = rx + dx[dir];
				int nextRY = ry + dy[dir];
				int nextBX = bx + dx[dir];
				int nextBY = by + dy[dir];
				
				while(true) {
					if (arr[nextRY][nextRX] != '#' && arr[nextRY][nextRX] != 'O') {
						nextRX += dx[dir];
						nextRY += dy[dir];
					} else {
						if(arr[nextRY][nextRX] == '#') {
							nextRX -= dx[dir];
							nextRY -= dy[dir];	
						}
						break;
					}
						
				}
				
				while(true) {
					if(arr[nextBY][nextBX] != '#' && arr[nextBY][nextBX] != 'O') {
						nextBX += dx[dir];
						nextBY += dy[dir];
					} else {
						if(arr[nextBY][nextBX] == '#') {
							nextBX -= dx[dir];
							nextBY -= dy[dir];	
						}
						if(arr[nextBY][nextBX] == 'O') {
							visited[nextRY][nextRX][nextBY][nextBX] = true;
						}
						break;
					}
					
				}
							
				if(nextRX == nextBX && nextRY == nextBY) {
					if(arr[nextRY][nextRX] != 'O') {
						int redDist = Math.abs(rx - nextRX) + Math.abs(ry - nextRY);
						int blueDist = Math.abs(bx - nextBX) + Math.abs(by - nextBY);
						if(redDist > blueDist) {
							nextRX -= dx[dir];
							nextRY -= dy[dir];
						} else {
							nextBX -= dx[dir];
							nextBY -= dy[dir];
						}	
					} else {
						visited[nextRY][nextRX][nextBY][nextBX] = true;
					}
				}
				
				if(!visited[nextRY][nextRX][nextBY][nextBX]) {
					visited[nextRY][nextRX][nextBY][nextBX] = true;
				/*	System.out.println("ry: "+ry+" rx:"+rx +" rCnt: "+rCnt);
					System.out.println("nextRY: "+nextRY+" nextRX:"+nextRX +" rCnt: "+(rCnt + 1));
*/
					Ball_13460[] temp = new Ball_13460[2];
					temp[0] = new Ball_13460(nextRY, nextRX, true, rCnt + 1);
					temp[1] = new Ball_13460(nextBY, nextBX, false, bCnt + 1);
					ballList.add(temp);
				}
			}
		}
		
		
	}
}

class Ball_13460 {
	int y;
	int x;
	boolean type;
	int cnt;
	
	public Ball_13460(int y, int x, boolean type, int cnt) {
		super();
		this.y = y;
		this.x = x;
		this.type = type;
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
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}