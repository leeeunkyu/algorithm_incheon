package baekjoon;

import java.util.Scanner;

public class P_14500_tetromino {
	static int[][][] shape = {{{0,0},{0,1},{0,2},{0,3}},{{0,0},{1,0},{2,0},{3,0}}, //Â¦´ë±â
							{{0,0},{0,1},{1,0},{1,1}}, // ³×¸ð
							{{0,0},{1,0},{2,0},{2,1}},{{0,0},{1,0},{0,1},{0,2}},{{0,0},{0,1},{1,1},{2,1}},{{1,0},{1,1},{1,2},{0,2}}, // LÀÚ
							{{2,0},{2,1},{1,1},{0,1}},{{0,0},{1,0},{1,1},{1,2}},{{0,0},{0,1},{1,0},{2,0}},{{0,0},{0,1},{0,2},{1,2}}, // L´ëÄª
							{{0,0},{1,0},{1,1},{2,1}},{{1,0},{1,1},{0,1},{0,2}}, // ¤©ÀÚ
							{{0,0},{0,1},{1,1},{1,2}},{{0,1},{1,1},{1,0},{2,0}}, // ¤©ÀÚ ´ëÄª
							{{0,0},{0,1},{0,2},{1,1}},{{1,0},{0,1},{1,1},{2,1}},{{0,0},{1,0},{2,0},{1,1}},{{1,0},{1,1},{0,1},{1,2}}}; // TÀÚ;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				loop:for (int k = 0; k < shape.length; k++) {
					int val = 0;
					for (int k2 = 0; k2 < 4; k2++) {
						int n_i = i+shape[k][k2][0];
						int n_j = j+shape[k][k2][1];
						if(n_i<0||n_j<0||n_i>=n||n_j>=m)continue loop;
						val+=arr[n_i][n_j];
					}
					result = result>val?result:val;
				}
			}
		}
		System.out.println(result);
	}

}
