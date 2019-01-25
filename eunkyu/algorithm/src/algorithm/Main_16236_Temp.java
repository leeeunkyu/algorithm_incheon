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
	static int n;	//n크기 공간에
	static int m;	//m마리 물고기 1마리 아기상어
	static int arr[][];
	static ArrayList<Fish> fish;
	static boolean visited[][];
	static int printCnt = 0;
	/*
	 * 아기 상어 크기는 2
	 * 1초에 상하좌우로 한칸씩 이동가능
	 * 자기보다 크기가 작은 물고기만 먹을 수 있고 같으면 이동은 가능
	 * 
	 * 이동방식
	 * 1. 먹을 물고기가 없으면 엄마상어에 도움 요청
	 * 2. 1마리면 그 물고기를 먹으러 출발
	 * 3. 1마리보다 많으면 가장 가까운 물고기를 먹으러 감
	 * 4. 크기 수 만큼 물고기를 먹으면 크기 1 증가
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
			//우선순위가 가장 큰 애를 뽑는다
			Shark tempShark = q.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = tempShark.getX() + dx[i];
				int nextY = tempShark.getY() + dy[i];				
				
				if(fish.isEmpty() ||fish.get(0).getSize() >= tempShark.getSize()) {
					//더이상 잡을 물고기가 없거나 남은 물고기 사이즈가 지금 내 사이즈보다 크가나 같을때 종료
					break loop;
				}
				
				int dist = Math.abs(nextX - preX) + Math.abs(nextY - preY);
				//상어가 이동한 거리를 계산
							
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
					//기준에 맞는 가장 가까운 물고기 인덱스를 찾는다.
					int minIndex = checkPrior(nextX, nextY, tempShark.getSize());
					
					if(nextX == fish.get(minIndex).getX()  && nextY == fish.get(minIndex).getY() 
							&& arr[nextY][nextX] < tempShark.getSize()) {
						System.out.println("nextX: "+nextX+" nextY: "+nextY+" VAL:"+arr[nextY][nextX]+ " minIndex: "+minIndex);
						printCnt++;
						cnt++;
						path++;
						arr[nextY][nextX] = 0;
						fish.remove(minIndex);	//잡아먹은 물고리를 물고기리스트에서 삭제
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
	
	//위쪽먼저 그다음 왼쪽
	private static int checkPrior(int preX, int preY, int size) {
		int min = 9999999;
		int minIndex = 0;
		for (int i = 0; i < fish.size(); i++) {
			if(fish.get(i).getSize() < size && arr[preX][preY] != 0) {
				int dist = Math.abs((fish.get(i).getX() - preX) + Math.abs(fish.get(i).getY() - preY));
				if(min >= dist) {
					//위쪽, 왼쪽
					if(min == dist) {
						//작은게 더 위쪽
						if(fish.get(i).getY() < fish.get(minIndex).getY()) {
							minIndex = i;		
						//높이가 똑같다면? 왼쪽꺼
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
