package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

//14:50
public class Main_7229_2 {
	
	static int n;
	static int m;
	static int[] cakes;
	static int[] cutInfo;
	static double res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			cakes = new int[n];
			cutInfo = new int[n];
			for (int i = 0; i < n; i++) {
				cakes[i] = Integer.parseInt(str[i]);
				cutInfo[i] = 1;
			}
			m = Integer.parseInt(br.readLine());
			res = Integer.MAX_VALUE;
			cutCake();
			goGame(0);
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void goGame(int cutCnt) {
		if(cutCnt == m) {
			return;
		}
		for (int i = 0; i < n; i++) {
			cutInfo[i] = cutInfo[i] + 1;
			cutCake();
			goGame(cutCnt + 1);
			cutInfo[i] = cutInfo[i] - 1;
		}
	}

	private static void cutCake() {
		double info[] = new double[n];
		for (int i = 0; i < n; i++) {
			info[i] = (double)((double)cakes[i] / (double)cutInfo[i]);
		}
		Arrays.sort(info);
		double r = (double)(info[n - 1] - info[0]);
		if(res > r)
			res = r;
	}
}
