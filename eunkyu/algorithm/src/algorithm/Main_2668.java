package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2668 {
	static int[][] arr;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 1; i <= n; i++) {
			arr[0][i - 1] = i;
		}
		for (int i = 0; i < n; i++) {
			arr[1][i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < n; i++) {
			dfs(i);	
		}
		
	}
	private static void dfs(int cnt) {
		int val1 = arr[0][cnt];
		int val2 = getVal(val1);
		if(val1 == val2) {
			
		}
	}
	private static int getVal(int cnt) {
		return arr[1][cnt];
	}
}
