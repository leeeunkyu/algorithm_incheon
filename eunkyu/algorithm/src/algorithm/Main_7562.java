package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_7562 {
	static int l;	//체스판 크기
	static int arr[][];
	static boolean visitied[][];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			l = Integer.parseInt(br.readLine());
			arr = new int[l][l];
			visitied = new boolean[l][l];
			String str[] = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			arr[y][x] = 1;	//나이트 현재 위치
			
			str = br.readLine().split(" ");
			int x2 = Integer.parseInt(str[0]);
			int y2 = Integer.parseInt(str[1]);
			arr[y2][x2] = -1; //나이트가 이동해야할 위치
			bfs(x, y);
			int cnt = 0;
	/*		for (int j = 0; j < visitied.length; j++) {
				for (int j2 = 0; j2 < visitied.length; j2++) {
					if(visitied[j][j2])
						cnt++;
					visitied[j][j2] = false;
				}
			}*/
			sb.append("-------------------------------------\n");
		}
		System.out.println(sb);
	}
	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		int dx[] = {1, 2, 2, 1, -1, -2, -2, -1};
		int dy[] = {-2, -1, 1, 2, 2, 1, -1, -2};
		int min = 300 * 300;
		q.add(new Point(x, y));
		visitied[y][x] = true;
		int temp = 1;
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 8; i++) {
				int nextX = p.getX() + dx[i];
				int nextY = p.getY() + dy[i];
				if(nextX >= 0 && nextX < l && nextY >= 0 && nextY < l) {
					if(arr[nextY][nextX] == -1) {
						sb.append(cnt+"\n");
						sb.append(temp+"\n");
						return ;
					}
					if(!visitied[nextY][nextX]) {
						q.add(new Point(nextX, nextY));
						visitied[nextY][nextX] = true;
					}	
				}
			}
		}
		
	}
}

class Point {
	
	int x;
	int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
	
}
