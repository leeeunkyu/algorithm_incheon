package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_13460 {
	final static int MAX_COUNT = 10;
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]); //����
		m = Integer.parseInt(str[1]); //����
		char bord[][] = new char[n][m];
		RedBall redBall = new RedBall(0, 0, 0);
		BlueBall blueBall = new BlueBall(0, 0);
		for (int i = 0; i < n; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				char val = temp[j];
				bord[i][j] = val;
				if (val == 'R') {
					redBall = new RedBall(j, i, 0); 
				} else if (val == 'B') {
					blueBall = new BlueBall(j, i);
				}
			}
		}

		
		new Main_13460().bordGame(bord, redBall, blueBall);

	}
	private void bordGame(char[][] bord, RedBall redBall, BlueBall blueBall) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int safeLineX = m - 1;
		int safeLineY = n - 1;

		Queue<RedBall> redBalls = new LinkedList<RedBall>();
		Queue<BlueBall> blueBalls = new LinkedList<BlueBall>();
		
		redBalls.add(redBall);
		blueBalls.add(blueBall);
		boolean isGoal = false;
		int goalCnt = 0;
		Stack<Integer> s;
		while(true) {
			if(redBalls.isEmpty() && blueBalls.isEmpty())
				break;
			if(!redBalls.isEmpty())
				redBall = redBalls.poll();
			
			int rx = redBall.getX();
			int ry = redBall.getY();
			int cnt = redBall.getCnt();
			if(!blueBalls.isEmpty())
				blueBall = blueBalls.poll();
			
			int bx = blueBall.getX();
			int by = blueBall.getY();
			System.out.println("rx: "+rx+" ry:"+ry+" cnt: "+cnt);
			loop:
			for (int i = 0; i < 4; i++) {
				int nextRX = rx + dx[i];
				int nextRY = ry + dy[i];
				int nextBX = bx + dx[i];
				int nextBY = by + dy[i];
				int rCnt = 0;
				int bCnt = 0;
				isGoal = false;
				goalCnt = -1;
				while (true) {
					boolean r = false;
					boolean b = false;
					if(nextBX >= 1 && nextBX < safeLineX && nextBY >= 1 && nextBY < safeLineY
							 && bord[nextBY][nextBX] != '#') {
						if(bord[nextBY][nextBX] == 'O') {
							isGoal = false;
							goalCnt = -1;
							continue loop;
						}
						nextBX += dx[i];
						nextBY += dy[i];
						b = true;
						++bCnt;
					}					
					if(nextRX >= 1 && nextRX < safeLineX && nextRY >= 1 && nextRY < safeLineY
							 && bord[nextRY][nextRX] != '#') {
						if(bord[nextRY][nextRX] == 'O') {
							isGoal = true;
							if(goalCnt == -1)
								goalCnt = cnt + 1;
							//System.out.println(cnt + 1);
							//return;
						}
						nextRX += dx[i];
						nextRY += dy[i];
						r = true;
						++rCnt;
					}
					
					if(nextBX == nextRX && nextBY == nextRY) {
						if(rCnt > bCnt) {
							nextRX -= dx[i];
							nextRY -= dy[i];
						} else {
							nextBX -= dx[i];
							nextBY -= dy[i];
						}
						break;
					}

					if(!r && !b) {
						break;
					}
				}
				nextBX -= dx[i];
				nextBY -= dy[i];
				nextRX -= dx[i];
				nextRY -= dy[i];
				blueBalls.add(new BlueBall(nextBX, nextBY));
				
				redBalls.add(new RedBall(nextRX, nextRY, cnt + 1));	

			}
			if(isGoal) {
				System.out.println(goalCnt);
				return;
			}
			if(cnt == MAX_COUNT) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(-1);
	}
}

class RedBall {
	int x;
	int y;
	int cnt;

	public RedBall(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}

class BlueBall {
	int x;
	int y;
	
	public BlueBall(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}

