package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//초기 상태에서 줄기세포는 비활성 상태
//생명력 수치 X -> X시간동안 비 활성 그 후 활성
//활성 후 X시간이 지남 죽음 -> 죽은상태여도 그리드 크기는 차지
//1시간동안 상 하 좌 우 번식
//번식하는 방향에 이미 세포가 있으면 추가적으로 번식X
public class Solution_5653 {
	final static int MAX_SIZE = 400;
	static int n;
	static int m;
	static int k;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Solution_5653 solution = new Solution_5653();
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			n = Integer.parseInt(info[0]); //세로크기
			m = Integer.parseInt(info[1]); //가로크기
			k = Integer.parseInt(info[2]); //시간
			PriorityQueue<Cell_5653> cells = new PriorityQueue<Cell_5653>();
			
			int[][] arr = new int[MAX_SIZE][MAX_SIZE];
			for (int i = 0; i < n; i++) {
				String[] arrInfo = br.readLine().split(" ");
				for (int j = 0; j < m; j++) {
					int val = Integer.parseInt(arrInfo[j]);
					arr[i + MAX_SIZE/2][j + MAX_SIZE/2] = val;
					if (val != 0) {
						cells.add(new Cell_5653(i + MAX_SIZE/2, j+ MAX_SIZE/2, 0, 1, val));
					}
				}
			}
			solution.goCell(arr, cells);
			int res = solution.sumCell(cells);
			sb.append("#"+tc+" "+res+'\n');
		}
		System.out.println(sb);
	}

	private int sumCell(Queue<Cell_5653> cells) {
		int sum = 0;
		while (!cells.isEmpty()) {
			if(cells.poll().getState() != 3)
				sum += 1;
		}
		return sum;
	}

	private void goCell(int[][] arr, Queue<Cell_5653> cells) {
		Queue<Cell_5653> temps = new LinkedList<Cell_5653>();
		for (int i = 0; i < k; i++) {
			while (!cells.isEmpty()) {
				Cell_5653 cell = cells.poll();
				int y = cell.getY();
				int x = cell.getX();
				int num = cell.getNum() + 1;
				int state = cell.getState();
				if(state != 3) {
					if(num == 1 && state == 2) {
						spreadCell(arr, y, x, temps);	
					}
					if(num == arr[y][x]) {
						if(state == 1) {
							state = 2;
							num = 0;
							cell.setState(state);
							cell.setNum(num);
							temps.add(cell);					
							continue;
						}
						if(state == 2) {
							cell.setState(3);
						}
					} 
					cell.setNum(num);
					temps.add(cell);					
				}
			}
			
			while (!temps.isEmpty()) {
				Cell_5653 tempCells = temps.poll();
				cells.add(tempCells);
			}
/*			System.out.println("===================");
			print(arr);*/
		}
	}

	private void print(int[][] arr) {
		for (int i = 350/2 - 10; i < 350/2 + 10; i++) {
			for (int j = 350/2 - 10; j < 350/2 + 10; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void spreadCell(int[][] arr, int y, int x, Queue<Cell_5653> temps) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(arr[nextY][nextX] == 0) {
				/*
				 * 1 2
				 * 1 2
				 */
				temps.add(new Cell_5653(nextY, nextX, 0, 1, arr[y][x]));
				arr[nextY][nextX] = arr[y][x];
			}
		}
	}
}

//비활성상태 -> 1시간()
class Cell_5653 implements Comparable<Cell_5653>{
	int y;
	int x;
	int num; //몇 시간
	int state; //1 비활성 2활성 3죽음
	int life;
	
	public Cell_5653(int y, int x, int num, int state, int life) {
		super();
		this.y = y;
		this.x = x;
		this.num = num;
		this.state = state;
		this.life = life;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public int compareTo(Cell_5653 cell) {
		return cell.getLife() - this.getLife();
	}
	
}
