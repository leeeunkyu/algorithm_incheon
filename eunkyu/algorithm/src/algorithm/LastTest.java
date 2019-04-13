package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LastTest {
	
	static int n;
	static int m;
	static int[][] arr;
	static int[] res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][n];
		res = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 0; i < m; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]) - 1;
			int b = Integer.parseInt(info[1]) - 1;
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE)
						continue;
					if(arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j)
					continue;
				res[i] += arr[i][j];
			}
		}
		
		for (int i = 0; i < n; i++) {
			if(min > res[i]) {
				min = res[i];
				minIdx = i;
			}
		}
		
		System.out.println(minIdx + 1);
	}
}