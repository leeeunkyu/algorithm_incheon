package algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1799 {
	
	static int n;
	static int[][] arr;
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		int a = 0;
		cnt = 0;
		dfs(0, 0, 0, 0);
		a = cnt;
		int b = 0;
		cnt = 0;
		dfs(1, 0, 1, 0);
		b = cnt;
		System.out.println(a + b);
	
	}

	private static void dfs(int x, int y, int type, int temp) {
		if(x >= n) {
			y += 1;
			x = 0;
		}
		if(y >= n) {
			if(cnt < temp)
				cnt = temp;
			return;
		}
		
		if((x + y) % 2 != type) {
			dfs(x + 1, y, type, temp);
			return;
		}
		
		if(arr[y][x] == 1 && check(x, y, type)) {
			arr[y][x] = 2;
			dfs(x + 2, y, type, temp + 1);
			arr[y][x] = 1;
		}
		dfs(x + 2, y, type, temp);
		
	}

	private static boolean check(int x, int y, int type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 2 && Math.abs(i - y) == Math.abs(j - x)) {
					return false;
				}
			}
		}
		return true;
	}
}