package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2178 {
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]); //����
		m = Integer.parseInt(temp[1]); //����
		int[][] arr = new int [n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(str[j - 1]);
			}
		}
		new Main_2178().bfs(arr);
	}

	private void bfs(int[][] arr) {
		Queue<Bot_2178> q = new LinkedList<Bot_2178>();
		Queue<Integer> res = new LinkedList<Integer>();
		boolean[][] visited = new boolean[n + 1][m + 1];

		q.add(new Bot_2178(1, 1, 1));
		visited[1][1] = true;
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		
		while (!q.isEmpty()) {
			Bot_2178 bot = q.poll();
			int x = bot.getX();
			int y = bot.getY();
			int cnt = bot.getCnt();
			
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX >= 1 && nextX <= m && nextY >= 1 && nextY <= n
						&& arr[nextY][nextX] != 0) {
					if(nextX == m && nextY == n) {
						res.add(cnt + 1);
						continue;
					} else if(!visited[nextY][nextX]){
						visited[nextY][nextX] = true;
						int nextCnt = bot.getCnt() + 1;
						q.add(new Bot_2178(nextX, nextY, nextCnt));
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		while(!res.isEmpty()) {
			int val = res.poll();
			if(min > val)
				min = val;
		}
		System.out.println(min);
	}
}

class Bot_2178 {
	
	private int x;
	private int y;
	private int cnt;
	
	public Bot_2178(int x, int y, int cnt) {
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
