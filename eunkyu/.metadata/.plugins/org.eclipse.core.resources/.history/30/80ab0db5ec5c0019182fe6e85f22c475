package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_16236 {
	static int n;	//n크기 공간에
	static int m;	//m마리 물고기 1마리 아기상어
	static int arr[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		Shark_16236 shark = new Shark_16236(0, 0, 2, 0, 0);
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(str[j]);
				arr[i][j] = val;
				if(val == 9) {
					shark = new Shark_16236(i, j, 2, 0, 0);
				} else if(val != 0) {
					pq.add(val);
				}
			}
		}
		
		int size = 2;
		Shark_16236 tempShark = shark;	
		while(!pq.isEmpty()) {
			int remainSize = pq.poll();
			if(remainSize < size) {
				arr[tempShark.getY()][tempShark.getX()] = 0;
				tempShark = bfs(tempShark);
				size = tempShark.getSize();
			}
		}
				
		System.out.println(tempShark.getStep());
		
	}
	
	private static Shark_16236 bfs(Shark_16236 shark) {
		boolean[][] visited = new boolean[n][n];
		boolean isEat = false;
		Queue<Shark_16236> sharks = new LinkedList<Shark_16236>();
		sharks.add(shark);
		Queue<Shark_16236> isEatSharks = new LinkedList<Shark_16236>();
		int maxStep = Integer.MAX_VALUE;
		
		while(!sharks.isEmpty()) {
			shark = sharks.poll();
			
			int x = shark.getX();
			int y = shark.getY();
			int size = shark.getSize();
			int eat = shark.getEat();
			int step = shark.getStep();
			
			if(maxStep < step)
				break;
			
			visited[y][x] = true;
			
			int[] dx = {0, -1, 1, 0};
			int[] dy = {-1, 0, 0, 1};
			
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX >= 0 && nextX < n && nextY >=0 && nextY < n
						&& !visited[nextY][nextX] && arr[nextY][nextX] <= size) {
					visited[nextY][nextX] = true;

					if(arr[nextY][nextX] != 0 && arr[nextY][nextX] < size) {
						int tempEat = eat + 1;
						int tempSize = size;
						if(tempEat >= tempSize) {
							tempSize += 1;
							tempEat = 0;
						}
						maxStep = step + 1;
						isEatSharks.add(new Shark_16236(nextY, nextX, tempSize, tempEat, step + 1));
						isEat = true;
					} else {
						sharks.add(new Shark_16236(nextY, nextX, size, eat, step + 1));
					}
				}
			}
		}
		
		if(isEat) {
			Shark_16236 eatShark = isEatSharks.poll();
			int ey = eatShark.getY();
			int ex = eatShark.getX();
			int eStep = eatShark.getStep();
			int eSize = eatShark.getSize();
			
			while(!isEatSharks.isEmpty()) {
				Shark_16236 tes = isEatSharks.poll();
				int tey = tes.getY();
				int tex = tes.getX();
				int testep = tes.getStep();
				if(eStep == testep) {
					if(ey == tey) {
						if(ex > tex) {
							eatShark = tes;
							ey = eatShark.getY();
							ex = eatShark.getX();
							eStep = eatShark.getStep();
						} else {
							isEatSharks.poll();
						}
					} else if(ey > tey){
						eatShark = tes;
						ey = eatShark.getY();
						ex = eatShark.getX();
						eStep = eatShark.getStep();
					} else {
						isEatSharks.poll();
					}
				} else if(eStep > testep) { 
					eatShark = tes;
					ey = eatShark.getY();
					ex = eatShark.getX();
					eStep = eatShark.getStep();
				} else{
					isEatSharks.poll();
				}					
			}
			arr[ey][ex] = eSize;

			return eatShark;
		}
		return shark;
	}		
}

class Shark_16236 {
	
	int y;
	int x;
	int size;
	int eat;
	int step;
	
	public Shark_16236(int y, int x, int size, int eat, int step) {
		super();
		this.y = y;
		this.x = x;
		this.size = size;
		this.eat = eat;
		this.step = step;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public int getEat() {
		return eat;
	}

	public void setEat(int eat) {
		this.eat = eat;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shark_16236 [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", size=");
		builder.append(size);
		builder.append(", eat=");
		builder.append(eat);
		builder.append(", step=");
		builder.append(step);
		builder.append("]");
		return builder.toString();
	}
		
	
}