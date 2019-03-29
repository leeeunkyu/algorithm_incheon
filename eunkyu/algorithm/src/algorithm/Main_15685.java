package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15685 {

	static int n;
	static Dragon_15685[] dragons;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dragons = new Dragon_15685[n];
		visited = new boolean[200][200];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int d = Integer.parseInt(str[2]);
			int g = Integer.parseInt(str[3]);
			dragons[i] = new Dragon_15685(x, y, d, g);
		}
		
		for (int i = 0; i < n; i++) {
			goDragon(dragons[i]);	
		}
		
		
		
	}
	/*
	 * 0: x��ǥ�� �����ϴ� ���� (��)
		1: y��ǥ�� �����ϴ� ���� (��)
		2: x��ǥ�� �����ϴ� ���� (��)
		3: y��ǥ�� �����ϴ� ���� (��)
	 */
	private static void goDragon(Dragon_15685 dragon) {
		int x = dragon.getX();
		int y = dragon.getY();
		int d = dragon.getD();
		int g = dragon.getG();
		//4 2 1 3
		for (int i = 0; i < g; i++) {
			
		}
		
	}
}

class Dragon_15685 {
	int x;
	int y;
	int d;
	int g;
	
	public Dragon_15685(int x, int y, int d, int g) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.g = g;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
}