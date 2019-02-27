package baekjoon;

import java.util.Scanner;

public class P_15661_link_and_strat {
	static int n;
	static int[][] feild;
	static int[] power;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		power = new int[(1<<n)-1];
		feild = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int v = scan.nextInt();
				// 능력치의 합을 보여주기 위한 단계
				feild[i][j] += v;
				feild[j][i] += v;
			}
		}
		// 대각선 반쪽만 돌도록 구성
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				power[(1<<i)|(1<<j)] = feild[i][j];
			}
		}
		int chae = 1;
		for (int i = 1; i < power.length; i++) {
			if(i==chae) {
				chae = (chae<<1);
				continue;
			}
			if(power[i]!=0)continue;
			for (int j = 0; j < n; j++) {
				if((i&(1<<j))!=0) {
					power[i] = power[i-(1<<j)];
					int target = 1<<j;
					for (j++; j < n; j++) {
						if((i&(1<<j))!=0) {
							power[i]+=power[target|1<<j];
						}
					}
				}
			}
		}
		int result = 9999999;
		int full_bit = (1<<n)-1; //n이 3이면 111 이 됨.
		for (int i = 1; i < power.length/2; i++) {
			int s_r = Math.abs(power[i]-power[full_bit^i]);
			result = result<s_r?result:s_r;
		}
		System.out.println(result);
	}
}
