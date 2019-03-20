package swexpertacademy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P_7229_eating_cake_DH {
	static double[] cake,div_cnt;
	static int n;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {
			n = scan.nextInt();
			cake = new double[n];
			div_cnt = new double[n];
			Arrays.fill(div_cnt, 1);
			for (int i = 0; i < n; i++) {
				cake[i] = scan.nextInt();
			}
			Arrays.sort(cake);
			double[] re_cake = new double[n];
			for (int i = 0; i < n; i++) {
				re_cake[i] = cake[n-1-i];
			}
			cake = re_cake;
			/*
			for (int i = 0; i < cake.length; i++) {
				System.out.print(cake[i]+" ");
			}
			System.out.println();
			*/
			int m = scan.nextInt();
			if(cake[0]==cake[n-1]) {
				System.out.println("#"+test+" 0"); continue;
			}
			int c_idx = 0; //조각을 증가시키는 케익 선택을 위한 idx
			double result = get_min();
			while(m-->0) {
				div_cnt[c_idx]++;
				double v = get_min();
				result = result<v?result:v;
				if(c_idx==n-1) {
					c_idx=0; continue;
				}
				if(cake[c_idx]/div_cnt[c_idx]>=cake[c_idx+1]/div_cnt[c_idx+1]) {
					c_idx=0;
				}else {
					c_idx++;
				}
			}
			System.out.println("#"+test+" "+result);
		}
	}	
	static double get_min() {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < cake.length; i++) {
			double v = cake[i]/div_cnt[i];
			min = min<v?min:v;
			max = max>v?max:v;
		}
		return max-min;
	}
}