package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1799 {
	
	static int n;
	static int[][] arr;
	static int[][] cp;
	static int size;
	static int maxRCnt;
	static Point_1799[] points;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		cp = new int[n][n];
		Queue<Point_1799> pointq = new LinkedList<Point_1799>();
		for (int i = 0; i < arr.length; i++) {
			String[] arrInfo = br.readLine().split(" ");
			for (int j = 0; j < arr.length; j++) {
				int val = Integer.parseInt(arrInfo[j]);
				arr[i][j] = val;
				cp[i][j] = val;
				if(val != 0) {
					pointq.add(new Point_1799(i, j));
				}
			}
		}
		
		maxRCnt = 0;
		size = pointq.size();
		points = new Point_1799[size];
		
		for (int i = 0; i < size; i++) {
			points[i] = pointq.poll();
		}
		
		pointq = null;
		
		Main_1799 main = new Main_1799();
		main.dfs(0, 0);
		
		System.out.println(maxRCnt);
	
		
	}
	private void dfs(int cnt, int rCnt) {
		if(cnt >= size) {
			if(maxRCnt < rCnt) {
				maxRCnt = rCnt;
			}
			return;
		}
		Point_1799 point = points[cnt];
		int y = point.getY();
		int x = point.getX();
		
		if(arr[y][x] == 3) {
			dfs(cnt + 1, rCnt);
			arr[y][x] = 1;
			return;
		}
		
		for (int i = 1; i <= 2; i++) {
			int val = arr[y][x];
			//����� �ѰŰ� && �ش� ��ġ���� �밢�� ���⿡ �̹� �ٸ� ����� ���� && 4���� ���� üũ 
			if(i == 2 && arr[y][x] != 3 && checkOne(y, x)) {
				arr[y][x] = i; //2�� �ִ´ٴ°� ���⿡ ����� �дٴ� �ǹ�
				dfs(cnt + 1, rCnt + 1);
				arr[y][x] = 1;
			} else {
				if(i == 2 && val == 3)
					break;
				dfs(cnt + 1, rCnt);
				arr[y][x] = 1;
			}	
		}
	}

	//�밢�� 4���⿡ ����� �ִ��� üũ
	private boolean checkOne(int y, int x) {
		copy(true);
		int[] dx = {-1, 1, -1, 1};
		int[] dy = {1, -1, -1, 1};
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			while(nextX >= 0 && nextX < n && nextY >=0 && nextY < n) {
				if(arr[nextY][nextX] == 1) {
					arr[nextY][nextX] = 3; //����� �� �밢�� ��λ� �ٸ� ����� �� �ʿ䰡 ���� 3�� ����.
				}
				else if(arr[nextY][nextX] == 2) {
					copy(false);
					return false;
				} 
				nextX += dx[i];
				nextY += dy[i];
			}
		}
		return true;
	}
	private void copy(boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(type)
					cp[i][j] = arr[i][j];
				else
					arr[i][j] = cp[i][j];
			}
		}
	}
}

class Point_1799 {
	int y;
	int x;
	
	public Point_1799(int y, int x) {
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