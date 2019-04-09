package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16918 {
	
	static int r;	//����
	static int c;	//����
	static int n;
	static char[][] arr;
	static Boom_16918[] booms;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		n = Integer.parseInt(str[2]);
		arr = new char[r][c];
		size = 0;
		booms = new Boom_16918[(r + 1)  * (c + 1)];
		for (int i = 0; i < r; i++) {
			String info = br.readLine();
			for (int j = 0; j < c; j++) {
				char val = info.charAt(j);
				arr[i][j] = val;
				if(val == 'O') {
					booms[size] = new Boom_16918(i, j, 0);
					++size;
				}
			}
		}
		
		goGame();
		print();
		
		
	}

	private static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void goGame() {
		goTime();
		for (int i = 1; i < n; i++) {
			//
			goTime();
			setBoom();
			isBoom();
			System.out.println(i+"  "+size);
			print();
			System.out.println();
		}
	}

	private static void goTime() {
		for (int j = 0; j < size; j++) {
			booms[j].setTime(booms[j].getTime() + 1);
		}
	}

	private static void isBoom() {
		Queue<Boom_16918> q = new LinkedList<Boom_16918>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for (int i = 0; i < size; i++) {
			if(booms[i].getTime() == 3) {
				arr[booms[i].getY()][booms[i].getX()] = '.';
				for (int j = 0; j < 4; j++) {
					int nextX = booms[i].getX() + dx[j];
					int nextY = booms[i].getY() + dy[j];
					if(nextX >= 0 && nextX < c && nextY >= 0 && nextY < r) {
						arr[nextY][nextX] = '.';
					}
				}
			}
		}
		int temp = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 'O') {
					for (int j2 = 0; j2 < size; j2++) {
						if(booms[j2].getX() == j && booms[j2].getY() == i) {
							booms[temp] = new Boom_16918(i, j, booms[j].getTime());
							++temp;		
						}
					}
				}
			}
		}
		
		size = temp;

		
	}

	private static void setBoom() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == '.') {
					arr[i][j] = 'O';
					booms[size] = new Boom_16918(i, j, 0);
					++size;
				}
			}
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
