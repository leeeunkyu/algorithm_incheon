package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1941_2 {
	
	static int n = 5;
	static char[][] arr;
	static int size;
	static People_1941_2[] peopels;
	static 	int res = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = n * n;
		peopels = new People_1941_2[size];
		arr = new char[n][n];
		int temp = 0;
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				char val = str.charAt(j);
				arr[i][j] = val;
				peopels[temp] = new People_1941_2(i, j, val);
				++temp;
			}
		}
		
		//7명중에 4명 + 5명 + 6명을 뽑는경우의 가지
	/*	for (int i = 0; i < (1 << (size)); i++) {
			if(Integer.bitCount(i) == 7) {
				int a = 0;
				int b = 0;
				People_1941_2[] temps = new People_1941_2[7];
				int t = 0;
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						People_1941_2 people = peopels[j];
						char type = people.getType();
						if(type == 'S')
							++a;
						else
							++b;
						temps[t] = people;
						++t;
					}
				}
				if(a + b == 7 && a > b) {
					if(bfs(temps)) {
						++res;
					}
				}
			}
		}*/
		
		int r = 7;
		int[] combArr = new int[r];
		doCombnation(combArr, r, 0, 0);
		System.out.println(res);
		
	}

	private static void doCombnation(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			int a = 0;
			int b = 0;
			People_1941_2[] temps = new People_1941_2[7];
			for (int i = 0; i < index; i++) {
				People_1941_2 people = peopels[combArr[i]];
				char type = people.getType();
				if(type == 'S')
					++a;
				else
					++b;
				temps[i] = people;
			}
			if(a + b == 7 && a > b) {
				if(bfs(temps)) {
					++res;
				}
			}
			
		} else if(target == size) {
			return;
		} else {
			combArr[index] = target;
			doCombnation(combArr, r - 1, index + 1, target + 1);
			doCombnation(combArr, r, index, target + 1);
		}
	}

	private static boolean bfs(People_1941_2[] temps) {
		boolean[][] conn = new boolean[n][n];
		boolean[][] visited = new boolean[n][n];
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for (int i = 0; i < 7; i++) {
			People_1941_2 people = temps[i];
			int px = people.getX();
			int py = people.getY();
			conn[py][px] = true;
		}
		
		Queue<People_1941_2> peopleq = new LinkedList<>();
		peopleq.add(temps[0]);
		visited[temps[0].getY()][temps[0].getX()] = true;
		int cnt = 0;
		while(!peopleq.isEmpty()) {
			People_1941_2 people = peopleq.poll();
			int px = people.getX();
			int py = people.getY();
			cnt += 1;
			for (int dir = 0; dir < 4; dir++) {
				int nextX = px + dx[dir];
				int nextY = py + dy[dir];
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
						&& !visited[nextY][nextX] && conn[nextY][nextX]) {
					visited[nextY][nextX] = true;
					peopleq.add(new People_1941_2(nextY, nextX, arr[nextY][nextX]));
					
				}
			}
		}
		if(cnt == 7) {
			return true;
		}
		return false;
	}
}

class People_1941_2 {
	int y;
	int x;
	char type;
	
	public People_1941_2(int y, int x, char type) {
		super();
		this.y = y;
		this.x = x;
		this.type = type;
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
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
}