package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JEditorPane;

public class Main_3190 {
	static int n;
	static int k;
	static int l;
	static final int APPLE_CODE = 1;
	static final int SNAKE_CODE = 2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		//Apple_3190[] apples = new Apple_3190[2];
		
		for (int i = 0; i < k; i++) {
			String[] str = br.readLine().split(" "); //0이 행 1 이열
			arr[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1]) - 1] = APPLE_CODE; //1이면 사과 , 2면 뱀
		}
		l = Integer.parseInt(br.readLine());
		Queue<Order_3190> orders = new LinkedList<Order_3190>();
		for (int i = 0; i < l; i++) {
			String[] str = br.readLine().split(" "); //0이 행 1 이열
			Order_3190 order_3190 = new Order_3190(Integer.parseInt(str[0]), str[1].charAt(0));
			orders.add(order_3190);
		}		
		System.out.println(goSnake(arr , orders));
	}
	/*
	 * 0 0 0 0 0 0
	 * 0 0 0 0 0 0
	 * 0 0 0 0 0 0
	 * 0 0 0 0 0 0
	 * 0 0 0 0 0 0
	 * 0 0 0 0 0 0
	 */
	private static int goSnake(int[][] arr, Queue<Order_3190> orders) {
		//D는 오른쪽으로 90도 회전, L은 왼쪽으로 회전
		int time = 0;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		//북, 동, 남, 서
		//1, 2, 3, 4
		//오른쪽 : 북 - 동 - 남 - 서
		//왼쪽: 북 - 서 - 남- 동 (1, 4, 3, 2)
		int tailX = 0;
		int tailY = 0;
		
		int size = 1;
		int orderTime = 0;
		int dirNum = 2;
		char dir;
		
		Order_3190 order = orders.poll();
		if(order != null) {
			orderTime = order.getStep();
			dir = order.getDir();
		} else
			return n;
		
		arr[tailY][tailX] = SNAKE_CODE;
		loop:
		while (tailX >= 0 && tailX < n && tailY >= 0 && tailY < n) {
			/*
			 * 좌표 이동 코드
			 */			
			System.out.println("시간: "+time+" 다음명령어: "+orderTime);
			if(time == orderTime && !orders.isEmpty()) {
				//방향 전환
				System.out.print(dirNum+"에서");
				if(dir == 'D') {
					//오른쪽
					dirNum = (dirNum + 1) % 4;
					if(dirNum == 0)
						dirNum = 4;
				} else {
					//왼쪽
					dirNum = (dirNum - 1) % 4;
					if(dirNum == 0)
						dirNum = 4;
				}
				order = orders.poll();
				orderTime = order.getStep();
				dir = order.getDir();
				System.out.println("방향전환:"+dir+" dirNum: "+dirNum);
			}
						
			if(arr[tailY][tailX] == 1) {
				size++;
				tailY -= dy[dirNum - 1];
				tailX -= dx[dirNum - 1];
				System.out.println("size: "+size);
			}			
			for (int j = 1; j <= size; j++) {
				if(tailX >= 0 && tailX < n && tailY >= 0 && tailY < n) {
					arr[tailY][tailX] = SNAKE_CODE;
					tailY += dy[dirNum - 1];
					tailX += dx[dirNum - 1];	
				} else {
					time += 1;
					break loop;
				}
				
			}

			for (int k = 0; k < n; k++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[k][j]+" ");
				}
				System.out.println();
			}
			System.out.println("=======================");
			time++;

			int x2 = tailX - dx[dirNum - 1];
			int y2 = tailY - dy[dirNum - 1];
			for (int j = 1; j <= size; j++) {
				arr[y2][x2] = 0;
				y2 -= dy[dirNum - 1];
				x2 -= dx[dirNum - 1];
			}
		}
		return time;
	}
}

class Snake_3190 {
	int x;
	int y;
	
	public Snake_3190(int x, int y) {
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

class Order_3190 {
	int step;
	char dir;
	
	public Order_3190(int step, char dir) {
		super();
		this.step = step;
		this.dir = dir;
	}
	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public char getDir() {
		return dir;
	}
	public void setDir(char dir) {
		this.dir = dir;
	}
}
