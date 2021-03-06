package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_2117 {
	
	static int n;
	static int m;
	static int[][] arr;
	static Home_2117[] homes;
	static int size;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			arr = new int[n][n];
			Home_2117[] temphomes = new Home_2117[n * n];
			size = 0;
			
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int val = Integer.parseInt(info[j]);
					arr[i][j] = val;
					if(val == 1) {
						temphomes[size] = new Home_2117(i, j, 0);
						++size;
					}
				}
			}
			homes = new Home_2117[size];
			for (int i = 0; i < size; i++) {
				homes[i] = temphomes[i];
			}
			
			int res = goPrevention();
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
	}


	private static int goPrevention() {
		//Arrays.sort(homes);
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int maxHome = bfs(new Point_2117(i, j, 0));
				if(res < maxHome)
					res = maxHome;
			}
		}
		return res;
	}


	private static int bfs(Point_2117 h) {
		int k = 1;
		int kVal = k * k + (k - 1) * (k - 1);
		int homeCnt = 0;
		boolean[][] visited = new boolean[n][n];
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Point_2117> points = new LinkedList<>();
		points.add(new Point_2117(h.getY(), h.getX(), 1));
		visited[h.getY()][h.getX()] = true;
		if(arr[h.getY()][h.getX()] == 1)
			homeCnt += 1;
		int maxHome = 0;
		int preStep = 0;
		
		while(!points.isEmpty()) {
			Point_2117 p = points.poll();
			int y = p.getY();
			int x = p.getX();
			k = p.getStep();
			kVal = k * k + (k - 1) * (k - 1);
			if(k != preStep) {
				if(kVal <= (m * homeCnt)) {
					maxHome = homeCnt;
				}
				/*System.out.println();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if(visited[i][j])
							System.out.print("1 ");
						else
							System.out.print("0 ");
					}
					System.out.println();
				}
				System.out.println(m * homeCnt+"  "+homeCnt+"  "+k+" "+kVal);
*/				preStep = k;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX]) {
					points.add(new Point_2117(nextY, nextX, k + 1));
					visited[nextY][nextX] = true;
					
					if(arr[nextY][nextX] == 1) {
						++homeCnt;
					}
					
				}
			}
			
			//if(k )
		}
		
		return maxHome;		
	}


	private static Point_2117 getDist() {
		int minVal = Integer.MAX_VALUE;
		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int dist = 0;
				int idx = 0;
				for (int dir = 0; dir < size; dir++) {
					Home_2117 home = homes[dir];
					int tempDist = (Math.abs(i - home.getY()) + Math.abs(j - home.getX()));
					dist += tempDist;
				}
				if(minVal > dist ) {
					minVal = dist;
					x = j;
					y = i;
				}
			}
		}
		System.out.println(y+" "+x);
		return new Point_2117(y, x, 0);
	}
}

class Home_2117 implements Comparable<Home_2117>{
	int y;
	int x;
	int dist;
	
	public Home_2117(int y, int x, int dist) {
		super();
		this.y = y;
		this.x = x;
		this.dist = dist;
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
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}

	@Override
	public int compareTo(Home_2117 home) {
		return this.dist - home.getDist();
	}
}

class Point_2117 {
	int y;
	int x;
	int step;
	
	public Point_2117(int y, int x, int step) {
		super();
		this.y = y;
		this.x = x;
		this.step = step;
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

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
}
