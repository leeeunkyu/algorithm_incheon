package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11048 {
	
	static int[][] arr;
	static int n;
	static int m;
	
	//11½Ã30ºÐ
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		
		dp();
	}

	private static void dp() {
		
		int[][] dpMax = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			int val1 = 0;
			int val2 = 0;
			int val3 = 0;
			for (int j = 0; j < m; j++) {
				if(i - 1 >= 0 && i - 1 < n)
					val1 = dpMax[i - 1][j];
				if(j - 1 >= 0 && j - 1 < m)
					val2 = dpMax[i][j - 1];
				if(i - 1 >= 0 && i - 1 < n && j - 1 >= 0 && j - 1 < m)
					val3 = dpMax[i - 1][j - 1];
				dpMax[i][j] = Math.max(val3, Math.max(val1, val2)) + arr[i][j];
			}
		}
		
		System.out.println(dpMax[n - 1][m - 1]);
	}
}
