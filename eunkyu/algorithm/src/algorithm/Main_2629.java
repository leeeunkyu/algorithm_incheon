package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2629 {
	
	static int[] stones;
	static int[] balls;
	static int n;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Queue<Integer> tempq = new LinkedList<Integer>();
		String[] str = br.readLine().split(" ");
		stones = new int[n];
		for (int i = 0; i < n; i++) {
			stones[i] = Integer.parseInt(str[i]);
		}
		
		int m = Integer.parseInt(br.readLine());
		balls = new int[m];
		String[] str2 = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			balls[i] = Integer.parseInt(str2[i]);
		}
		visited = new boolean[n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int val = balls[i];
			boolean res = go(val);
			if(res)
				sb.append("Y ");
			else
				sb.append("N ");
		}
		System.out.println(sb);
	}

	private static boolean go(int val) {
		for (int pick = 0; pick < n; pick++) {
			for (int i = 0; i < (1 << n); i++) {
				if(Integer.bitCount(i) == pick) {
					int a = val;
					int b = 0;
					boolean[] visited = new boolean[n];
					int cnt = 0;
					for (int j = 0; j < n; j++) {
						if(((1 << j) & i) > 0) {
							a += stones[j];
							visited[j] = true;
							cnt ++;
						}
					}
					for (int pick2 = 1; pick2 < n - cnt; pick2++) {
						for (int i2 = 0; i2 < (1 << n); i2++) {
							if(Integer.bitCount(i2) == pick2) {
								for (int j = 0; j < n; j++) {
									if(((1 << j) & i) > 0) {
										if(visited[j]) {
											b = 0;
											break;
										}
										b += stones[j];
									}
								}
								if(a == b)
									return true;
								else
									b = 0;
							}
						}	
					}
				}
			}	
		}
		return false;
	}
}