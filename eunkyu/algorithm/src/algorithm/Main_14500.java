package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14500 {
	
	static int n;
	static int m;
	static boolean[][] visited;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = true;
				int val = dfs(arr, i, j, 1, arr[i][j]);
				int diffCase = addCase(arr, i, j, arr[i][j]);
				if(max < val) {
					max = val;
				}
				if(diffCase > max) {
					max = diffCase;
				}
				visited[i][j] = false;//����?
			}
		}
		System.out.println(max);
	}

	private static int dfs(int[][] arr, int i, int j, int num, int val) {
		if(num == 4) {
			return val;
		}
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int data = 0;

		for (int idx = 0; idx < 4; idx++) {
			int nextX = j + dx[idx];
			int nextY = i + dy[idx];
			if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
					&& !visited[nextY][nextX]) {
				visited[nextY][nextX] = true;
				data = Math.max(dfs(arr, nextY, nextX, (num + 1), (val + arr[nextY][nextX])), data);
				visited[nextY][nextX] = false;
			}
		}
		
		return data;
		
	}

	private static int addCase(int[][] arr, int i, int j, int val) {
		//�� �� �� ��
		//x-1, x+1, y-1 | x-1, x+1, y+1 | x+1, y-1, y+1, | x-1, y-1, y+1
		int[][] dx = {{-1, +1, 0}, {-1, +1, 0}, {1, 0, 0}, {-1, 0, 0}};
		int[][] dy = {{0, 0, -1}, {0, 0, 1}, {0, -1, 1}, {0, -1, 1}};
		int max = 0;
		int sum = 0;
		for (int idx = 0; idx < 4; idx++) {
			sum = val;
			for (int idx2 = 0; idx2 < 3; idx2++) {
				int nextX = j + dx[idx][idx2];
				int nextY = i + dy[idx][idx2];
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
					sum += arr[nextY][nextX];
				} else {
					sum = 0;
					break;
				}
			}
			if(max < sum) {
				max = sum;
			}
		}
		return max;
	}
}
