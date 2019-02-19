package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Zoom_2 {
	static int N;
	static int M;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 3;
		M = 5;
		int[][] A = new int [N][M];
		visited = new boolean [N][M];
		for (int i = 0; i < A.length; i++) {
			String str[] = br.readLine().split(" ");
			for (int j = 0; j < A.length; j++) {
				A[i][j] = Integer.parseInt(str[j]);
			}
		}
		new Zoom_2().solution(A, N, M);
	}

	private void solution(int[][] a, int n, int m) {
		PriorityQueue<Pivot> pivots = new PriorityQueue<Pivot>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				pivots.add(new Pivot(j, i, a[i][j]));
			}
		}		
		Pivot pivot = pivots.poll();
		int res = dfs(a, pivot, ""+pivot.getNum());
		
		while(res != 0) {
			pivot = pivots.poll();
			res = dfs(a, pivot, ""+pivot.getNum());
		}
		
		System.out.println(res);
		
		
	}

	private int dfs(int[][] a, Pivot pivot, String str) {
		int max = 0;
		int x = pivot.getX();
		int y = pivot.getY();
		if(str.length() >= 4) {
			System.out.println("res: "+Integer.parseInt(str));
			return Integer.parseInt(str);
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if(nextX >= 0 && nextX < M && nextY >= 0 && nextY < N
					&& !visited[nextY][nextX]) {
				visited[nextY][nextX] = true;
				str += a[nextY][nextX];
				pivot.setX(nextX);
				pivot.setY(nextY);
				System.out.println(str.length()+" y: "+nextY +" x: " +nextX+" str: "+str);
				max = Math.max(Integer.parseInt(str), dfs(a, pivot, str));
			}
		}
		
		return max;
	}
}

class Pivot implements Comparable<Pivot>{
	int x;	//x좌표 행렬의 열
	int y;  //y좌표 행렬의 행
	int num; //해당 값
	
	public Pivot(int x, int y, int num) {
		super();
		this.x = x;
		this.y = y;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int compareTo(Pivot pivot) {
		return pivot.getNum() - this.getNum();
	}
	
	
}
