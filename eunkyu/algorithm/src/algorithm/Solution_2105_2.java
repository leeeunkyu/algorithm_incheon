package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2105_2 {
	
	static int res = -1;
	static int n;
	static int[][] arr;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			visited = new boolean[101];
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}
			res = -1;
			goGame();
			sb.append("#"+tc+" "+res+"\n");
			
		}
		System.out.println(sb);
	}

	private static void goGame() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int a = 1; a < n - 1; a++) {
					for (int b = 1; b < n - 1; b++) {
						if(j + a < n && i + a + b < n
								&& j - b >= 0 && res < 2 * (a + b)) {
							visitClear();
							int[] dx = {1, -1, -1, 1};
							int[] dy = {1, 1, -1, -1};
							int pivotY = i;
							int pivotX = j;
							//visited[arr[pivotY][pivotX]] = true;
							boolean isAble = true;

							for (int dir = 0; dir < 4; dir++) {
								int type = 0;
								if(dir % 2 == 0) {
									type = a;
								} else {
									type = b;
								}
								for (int k = 0; k < type; k++) {
									pivotY += dy[dir];
									pivotX += dx[dir];
									if(!visited[arr[pivotY][pivotX]]) {
										visited[arr[pivotY][pivotX]] = true;
									} else {
										isAble = false;
										break;
									}
								}
								
								if(!isAble) {
									break ;
								}	
							}
							if(!isAble) {
								continue;
							}
							
							res = 2 * (a + b);
						}
					}
				}
			}
		}
	}

	private static void visitClear() {
		for (int i = 0; i < 101; i++) {
			visited[i] = false;
		}
	}
}
