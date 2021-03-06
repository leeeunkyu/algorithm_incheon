package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1799_copy {
	static int map[][], N, max = 0, temp = 0;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		
		for (int i = 0; i< N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ;j < N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int wcount = 0;
		int bcount = 0;

		
		search(0, 0, 0, 0);
		wcount = max;
		max = 0; 
		
		search(0, 1, 1, 0);
		bcount = max;
		
		int ans = wcount + bcount;
		System.out.println(ans);
		
	}
	
	
	static void search(int y, int x, int in, int temp) {
		max = Math.max(max, temp);
		
		if (x >= N) {
			y += 1;
			x = 0;
		}
		
		if (y >= N ) {
			return;
		}
		
		if ((y + x) % 2 != in) {
			search(y, x + 1, in, temp);
			return;
		}
		
		if ((y + x) % 2 == in && check(y, x, in) && map[y][x] == 1) {
			map[y][x] = 2;
			search(y, x + 2, in, temp + 1);
			map[y][x] = 1;
		}
		
		search(y, x + 2, in, temp);
	}
	
	
	static boolean check(int y, int x, int num) {
		for (int i = 0; i < N; i++) {
			for (int j =0 ;j < N; j++) {
				if ((i + j) % 2 != num) continue;
				if ((i + j) % 2 == num && map[i][j] == 2 && Math.abs(y-i) == Math.abs(x-j)) {
					return false;
				}
			}
		}
		return true;
	}
}