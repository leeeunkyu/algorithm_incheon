package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1051 {
	
	static int n;	//����
	static int m;	//����
	static int[][] arr;
	static int w;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
	
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String info = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = info.charAt(j) - '0';
			}
		}
		if(n > m) {
			w = m;
		} else {
			w = n;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				go(i, j);
			}
		}
		System.out.println(res);
	
	}
	private static void go(int i, int j) {
		for (int idx = 1; idx < w - i && idx < w - j; idx++) {
			System.out.println(idx+"  i: "+i+"  j: "+j);
			if(check(i, j, idx)) {
				res = idx;
			}	
		}
		
	}
	private static boolean check(int i, int j, int idx) {
		int val = arr[i][j];
		if(arr[i][j + idx] == val
				&& arr[i + idx][j] == val
				&& arr[i + idx][j + idx] == val) {
			return true;
		}
		return false;
	}
}
