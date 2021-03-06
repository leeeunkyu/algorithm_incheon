package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2606 {
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			arr[a - 1][b - 1] = 1;
			arr[b - 1][a - 1] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] != 1 && i != j)
					arr[i][j] = Integer.MAX_VALUE;
			}
		}
		
		goVirus(arr);
		
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			if(arr[0][i] != Integer.MAX_VALUE)
				cnt++;
		}
		
		System.out.println(cnt - 1);

	}

	private static void goVirus(int[][] arr) {
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
	}
}
