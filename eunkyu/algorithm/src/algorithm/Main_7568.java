package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_7568 {
	
	static int n;
	static int[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int [n][4];
		
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]);
			int b = Integer.parseInt(info[1]);
			arr[i][0] = i;
			arr[i][1] = a;
			arr[i][2] = b;
		}
		
		for (int i = 0; i < n; i++) {
			int cnt = 1;
			for (int j = 0; j < n; j++) {
				if(i == j) continue;
				
				if(arr[i][1] < arr[j][1] && arr[i][2] < arr[j][2]) {
					++cnt;
				}
			}
			arr[i][3] = cnt;
		}
		
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i][3]+" ");
		}
		
	}
}