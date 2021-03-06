package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//12:40 ~ 13:55
public class Main_12100 {
	
	static int n;
	static int[][] arr;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		res = 0;
		goGame(0);
		System.out.println(res);
	
	}

	private static void goGame(int cnt) {
		if(cnt == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(res < arr[i][j]) {
						res = arr[i][j];
					}
				}
			}
			return;
			
		}
		for (int i = 0; i < 4; i++) {
			int[][] copy = new int[n][n];
			copy(copy, true);
			move(i);
			goGame(cnt + 1);
			copy(copy, false);
		}
	}
	
	private static void copy(int[][] copy, boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(type)
					copy[i][j] = arr[i][j];
				else
					arr[i][j] = copy[i][j];
			}
		}
	}

	private static void move(int dir) {
		Queue<Integer> q = new LinkedList<Integer>();
		switch (dir) {
		case 0:
			//위
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if(arr[y][x] == 0)
						continue;
					q.add(arr[y][x]);
				}
				for (int y = 0; y < n; y++) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
					else
						arr[y][x] = 0;
				}
				
				for (int y = 0; y < n; y++) {
					if(y + 1 < n && arr[y][x] == arr[y + 1][x]) {
						arr[y][x] += arr[y + 1][x];
						arr[y + 1][x] = 0;
					}
					if(arr[y][x] != 0) {
						q.add(arr[y][x]);
						arr[y][x] = 0;
					}
				}

				for (int y = 0; y < n; y++) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
				}
			}
			break;
		case 1:
			//아래
			for (int x = 0; x < n; x++) {
				
				for (int y = n - 1; y >= 0; y--) {
					if(arr[y][x] == 0)
						continue;
					q.add(arr[y][x]);
				}
				for (int y = n - 1; y >= 0; y--) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
					else
						arr[y][x] = 0;
				}	
				
				for (int y = n - 1; y >= 0; y--) {
					if(y - 1 >= 0 && arr[y][x] == arr[y - 1][x]) {
						arr[y][x] += arr[y - 1][x];
						arr[y - 1][x] = 0;
					}
					if(arr[y][x] != 0) {
						q.add(arr[y][x]);
						arr[y][x] = 0;
					}
				}
				
				for (int y = n - 1; y >= 0; y--) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
				}	
			}
			break;
		case 2:
			//오른쪽
			for (int y = 0; y < n; y++) {
				
				for (int x = n - 1; x >= 0; x--) {
					if(arr[y][x] == 0)
						continue;
					q.add(arr[y][x]);
				}
				for (int x = n - 1; x >= 0; x--) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
					else
						arr[y][x] = 0;
				}
				for (int x = n - 1; x >= 0; x--) {
					if(x - 1 >= 0 && arr[y][x] == arr[y][x - 1]) {
						arr[y][x] += arr[y][x - 1];
						arr[y][x - 1] = 0;
					}
					if(arr[y][x] != 0) {
						q.add(arr[y][x]);
						arr[y][x] = 0;
					}
				}

				for (int x = n - 1; x >= 0; x--) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
				}

			}
			break;
		case 3:
			for (int y = 0; y < n; y++) {
				
				for (int x = 0; x < n; x++) {
					if(arr[y][x] == 0)
						continue;
					q.add(arr[y][x]);
				}
				for (int x = 0; x < n; x++) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
					else
						arr[y][x] = 0;
				}

				for (int x = 0; x < n; x++) {
					if(x + 1 < n && arr[y][x] == arr[y][x + 1]) {
						arr[y][x] += arr[y][x + 1];
						arr[y][x + 1] = 0;
					}
					if(arr[y][x] != 0) {
						q.add(arr[y][x]);
						arr[y][x] = 0;
					}
				}

				for (int x = 0; x < n; x++) {
					if(!q.isEmpty())
						arr[y][x] = q.poll();
					else
						arr[y][x] = 0;
				}

			}
			break;
		}
	}
	
	private static void print(int cnt, int dir) {
		System.out.println("========cnt: "+cnt+"   dir: "+dir+"==================");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}