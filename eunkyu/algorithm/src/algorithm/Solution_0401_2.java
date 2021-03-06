package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_0401_2 {
	
	static int n; //세로크기 
	static int m; //가로크기
	static int k; //배양시간
	static int[][] arr;
	static PriorityQueue<Point_0401_2> pointq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			n = Integer.parseInt(info[0]);
			m = Integer.parseInt(info[1]);
			k = Integer.parseInt(info[2]);
			arr = new int[650][650];
			pointq = new PriorityQueue<Point_0401_2>();
			
			for (int i = 300; i < n + 300; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 300; j < m + 300; j++) {
					int val = Integer.parseInt(str[j - 300]);
					arr[i][j] = val;
					if(val != 0) {
						pointq.add(new Point_0401_2(i, j, 0, val));
					}
				}
			}
/*			System.out.println(pointq.size());
*/			for (int i = 0; i <= k; i++) {
				Queue<Point_0401_2> q = new LinkedList<Point_0401_2>();
				
				while(!pointq.isEmpty()) {
					Point_0401_2 point = pointq.poll();
					if(point.getState() == 1 && 
							(point.getTime() == 1)) {
						go(point);
					}
					
					if(point.getState() == 0 && 
							(point.getTime() == point.getVal())) {
						point.setState(1);
						point.setTime(0);
					} else if(point.getState() == 1 && 
							(point.getTime() == point.getVal())) {
						point.setState(2);
					} else if(point.getState() == 2){
						continue;
					}
					
					
					point.setTime(point.getTime() + 1);
					q.add(point);	
				}
				
				while(!q.isEmpty()) {
					pointq.add(q.poll());
				}
				
			}
/*			for (int i = 280; i < 320; i++) {
				for (int j = 280; j < 320; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}*/
			int a = 0;
			int b = 0;
			int c = 0;
			while (!pointq.isEmpty()) {
				Point_0401_2 point = pointq.poll();
				if(point.getState() == 0)
					++a;
				if(point.getState() == 1)
					++b;
				if(point.getState() == 2)
					++c;
			}
			sb.append("#"+tc+" "+(a+b)+"\n");
		}
		System.out.println(sb);
	
	
	}

	private static void go(Point_0401_2 point) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int y = point.getY();
		int x = point.getX();
		for (int dir = 0; dir < 4; dir++) {
			int nextX = x + dx[dir];
			int nextY = y + dy[dir];
			if(arr[nextY][nextX] == 0) {
				arr[nextY][nextX] = arr[y][x];
				pointq.add(new Point_0401_2(nextY, nextX, 0, arr[y][x]));
			}else {
				//System.out.println(arr[nextY][nextX]+"??");
			}
		}
	}
}

class Point_0401_2 implements Comparable<Point_0401_2>{
	int y;
	int x;
	int state; //0 비활성 1 활성 2죽음
	int time;
	int val;
	
	public Point_0401_2(int y, int x, int state, int val) {
		super();
		this.y = y;
		this.x = x;
		this.state = state;
		this.val = val;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	@Override
	public int compareTo(Point_0401_2 o) {
		return o.getVal() - this.getVal();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point_0401_2 [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", state=");
		builder.append(state);
		builder.append(", time=");
		builder.append(time);
		builder.append(", val=");
		builder.append(val);
		builder.append("]");
		return builder.toString();
	}
	
}
