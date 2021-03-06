package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1389 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		int n = Integer.parseInt(str[0]); //친구 수
		int m = Integer.parseInt(str[1]); //연결 수
		int length = n + 1;
		int arr[][] = new int [length][length];
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(i == j)
					arr[i][j] = 0;
				else
					arr[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 0; i < m; i++) {
			String str2[] = br.readLine().split(" ");
			int a = Integer.parseInt(str2[0]); //친구 수
			int b = Integer.parseInt(str2[1]); //연결 수
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
	
		floydWarshall(arr, length); 
		int minIdx = 0;
		int res = Integer.MAX_VALUE;
		for (int i = 1; i < length; i++) {
			int sum = 0;
			for (int j = 1; j < length; j++) {
				sum += arr[i][j];
			}
			if(res > sum) {
				res = sum;
				minIdx = i;
			}
		}
		System.out.println(minIdx);
	}

	private static void floydWarshall(int[][] arr, int length) {
		for (int k = 0; k < length; k++) {
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					if(arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE)
						continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
	}
	
}
