package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146 {
	public static final int[] dx = { 0, 0, 1, -1 };
	public static final int[] dy = { 1, -1, 0, 0 };
	public static final ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 1. BFS 통해 섬의 번호를 지정해준다.
		// 2. 섬인 위치는 모두 먼저 큐에 넣는다 (동시에 간척사업을 시작하기 위함)
		// 3. 간척사업 중 먼저 서로 다른 섬이 닿는다면 최단경로
		//   * 최단 경로가 아닐 수도 있기에 닿는 경우를 모두 저장 후 최소값 출력.
		
		/**원본 배열*/
		int[][] a = new int[n + 1][n + 1];
		int[][] dist = new int[n + 1][n + 1];
		/**방문 확인 용 배열*/
		boolean[][] c = new boolean[n + 1][n + 1];
		Queue<Pair> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 1; j <= n; j++) {
				a[i][j] = Integer.parseInt(s[j - 1]);
			}
		}

		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (a[i][j] == 1 && !c[i][j]) {
					numberNominate(a, c, j, i, ++cnt);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (a[i][j] != 0) {
					q.add(new Pair(j, i));
				}
			}
		}

		bfs(q, a, c, dist);

		int ans = Integer.MAX_VALUE;
		for(int v : list) {
			if (ans > v) {
				ans = v;
			}
		}
		
		System.out.println(ans);
		
	}

	public static void numberNominate(int[][] a, boolean[][] c, int x, int y, int idx) {
		int n = a.length - 1;
		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(x, y));
		a[y][x] = idx;
		c[y][x] = true;

		while (!q.isEmpty()) {

			x = q.peek().x;
			y = q.peek().y;
			q.poll();

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (0 < nx && nx <= n && 0 < ny && ny <= n) {

					if (a[ny][nx] == 1 && !c[ny][nx]) {
						q.add(new Pair(nx, ny));
						a[ny][nx] = idx;

						c[ny][nx] = true;
					}
				}
			}

		}

	}

	public static void bfs(Queue<Pair> q, int[][] a, boolean[][] c, int[][] dist) {
		int n = a.length - 1;

		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (0 < nx && nx <= n && 0 < ny && ny <= n) {
					//간척사업 하는 부분
					if (a[ny][nx] != 0 && a[ny][nx] != a[y][x]) { 
						list.add(dist[ny][nx] + dist[y][x]);
					}
					//0을 지나올때마다 거리 1씩 추가
					if (a[ny][nx] == 0) {
						q.add(new Pair(nx, ny));

						a[ny][nx] = a[y][x];
						dist[ny][nx] = dist[y][x] + 1;
					}
				}
			}

		}

	}
}

class Pair {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}