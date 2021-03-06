package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * (점)은 빈 필드를 의미하며, 글자 '#'는 울타리를, 'o'는 양, 'v'는 늑대를 의미한다.
 * 다행히 우리의 양은 카라테 검은 띠 보유자여서 늑대에게 싸움을 걸 수 있고 영역 안의 양의 수가 늑대의 수보다 많다면 이기게 된다. 
 * 그렇지 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
 */
//11시 25분 까지
public class Main_3184 {
	
	static int r; //행
	static int c; //열
	static char[][] arr;
	static boolean[][] visited;
	static int resSheep;
	static int resWolf;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		arr = new char[r][c];
		visited = new boolean[r][c];
		resSheep = 0;
		resWolf = 0;
		
		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
/*		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		*/
		goGame();
		System.out.println(resSheep+" "+resWolf);
	}

	private static void goGame() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] != '#' && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
	}

	private static void bfs(int i, int j) {
		Queue<Point_3184> points = new LinkedList<Point_3184>();
		points.add(new Point_3184(i, j));
		visited[i][j] = true;
		
		int sheepCnt = 0;
		int wolfCnt = 0;
		
		if(arr[i][j] == 'o') {
			sheepCnt++;
		} else if(arr[i][j] == 'v') {
			wolfCnt++;
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		while (!points.isEmpty()) {
			Point_3184 point = points.poll();
			int x = point.getX();
			int y = point.getY();
			for (int idx = 0; idx < 4; idx++) {
				int nextX = x + dx[idx];
				int nextY = y + dy[idx];
				if(nextX >= 0 && nextX < c && nextY >= 0 && nextY < r
						&& !visited[nextY][nextX] && arr[nextY][nextX] != '#') {
					visited[nextY][nextX] = true;
					if(arr[nextY][nextX] == 'o') {
						sheepCnt++;
					} else if(arr[nextY][nextX] == 'v') {
						wolfCnt++;
					}
					points.add(new Point_3184(nextY, nextX));
/*					System.out.println(arr[nextY][nextX]+" y: "+nextY+" x:"+nextX);
*/				}
			}
		}
		
		if(sheepCnt > wolfCnt) {
			resSheep += sheepCnt;
		} else {
			resWolf += wolfCnt;
		}
	}
}

class Point_3184 {
	int y;
	int x;
	
	public Point_3184(int y, int x) {
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
