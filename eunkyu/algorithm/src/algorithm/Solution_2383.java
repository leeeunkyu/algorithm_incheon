package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_2383 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int arr [][] = new int [n][n];
		PriorityQueue<Stair> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			String str[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(val >= 2 && val <= 10) {
					pq.add(new Stair(j, i, val, 0));
				}
			}
		}
		
		bfs(arr, pq, n);
		
		System.out.println(pq);
	}

	private static void bfs(int[][] arr, PriorityQueue<Stair> pq, int n) {
		boolean visited [][] = new boolean [n][n];
		Queue<Stair> qList []= new Queue [pq.size()];
		
		//Stair stair = pq.poll();
		/*
		q.add(stair);*/
		int cnt = 0;

		while (!pq.isEmpty()) {
			Stair stair = pq.poll();
			int x = stair.getX();
			int y = stair.getY();
			int num = stair.getNum();
			int peopleCnt = stair.getPeopleCnt();
			
			int sub = pq.peek().getNum() - num; //다음 길이 크기 계단과의 차
			visited[y][x] = true;
			
			int dx[] = {0, 0, 1, -1};
			int dy[] = {1, -1, 0, 0};
			while(true) {
				Queue<Stair> q = new LinkedList<Stair>();
				q.add(stair);
				
				while(!q.isEmpty()) {
					for (int i = 0; i < 4; i++) {
						int nextX = x + dx[i];
						int nextY = y + dy[i];
						if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX]) {
							visited[nextY][nextX] = true;
							if(arr[nextY][nextX] == 1) {
								peopleCnt = stair.getPeopleCnt() + 1;
								stair.setPeopleCnt(peopleCnt);
							}
							q.add(new Stair(nextX, nextY, num, peopleCnt));
						}
					}
					cnt++;
				}
				if (cnt == sub) {
					break;
				}
			}
			cnt = 0;
		}
		
	}
}

class People {
	private int x;
	private int y;
	
	public People(int x, int y) {
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

class Stair implements Comparable<Stair>{
	private int x;
	private int y;
	private int num;
	private int peopleCnt;
	
	public Stair(int x, int y, int num, int peopleCnt) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
		this.peopleCnt = peopleCnt;
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
	public int getPeopleCnt() {
		return peopleCnt;
	}

	public void setPeopleCnt(int peopleCnt) {
		this.peopleCnt = peopleCnt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stair [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", num=");
		builder.append(num);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Stair stair) {
		return this.num - stair.getNum();
	}
	
	
}
