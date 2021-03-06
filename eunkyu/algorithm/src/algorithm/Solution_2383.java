package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_2383 {
	static int n;
	static int[][] arr;
	static int minTime;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			Queue<Stair_2383> stairs = new LinkedList<Stair_2383>();
			Queue<People_2383> peoplesq = new LinkedList<People_2383>();
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int val = Integer.parseInt(info[j]);
					arr[i][j] = val;
					if(val >= 2) {
						stairs.add(new Stair_2383(i, j, val));
					}
					if(val == 1) {
						peoplesq.add(new People_2383(i, j));
					}
				}
			}
			
			int size = peoplesq.size();
			People_2383[] peoples = new People_2383[size];
			for (int i = 0; i < size; i++) {
				peoples[i] = peoplesq.poll();
			}
			minTime = Integer.MAX_VALUE;
			for (int c = 1; c < size; c++) {
				for (int i = 0; i < (1 << size); i++) {
					Queue<People_2383> cPeoples = new LinkedList<People_2383>();
					Queue<People_2383> ncPeoples = new LinkedList<People_2383>();
					
					boolean[] visited = new boolean[size];
					
					if(Integer.bitCount(i) == c) {
						//c명을 선택하고
						for (int j = 0; j < size; j++) {
							if(((1 << j) & i) > 0) {
								cPeoples.add(peoples[j]);
								visited[j] = true;
							}
						}
						//선택받지 못한 사람들
						for (int j = 0; j < size; j++) {
							if(!visited[j])
								ncPeoples.add(peoples[j]);
						}
						
						Stair_2383 stair = stairs.poll();
						int resA = goWalk(stair, cPeoples);
						Stair_2383 stair2 = stairs.poll();
						int resB = goWalk(stair2, ncPeoples);
						
						stairs.add(stair);
						stairs.add(stair2);
						//선택한 사람들이 계단 1을 가고 선택받지 못한사람이 계단 2를 갈경우
						if(minTime > resA + resB) {
							minTime = resA + resB;
						}
					}
					
				}	
			}
			
			
			sb.append("#"+tc+" "+minTime+"\n");
		
		}
		System.out.println(sb);
	}

	private static int goWalk(Stair_2383 stair, Queue<People_2383> peoples) {
		int sx = stair.getX();
		int sy = stair.getY();
		int snum = stair.getNum();
		int dist = 0;
		while(!peoples.isEmpty()) {
			People_2383 people = peoples.poll();
			int x = people.getX();
			int y = people.getY();
			
			dist = Math.abs(x - sx) + Math.abs(y - sy);			
		}
		return 2 + dist + snum;
	}
}

class Stair_2383 {
	int y;
	int x;
	int num;
	
	public Stair_2383(int y, int x, int num) {
		super();
		this.y = y;
		this.x = x;
		this.num = num;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}

class People_2383 {
	int y;
	int x;
	
	public People_2383(int y, int x) {
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