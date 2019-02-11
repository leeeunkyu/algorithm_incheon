package baekjoon;
import java.util.Scanner;

public class P_14890_slope {


		static int n, l, slopeCnt;
		static int[][] slope;
		static boolean[] checkI, checkJ, setslope;

		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			n = scan.nextInt();
			l = scan.nextInt();
			slopeCnt = n * 2;
			slope = new int[n * 2][n];
			setslope = new boolean[n];
			checkI = new boolean[n];
			checkJ = new boolean[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					slope[i][j] = slope[j + n][i] = scan.nextInt();
				}
			}

			for (int i = 0; i < n * 2; i++) {
				setslope = new boolean[n];
				checkOneLineI(i);
			}

			System.out.println(slopeCnt);
		}

		public static void checkOneLineI(int stval) {
			for (int j = 0; j < n - 1; j++) {
				// case1. 현재랑 다음이랑 경사가 같니?
				if (slope[stval][j] == slope[stval][j + 1])
					continue;
				// 높이 초과 검사
				if (Math.abs(slope[stval][j] - slope[stval][j + 1]) > 1) {
					slopeCnt--;
					// System.out.println("i: " + stval);
					return;
				}
				
				// case2. 다음경사가 현재경사보다 작니?
				if (slope[stval][j] > slope[stval][j + 1]) {
					// 경사 차이가 1이야!!
					if ((j + l) >= n) { // 너 경사로 놨을때 범위 넘니?
						slopeCnt--;
						// System.out.println("i: " + stval);
						return;
					}
					setslope[j + 1] = true;
					for (int i = 0; i < l - 1; i++) {
						if (slope[stval][j + 1 + i] != slope[stval][j + 2 + i]) {
							slopeCnt--;
							// System.out.println("i: " + stval);
							return;
						} else {
							setslope[j + 2 + i] = true;
						}
					}
					j += (l - 1);
				} else {
					// case3. 현재경사가 다음경사보다 작니?
					if (j + 1 - l < 0) {
						slopeCnt--;
						// System.out.println("i: " + stval);
						return;
					}
					for (int i = j - (l - 1); i < j + 1; i++) {
						if (setslope[i]) {
							slopeCnt--;
							// System.out.println("i: " + stval);
							return;
						}
					}

					for (int i = j - (l - 1); i < j; i++) {
						if (slope[stval][i] != slope[stval][i + 1]) {
							slopeCnt--;
							// System.out.println("i: " + stval);
							return;
						}
					}

				}
			}
		}

	}
