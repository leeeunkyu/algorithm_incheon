package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 1. 현재 위치를 청소한다.
 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.
 */
public class Main_14503 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1[] = br.readLine().split(" ");
		int n = Integer.parseInt(str1[0]);	//세로
		int m = Integer.parseInt(str1[1]);	//가로
		int[][] arr = new int [n][m];
		String str2[] = br.readLine().split(" ");
		int r = Integer.parseInt(str2[0]); //y
		int c = Integer.parseInt(str2[1]); //x
		int d = Integer.parseInt(str2[2]); //0 - 북, 1 - 동, 2 - 남, 3 - 서
		CleanBot cleanBot = new CleanBot(c, r, d, 1);
		
		for (int i = 0; i < n; i++) {
			String[] str3 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str3[j]);
			}
		}
		
		goClean(cleanBot, arr);
	}

	//왼쪽방향
	//북(0) - 서(3), 서(3) - 남(2), 남(2) - 동(1), 동(1) - 북(0)
	private static void goClean(CleanBot cleanBot, int[][] arr) {
		int[] leftDir = {3, 0, 1, 2};
		int n = arr.length; //세로
		int m = arr[0].length; //가로
		boolean[][] visited = new boolean [n][m];
		int nextY = 0;
		int nextX = 0;
		boolean isClean = false;
		int dirCnt = 0;
		int x = cleanBot.getX();
		int y = cleanBot.getY();
		int cleanNum = cleanBot.getCleanNum();
		visited[y][x] = true;
		int dir = leftDir[cleanBot.getDir()];
		//print(arr, cleanBot, dir, visited);
		
		loop:
		while (true) {
			int temp = dir; //4방향 모두 검사를 하고 후진을 위해 미리 현재방향을 저장해놓는다.
			if (dir == 0) { //북쪽 방향이라면
				nextY = y - 1;
				nextX = x;
				if (nextY >= 0 && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 1) { //동쪽 방향이라면
				nextY = y;
				nextX = x + 1;
				if (nextX < m  && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 2) { //남쪽 방향이라면
				nextY = y + 1;
				nextX = x;
				if (nextY < n && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 3) { //서쪽방향이라면..
				nextY = y;
				nextX = x - 1;
				if (nextX >= 0 && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			}
			if (isClean) { //조건에 부합하다면 청소를 한다.
				dir = leftDir[dir];
				y = nextY;
				x = nextX;
				++cleanNum;
				cleanBot.setCleanNum(cleanNum);
				visited[y][x] = true;
				isClean = false;
				dirCnt = 0; 
			} else {
				if(dirCnt >= 4) { //4방향 모두 벽이거나 이미 청소를 했다면 후진을 한다.
					if(temp == 0) {
						//북 쪽 뒤는 남
						if((y + 1 < n) && arr[y + 1][x] != 1) {
							y = y + 1;
						} else {
							break loop;
						}
					} else if(temp == 1) {
						//동쪽 뒤는 서
						if((x - 1 >= 0) && arr[y][x - 1] != 1) {
							x = x - 1;
						} else {
							break loop;
						}
					} else if(temp == 2) {
						//남쪽 뒤는 북
						if((y - 1 >= 0) && arr[y - 1][x] != 1) {
							y = y - 1;
						} else {
							break loop;
						}
					} else {
						//서쪽뒤는 동
						if((x + 1 < n) && arr[y][x + 1] != 1) {
							x = x + 1;
						} else {
							break loop;
						}
					}
					dirCnt = 0;
				}
			}
			cleanBot.setX(x);
			cleanBot.setY(y);
			//print(arr, cleanBot, temp, visited);
		}
		System.out.println(cleanNum);
	}

	//청소기가 지나가는 자리 확인하는 용도
	private static void print(int[][] arr, CleanBot cleanBot, int dir, boolean[][] visited) {
		int x = cleanBot.getX();
		int y = cleanBot.getY();
		System.out.println("dir: "+dir);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(j == x && y == i)
					System.out.print("B ");
				else if(visited[i][j])
					System.out.print("T ");
				else
					System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class CleanBot {
	private int x;
	private int y;
	private int dir; //방향
	private int cleanNum; //청소 횟수
	
	public CleanBot(int x, int y, int dir, int cleanNum) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cleanNum = cleanNum;
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
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public int getCleanNum() {
		return cleanNum;
	}
	public void setCleanNum(int cleanNum) {
		this.cleanNum = cleanNum;
	}
}
