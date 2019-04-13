package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//16:40 ~ 
public class Main_14499_2 {
	
	static int n;
	static int m;
	static int x;
	static int y;
	static int k;
	static int[][] arr;
	static int[][] val ={{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0}};
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		x = Integer.parseInt(str[2]);
		y = Integer.parseInt(str[3]);
		k = Integer.parseInt(str[4]);
	
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		
		String[] orderInfo = br.readLine().split(" ");
		int[] order = new int[orderInfo.length];
		
		for (int i = 0; i < order.length; i++) {
			order[i] = Integer.parseInt(orderInfo[i]);
		}
		
		for (int i = 0; i < order.length; i++) {
			int o = order[i];
			int v = 0;
			switch (o) {
			case 1:
				if(x + 1 >= m)
					continue;
				v = arr[y][++x];
				moveRight();
				break;
			case 2:
				if(x - 1 < 0)
					continue;
				v = arr[y][--x];
				moveLeft();
				break;
			case 3:
				if(y - 1 < 0)
					continue;
				v = arr[--y][x];
				moveTop();
				break;
			case 4:
				if(y + 1 >= n) {
					continue;
				}
				v = arr[++y][x];
				moveBot();
				break;
			}
			int top = val[1][1];
			int bot = val[3][1];
			if(v == 0)
				arr[y][x] = bot;
			else {
				val[3][1] = v;
				arr[y][x] = 0;
			}
			print();
			System.out.println(top);
			
		}
	}
	
	private static void print() {
		for (int i = 0; i < val.length; i++) {
			for (int j = 0; j < val[i].length; j++) {
				System.out.print(val[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void moveRight() {
		int temp = val[1][0];
		val[1][0] = val[1][1];
		val[1][1] = val[1][2];
		val[1][2] = val[3][1];
		val[3][1] = temp;
	}
	
	public static void moveLeft() {
		int temp = val[1][0];
		val[1][0] = val[3][1];
		val[3][1] = val[1][2];
		val[1][2] = val[1][1];
		val[1][1] = temp;
	}
	
	public static void moveTop() {
		int temp = val[0][1];
		val[0][1] = val[1][1];
		val[1][1] = val[2][1];
		val[2][1] = val[3][1];
		val[3][1] = temp;
	}
	
	public static void moveBot() {
		int temp = val[1][1];
		val[1][1] = val[2][1];
		val[2][1] = val[3][1];
		val[3][1] = val[0][1];
		val[0][1] = temp;
	}
}