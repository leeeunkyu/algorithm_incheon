package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_7229 {
	
	static int n;
	static int m;
	static double[] cakes;
	static double[] cut;
	static double minRes;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			cakes = new double[n];
			cut = new double[n];
			for (int i = 0; i < n; i++) {
				cut[i] = 1;
			}
			String[] info = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				cakes[i] = Integer.parseInt(info[i]);
			}
			
			m = Integer.parseInt(br.readLine());
			
			minRes = Integer.MAX_VALUE;
			Arrays.sort(cakes);
			if(cakes[0] - cakes[n - 1] == 0) {
				sb.append("#"+tc+" "+0+"\n");
				continue;
			}
			dfs(0);
			sb.append("#"+tc+" "+minRes+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt) {
		if(cnt == m) {
			return;
		}
		for (int i = 0; i < n; i++) {
			++cut[i];
			double res = cutCake();
			if(res < minRes) {
				minRes = res;
			}
			dfs(cnt + 1);
			--cut[i];
		}
	}

	private static double cutCake() {
		double max = Integer.MIN_VALUE;
		double min = Integer.MAX_VALUE;
		double val = 0;
		for (int i = 0; i < n; i++) {
			if(cut[i] > 1) {
				val = cakes[i] / cut[i];
			} else {
				val = cakes[i];
			}
			if(val > max) {
				max = val;
			}
			
			if(val < min) {
				min = val;
			}
		}
		return max - min;
	}
}