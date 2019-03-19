package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_7229 {
	
	static int n;
	static double[] cakes;
	static int m; //������
	static double minSub;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			String[] nInfo = br.readLine().split(" ");
			int[] vals = new int[n];
			for (int i = 0; i < n; i++) {
				vals[i] = Integer.parseInt(nInfo[i]);
			}
			m = Integer.parseInt(br.readLine());		
			cakes = new double[n + n + m + m + 1];
			for (int i = 0; i < n; i++) {
				cakes[i] = vals[i];
			}
			minSub = Integer.MAX_VALUE;
			
			boolean check = false; 
			for (int i = 0; i < vals.length; i++) {
				if(i + 1 < vals.length && vals[i] != vals[i + 1]) {
					check = true;
				}
			}
			
			if(!check) {
				sb.append(0+"\n");
				continue;
			}
			
			cutCake(m - 1, -1);
			sb.append(minSub+"\n");
		}
		System.out.println(sb);
	}

	private static void cutCake(int cnt, int idx) {		
		
		
		
	}
	
	private static void cakeVal(int idx) {
		double max = 0;
		double min = Integer.MAX_VALUE;
		
		for (int i = 0; i <= idx; i++) {
			double val = cakes[i];
			if(val == 0)
				continue;
			if(val > max) {
				max = val;
			}
			if(val < min) {
				min = val;
			}
		}
		double sub = max - min;
		if(sub < minSub) {
			minSub = sub;
			System.out.println(minSub);
		}
	}
}
