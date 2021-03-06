package algorithm;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;

public class Solution_8 {

	static int n;
	static int w;
	static int h;
	static int[][] arr;
	static int minCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			w = Integer.parseInt(str[1]);
			h = Integer.parseInt(str[2]);
			arr = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(info[j]);
				}
			}
			minCnt = Integer.MAX_VALUE;
			goGame(0);
			sb.append("#"+tc+" "+minCnt+"\n");
		}
		System.out.println(sb);
	}

	private static void goGame(int cnt) {
		if(cnt == n) {
			//���� ����
			int block = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(arr[i][j] != 0) {
						++block;
					}
				}
			}
			
			if(block < minCnt) {
				minCnt = block;
			}
			return;
		}
		for (int i = 0; i < w; i++) {
			int[][] copy = new int[h][w];
			copy(copy, true);
			drop(i);
		//	print();
			setMap();
			goGame(cnt + 1);
			copy(copy, false);
		}
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void copy(int[][] copy, boolean type) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(type)
					copy[i][j] = arr[i][j];
				else
					arr[i][j] = copy[i][j];
			}
		}
	}

	private static void setMap() {
		//System.out.println("=========");
	//print();
		for (int x = 0; x < w; x++) {
			Queue<Integer> q = new LinkedList<Integer>();
			for (int y = h - 1; y >= 0; y--) {
				if(arr[y][x] != 0) {
					q.add(arr[y][x]);
					arr[y][x] = 0;
				}
			}
			int temp = h - 1;
			while(!q.isEmpty()) {
				arr[temp][x] = q.poll();
				--temp;
			}
		}
		//print();
		//System.out.println("=========");

	}

	private static void drop(int i) {
		for (int y = 0; y < h; y++) {
			if(arr[y][i] != 0) {
				int val = arr[y][i];
				arr[y][i] = 0;
				if(val > 1) {
					boom(y, i, val);
				}
				break;
			}
		}
	}

	private static void boom(int y, int x, int val) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int dir = 0; dir < 4; dir++) {
			int cpx = x;
			int cpy = y;
			for (int idx = 1; idx < val; idx++) {
				cpy += dy[dir];
				cpx += dx[dir];
				if(cpx >= 0 && cpx < w && cpy >= 0 && cpy <h) {
					int val2 = arr[cpy][cpx];
					arr[cpy][cpx] = 0;
					if(val2 > 1) {
						boom(cpy, cpx, val2);
					}	
				}
			}
		}
	}
}
