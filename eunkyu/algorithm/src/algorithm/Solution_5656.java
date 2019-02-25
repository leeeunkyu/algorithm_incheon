package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_5656 {
	
	static int n;
	static int w;
	static int h;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			n = Integer.parseInt(info[0]);
			w = Integer.parseInt(info[1]);
			h = Integer.parseInt(info[2]);
			int[][] arr = new int[h][w];
			int[][] copyMap = new int[h][w];
			for (int i = 0; i < h; i++) {
				String[] stoneInfo = br.readLine().split(" ");
				for (int j = 0; j < w; j++) {
					int val = Integer.parseInt(stoneInfo[j]);
					arr[i][j] = val;
					copyMap[i][j] = val;
				}
			}
			
			for (int i = 0; i < n; i++) {
				Stone_5656[] stones = topStone(arr);
				dropStone(arr, stones, copyMap);
				modiStone(arr);
			}
			
			int res = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(arr[i][j] != 0) {
						res += 1;
					}
				}
			}
			
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void modiStone(int[][] arr) {
		System.out.println("=========modi============");
		for (int i = 0; i < w; i++) {
			int cnt = 0;
			boolean isModi = false;
			for (int j = h - 1; j >= 0; j--) {
				if(arr[j][i] == 0) {
					if(isModi)
						cnt = 0;
					cnt += 1;
				} else {
					if(cnt == 0)
						continue;
					int val = arr[j][i];
					arr[j + cnt][i] = val;
					arr[j][i] = 0;
					isModi = true;
				}
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void dropStone(int[][] arr, Stone_5656[] stones, int[][] copyMap) {
		System.out.println("=====drop========");
		int cnts = 0;
		int max = 0;
		int[][] resMap = new int[h][w];
		Queue<Stone_5656> stoneQueue = new LinkedList<Stone_5656>();
		
		for (int i = 0; i < w; i++) {
			Stone_5656 stone = stones[i];
			if(stone == null)
				continue;
			stoneQueue.add(stone);
			cnts = 0;
			while (!stoneQueue.isEmpty()) {
				int[] dx = {0, 0, 1, -1};
				int[] dy = {1, -1, 0, 0};
				stone = stoneQueue.poll();
				int y = stone.getY();
				int x = stone.getX();
				int num = stone.getNum();
				cnts += 1;
				arr[y][x] = 0;
				for (int j = 1; j < num; j++) {					
					for (int j2 = 0; j2 < 4; j2++) {
						int nextX = x + dx[j2];
						int nextY = y + dy[j2];
						if(nextX >= 0 && nextX < w && nextY >= 0 && nextY < h
								&& arr[nextY][nextX] != 0) {
							stoneQueue.add(new Stone_5656(nextY, nextX, arr[nextY][nextX]));
							arr[nextY][nextX] = 0;
							cnts += 1;
						}
					}
					dx[2] += 1;
					dx[3] += (-1);
					dy[0] += 1;
					dy[1] += (-1);
				}	
			}
			if(max < cnts) {
				max = cnts;
				copy(arr, resMap);
			} else {
				copy(copyMap, arr);
			}		
		}
		copy(resMap, arr);
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(resMap[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void copy(int[][] copy, int[][] arr) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arr[i][j] = copy[i][j];
			}
		}
	}

	private static Stone_5656[] topStone(int[][] arr) {
		Stone_5656[] stones = new Stone_5656[w];
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int val = arr[i][j];
				if(val != 0 && stones[j] == null) {
					stones[j] = new Stone_5656(i, j, val);
				}
			}
		}

		return stones;
	}
}

class Stone_5656 {
	int y;
	int x;
	int num;
	
	public Stone_5656(int y, int x, int num) {
		super();
		this.y = y;
		this.x = x;
		this.num = num;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stone_5656 [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", num=");
		builder.append(num);
		builder.append("]");
		return builder.toString();
	}
}
