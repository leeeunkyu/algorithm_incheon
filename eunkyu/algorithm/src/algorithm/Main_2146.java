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

		// 1. BFS ���� ���� ��ȣ�� �������ش�.
		// 2. ���� ��ġ�� ��� ���� ť�� �ִ´� (���ÿ� ��ô����� �����ϱ� ����)
		// 3. ��ô��� �� ���� ���� �ٸ� ���� ��´ٸ� �ִܰ��
		//   * �ִ� ��ΰ� �ƴ� ���� �ֱ⿡ ��� ��츦 ��� ���� �� �ּҰ� ���.
		
		/**���� �迭*/
		int[][] a = new int[n + 1][n + 1];
		int[][] dist = new int[n + 1][n + 1];
		/**�湮 Ȯ�� �� �迭*/
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
					//��ô��� �ϴ� �κ�
					if (a[ny][nx] != 0 && a[ny][nx] != a[y][x]) { 
						list.add(dist[ny][nx] + dist[y][x]);
					}
					//0�� �����ö����� �Ÿ� 1�� �߰�
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