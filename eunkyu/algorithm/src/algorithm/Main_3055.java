package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3055 {
	
	static char[][] arr;
	static int r;
	static int c;
	static int bNum = 1;
	
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
		
		goGame();
	}

	private static void goGame() {
		int time = 0;
		boolean res = false;
		while(true) {
			time++;
			goWather();
			res = goBB();
			if(res)
				break;
			//print();
			if(bNum == 0) {
				System.out.println("KAKTUS");
				return;
			}
		}
		System.out.println(time);
	}

	private static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean goBB() {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				boolean go = false;
				if(arr[i][j] == 'S') {
					arr[i][j] = '.';
					bNum--;
					go = true;
				}
				if(arr[i][j] == 'a') {
					arr[i][j] = '*';
					go = true;
				}
				if(go) {
					for (int dir = 0; dir < 4; dir++) {
						int nextX = j + dx[dir];
						int nextY = i + dy[dir];
						if(nextX >= 0 && nextX < c 
								&& nextY >= 0 && nextY < r) {
							if(arr[nextY][nextX] == '.') {
								arr[nextY][nextX] = 's';
								++bNum;
							}
							if(arr[nextY][nextX] == 'D')
								return true;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 's')
					arr[i][j] = 'S';
			}
		}
		return false;
	}

	private static void goWather() {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == '*') {
					for (int dir = 0; dir < 4; dir++) {
						int nextX = j + dx[dir];
						int nextY = i + dy[dir];
						if(nextX >= 0 && nextX < c 
								&& nextY >= 0 && nextY < r) {
							if(arr[nextY][nextX] == '.' ) {
								arr[nextY][nextX] = '-';
							}
							if(arr[nextY][nextX] == 'S') {
								--bNum;
								arr[nextY][nextX] = 'a';
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == '-')
					arr[i][j] = '*';
			}
		}
	}
}
