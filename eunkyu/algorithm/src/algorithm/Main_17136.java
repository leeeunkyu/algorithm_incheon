package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//13:20
public class Main_17136 {
	
	static int n = 10;
	static int[][] arr;
	static boolean[][] visited;
	static int[] nums = {0, 5, 5, 5 ,5, 5};
	static int total;
	static int drawNum;
	static int cnt;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[n][n];
		total = 0;
		cnt = 0;
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(val == 1)
					++total;
			}
		}

		visited = new boolean[n][n];
		res = -1;
		goGame(0, 0);
		System.out.println(res);
	}

	private static void goGame(int a, int b) {
		int y = 0;
		int x = 0;
		if(drawNum == total) {
			if(res == -1 || res > cnt)
				res = cnt;
			return;
		}
		if(res != -1 && cnt >= res) {
			return;
		}
		loop:
		for (int i = a; i < n; i++) {
			for (int j = b; j < n; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					y = i;
					x = j;
					break loop;
				}
			}
			a = 0;
			b = 0;
		}
		for (int i = 5; i >= 1; i--) {
			if(nums[i] > 0 && check(y, x, i)) {
				draw(y, x, i);
				nums[i] -= 1;
				++cnt;
				goGame(y, x + i);
				nums[i] += 1;
				--cnt;
				erase(y, x, i);
			}
		}
	}

	private static void erase(int y, int x, int idx) {
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				visited[i][j] = false;
				--drawNum;
			}
		}
	}

	private static void draw(int y, int x, int idx) {
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				visited[i][j] = true;
				++drawNum;
			}
		}
	}

	private static boolean check(int y, int x, int idx) {
		//idx 1, 2, 3....5
		for (int i = y; i < y + idx; i++) {
			for (int j = x; j < x + idx; j++) {
				if(i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || arr[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}
}
