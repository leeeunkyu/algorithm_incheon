package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution_1767 {
	
	static int n;
	static int size;
	static int maxCore;
	static int minLine;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			Core_1767[] cores= new Core_1767[12];
			size = 0;
			
			for (int i = 0; i < n; i++) {
				String[] cellInfo = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int val = Integer.parseInt(cellInfo[j]);
					arr[i][j] = val;
					if(val == 1) {
						cores[size] = new Core_1767(i, j);
						++size;
					}
				}
			}
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			dfs(arr, cores, 0, 0);
			sb.append("#"+tc+" "+minLine+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int[][] arr, Core_1767[] cores, int coreCnt, int realCnt) {
		if(size <= coreCnt) {
			int lineCnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] == 2)
						++lineCnt;
				}
			}

			if(realCnt >= maxCore) {
				if(realCnt == maxCore) {
					if(minLine >= lineCnt) {
						minLine = lineCnt;
					}
				}
				else if(realCnt > maxCore) {
					minLine = lineCnt;
				}
				maxCore = realCnt;

			}
			return;
		}
		Core_1767 core = cores[coreCnt];
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		int x = core.getX();
		int y = core.getY();
		int failCnt = 0;
		loop:
		for (int i = 0; i < 4; i++) {
			
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			while(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
				if(arr[nextY][nextX] == 1 || arr[nextY][nextX] == 2) {
					++failCnt;
					if(failCnt == 4) {
						dfs(arr, cores, coreCnt+1, realCnt);
					}
					continue loop;
				}
				nextX += dx[i];
				nextY += dy[i];
			}
			
			nextX -= dx[i];
			nextY -= dy[i];
			int tempX = nextX;
			int tempY = nextY;
			
			while (!(tempX == x && tempY == y)) {
				arr[tempY][tempX] = 2;
				tempX -= dx[i];
				tempY -= dy[i];
			}

			dfs(arr, cores, coreCnt+1, realCnt+1);				
			
			while (!(nextX == x && nextY == y)) {
				arr[nextY][nextX] = 0;
				nextX -= dx[i];
				nextY -= dy[i];
			}
		}		
	}
	
}

class Core_1767 {
	int y;
	int x;
	
	public Core_1767(int y, int x) {
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
