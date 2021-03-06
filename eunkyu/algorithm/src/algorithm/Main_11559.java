package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559 {
	
	static final int HEIGHT = 12;
	static final int WEIDTH = 6;
	static char[][] map;
	static boolean[][] visited;
	static int boomCnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[HEIGHT][WEIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			char[] temp = br.readLine().toCharArray();
			map[i] = temp;
		}
		
		visited = new boolean[HEIGHT][WEIDTH];
		
		boom();
		System.out.println(boomCnt);
	}

	private static void boom() {
		boolean[][] boomList = new boolean[HEIGHT][WEIDTH];
		int boomcheck = 1;
		
		//한 그룹이라도 터트렸니?
		while(boomcheck != 0) {
			boomcheck = 0;
			
			//map에 있는것들중에 터트릴만한 애들이있니?
			if(!check()) {
				break;
			}
			
			boolean[][] tempBoom = new boolean[HEIGHT][WEIDTH];
			for (int i = 0; i < HEIGHT; i++) {
				for (int j = 0; j < WEIDTH; j++) {
					if(map[i][j] != '.' && !visited[i][j]) {
						visited[i][j] = true;
						int cnt = bfs(i, j, map[i][j], tempBoom);
						if(cnt >= 3) {
							//터트리는 그룹목록 리스트에 저장
							tempBoom[i][j] = true;
							copy(boomList, tempBoom);
							boomcheck++;
						}
					}
				}
			}
			
			visited = new boolean[HEIGHT][WEIDTH];

			if(boomcheck != 0) {
				//터트리는 그룹이 있넹
				goBoom(boomList);
				reset();
			}	
		}
			
	}

	private static boolean check() {
		char[] word = {'R', 'G', 'B', 'P', 'Y'};
		int r = 0;
		int g = 0;
		int b = 0;
		int p = 0;
		int y = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WEIDTH; j++) {
				if(map[i][j] != '.') {
					if(map[i][j] == word[0]) {
						++r;
					}
					else if(map[i][j] == word[1]) {
						++g;
					}
					else if(map[i][j] == word[2]) {
						++b;
					}
					else if(map[i][j] == word[3]) {
						++p;
					}
					else if(map[i][j] == word[4]) {
						++y;
					}
				}
			}
		}
		
		if(r >= 3 || g >= 3 || b >= 3 || p >= 3 || y >= 3) {
			return true;
		}
		
		return false;
	}

	private static void reset() {
		for (int w = 0; w < WEIDTH; w++) {
			Queue<Character> q = new LinkedList<>();
			for (int h = HEIGHT - 1; h >= 0; h--) {
				if(map[h][w] != '.') {
					q.add(map[h][w]);
					map[h][w] = '.';
				}
			}
			int temp = HEIGHT - 1;
			while (!q.isEmpty()) {
				char val = q.poll();
				map[temp][w] = val;
				temp--;
			}
		}
	}

	private static void goBoom(boolean[][] boomList) {
		boomCnt += 1;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WEIDTH; j++) {
				if(boomList[i][j]) {
					map[i][j] = '.';
				}
			}
		}
	}

	private static void copy(boolean[][] pre, boolean[][] next) {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WEIDTH; j++) {
				pre[i][j] = next[i][j];
			}
		}
	}

	private static int bfs(int y, int x, char val, boolean[][] tempBoom) {
		Point_11559 p = new Point_11559(y, x);
		Queue<Point_11559> points = new LinkedList<Point_11559>();
		boolean[][] bmap = new boolean[HEIGHT][WEIDTH];
		
		copy(bmap, tempBoom);
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0 ,0};
		
		int cnt = 0;
		points.add(p);
		
		while(!points.isEmpty()) {
			Point_11559 point = points.poll();
			int py = point.getY();
			int px = point.getX();
			for (int i = 0; i < 4; i++) {
				int nextX = px + dx[i];
				int nextY = py + dy[i];
				
				if(nextX >= 0 && nextX < WEIDTH && nextY >= 0 && nextY < HEIGHT) {
					if(val == map[nextY][nextX] && !visited[nextY][nextX]) {
						cnt++;
						visited[nextY][nextX] = true;
						tempBoom[nextY][nextX] = true;
						points.add(new Point_11559(nextY, nextX));
						
					}
				}
			}
		}
		
		
		if(cnt < 3) {
			//같은 문자를 가진애가 4개는 되니?
			copy(tempBoom, bmap);
		}		
		return cnt;
	}
}

class Point_11559 {
	int y;
	int x;
	
	public Point_11559(int y, int x) {
		super();
		this.y = y;
		this.x = x;
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
}
