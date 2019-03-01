package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_5644 {
	
	static final int MAP_SIZE = 11;//�� ������
	static int m;	//�� �̵��ð�
	static int a;	//bc�� ����
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			m = Integer.parseInt(info[0]);
			a = Integer.parseInt(info[1]);
			Battery_5644[] batterys = new Battery_5644[a];
			int[] walkA = new int[m];
			int[] walkB = new int[m];
			
			String[] walkInfoA = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				walkA[i] = Integer.parseInt(walkInfoA[i]);
			}
			
			String[] walkInfoB = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				walkB[i] = Integer.parseInt(walkInfoB[i]);
			}
			
			for (int i = 0; i < a; i++) {
				String[] batteryInfo = br.readLine().split(" ");
				//x,y   c,  p
				batterys[i] = new Battery_5644(
						Integer.parseInt(batteryInfo[0]),
						Integer.parseInt(batteryInfo[1]),
						Integer.parseInt(batteryInfo[2]),
						Integer.parseInt(batteryInfo[3]),
						i);
			}
			//��ġ�°� ��� ó��
			//500�̻���ڷ� ó���ϸ� ���
			/*
			 * a b c d e f g h
			 * a b c d e f g h
			 * ....
			 * a b c d e f g h
			 */
			int[][][] valueMap = new int[MAP_SIZE][MAP_SIZE][8];
			int[][] map = new int[MAP_SIZE][MAP_SIZE];
			init(map, batterys, valueMap);
			
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			
			System.out.println();
			int res = goWalk(map, batterys, valueMap, walkA, walkB);
			System.out.println(res);
		}
	}

	private static int goWalk(int[][] map, Battery_5644[] batterys, int[][][] valueMap, int[] walkA, int[] walkB) {
		int ax = 1;
		int ay = 1;
		int bx = 10;
		int by = 10;
		int[] dx = {0, 0, 1, 0, -1};
		int[] dy = {0, -1, 0, 1, 0};
		int sumA = map[ay][ax];
		int sumB = map[by][bx];
		int sum = 0;
		int res = 0;
		for (int i = 0; i < m; i++) {
			int a = walkA[i];
			int b = walkB[i];
			ax += dx[a];
			ay += dy[a];
			
			bx += dx[b];
			by += dy[b];
			
			if(ax == bx && ay == by) {
				int val = map[ay][ax];
				if(val > 500) {
					String str = val+"";
					char[] temp = str.toCharArray();
					int valueMapY = temp[0] - '0';
					int valueMapX = temp[1] - '0';
					int[] tempMap = new int[8];
					//y, x, num
					for (int k = 0; k < valueMap[valueMapY][valueMapX].length; k++) {
						tempMap[k] = valueMap[valueMapY][valueMapX][k];
					}
					Arrays.sort(tempMap);
					sumA += tempMap[tempMap.length - 1];
					sumB += tempMap[tempMap.length - 2];
				} 
				else {
					sumA += val;
				}
				
			} else {
				int val = map[ay][ax];
				if(val > 500) {
					String str = val+"";
					char[] temp = str.toCharArray();
					int valueMapY = temp[0] - '0';
					int valueMapX = temp[1] - '0';
					int[] tempMap = new int[8];
					//y, x, num
					for (int k = 0; k < valueMap[valueMapY][valueMapX].length; k++) {
						tempMap[k] = valueMap[valueMapY][valueMapX][k];
					}
					Arrays.sort(tempMap);
					sumA += tempMap[tempMap.length - 1];
				} else {
					sumA += val;
				}
				val = map[by][bx];
				if(val > 500) {
					String str = val+"";
					char[] temp = str.toCharArray();
					int valueMapY = temp[0] - '0';
					int valueMapX = temp[1] - '0';
					int[] tempMap = new int[8];
					//y, x, num
					for (int k = 0; k < valueMap[valueMapY][valueMapX].length; k++) {
						tempMap[k] = valueMap[valueMapY][valueMapX][k];
					}
					Arrays.sort(tempMap);
					sumB += tempMap[tempMap.length - 1];
				} else {
					sumB += val;
				}
			}
			sum = sumA + sumB;
		}
		res += sum;
		return res;
	}

	private static void init(int[][] map, Battery_5644[] batterys, int[][][] valueMap) {
		for (int i = 0; i < a; i++) {
			Battery_5644 battery = batterys[i];
			int x = battery.getX();
			int y = battery.getY();
			int c = battery.getC();
			int p = battery.getP();
			int num = battery.getNum();
			if(map[y][x] == 0){
				map[y][x] = p;
				bfs(map, valueMap, y, x, c, p, num);
			} else {
				map[y][x] = Integer.parseInt(""+5+y+x+num);
				valueMap[y][x][num] = p;
				bfs(map, valueMap, y, x, c, p, num);
			}
		}
	}

	private static void bfs(int[][] map, int[][][] valueMap, int y, int x, int c, int p, int num) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
		Queue<Point_5644> points = new LinkedList<Point_5644>();
		points.add(new Point_5644(x, y));
		visited[y][x] = true;
		while(!points.isEmpty()) {
			Point_5644 point = points.poll();
			int px = point.getX();
			int py = point.getY();
			for (int i = 0; i < 4; i++) {
				int nextX = px + dx[i];
				int nextY = py + dy[i];
				if(nextX >= 1 && nextX < MAP_SIZE && nextY >= 1 && nextY <MAP_SIZE
						&& !visited[nextY][nextX] && nextX > x-c && nextX < x+c
						&& nextY > y-c && nextY < y+c) {
					visited[nextY][nextX] = true;
					if(map[y][x] == 0) {
						map[y][x] = p;
					} else {
						map[y][x] = Integer.parseInt(""+5+y+x+num);
						valueMap[y][x][num] = p;
					}
					points.add(new Point_5644(nextX, nextY));
				}
			}	
		}
		
	}
}

class Battery_5644 {
	int y;
	int x;
	int c;
	int p;
	int num;
	
	public Battery_5644(int x, int y, int c, int p, int num) {
		super();
		this.y = y;
		this.x = x;
		this.c = c;
		this.p = p;
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
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

class Point_5644 {
	int x;
	int y;
	
	public Point_5644(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
	
}