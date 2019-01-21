package baekjoon;

import java.util.Scanner;

public class P_1389_cavin_bacon_6step_rule {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int[][] relation = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if(i==j)continue;
				relation[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < m; i++) {
			int a1 = scan.nextInt();
			int a2 = scan.nextInt();
			relation[a1][a2] = relation[a2][a1] = 1;
		}
		
		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if(i==j||relation[i][k]==Integer.MAX_VALUE||relation[k][j]==Integer.MAX_VALUE) continue;
					relation[i][j] = min(relation[i][j],relation[i][k]+relation[k][j]);
				}
			}
		}
		int mincost = Integer.MAX_VALUE;
		int min_idx = -1;
		for (int i = 1; i < n+1; i++) {
			int bacon = 0;
			for (int j = 1; j < n+1; j++) {
				bacon+=relation[i][j];
			}
			if(mincost>bacon) {
				mincost=bacon;
				min_idx = i;
			}
			
		}
		System.out.println(min_idx);
	}
	static int min(int a, int b) {return a<b?a:b;}
}
