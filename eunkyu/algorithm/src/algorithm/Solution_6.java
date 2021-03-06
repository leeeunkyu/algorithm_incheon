package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//10:40~
public class Solution_6 {
	
	static int n = 10;
	static int[][] arr;
	static int m;
	static int bc;	//bc의 갯수
	static int [][][] isVal;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			arr = new int[n][n];
			isVal = new int [n][n][8];
			
			String[] str = br.readLine().split(" ");
			m = Integer.parseInt(str[0]);
			bc = Integer.parseInt(str[1]);
			int[] aInfo = new int[m];
			int[] bInfo = new int[m];
			String[] info = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				aInfo[i] = Integer.parseInt(info[i]);
			}
			info = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				bInfo[i] = Integer.parseInt(info[i]);
			}
			Battery_6[] batterys = new Battery_6[bc + 1];
			batterys[0] = new Battery_6(0, 0, 0, 0, 0);
			for (int i = 0; i < bc; i++) {
				String[] batteryInfo = br.readLine().split(" ");
				batterys[i + 1] = new Battery_6(
						Integer.parseInt(batteryInfo[0]) - 1,
						Integer.parseInt(batteryInfo[1]) - 1,
						Integer.parseInt(batteryInfo[2]),
						Integer.parseInt(batteryInfo[3]),
						i + 1);
			}			
			setMap(batterys);
			int res = goWalk(aInfo, bInfo, batterys);
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);

	}
	private static int goWalk(int[] aInfo, int[] bInfo, Battery_6[] batterys) {
		int aSum = 0;
		int bSum = 0;
		int ax = 0;
		int ay = 0;
		int bx = 0;
		int by = 0;
		//0 이동하지않음, 1 상, 2우, 3,하, 4좌
		int[] dx = {0, 0, 1, 0, -1};
		int[] dy = {0, -1, 0, 1, 0};
		for (int i = -1; i < m; i++) {
			if(i == -1) {
				ax = 0;
				ay = 0;
				bx = n - 1;
				by = n - 1;
			} else {
				ax += dx[aInfo[i]];
				ay += dy[aInfo[i]];
				bx += dx[bInfo[i]];
				by += dy[bInfo[i]];	
			}
			
			int aVal = arr[ay][ax];
			int bVal = arr[by][bx];
			if(aVal == 0 && bVal == 0)
				continue;
			if(aVal != bVal) {
				
				if(aVal == 10) {
					int amax = 0;
					for (int j = 0; j < 8; j++) {
						int num = isVal[ay][ax][j];
						if(num != 0) {
							if(num != bVal && amax < batterys[num].getP())
								amax = batterys[num].getP();
						}
					}
					
					aSum += amax;
				} else {
					aSum += batterys[aVal].getP();
				}
				
				if(bVal == 10) {
					int bmax = 0;
					for (int j = 0; j < 8; j++) {
						int num = isVal[by][bx][j];
						if(num != 0) {
							if(num != aVal && bmax < batterys[num].getP())
								bmax = batterys[num].getP();
						}
					}
					bSum += bmax;
				} else {
					bSum += batterys[bVal].getP();
				}
			}
			else if(aVal == bVal){
				Queue<Integer> q1 = new LinkedList<>();
				Queue<Integer> q2 = new LinkedList<>();
				Queue<Integer> q3 = new LinkedList<>();
				Queue<Integer> q4 = new LinkedList<>();
				
				for (int j = 0; j < 8; j++) {
					if(isVal[ay][ax][j] != 0) {
						q1.add(isVal[ay][ax][j]);
						q3.add(isVal[ay][ax][j]);
					}
					if(isVal[by][bx][j] != 0) {
						q2.add(isVal[by][bx][j]);
						q4.add(isVal[by][bx][j]);
					}
				}
				if(q1.isEmpty() && q2.isEmpty()) {
					aSum += batterys[aVal].getP() / 2;
					bSum += batterys[bVal].getP() / 2;
				} else {
					
					int max = 0;
					int maxNum = 0;
					while(!q1.isEmpty()) {
						int num = q1.poll();
						if(max < batterys[num].getP()) {
							max = batterys[num].getP();
							maxNum = num;
						}
					}
					int max2 = 0;
					while(!q2.isEmpty()) {
						int num = q2.poll();
						if(max2 < batterys[num].getP() && num != maxNum) {
							max2 = batterys[num].getP();
						}
					}
					int total1 = max + max2;
					
					int max3 = 0;
					int maxNum3 = 0;
					while(!q4.isEmpty()) {
						int num = q4.poll();
						if(max3 < batterys[num].getP()) {
							max3 = batterys[num].getP();
							maxNum3 = num;
						}
					}
					int max4 = 0;
					while(!q3.isEmpty()) {
						int num = q3.poll();
						if(max4 < batterys[num].getP() && num != maxNum3) {
							max4 = batterys[num].getP();
						}
					}
					int total2 = max3 + max4;
					if(total1 > total2) {
						aSum += max;
						bSum += max2;
					} else {
						aSum += max3;
						bSum += max4;
					}
				}
			}			
		}
		return aSum + bSum;
	}
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void setMap(Battery_6[] batterys) {
		for (int i = 0; i < bc; i++) {
			Battery_6 battery = batterys[i + 1];
			int x = battery.getX();
			int y = battery.getY();
			int c = battery.getC();
			int p = battery.getP();
			int num = battery.getNum();
			bfs(x, y, c, p, num);
		}
	}
	private static void bfs(int x, int y, int c, int p, int num) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[n][n];
		
		Queue<Battery_6> batteryq = new LinkedList<Battery_6>();
		batteryq.add(new Battery_6(x, y, 0, p, num));
		visited[y][x] = true;
		if(arr[y][x] != 0) {
			int temp = arr[y][x];
			int idx = 0;
			for (int i = 0; i < 8; i++) {
				if(isVal[y][x][i] == 0) {
					isVal[y][x][i] = num;
					idx = i;
					break;
				}
			}
			if(arr[y][x] != 10) {
				isVal[y][x][idx + 1] = temp;
			}
			arr[y][x] = 10;
		} else {
			arr[y][x] = num;			
		}

		while(!batteryq.isEmpty()) {
			Battery_6 battery = batteryq.poll();
			int bx = battery.getX();
			int by = battery.getY();
			int bc = battery.getC();
			if(bc == c)
				break;
			for (int dir = 0; dir < 4; dir++) {
				int nextX = bx + dx[dir];
				int nextY = by + dy[dir];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX]) {
					
					if(arr[nextY][nextX] != 0) {
						int temp = arr[nextY][nextX];
						
						int idx = 0;
						for (int i = 0; i < 8; i++) {
							if(isVal[nextY][nextX][i] == 0) {
								isVal[nextY][nextX][i] = num;
								idx = i;
								break;
							}
						}
						if(arr[nextY][nextX] != 10) {
							isVal[nextY][nextX][idx + 1] = temp;
						}
						
						arr[nextY][nextX] = 10;
						
					}else {
						arr[nextY][nextX] = num;	
					}
					visited[nextY][nextX] = true;
					batteryq.add(new Battery_6(nextX, nextY,bc + 1, p, num));
				}
			}
		}
	}
}

class Battery_6 {
	int x;
	int y;
	int c;
	int p;
	int num;
	
	public Battery_6(int x, int y, int c, int p, int num) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
		this.num = num;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Battery_6 [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", c=");
		builder.append(c);
		builder.append(", p=");
		builder.append(p);
		builder.append("]");
		return builder.toString();
	}
	
}
