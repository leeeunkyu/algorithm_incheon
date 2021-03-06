package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//18:40
public class Main_1018 {
	
	static int m;	//����
	static int n;	//����
	static char[][] arr;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		m = Integer.parseInt(str[0]);
		n = Integer.parseInt(str[1]);
		arr = new char[m][n];
		
		for (int i = 0; i < m; i++) {
			String info = br.readLine();
			arr[i] = info.toCharArray();
		}
		
		/*for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}*/
		res = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				go(i, j);
			}
		}
		System.out.println(res);
	}

	private static void go(int y, int x) {
		if(y + 7 >= m || x + 7 >= n)
			return;
		char val = arr[y][x];
		int cnt = 0;
		for (int i = y; i < y + 8; i++) {
			for (int j = x; j < x + 8; j++) {
				if((i + y + j + x) % 2 == 0) {
					if(arr[i][j] != val)
						++cnt;
				} else {
					if(arr[i][j] == val)
						++cnt;
				}
			}
		}
		cnt = Math.min(cnt, 64 - cnt);
		if(res > cnt)
			res = cnt;
	}
}
