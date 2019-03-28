package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12100 {
	static int n;
	static int[][] arr;
	static boolean[][] isSum;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		int[][] copy = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		max = Integer.MIN_VALUE;
		for (int i = 0; i < 4; i++) {
			isSum = new boolean[n][n];
			cp(copy, true);
			goGame(i, 0);
			cp(copy, false);
		}
		System.out.println(max);
		
	}
	private static void cp(int[][] copy, boolean type) {	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(type)
					copy[i][j] = arr[i][j];
				else
					arr[i][j] = copy[i][j];
			}
		}
	
	}
	private static void cp2(boolean[][] copy2, boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(type)
					copy2[i][j] = isSum[i][j];
				else
					isSum[i][j] = copy2[i][j];
			}
		}
	}
	private static void goGame(int dir, int cnt) {
	//0 위 1 아래 2오른쪽 3 왼쪽
		if(cnt == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] > max) {
						max = arr[i][j];
					}
				}
			}
			//print();
			//System.out.println(max);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int[][] copy = new int[n][n];
			boolean[][] copy2 = new boolean[n][n];
			cp(copy, true);
			cp2(copy2, true);
			//print();
			move(dir);
			goGame(i, cnt + 1);
			cp(copy, false);
			cp2(copy2, false);
			//System.out.println("============");
			//print();

		}
	}
	
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(isSum[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	private static void move(int dir) {
		switch (dir) {
		case 0:
			top();
			break;
		case 1:
			down();
			break;
		case 2:
			right();
			break;
		case 3:
			left();
			break;
		}
	}
	private static void left() {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if(!isSum[i][j] && j - 1 >= 0 && arr[i][j] == arr[i][j - 1]) {
					arr[i][j] += arr[i][j - 1];
					arr[i][j - 1] = 0;
					isSum[i][j] = true;
				}
				if(j - 1 >= 0 && arr[i][j] == 0) {
					arr[i][j] = arr[i][j - 1];
					arr[i][j - 1] = 0;
					if(isSum[i][j - 1])
						isSum[i][j] = true;
					isSum[i][j - 1] = false;


				}
			}
		}
		
	}
	private static void right() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!isSum[i][j] && j + 1 < n && arr[i][j] == arr[i][j + 1]) {
					arr[i][j] += arr[i][j + 1];
					arr[i][j + 1] = 0;
					isSum[i][j] = true;
				}
				if(j + 1 < n && arr[i][j] == 0) {
					arr[i][j] = arr[i][j + 1];
					arr[i][j + 1] = 0;
					if(isSum[i][j + 1])
						isSum[i][j] = true;
					isSum[i][j + 1] = false;
				}
			}
		}
		
	}
	private static void down() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if(!isSum[i][j] && i - 1 >= 0 && arr[i][j] == arr[i - 1][j]) {
					arr[i][j] += arr[i - 1][j];
					arr[i - 1][j] = 0;
					isSum[i][j] = true;
				}
				if(i - 1 >= 0 && arr[i][j] == 0) {
					arr[i][j] = arr[i - 1][j];
					arr[i - 1][j] = 0;
					if(isSum[i - 1][j])
						isSum[i][j] = true;
					isSum[i - 1][j] = false;
				}
			}
		}
	}
	private static void top() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!isSum[i][j] && i + 1 < n && arr[i][j] == arr[i + 1][j]) {
					arr[i][j] += arr[i + 1][j];
					arr[i + 1][j] = 0;
					isSum[i][j] = true;
				}
				if(i + 1 < n && arr[i][j] == 0) {
					arr[i][j] = arr[i + 1][j];
					arr[i + 1][j] = 0;
					if(isSum[i + 1][j])
						isSum[i][j] = true;
					isSum[i + 1][j] = false;
				}
			}
		}
	}
}
