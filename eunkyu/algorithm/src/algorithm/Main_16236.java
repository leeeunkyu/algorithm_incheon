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
	static int n;	//nũ�� ������
	static int m;	//m���� ������ 1���� �Ʊ���
	static int arr[][];
	static ArrayList<Fish> fishSort;
	static boolean visited[][];
	static int path = 0;
	/*
	 * �Ʊ� ��� ũ��� 2
	 * 1�ʿ� �����¿�� ��ĭ�� �̵�����
	 * �ڱ⺸�� ũ�Ⱑ ���� �����⸸ ���� �� �ְ� ������ �̵��� ����
	 * 
	 * �̵����
	 * 1. ���� �����Ⱑ ������ ������ ���� ��û
	 * 2. 1������ �� �����⸦ ������ ���
	 * 3. 1�������� ������ ���� ����� �����⸦ ������ ��
	 * 4. ũ�� �� ��ŭ �����⸦ ������ ũ�� 1 ����
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		fishSort = new ArrayList<Fish>();
		visited = new boolean[n][n];
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < n; i++) {
			String str[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if(arr[i][j] == 9) {
					x = j;
					y = i;
				}else if(arr[i][j] != 0 ) {
					fishSort.add(new Fish(j, i, arr[i][j]));
				}
					
			}
		}
		
		fishSort.sort(new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				return o1.getSize() - o2.getSize();
			}		
		});
		/*for (int i = 0; i < fish.size(); i++) {
			System.out.println("x: "+fish.get(i).getX() + " y: "+ fish.get(i).getY() + " size: "+ fish.get(i).getSize());
		}*/
		System.out.println();
		System.out.println();
		
		bfs(x, y);
		
		System.out.println();
		
	}
	private static void bfs(int x, int y) {
		int dx[] = {0, -1, 0, 1};
		int dy[] = {-1, 0, 1, 0};
		
		Shark s = new Shark(x, y, 2, 0);
		Queue<Shark> q = new LinkedList<>();
		ArrayList<Fish> fishList = new ArrayList<>();
		boolean isFish = false;
		int sizeCnt = 0;
		int pointX = x;
		int pointY = y;
		
		q.add(s);
		
		while(!q.isEmpty()) {
			
			Shark tempShark = q.poll();
			int sharkX = tempShark.getX();
			int sharkY = tempShark.getY();
			int sharkSize = tempShark.getSize();
			int sharkStep = tempShark.getStep();
			
			if(fishSort.size() <= 0 || sharkSize <= fishSort.get(0).getSize()) {
				break;
			}
			
			fishList.clear();
			
			for (int i = 0; i < 4; i++) {			
				int nextX = sharkX + dx[i];
				int nextY = sharkY + dy[i];
				int nextStep = sharkStep + 1;
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n 
						&& arr[nextY][nextX] <= sharkSize) {
					//�̵� �� �� �ִ� ���� ����
					if(arr[nextY][nextX] > 0 && arr[nextY][nextX] < sharkSize) {
						//���� �� �ִ� ������� �Ҹ�
						fishList.add(new Fish(nextX, nextY, arr[nextY][nextX]));
					}
					q.add(new Shark(nextX, nextY, sharkSize, nextStep));
				}
			}
			path++;
			
			if (fishList.size() > 0) {
				int minDistIdx = dist(sharkX, sharkY, fishList);
				Fish fish = fishList.get(minDistIdx);
				++sizeCnt;
				if(sharkSize == sizeCnt) {
					++sharkSize;
					sizeCnt = 0;
				}
				q.clear();
				q.add(new Shark(fish.getX(), fish.getY(), sharkSize, sharkStep + 1));
				fishSort.remove(0);
				pointX = fish.getX();
				pointY = fish.getY();
				arr[pointY][pointX] = -1;
				System.out.println();
				System.out.println("====================================");
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr.length; j++) {
						if(arr[i][j] == -1)
							System.out.print(9+" ");
						else
							System.out.print(arr[i][j]+" ");
					}
					System.out.println();
				}
				System.out.println("��� size: "+sharkSize);
				System.out.println("�̵��� step: "+ (sharkStep + 1));
			}
		}
	
	}
	private static int dist(int sharkX, int sharkY, ArrayList<Fish> fishList) {		
		int minDistIdx = 0;
		
		for (int i = 0; i < fishList.size(); i++) {
			if(fishList.get(minDistIdx).getY() > fishList.get(i).getY()) {
				//���ݲ� y���� �� ����?
				minDistIdx = i;
			}else if(fishList.get(i).getY() == fishList.get(minDistIdx).getY() && fishList.get(minDistIdx).getX() > fishList.get(i).getX()) {
				//y�� �Ÿ��� ������ x���� �� ���ʿ� ������?
				minDistIdx = i;
			}
		}
		return minDistIdx;
	}
		
}

class Shark {
	
	int x;
	int y;
	static int size;
	int step;
	
	public Shark(int x, int y, int size, int step) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.step = step;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
}

class Fish {
	
	int x;
	int y;
	int size;
	
	public Fish(int x, int y, int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}