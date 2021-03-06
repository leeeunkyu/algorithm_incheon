package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2580 {
	
	static final int SDQ_Size = 9;
	//static int[][] arr;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[SDQ_Size][SDQ_Size];
		Queue<Point_2580> pointq = new LinkedList<Point_2580>();
		for (int i = 0; i < SDQ_Size; i++) {
			String[] sdqInfo = br.readLine().split(" ");
			for (int j = 0; j < SDQ_Size; j++) {
				int val =  Integer.parseInt(sdqInfo[j]);
				arr[i][j] = val;
				if(val == 0) {
					pointq.add(new Point_2580(i, j));
				}
			}
		}
		size = pointq.size();
		Point_2580[] points = new Point_2580[size];
		for (int i = 0; i < points.length; i++) {
			points[i] = pointq.poll();
		}
		
		goGame(arr, points, 0);
		
		
	}
	private static void goGame(int[][] arr, Point_2580[] points, int cnt) {
		if(size <= cnt) {
			for (int k = 0; k < arr.length; k++) {
				for (int j = 0; j < arr[k].length; j++) {
					if(j == arr[k].length - 1)
						System.out.print(arr[k][j]);
					else
						System.out.print(arr[k][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		Point_2580 point = points[cnt];

		for (int i = 1; i <= 9; i++) {
			if(checkOne(arr, i, point, true) && checkOne(arr, i, point, false) && checkTwo(arr, i, point)) {
				//가로, 세로, 정사각형 조건을 모두 만족한다면?
				goGame(arr, points, cnt + 1);
				arr[point.getY()][point.getX()] = 0;
			}
		}
		
		
	}
	private static boolean checkTwo(int[][] arr, int idx, Point_2580 point) {		
		Point_2580[] squreCenter = {
				new Point_2580(1, 1), new Point_2580(1, 4), new Point_2580(1, 7),
				new Point_2580(4, 1), new Point_2580(4, 4), new Point_2580(4, 7),
				new Point_2580(7, 1), new Point_2580(7, 4), new Point_2580(7, 7)	
		};
		
		int x = point.getX();
		int y = point.getY();
		arr[y][x] = idx;
		for (int i = 0; i < squreCenter.length; i++) {
			Point_2580 center = squreCenter[i];
			int centerX = center.getX();
			int centerY = center.getY();
			
			if(Math.abs(x - centerX) <= 1 && Math.abs(y - centerY) <= 1) {
				boolean[] visited = new boolean[SDQ_Size + 1];
				int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
				int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
				for (int j = 0; j < 9; j++) {
					int nextX = centerX + dx[j];
					int nextY = centerY + dy[j];
					int val = arr[nextY][nextX];
					if(val == 0)
						continue;
					if(visited[val] != true) {
						visited[val] = true;
					}
					else if(visited[val] == true) {
						arr[y][x] = 0;
						return false;
					}
				}
				break;
			}
		}
		return true;
	}
	private static boolean checkOne(int[][] arr, int i, Point_2580 point, boolean type) {
		arr[point.getY()][point.getX()] = i;
		boolean[] visited = new boolean[SDQ_Size + 1];
		visited[i] = true;
		
		for (int idx = 0; idx < SDQ_Size; idx++) {
			if(type) {
				int val = arr[point.getY()][idx];
				if(idx == point.getX() || val == 0)
					continue;
				if (visited[val] != true)
					visited[val] = true;
				else {
					arr[point.getY()][point.getX()] = 0;
					return false;
				}
			}
			else{
				int val = arr[idx][point.getX()];
				if(idx == point.getY() || val == 0)
					continue;
				if (visited[val] != true)
					visited[val] = true;
				else {
					arr[point.getY()][point.getX()] = 0;
					return false;
				}
			}
		}
		return true;
	}
	
}

class Point_2580 {
	int y;
	int x;
	
	public Point_2580(int y, int x) {
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
