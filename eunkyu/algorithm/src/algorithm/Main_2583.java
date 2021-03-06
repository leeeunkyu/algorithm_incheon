package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_2583 {
	
	static int[][] arr;
	static int n;
	static int m;
	static boolean[][] visited;
	static int sum; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		m = Integer.parseInt(str[0]);	//y ����
		n = Integer.parseInt(str[1]);	//x ����
		int k = Integer.parseInt(str[2]);
		
		arr = new int[m][n];
		
		for (int i = 0; i < k; i++) {
			String[] info = br.readLine().split(" ");
			int x1 = Integer.parseInt(info[0]);
			int y1 = m - Integer.parseInt(info[1]);
			int x2 = Integer.parseInt(info[2]);
			int y2 = m - Integer.parseInt(info[3]);
			
			setSqure(x1, y1, x2, y2);
		}

		visited = new boolean[m][n];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(!visited[i][j] && arr[i][j] == 0) {
					sum = 1;
					visited[i][j] = true;
					dfs(i, j);
					pq.add(sum);
				}
			}
		}
		System.out.println(pq.size());
		while (!pq.isEmpty()) {
			if(pq.size() != 1)
				System.out.print(pq.poll()+" ");
			else
				System.out.print(pq.poll());
		}
	}

	private static void dfs(int i, int j) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for (int idx = 0; idx < 4; idx++) {
			int nextX = j + dx[idx];
			int nextY = i + dy[idx];
			if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m
					&& !visited[nextY][nextX] && arr[nextY][nextX] == 0) {
				visited[nextY][nextX] = true;
				sum++;
				dfs(nextY, nextX);
			}
		}
	}

	private static void setSqure(int x1, int y1, int x2, int y2) {
		for (int i = y2; i < y1; i++) {
			for (int j = x1; j < x2; j++) {
				arr[i][j] = 1;
			}
		}
		
	}
}
