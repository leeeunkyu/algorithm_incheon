package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_5644 {
	
	static final int MAP_SIZE = 11;//맵 사이즈
	static int m;	//총 이동시간
	static int a;	//bc의 갯수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			m = Integer.parseInt(info[0]);
			a = Integer.parseInt(info[1]);
			Battery_5644[] batterys = new Battery_5644[a];
			int[] walkA = new int[m + 1];
			int[] walkB = new int[m + 1];
			walkA[0] = 0;
			walkB[0] = 0;
			String[] walkInfoA = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				walkA[i + 1] = Integer.parseInt(walkInfoA[i]);
			}
			
			String[] walkInfoB = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				walkB[i + 1] = Integer.parseInt(walkInfoB[i]);
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
			//겹치는걸 어떻게 처리
			//500이상숫자로 처리하면 어떨까
			/*
			 * a b c d e f g h
			 * a b c d e f g h
			 * ....
			 * a b c d e f g h
			 */
			
			
			int[][][] valueMap = new int[MAP_SIZE][MAP_SIZE][1<<8];
			int[][] map = new int[MAP_SIZE][MAP_SIZE];
			init(map, batterys, valueMap);
			
			
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
		int sumA = 0;
		int sumB = 0;
		int sum = 0;
		int res = 0;
		for (int i = 0; i < m + 1; i++) {
			int a = walkA[i];
			int b = walkB[i];
			
			sumA = 0;
			sumB = 0;
			
			ax += dx[a];
			ay += dy[a];
			bx += dx[b];
			by += dy[b];
						
			if(checkSection(ax, ay, bx, by, map, batterys)) {
				//같은 베터리 영역안에 있다.
				int aVal = map[ay][ax];
				int bVal = map[by][bx];
				if(aVal <= 500 && bVal <= 500) {
					sumA += (aVal/2);
					sumB += (bVal/2);
				} else if(aVal > 500 && bVal > 500){
					
				}
				/*if(val > 500) {
					int[] tempMap = new int[8];
					//y, x, num
					for (int k = 0; k < valueMap[ay][ax].length; k++) {
						tempMap[k] = valueMap[ay][ax][k];
					}
					Arrays.sort(tempMap);
					sumA += tempMap[tempMap.length - 1];
					sumB += tempMap[tempMap.length - 2];
				} 
				else {
					sumA += val;
				}*/
				
			} 
			
			
			else {
				int val = map[ay][ax];
				if(val > 500) {
					int[] tempMap = new int[8];
					//y, x, num
					for (int k = 0; k < valueMap[ay][ax].length; k++) {
						tempMap[k] = valueMap[ay][ax][k];
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

			/*System.out.println("sumA: "+sumA);
			System.out.println("sumB: "+sumB);*/
			sum = sumA + sumB;
		}

		res += sum;
		return res;
	}

	private static boolean checkSection(int ax, int ay, int bx, int by, int[][] map, Battery_5644[] batterys) {
		boolean check = false;
		for (int i = 0; i < batterys.length; i++) {
			int x = batterys[i].getX();
			int y = batterys[i].getY();
			int c = batterys[i].getC();
			int num = batterys[i].getNum();
			int subAX = Math.abs(ax - x) + Math.abs(y - ay);
			int subBX = Math.abs(bx -x ) + Math.abs(y - by);
			if(subAX <= c * 2) {
				//a는 이 베터리 안에 있다.
				//둘은 같은 베터리 안에 있다.
				if(subBX <= c * 2) {
					check =  true;					
				} else {
					
				}
			}
			
		}
		return check;
	}

	private static void init(int[][] map, Battery_5644[] batterys, int[][][] valueMap) {
		int cnt = 0;
		for (int i = 0; i < a; i++) {
			Battery_5644 battery = batterys[i];
			int x = battery.getX();
			int y = battery.getY();
			int c = battery.getC();
			int p = battery.getP();
			if(map[y][x] == 0){
				map[y][x] = p;
				bfs(map, valueMap, y, x, c, p, i);
			} else {
				int temp = map[y][x];
				map[y][x] = Integer.parseInt(""+5+y+x);
				valueMap[y][x][1<<i] = p;
				valueMap[y][x][1<<i-1] = temp;
				bfs(map, valueMap, y, x, c, p, i);
			}
			
		}
	}

	private static void bfs(int[][] map, int[][][] valueMap, int y, int x, int c, int p, int num) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
		Queue<Point_5644> points = new LinkedList<Point_5644>();
		points.add(new Point_5644(x, y, 0));
		visited[y][x] = true;
		while(!points.isEmpty()) {
			Point_5644 point = points.poll();
			int px = point.getX();
			int py = point.getY();
			int cnt = point.getCnt();
			for (int i = 0; i < 4; i++) {
				int nextX = px + dx[i];
				int nextY = py + dy[i];
				
				if(nextX >= 1 && nextX < MAP_SIZE && nextY >= 1 && nextY <MAP_SIZE
						&& !visited[nextY][nextX] && cnt < c) {
					
					visited[nextY][nextX] = true;
					if(map[nextY][nextX] == 0) {
						map[nextY][nextX] = p;
					} else {
						int temp = map[nextY][nextX];
						map[nextY][nextX] = Integer.parseInt(""+5+nextY+nextX);
						valueMap[nextY][nextX][1<<num] = p;
						valueMap[nextY][nextX][1<<num-1] = temp;
					}
					points.add(new Point_5644(nextX, nextY, cnt + 1));
				}
				
			}	
		}
		System.out.println();
		for (int k = 0; k < map.length; k++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[k][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		
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
	int cnt;
	
	public Point_5644(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}