package algorithm_review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1389 {
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		int[][] arr = new int[n][n];
		for (int i = 0; i < m; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]);
			int b = Integer.parseInt(info[1]);
			arr[a - 1][b - 1] = 1;
			arr[b - 1][a - 1] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] != 1) {
					arr[i][j] = Integer.MAX_VALUE;
				} else if(i == j) {
					arr[i][j] = 0;
				}
			}
		}
		
		for (int k = 0; k < arr.length; k++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE)
						continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}	
		}
		
		
		
	}
}
