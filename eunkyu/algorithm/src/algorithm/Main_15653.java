package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//14:20 ~
public class Main_15653 {
	
	static int n;	//����
	static int m;	//����
	static char[][] arr;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new char[n][m];
		Ball_15653 ball = new Ball_15653();
		for (int i = 0; i < n; i++) {
			String info = br.readLine();
			for (int j = 0; j < m; j++) {
				char val = info.charAt(j);
				if(val == 'B') {
					ball.setBx(j);
					ball.setBy(i);
				}
				if(val == 'R') {
					ball.setRx(j);
					ball.setRy(i);
				}
				arr[i][j] = val;
			}
		}
		
		res = -1;
		goGame(ball);
		System.out.println(res);
		
	}

	private static void goGame(Ball_15653 ball) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][][][] visited = new boolean[n][m][n][m];
		visited[ball.getRy()][ball.getRx()][ball.getBy()][ball.getBx()] = true;
		Queue<Ball_15653> balls = new LinkedList<Ball_15653>();
		balls.add(ball);

		while(!balls.isEmpty()) {
			Ball_15653 b = balls.poll();
			int rx = b.getRx();
			int ry = b.getRy();
			int bx = b.getBx();
			int by = b.getBy();
			int cnt = b.getCnt();
			if(arr[ry][rx] == 'O' && arr[by][bx] != 'O') {
				res = cnt;
				return;
			}
				
			for (int dir = 0; dir < 4; dir++) {
				int nextRx = rx + dx[dir];
				int nextRy = ry + dy[dir];
				int nextBx = bx + dx[dir];
				int nextBy = by + dy[dir];
				
				while(true) {
					if (arr[nextRy][nextRx] != '#' && arr[nextRy][nextRx] != 'O') {
						nextRx += dx[dir];
						nextRy += dy[dir];
						
					} else {
						if(arr[nextRy][nextRx] == '#') {
							nextRx -= dx[dir];
							nextRy -= dy[dir];	
						}						
						break;
					}
				}

				while(true) {
					if (arr[nextBy][nextBx] != '#' && arr[nextBy][nextBx] != 'O') {
						nextBx += dx[dir];
						nextBy += dy[dir];
						
					} else {
						if(arr[nextBy][nextBx] == '#') {
							nextBx -= dx[dir];
							nextBy -= dy[dir];
						}
						break;
					}
				}
				if(arr[nextBy][nextBx] == 'O') {
					visited[nextRy][nextRx][nextBy][nextBx] = true;
					break;
				}
				if(nextRx == nextBx && nextRy == nextBy) {
					int rDist = Math.abs(rx - nextRx) + Math.abs(ry - nextRy);
					int bDist = Math.abs(bx - nextBx) + Math.abs(by - nextBy);
					if(rDist > bDist) {
						nextRx -= dx[dir];
						nextRy -= dy[dir];
					} else {
						nextBx -= dx[dir];
						nextBy -= dy[dir];
					}
				}
				if(!visited[nextRy][nextRx][nextBy][nextBx]) {
					visited[nextRy][nextRx][nextBy][nextBx] = true;
					balls.add(new Ball_15653(nextRx, nextRy, nextBx, nextBy, cnt + 1));
				}
			}	
		}		
	}
}

class Ball_15653 {
	int rx;
	int ry;
	int bx;
	int by;
	int cnt;
	
	public Ball_15653() {
		super();
	}
	public Ball_15653(int rx, int ry, int bx, int by, int cnt) {
		super();
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
	
	public int getRx() {
		return rx;
	}
	public void setRx(int rx) {
		this.rx = rx;
	}
	public int getRy() {
		return ry;
	}
	public void setRy(int ry) {
		this.ry = ry;
	}
	public int getBx() {
		return bx;
	}
	public void setBx(int bx) {
		this.bx = bx;
	}
	public int getBy() {
		return by;
	}
	public void setBy(int by) {
		this.by = by;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}