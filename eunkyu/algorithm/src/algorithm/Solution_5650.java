package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//�ѽð�
public class Solution_5650 {
	
	static int n;
	static int holeSize = 6;
	static int[][] arr;
	static Hole_5650[][] hole;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			hole = new Hole_5650[2][11];
			arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int val = Integer.parseInt(str[j]);
					
					if(val >= 6 && val <= 10) {
						//��Ȧ
						if(hole[0][val] == null) {
							hole[0][val] = new Hole_5650(i, j);
						} else {
							hole[1][val] = new Hole_5650(i, j);
						}
					}
					arr[i][j] = val;
				}
			}
			
			int maxScore = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] == 0) {
						for (int dir = 0; dir < 4; dir++) {
							int score = goGame(i, j, dir);
							if(score > maxScore) {
								maxScore = score;
							}
						}
					}
				}
			}
			sb.append("#"+tc+" "+maxScore+"\n");	
		}
		System.out.println(sb);
	
	}


	private static int goGame(int y, int x, int dir) {
		//dir 0~3 0 �� 1 �� 2 �� 3 ��
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		int cnt = 0;
		int nextX = x + dx[dir];
		int nextY = y + dy[dir];
		
		while(!(x == nextX && nextY == y)) {
			
			while(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
				if(x == nextX && y == nextY)
					return cnt;
				if(arr[nextY][nextX] != 0) {
					//����, ��Ȧ, ����Ȧ�� ����
					int val = arr[nextY][nextX];
					if(val == -1) {
						return cnt;
					} else if(val >= 1 && val <= 5)  {
						//����
						dir = crashBlock(val, dir);
						cnt++;
					} else {
						//��Ȧ
						Hole_5650 h = getExitHole(nextY, nextX);
						nextX = h.getX();
						nextY = h.getY();
					}
				
				}
				
				nextX += dx[dir];
				nextY += dy[dir];
			}
			
			//���� �ε�ħ
			int[] dirChange = {2, 3, 0, 1};
			nextX -= dx[dir];
			nextY -= dy[dir];
			dir = dirChange[dir] ;
			cnt++;
		}
		
		return cnt;
	}

	private static int crashBlock(int val, int dir) {
		//dir 0~3 0 �� 1 �� 2 �� 3 ��
		switch (val) {
		case 1:
			int[] dirChange1 = {2, 3, 1, 0};
			//dirChange �� => �� / �� => �� / ��  => �� / �� => ��
			dir = dirChange1[dir];
			break;
		case 2:
			int[] dirChange2 = {1, 3, 0, 2};
			//dirChange �� => �� / �� => �� / ��  => �� / �� => ��
			dir = dirChange2[dir];
			break;
		case 3:
			int[] dirChange3 = {3, 2, 0, 1};
			//dirChange �� => �� / �� => �� / ��  => �� / �� => ��
			dir = dirChange3[dir];
			break;
		case 4:
			int[] dirChange4 = {2, 0, 3, 1};
			//dirChange �� => ��/ �� => �� / ��  => �� / �� => ��
			dir = dirChange4[dir];
			break;
		case 5:
			int[] dirChange5 = {2, 3, 0, 1};
			//dirChange �� => ��/ �� => �� / ��  => �� / �� => ��
			dir = dirChange5[dir];
			break;
		}
		return dir;
	}


	private static Hole_5650 getExitHole(int nextY, int nextX) {
		int index = 0;
		int num = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 6; j <= 10; j++) {
				Hole_5650 h = hole[i][j];
				if(h == null)
					continue;
				if(h.getX() == nextX && h.getY() == nextY) {
					index = i;
					num = j;
				}
			}
		}		
		return hole[(index + 1) % 2][num];
	}
	
}

class Hole_5650 {
	int y;
	int x;
		
	public Hole_5650(int y, int x) {
		super();
		this.y = y;
		this.x = x;
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
	
}
