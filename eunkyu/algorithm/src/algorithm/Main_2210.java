package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_2210 {
	
	static final int MAP_SIZE = 5;
	static int[][] arr;
	static int numCnt = 0;
	static HashSet<String> nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		arr = new int[MAP_SIZE][MAP_SIZE];
		
		for (int i = 0; i < MAP_SIZE; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < MAP_SIZE; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		nums = new HashSet<>();
		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				dfs(i, j, "");
			}
		}
		System.out.println(numCnt);
	}

	private static void dfs(int x, int y, String str) {
		if(str.length() == 6) {
			numCnt++;
			nums.add(str);
			return;
		}
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(nextX >= 0 && nextX < MAP_SIZE && nextY >= 0 && nextY < MAP_SIZE
					&& !nums.contains(str+arr[nextY][nextX])) {
				dfs(nextX, nextY, str+arr[nextY][nextX]);
			}
		}
	}
}
