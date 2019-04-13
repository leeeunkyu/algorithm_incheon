package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2239 {
	
	static int n = 9;
	static int[][] arr;
	static boolean[][] visited;
	static int zeroCnt;
	static int resCnt;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				int val = str.charAt(j) - '0';
				arr[i][j] = val;
				if(val == 0)
					zeroCnt++;
				
			}
		}
		
		dfs(0, 0);
		
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	private static void dfs(int y, int x) {
		if(zeroCnt == resCnt) {
			print();
			System.exit(1);
		}
		loop:
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 0 && !visited[i][j]) {
					//System.out.println("i: "+i+" j: "+j);
					y = i;
					x = j;
					break loop;
				}
			}
		}
		
		if(y == 6 && x == 7) {
			//System.out.println("stop");
		}
		for (int i = 1; i <= 9; i++) {
			arr[y][x] = i;
			if(check(y, x)) {
				visited[y][x] = true;
				resCnt++;
			//	print();
				dfs(0, 0);
				resCnt--;
				visited[y][x] = false;
				arr[y][x] = 0;
			} else {
				arr[y][x] = 0;
			}
		}
	}

	private static boolean check(int y, int x) {
		boolean[] isNum = new boolean[10];
		for (int i = 0; i < n; i++) {
			if(arr[y][i] == 0) {
				continue;
			}
			if(!isNum[arr[y][i]]) {
				isNum[arr[y][i]] = true;
			} else {				
				return false;
			}
		}
		
		isNum = new boolean[10];
		for (int i = 0; i < n; i++) {
			if(arr[i][x] == 0) {
				continue;
			}
			if(!isNum[arr[i][x]]) {
				isNum[arr[i][x]] = true;
			} else {
				return false;
			}
		}
		int py;
		int px;
		
		isNum = new boolean[10];
		if(y < 3) {
			if(x < 3) {
				py = 0;
				px = 0;
			} else if(x>= 3 && x < 6) {
				py = 0;
				px = 3;
			} else {
				py = 0;
				px = 6;
			}
		} else if(y >= 3 && y < 6) {
			if(x < 3) {
				py = 3;
				px = 0;
			} else if(x>= 3 && x < 6) {
				py = 3;
				px = 3;
			} else {
				py = 3;
				px = 6;
			}
		} else {
			if(x < 3) {
				py = 6;
				px = 0;
			} else if(x>= 3 && x < 6) {
				py = 6;
				px = 3;
			} else {
				py = 6;
				px = 6;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(arr[py + i][px + j] == 0) {
					continue;
				}
				if(!isNum[arr[py + i][px + j]]) {
					isNum[arr[py + i][px + j]] = true;
				} else {
					return false;
				}
			}
		}
		
		return true;
	}
}
