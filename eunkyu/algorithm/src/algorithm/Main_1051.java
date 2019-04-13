package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1051 {
	
	static int n;
	static int m;
	static int[][] arr;
	static int minVal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		minVal = Math.min(n, m);
		
		for (int i = 0; i < n; i++) {
			String info = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = info.charAt(j) - '0';
			}
		}
		
		for (int p = minVal; p >= 1; p--) {
			int idx = p - 1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(i + idx >= 0 && i + idx < n
							&& j + idx >= 0 && j + idx < m) {
						if(arr[i][j] == arr[i + idx][j] && arr[i][j] == arr[i][j + idx]
								&& arr[i][j] == arr[i + idx][j + idx]) {
							System.out.println(p * p);
							return;
						}
					}
				}
			}
		}
		
		
	
	}
}