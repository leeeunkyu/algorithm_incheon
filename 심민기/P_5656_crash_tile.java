package swexpertacademy;

import java.util.Scanner;

public class P_5656_crash_tile {
	static int[][] map;
	static int n, w, h, crash_cnt_global;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
			int total_tile = 0;
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] != 0)
						total_tile++;
				}
			}
			int cr = drop_ball(n, map);
			System.out.println("토탈 : "+total_tile+", 부신것 : "+ cr+", 남은것 : "+(total_tile-cr));
		}
	}

	static int drop_ball(int ball_cnt, int[][] sub_map) {
		System.out.println("지금 남은 공의 갯수 : "+ball_cnt);
		int max_crash = 0;
		for (int i = 0; i < w; i++) {
			int[][] cp_map = copy_map(sub_map);
			System.out.println("부시기 전 맵상태");
			draw_map(cp_map);
			int crash_cnt = crash_oneline(i, cp_map);
			System.out.println("부시고 나서 맵상태");
			draw_map(cp_map);
			if (ball_cnt > 1) {
				down_tile(cp_map);
				System.out.println("벽돌을 내린다");
				draw_map(cp_map);
				crash_cnt += drop_ball(ball_cnt - 1, cp_map);
			}
			max_crash = max_crash > crash_cnt ? max_crash : crash_cnt;
		}
		return max_crash;
	}
	static void draw_map(int[][] cp_map) {
		System.out.println("-------그리기----------");
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print((cp_map[i][j]==0?" ":cp_map[i][j])+" ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
	static void down_tile(int[][] cp_map) {
		for (int j = 0; j < w; j++) {
			for (int i = h - 1; i >= 0; i--) {
				if (cp_map[i][j] == 0) {
					int fill_p = i;
					i--;
					while (i >= 0) {
						if (cp_map[i][j] != 0) {
							cp_map[fill_p--][j] = cp_map[i][j];
							cp_map[i][j] = 0;
						}
						i--;
					}
				}
			}
		}
	}

	static int crash_oneline(int idx, int[][] cp_map) {
		System.out.println(idx+"에서 공을 떨군다.");
		crash_cnt_global = 0;
		for (int i = 0; i < h; i++) {
			if (cp_map[i][idx] == 0)
				continue;
			crash_cnt_global = 1;
			crash_tile_dfs(i, idx, cp_map);
			break;
		}

		return crash_cnt_global;
	}

	static void crash_tile_dfs(int pi, int pj, int[][] cp_map) {
		// 네방향을 부셔보자
		int power = cp_map[pi][pj] - 1;
		cp_map[pi][pj] = 0;
		// 왼쪽
		for (int j = 1; j < power + 1; j++) {
			if (pj - j < 0)
				continue;
			if (cp_map[pi][pj - j] != 0) {
				crash_cnt_global++;
				crash_tile_dfs(pi, pj - j, cp_map);
			}
		}
		// 아래
		for (int i = 1; i < power + 1; i++) {
			if (pi + i >= h)
				continue;
			if (cp_map[pi + i][pj] != 0) {
				crash_cnt_global++;
				crash_tile_dfs(pi + i, pj, cp_map);
			}
		}
		// 오른쪽
		for (int j = 1; j < power + 1; j++) {
			if (pj + j >= w)
				continue;
			if (cp_map[pi][pj + j] != 0) {
				crash_cnt_global++;
				crash_tile_dfs(pi, pj + j, cp_map);
			}
		}
		// 위
		for (int i = 1; i < power + 1; i++) {
			if (pi - i < 0)
				continue;
			if (cp_map[pi - i][pj] != 0) {
				crash_cnt_global++;
				crash_tile_dfs(pi - i, pj, cp_map);
			}
		}
	}

	static int[][] copy_map(int[][] sub_map) {
		int[][] cp_map = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				cp_map[i][j] = sub_map[i][j];
			}
		}
		return cp_map;
	}
}
