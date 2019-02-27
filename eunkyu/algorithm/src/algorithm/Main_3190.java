package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

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
			String[] str = br.readLine().split(" "); //0�� �� 1 �̿�
			arr[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = APPLE_CODE; //1�̸� ��� , 2�� ��
		}
		l = Integer.parseInt(br.readLine());
		Queue<Order_3190> orders = new LinkedList<Order_3190>();
		for (int i = 0; i < l; i++) {
			System.out.println("?");
			String[] str = br.readLine().split(" "); //0�� �� 1 �̿�
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
		//D�� ���������� 90�� ȸ��, L�� �������� ȸ��
		int time = 1;
		//��, ��, ��, ��
		//1, 2, 3, 4
		//������ : �� - �� - �� - ��
		//����: �� - �� - ��- �� (1, 4, 3, 2)
		int x = 0;
		int y = 0;
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
		
		arr[y][x] = SNAKE_CODE;
		
		loop:
		while (x >= 0 && x < n && y >= 0 && y < n) {
			/*
			 * ��ǥ �̵� �ڵ�
			 */			
			if(time == orderTime && !orders.isEmpty()) {
				//���� ��ȯ
				
				if(dir == 'D') {
					//������
					dirNum = (dirNum + 1) % 4;
					if(dirNum == 0)
						dirNum = 4;
				} else {
					//����
					dirNum = (dirNum - 1) % 4;
					if(dirNum == 0)
						dirNum = 4;
				}
				order = orders.poll();
				orderTime = order.getStep();
				dir = order.getDir();
			}
			//idx + 1
			int[] dx = {0, 1, 0, -1};
			int[] dy = {-1, 0, 1, 0};
			for (int i = 0; i < size; i++) {
				System.out.println("size: "+size);
				//ũ�⸸ŭ
				if(!(x >= 0 && x < n && y >= 0 && y < n)) {
					break;
				}
				switch (dirNum) {
				case 1:
					arr[y][x] = 0;
					x = x + dx[dirNum - 1];
					y = y + dy[dirNum - 1];
					if(!(x >= 0 && x < n && y >= 0 && y < n)
							|| arr[y][x] == SNAKE_CODE)
						break loop;
					if(arr[y][x] == 1)
						size++;
					arr[y][x] = SNAKE_CODE;	
					break;
				case 2:
					arr[y][x] = 0;
					x = x + dx[dirNum - 1];
					y = y + dy[dirNum - 1];
					if(!(x >= 0 && x < n && y >= 0 && y < n)
							|| arr[y][x] == SNAKE_CODE)
						break loop;
					if(arr[y][x] == 1)
						size++;
					arr[y][x] = SNAKE_CODE;	
					break;
				case 3:
					x = x + dx[dirNum - 1];
					y = y + dy[dirNum - 1];
					if(!(x >= 0 && x < n && y >= 0 && y < n)
							|| arr[y][x] == SNAKE_CODE)
						break loop;
					if(arr[y][x] == 1) {
						arr[y][x] = SNAKE_CODE;							
						size++;
					} else {
						arr[y][x] = SNAKE_CODE;							
					}
					break;
				case 4:
					arr[y][x] = 0;
					x = x + dx[dirNum - 1];
					y = y + dy[dirNum - 1];
					if(!(x >= 0 && x < n && y >= 0 && y < n)
							|| arr[y][x] == SNAKE_CODE)
						break loop;
					if(arr[y][x] == 1)
						size++;
					arr[y][x] = SNAKE_CODE;	
					break;
				}
				System.out.println("================================");
				for (int k = 0; k < n; k++) {
					for (int j = 0; j < n; j++) {
						System.out.print(arr[k][j]+" ");
					}
					System.out.println();
				}
			}
			time++;
			
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