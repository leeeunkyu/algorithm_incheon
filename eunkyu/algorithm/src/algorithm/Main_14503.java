package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;

public class Main_14503 {
	
	static int n;
	static int m;
	static int r;	//y축
	static int c;	//x축
	static int d;	//방향
	static int[][] arr;
	static boolean[][] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		String[] info = br.readLine().split(" ");
		r = Integer.parseInt(info[0]);
		c = Integer.parseInt(info[1]);
		d = Integer.parseInt(info[2]);
		
		visited = new boolean[n][m];
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		cnt = 1;
		visited[r][c] = true;
		arr[r][c] = 2;
		goClean();
		System.out.println(cnt);
	}
	// d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
	// 빈칸은 0 벽은 1
	// 외각은 벽이기때문에 1 ~ n-1까지만 탐색
	private static void goClean() {
		//왼쪽 방향 탐색 (청소할 공간이 있든 없든 일단 회전)
	/*	System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}*/

		int[] dir = {3, 0, 1, 2};
		
		d = dir[d];
		
		
		boolean okClean = false;
		for (int i = 0; i < 4; i++) {
			//왼쪽 방향에 청소할 애가 있냐?
			if (isClean()) {
				okClean = true;
				cnt++;
				//현재 위치 청소
				visited[r][c] = true;
				arr[r][c] = 2;
				goClean();
				break;
			} else {
				d = dir[d];
			}	
		}
		
		//4방향 어느방향으로도 청소하지 못했다면?
		boolean okBack = false;
		if(!okClean) {
			for (int i = 0; i < dir.length; i++) {
				if(isBack()) {
					okBack = true;
					goClean();
					break;
				} else {
					d = dir[d];
				}	
			}
			if(!okBack) {
				return;
			}
		}
		
	
	}
	private static boolean isBack() {
		int[] dx = {0, -1, 0, 1};
		int[] dy = {1, 0, -1, 0};
		
		int nextX = c + dx[d];
		int nextY = r + dy[d];
		
		if(nextX >= 1 && nextX < n-1 && nextY >= 1 && nextY < n -1
				&& arr[nextY][nextX] != 1) {
			
				c += dx[d];
				r += dy[d];
				return true;	
			/* else {
				nextX += dx[d];
				nextY += dy[d];
			}*/
		}
		return false;
	}
	private static boolean isClean() {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		int nextX = c + dx[d];
		int nextY = r + dy[d];
		
		if(nextX >= 1 && nextX < n-1 && nextY >= 1 && nextY < n -1
				&& arr[nextY][nextX] != 1) {
			if(!visited[nextY][nextX]) {
				c += dx[d];
				r += dy[d];
				return true;	
			}/* else {
				nextX += dx[d];
				nextY += dy[d];
			}*/
		}
		return false;
	
	}
}