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
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(val == 0)
					arr[i][j] = 9999;
				/*if(i == j)
					arr[i][j] = 1;*/
				
			}
		}
		

		
	/*	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}*/
		
		
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
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 9999)
					System.out.print(0+" ");
				else
					System.out.print(1+" ");
			}
			System.out.println();
		}
	}
}
