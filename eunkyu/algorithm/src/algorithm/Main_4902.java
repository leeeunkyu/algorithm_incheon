package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//10:11
public class Main_4902 {
	
	static int n;
	static int[][] arr;
	static int size;
	static boolean[][] type;
	static int width;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(Integer.parseInt(str[0]) != 0) {
			n = Integer.parseInt(str[0]);
			width = (2 * n) - 1;
			arr = new int[n][width];
			type = new boolean[n][width];
			boolean check = (n % 2 == 1) ? true : false; //Ȧ���̸� true ¦���̸� false
			init();
			int temp = 1;
			for (int i = 0; i < n; i++) {
				int width = i * 2 + 1;
				int k = 0;
				for (int j = n - (1 + i); k < width; j++) {
					arr[i][j] = Integer.parseInt(str[temp]);
						type[i][j] = true;
					if(check && ((i + j) % 2 == 1))
						type[i][j] = false;
					else if(!check && ((i + j) % 2 == 0))
						type[i][j] = false;
					++temp;
					++k;
				}
			}			
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < width; j++) {
					if(type[i][j] && arr[i][j] < 1001) {
						dfs(i, j, true);
					}
					if(!type[i][j] && arr[i][j] < 1001) {
						dfs(i, j, false);
					}
				}
			}
			sb.append(tc+". "+max+"\n");
			++tc;
			max = Integer.MIN_VALUE;
			str = br.readLine().split(" ");
		}
		System.out.println(sb);
	}

	private static void init() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < width; j++) {
				arr[i][j] = 1001;
			}
		}
	}

	private static void dfs(int y, int x, boolean type) {
		if(max < arr[y][x]) {
			max = arr[y][x];
		}
		if(type) {
			int resSize = n - y;			
			int[] resList = new int[resSize];
			resList[0] = arr[y][x];
			for (int i = 1; i < resSize; i++) {
				int val = findVal(y + i, x, i);
				resList[i] = resList[i - 1] + val; 
				if(max < resList[i]) {
					max = resList[i];
				}
			}
		} else {
			int resSize = y + 1;
			int[] resList = new int[resSize];
			resList[0] = arr[y][x];
			for (int i = 1; i < resSize; i++) {
				int val = findVal2(y - i, x, i);
				resList[i] = resList[i - 1] + val;
				if(max < resList[i]) {
					max = resList[i];
				}
			}
		}
	}

	//�� �ﰢ��
	private static int findVal2(int y, int x, int step) {
		int dx[] = setDx(step + 1);
		int sum = 0;
		for (int i = 0; i < (step + 1) * 2 - 1; i++) {
			int nextX = x + dx[i];//1(2��),2(3��)...
			if(nextX >= 0 && nextX < width) {
				if(arr[y][nextX] >= 1001) {
					sum = -999999;
					break;
				}
				sum += arr[y][nextX];	
			} else {
				sum = -999999;
				break;
			}
			
		}
		return sum;
	}

	//�� �ﰢ��
	private static int findVal(int y, int x, int step) {
		int dx[] = setDx(step + 1);
		int sum = 0;
		for (int i = 0; i < (step + 1) * 2 - 1; i++) {
			int nextX = x + dx[i];//1(2��),2(3��)...
			sum += arr[y][nextX];
		}
		return sum;
	}

	private static int[] setDx(int size) {
		int xSize = size * 2 - 1;//2���� 3�� 3���� 5��...
		int dx[] = new int[xSize];
		int temp = (size * -1) + 1;
		//2��(size = 2) -1,0,1    3��(size = 3) -2, -1, 0, 1, 2
		for (int i = 0; i < xSize; i++) {
			dx[i] = temp;
			temp++;
		}
		return dx;
	}
}

class Point_4902 {
	int y;
	int x;
	int size;
	
	public Point_4902(int y, int x, int size) {
		super();
		this.y = y;
		this.x = x;
		this.size = size;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}

