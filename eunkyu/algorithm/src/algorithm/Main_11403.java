package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11403 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				
/*				arr[i][j] = Integer.MAX_VALUE;
				arr[j][i] = Integer.MAX_VALUE;*/
				int val = Integer.parseInt(str[j]);
				if(val == 1) {
					arr[i][j] = 1;
				}
				if(i == j) {
					arr[i][j] = 0;
					arr[j][i] = 0;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 0 && i != j) {
					arr[i][j] = Integer.MAX_VALUE;
					arr[j][i] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int val = arr[i][j];
				/*if(val != Integer.MAX_VALUE && i != j) {
					System.out.print(1+" ");
				} else {
					System.out.print(0+" ");
				}*/
				System.out.print(val+" ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][k] == Integer.MAX_VALUE 
							|| arr[k][j] == Integer.MAX_VALUE)
						continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int val = arr[i][j];
				/*if(val != Integer.MAX_VALUE) {
					System.out.print(1+" ");
				} else {
					System.out.print(0+" ");
				}*/
				System.out.print(val+" ");
			}
			System.out.println();
		}
	}
}