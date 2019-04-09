package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3109 {
	
	static int r; //����
	static int c; //����
	static char[][] arr;
	static boolean[][] visited;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		arr = new char[r][c];
		
		for (int i = 0; i < r; i++) {
			String info = br.readLine();
			for (int j = 0; j < c; j++) {
				char val = info.charAt(j);
				arr[i][j] = val;
			}
		}
		
		int ans = 0;
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			arr[i][0] = '+';
			go(i, 0);
			arr[i][0] = '.';
			
		}
		System.out.println(res);
		
	}
	private static boolean go(int y, int x) {
		if(x == c - 1) {
			//print();
			++res;
			return true;
		} else {
			if(y - 1 >= 0 && !visited[y - 1][x + 1] && arr[y - 1][x + 1] != 'x') {
				visited[y - 1][x + 1] = true;
				arr[y - 1][x + 1] = '+';
				if(go(y - 1, x + 1)) {
					return true;
				}
/*				arr[y - 1][x + 1] = '.';
				visited[y - 1][x + 1] = false;
*/			}
			if(!visited[y][x + 1] && arr[y][x + 1] != 'x') {
				visited[y][x + 1] = true;
				arr[y][x + 1] = '+';
				if(go(y, x + 1)){
					return true;
				}
/*				arr[y][x + 1] = '.';
				visited[y][x + 1] = false;*/
			}
		
			if(y + 1 < r && !visited[y + 1][x + 1] && arr[y + 1][x + 1] != 'x') {
				visited[y + 1][x + 1] = true;
				arr[y + 1][x + 1] = '+';
				if(go(y + 1, x + 1)) {
					return true;
				}
	/*			arr[y + 1][x + 1] = '.';
				visited[y + 1][x + 1] = false;
*/			}
		}
		return false;
	}
	private static void print() {
		System.out.println();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
