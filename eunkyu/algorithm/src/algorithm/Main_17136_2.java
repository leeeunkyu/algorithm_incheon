package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17136_2 {
	
	static int n = 10;
	static int[][] arr;
	static int[] nums = {0, 5, 5, 5, 5, 5};
	static boolean[][] visited;
	static int sCnt;
	static int dCnt;
	static int min;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(val == 1) {
					sCnt++;
				}
			}
		}
		visited = new boolean[n][n];
		min = Integer.MAX_VALUE;
		if(sCnt == 0) {
			System.out.println(0);
			return;
		}
		goGame(0, 0);
		if(min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}

	private static void goGame(int y, int x) {
		if(sCnt == dCnt && min > cnt) {
			min = cnt;
			return;
		}
		if(cnt >= min - 1) {
			return;
		}
		loop:
		for (int i = y; i < n; i++) {
			for (int j = x; j < n; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					y = i;
					x = j;
					break loop;
				}
			}
			x = 0;
		}
		
		for (int i = 5; i >= 1; i--) {
			if(nums[i] > 0 && check(y, x, i)) {
				draw(y, x, i);
				++cnt;
				nums[i] -= 1;
				goGame(y, x + i);
				--cnt;
				nums[i] += 1;
				erase(y, x, i);
			}
		}
		
		
	}

	private static void erase(int y, int x, int idx) {
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				visited[i][j] = false;
				dCnt--;
			}
		}
	}

	private static void draw(int y, int x, int idx) {
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				visited[i][j] = true;
				dCnt++;
			}
		}
	}

	private static boolean check(int y, int x, int idx) {
		/* 0 1 2 3 4 5
		 * 0 0 0 0 0 0 0
		 * 0 0 1 1 1 0 1   y = 1, x = 2  idx = 3
		 * 0 0 1 1 1 0 2
		 * 0 0 1 1 1 0 3
		 * 0 0 0 0 0 0 4
		 * 0 0 0 0 0 0 5
		 */
		
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				if(i >= n || j >= n || 
						arr[i][j] == 0 || visited[i][j])
					return false;
			}
		}
		return true;
	}
}