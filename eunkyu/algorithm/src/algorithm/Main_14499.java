package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14499 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]); 	//세로크기 n
		int m = Integer.parseInt(str[1]);	//가로크기 m
		int y = Integer.parseInt(str[2]);	//주사위 놓을곳 y
		int x = Integer.parseInt(str[3]);	//주사위 놓을곳 x
		int k = Integer.parseInt(str[4]);	//명령어 갯수 k
		int[][] arr = new int[n][m];
		int[] orderList = new int[k];
		
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		String[] temp2 = br.readLine().split(" ");
		for (int i = 0; i < k; i++) {
			orderList[i] = Integer.parseInt(temp2[i]);
		}
		
		new Main_14499().rolling(arr, orderList);
	}

	private void rolling(int[][] arr, int[] orderList) {
		int[][] dice = {{-1, 0, -1},{0, 0, 0}, {-1, 0, -1}, {-1, 0, -1}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(dice[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
