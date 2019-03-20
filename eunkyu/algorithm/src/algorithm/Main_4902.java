package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//10:11
public class Main_4902 {
	
	static int n;
	static int[][] arr;
	static int size;
	static boolean[][] type;
	static int width;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		while(Integer.parseInt(str[0]) != 0) {
			n = Integer.parseInt(str[0]);
			width = (2 * n) - 1;
			arr = new int[n][width];
			type = new boolean[n][width];
		
			init();
			
			int temp = 1;
			for (int i = 0; i < n; i++) {
				int width = i * 2 + 1;
				int k = 0;
				for (int j = n - (1 + i); k < width; j++) {
					arr[i][j] = Integer.parseInt(str[temp]);
					if((i + j) % 2 == 0)
						type[i][j] = true;
					++temp;
					++k;
				}
			}			
			sb.append(dfs(0)+"\n");
			
			str = br.readLine().split(" ");
		}
		System.out.println(sb);
	
	
	}

	private static void init() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < width; j++) {
				arr[i][j] = 1001;
			}
		}
		
	}

	private static int dfs(int cnt) {
		int res = 0;
		int max = Integer.MIN_VALUE;
		for (; cnt < n; cnt++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < width; j++) {
					if(type[i][j]) { 
						res = findVal(i, j, cnt + 1);
						if(res > max) {
							max = res;
						}
					}
				}
			}	
		}
		
		return max;
	}

	private static int findVal(int y, int x, int cnt) {
		int sum = 0;
/*		int sum = arr[y][x];
*/		Map<Integer, Integer> resMap = new HashMap<Integer, Integer>();
		resMap.put(0, 1);	//1은 잘 돌아갔다는 의미
		
		Queue<Point_4902> points = new LinkedList<Point_4902>();
		points.add(new Point_4902(y, x, 1));
		while(!points.isEmpty()) {
			Point_4902 point = points.poll();
			int px = point.getX();
			int py = point.getY();
			int size = point.getSize();
			if(size >= cnt) {
				return sum;
			}
			int[] dx = setDx(cnt);
			int[] dy = setDy(cnt);
			System.out.println(cnt);
			for (int i = 0; i < cnt * cnt; i++) {
				int nextX = px + dx[i];
				int nextY = py + dy[i];
				
				if(nextX >= 0 && nextX < width && nextY >= 0 && nextY < n
						&& arr[nextY][nextX] < 1000) {
					sum += arr[nextY][nextX];
					points.add(new Point_4902(nextY, nextX, size + 1));
				} else {
					return Integer.MIN_VALUE;
				}
			}	
		}
		return sum;
	}

	private static int[] setDy(int size) {
		int[] dy = new int[size * size];
		int temp = 0;
		int split = 1;
		int cnt = 0;
		for (int i = 0; i < size * size; i++) {
			if(split == cnt) {
				split += 2;
				cnt = 0;
				temp += 1;
			}
			dy[i] = temp;
			cnt += 1;
			
		}
		return dy;
	}

	private static int[] setDx(int size) {
		int dx[] = new int[size * size];
		dx[0] = 0;
		int temp = 0;
		int split = 1;
		int cnt = 1;
		for (int i = 0; i < size * size; i++) {
			if(split <= cnt) {
				temp = split * -1;
				cnt = 0;
				split += 1;
			}
			dx[i] = temp;
			temp ++;
			cnt++;
		}
		return dx;
	}
}

class Point_4902 {
	int y;
	int x;
	int size;
	
	public Point_4902(int y, int x, int size) {
		super();
		this.y = y;
		this.x = x;
		this.size = size;
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
}

