package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15685 {
	
	static int n;
	static boolean[][] arr;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new boolean[101][101];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int d = Integer.parseInt(str[2]);
			int g = Integer.parseInt(str[3]);
			
			int curveSize = 0;
			int[] curve = new int[1024];
			curve[curveSize++] = d;
			arr[y][x] = true;
			
			for (int j = 0; j < g; j++) {
				for (int k = curveSize - 1; k >= 0; k--) {
					curve[curveSize++] = (curve[k] + 1) % 4;
				}
			}
			for (int j = 0; j < curveSize; j++) {
				y += dy[curve[j]];
				x += dx[curve[j]];
				if(x >= 0 && x < 101 && y >= 0 && y < 101) {
					arr[y][x] = true;
				}
			}
		}
		
		int res = 0;
		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if(arr[y][x] && arr[y][x + 1] && arr[y + 1][x] && arr[y + 1][x + 1]) {
					++res;
				}
			}
			
		}		
		System.out.println(res);
	}
	private static void print() {
		System.out.println();
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if(arr[i][j])
					System.out.print("T");
				else
					System.out.print("F");
			}
			System.out.println();
		}
	}
}