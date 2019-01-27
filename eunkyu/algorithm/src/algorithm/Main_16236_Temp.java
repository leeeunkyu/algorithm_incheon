package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_16236_Temp {
	static int n;	//nũ�� ������
	static int m;	//m���� ������ 1���� �Ʊ���
	static int arr[][];
	static ArrayList<Fish> fish;
	static boolean visited[][];
	static int printCnt = 0;
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
		fish = new ArrayList<Fish>();
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
					fish.add(new Fish(j, i, arr[i][j]));
				}
					
			}
		}
		
		fish.sort(new Comparator<Fish>() {
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
		System.out.println();
		
		/*for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}*/
	}
	private static void bfs(int x, int y) {
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		Shark s = new Shark(x, y, 2, 0);
		//Queue<Shark> q = new LinkedList<>();
		PriorityQueue<Shark> q = new PriorityQueue<Shark>();
		q.add(s);
		int cnt = 0;
		int path = 0;
		int preX = x;
		int preY = y;
		ArrayList<Integer> distArray = new ArrayList<Integer>();
		int res = 0;
		System.out.println("preX: "+preX+" preY: "+preY+" VAL:"+arr[preX][preY]);

		loop:
		while(!q.isEmpty()) {
			//�켱������ ���� ū �ָ� �̴´�
			Shark tempShark = q.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = tempShark.getX() + dx[i];
				int nextY = tempShark.getY() + dy[i];				
				
				if(fish.isEmpty() ||fish.get(0).getSize() >= tempShark.getSize()) {
					//���̻� ���� �����Ⱑ ���ų� ���� ������ ����� ���� �� ������� ũ���� ������ ����
					break loop;
				}
				
				int dist = Math.abs(nextX - preX) + Math.abs(nextY - preY);
				//�� �̵��� �Ÿ��� ���
							
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
					//���ؿ� �´� ���� ����� ������ �ε����� ã�´�.
					int minIndex = checkPrior(nextX, nextY, tempShark.getSize());
					
					if(nextX == fish.get(minIndex).getX()  && nextY == fish.get(minIndex).getY() 
							&& arr[nextY][nextX] < tempShark.getSize()) {
						System.out.println("nextX: "+nextX+" nextY: "+nextY+" VAL:"+arr[nextY][nextX]+ " minIndex: "+minIndex);
						printCnt++;
						cnt++;
						path++;
						arr[nextY][nextX] = 0;
						fish.remove(minIndex);	//��Ƹ��� �������� �����⸮��Ʈ���� ����
						preX = nextX;
						preY = nextY;
						distArray.add(dist);
						
						System.out.println("=======================================");
						for (int k = 0; k < arr.length; k++) {
							for (int j = 0; j < arr.length; j++) {
								System.out.print(arr[k][j]+" ");
							}
							System.out.println();
						}
						System.out.println("=======================================");

						
					}
					System.out.println(path);
					q.add(new Shark(nextX, nextY, tempShark.getSize(), path));
				}
				//System.out.println("cnt: "+cnt+" tempShark.getSize(): "+tempShark.getSize());
				if(cnt == tempShark.getSize()) {
					cnt = 0;
					tempShark.setSize(tempShark.getSize() + 1);
				}
					
			}


		}
		for (int i = 0; i < distArray.size(); i++) {
			System.out.print(distArray.get(i)+" ");
			res += distArray.get(i);
		}
		System.out.println();
		System.out.println(res);
	}
	
	//���ʸ��� �״��� ����
	private static int checkPrior(int preX, int preY, int size) {
		int min = 9999999;
		int minIndex = 0;
		for (int i = 0; i < fish.size(); i++) {
			if(fish.get(i).getSize() < size && arr[preX][preY] != 0) {
				int dist = Math.abs((fish.get(i).getX() - preX) + Math.abs(fish.get(i).getY() - preY));
				if(min >= dist) {
					//����, ����
					if(min == dist) {
						//������ �� ����
						if(fish.get(i).getY() < fish.get(minIndex).getY()) {
							minIndex = i;		
						//���̰� �Ȱ��ٸ�? ���ʲ�
						}else if(fish.get(i).getX() < fish.get(minIndex).getX()){
							minIndex = i;		
						}
						min = dist;
					}else {
						minIndex = i;
						min = dist;

					}
				}
				
			}
		}
		return minIndex;
	}
}