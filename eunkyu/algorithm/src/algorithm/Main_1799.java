package algorithm;

import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1799 {
	
	static int n;
	static int[][] arr;
	static int[][] colorMap;
	static boolean[][] visited;
	static int aMax;
	static int bMax;
	static int aNum;
	static int bNum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		colorMap = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						colorMap[i][j] = 1;
						if(val == 1) {
							aNum += 1;
						}
					} else {
						if(val == 1) {
							bNum += 1;
						}
					}
				} else {
					if(j % 2 != 0) {
						colorMap[i][j] = 1;
						if(val == 1) {
							aNum += 1;
						}
					} else {
						if(val == 1) {
							bNum += 1;
						}
					}
				}
			
			}
		}
		System.out.println(aNum+"  "+bNum);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(colorMap[i][j]);
			}
			System.out.println();
		}
		
		goGame();

	}

	private static void goGame() {
		visited = new boolean[n][n];
		int resa = 0;
		int resb = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 1 & !visited[i][j]) {
					visited[i][j] = true;
					arr[i][j] = 2;
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
						dfs(i, j, 1, 0, 0);
					} else {
						dfs(i, j, 0, 0, 0);
					}
					arr[i][j] = 1;
					visited[i][j] = false;
				}
			}
		}
		System.out.println(aMax + bMax);
		
	}

	private static void dfs(int y, int x, int color, int cnt, int rCnt) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		if(color == 1 && aNum == cnt) {
			System.out.println("??");
			if(rCnt > aMax) {
				aMax = rCnt;
			}
			System.out.println("rCnt: "+rCnt);
		}
		if(color == 0 && bNum == cnt) {
			System.out.println("!!");
			if(rCnt > bMax) {
				bMax = rCnt;
			}
			System.out.println("rCnt: "+rCnt);

		}
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
/*			System.out.println(nextX >= 0 && nextX < n);
			System.out.println(nextY >= 0 && nextY < n);
			System.out.println(!visited[nextY][nextX]);
			System.out.println(arr[nextY][nextX]);
			System.out.println(colorMap[nextY][nextX] == color);*/
			if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
					&& !visited[nextY][nextX] && arr[nextY][nextX] != 0
					&& colorMap[nextY][nextX] != color) {
				if(checkOne(y, x)) {
					visited[nextY][nextX] = true;
					arr[nextY][nextX] = 2;
					dfs(nextY, nextX, color, cnt + 1, rCnt + 1);
					visited[nextY][nextX] = false;
					arr[nextY][nextX] = 1;
				} else {
					dfs(nextY, nextX, color, cnt + 1, rCnt);
				}
			}
		}
	
	}
	private static boolean checkOne(int y, int x) {
		// \���� üũ
		int[] dx = {-1, 1, -1, 1};
		int[] dy = {1, -1, -1, 1};
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			while(nextX >= 0 && nextX < n && nextY >=0 && nextY < n) {
				if(arr[nextY][nextX] == 2) {
					return false;
				}
				nextX += dx[i];
				nextY += dy[i];
			}
		}
		return true;
	}

	private static boolean check(int y, int x) {
		// TODO Auto-generated method stub
		return false;
	}
}