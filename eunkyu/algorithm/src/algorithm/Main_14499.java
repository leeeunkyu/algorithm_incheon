package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14499 {
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]); 	//세로크기 n
		m = Integer.parseInt(str[1]);	//가로크기 m
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
		
		new Main_14499().rolling(arr, orderList, x, y);
	}

	private void rolling(int[][] arr, int[] orderList, int x2, int y2) {
		int[][] dice = {{-1, 0, -1},{0, 0, 0}, {-1, 0, -1}, {-1, 0, -1}};
		int[][] copy = {{-1, 0, -1},{0, 0, 0}, {-1, 0, -1}, {-1, 0, -1}};
		int x = x2;
		int y = y2;
		for (int i = 0; i < orderList.length; i++) {
			boolean isRoll = false;
			switch (orderList[i]) {
			//동 1오, 서2왼, 북3위, 남4아
			case 1:
				x += 1;
				if(x >= 0 && x < m) {
					right(dice, copy);
					isRoll = true;
				}
				else
					x -= 1;
				break;
			case 2:
				x -= 1;
				if(x >= 0 && x < m) {
					left(dice, copy);
					isRoll = true;
				}
				else
					x += 1;
				break;
			case 3:
				y -= 1;
				if(y >= 0 && y < n) {
					up(dice, copy);
					isRoll = true;
				}
				else
					y += 1;
				break;
			case 4:
				y += 1;
				if(y >= 0 && y < n) {
					down(dice, copy);
					isRoll = true;
				}
				else
					y -= 1;
				break;
			}
			if(isRoll) {
				copy(dice, copy);
				//윗면 3,1
				int top = dice[3][1];
				if(arr[y][x] == 0) {
					arr[y][x] = dice[1][1];
				} else {
					dice[1][1] = arr[y][x];
					arr[y][x] = 0;
				}
				System.out.println(top);	
			}
		}
	}
	
	private void copy(int[][] dice, int[][] copy) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				dice[i][j] = copy[i][j];
			}
		}
	}

	private void left(int[][] dice, int[][] copy) {
		//1,0 <- 3,1  1,1 <- 1,0 1,2 <- 1,1 3,1 <- 1,2
		copy[1][0] = dice[3][1];
		copy[3][1] = dice[1][2];
		copy[1][2] = dice[1][1];
		copy[1][1] = dice[1][0];
	}
	private void right(int[][] dice, int[][] copy) {
		//1,0 <- 1,1  1,1 <- 1,2 1,2 <- 3,1 3,1 <- 1,1
		copy[1][0] = dice[1][1];
		copy[1][1] = dice[1][2];
		copy[1][2] = dice[3][1];
		copy[3][1] = dice[1][0];
	}
	private void up(int[][] dice, int[][] copy) {
		//0,1 <- 3,1 1,1 <- 0,1 2,1 <- 1,1 3,1 <- 2,1
		copy[0][1] = dice[3][1];
		copy[1][1] = dice[0][1];
		copy[2][1] = dice[1][1];
		copy[3][1] = dice[2][1];
	}
	private void down(int[][] dice, int[][] copy) {
		//0,1 <- 3,1 1,1 <- 0,1 2,1 <- 1,1 3,1 <- 2,1
		copy[0][1] = dice[1][1];
		copy[1][1] = dice[2][1];
		copy[2][1] = dice[3][1];
		copy[3][1] = dice[0][1];
	}
}
