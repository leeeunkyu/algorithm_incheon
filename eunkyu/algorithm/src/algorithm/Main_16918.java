package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16918 {
	
	static int r;
	static int c;
	static int n;
	static char[][] arr;
	static int [][] boomMap;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		n = Integer.parseInt(str[2]);
		size = r * c;
		arr = new char[r][c];
		boomMap = new int[r][c];
		for (int i = 0; i < r; i++) {
			String info = br.readLine();
			for (int j = 0; j < c; j++) {
				char val = info.charAt(j);
				arr[i][j] = val;
				if(val == 'O') {
					boomMap[i][j] = 1;
				}
			}
		}
		
		
		goGame();
		print();
	
	
	}

	private static void goGame() {
		/*
		 * 1. 0 �� - ����(�Ϻ� ������ ��ź ��ġ)
		 * 2. 1��  ���� 1�� ���� �������� �ƹ��͵� ���� �ʴ´�. setTime(time + 1)
		 * 3. 2�� setBoom();
		 * 4. 3�� ��ź ����
		 * 3 4 �ݺ�
		 */
		
		setTime(); //1��
		//print();
		for (int i = 0; i < n - 1; i++) {
			setTime();
			setBoom();		
			isBoom();
			//print();
		}
		

		
/*		 //2��
		print();

		setTime();
		
		print();*/

	}
	
	private static void isBoom() {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 'O' && boomMap[i][j] > 3) {
					arr[i][j] = '.';
					boomMap[i][j] = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nextX = j + dx[dir];
						int nextY = i + dy[dir];
						if(nextX >= 0 && nextX < c 
								&& nextY >=0 && nextY < r
								&& boomMap[nextY][nextX] <= 3) {
							arr[nextY][nextX] = '.';
							boomMap[nextY][nextX] = 0;
						}
					}
				}
			}
		}
	}

	private static void setBoom() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == '.') {
					arr[i][j] = 'O';
					boomMap[i][j] = 1;
				}
			}
		}
	}

	private static void setTime() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(boomMap[i][j] != 0) {
					boomMap[i][j] = boomMap[i][j] + 1;
				}
			}
		}
	}

	private static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
}

class Boom_16918 {
	int y;
	int x;
	int time;
	
	public Boom_16918(int y, int x, int time) {
		super();
		this.y = y;
		this.x = x;
		this.time = time;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}