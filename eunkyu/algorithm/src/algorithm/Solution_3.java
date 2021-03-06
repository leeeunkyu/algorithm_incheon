package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_3 {
	public static void main(String[] args) {
		String B [] = new String[6];
		B[0] = "..X...";
		B[1] = "......";
		B[2] = "....X.";
		B[3] = ".X....";
		B[4] = "..X.X.";
		B[5] = "...O..";
		System.out.println(new Solution_3().solution(B));
	}
	public int solution(String[] B) {
		int n = B.length;
		char arrList [][] = new char [n][n];
		boolean visited [][] = new boolean [n][n];
		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char val = B[i].charAt(j);
				arrList[i][j] = val;
				if(val == 'O') {
					x = j;
					y = i;
					break;
				}
			}
		}		
		return dfs(arrList, visited, x, y, false, n, 0);
    }
	private int dfs(char[][] arrList, boolean[][] visited, int x, int y, boolean isX, int n, int score) {
		
		int dx[] = {-1, 1};
		int dy[] = {-1, -1};
		for (int i = 0; i < 2; i++) {
			int nextX = x + dx[i]; //��
			int nextY = y + dy[i]; //��
			if(nextX >= 0 && nextX < n && nextY >= 0 && !visited[nextX][nextY]) {
				visited[nextY][nextX] = true;
				if (isX) {
					if(arrList[nextY][nextX] == 'X')
						break;
					score = Math.max(dfs(arrList, visited, nextX, nextY, false, n, score), ++score);
				} else if(arrList[nextY][nextX] == 'X') {
					score = Math.max(dfs(arrList, visited, nextX, nextY, true, n, score), score);
				} else {
					continue;
				}
			}
		}
		return score;
	}
	
	
}
