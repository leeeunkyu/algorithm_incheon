package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 1. ���� ��ġ�� û���Ѵ�.
 2. ���� ��ġ���� ���� ������ �������� ���ʹ������ ���ʴ�� Ž���� �����Ѵ�.
���� ���⿡ ���� û������ ���� ������ �����Ѵٸ�, �� �������� ȸ���� ���� �� ĭ�� �����ϰ� 1������ �����Ѵ�.
���� ���⿡ û���� ������ ���ٸ�, �� �������� ȸ���ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���� ��쿡��, �ٶ󺸴� ������ ������ ä�� �� ĭ ������ �ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���̸鼭, ���� ������ ���̶� ������ �� �� ���� ��쿡�� �۵��� �����.
�κ� û�ұ�� �̹� û�ҵǾ��ִ� ĭ�� �� û������ ������, ���� ����� �� ����.
 */
public class Main_14503 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1[] = br.readLine().split(" ");
		int n = Integer.parseInt(str1[0]);	//����
		int m = Integer.parseInt(str1[1]);	//����
		int[][] arr = new int [n][m];
		String str2[] = br.readLine().split(" ");
		int r = Integer.parseInt(str2[0]); //y
		int c = Integer.parseInt(str2[1]); //x
		int d = Integer.parseInt(str2[2]); //0 - ��, 1 - ��, 2 - ��, 3 - ��
		CleanBot cleanBot = new CleanBot(c, r, d, 1);//x, y, dir, num
		
		for (int i = 0; i < n; i++) {
			String[] str3 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str3[j]);
			}
		}
		
		goClean(cleanBot, arr);
	}

	//���ʹ���
	//��(0) - ��(3), ��(3) - ��(2), ��(2) - ��(1), ��(1) - ��(0)
	private static void goClean(CleanBot cleanBot, int[][] arr) {
		int[] leftDir = {3, 0, 1, 2};
		int n = arr.length; //����
		int m = arr[0].length; //����
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
			int temp = dir; //4���� ��� �˻縦 �ϰ� ������ ���� �̸� ��������� �����س��´�.
			if (dir == 0) { //���� �����̶��
				nextY = y - 1;
				nextX = x;
				if (nextY >= 0 && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 1) { //���� �����̶��
				nextY = y;
				nextX = x + 1;
				if (nextX < m  && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 2) { //���� �����̶��
				nextY = y + 1;
				nextX = x;
				if (nextY < n && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			} else if (dir == 3) { //���ʹ����̶��..
				nextY = y;
				nextX = x - 1;
				if (nextX >= 0 && arr[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					isClean = true;
				} else {
					dir = leftDir[dir];
					++dirCnt;
				}
			}
			if (isClean) { //���ǿ� �����ϴٸ� û�Ҹ� �Ѵ�.
				dir = leftDir[dir];
				y = nextY;
				x = nextX;
				++cleanNum;
				cleanBot.setCleanNum(cleanNum);
				visited[y][x] = true;
				isClean = false;
				dirCnt = 0; 
			} else {
				if(dirCnt >= 4) { //4���� ��� ���̰ų� �̹� û�Ҹ� �ߴٸ� ������ �Ѵ�.
					if(temp == 0) {
						//�� �� �ڴ� ��
						if((y + 1 < n) && arr[y + 1][x] != 1) {
							y = y + 1;
						} else {
							break loop;
						}
					} else if(temp == 1) {
						//���� �ڴ� ��
						if((x - 1 >= 0) && arr[y][x - 1] != 1) {
							x = x - 1;
						} else {
							break loop;
						}
					} else if(temp == 2) {
						//���� �ڴ� ��
						if((y - 1 >= 0) && arr[y - 1][x] != 1) {
							y = y - 1;
						} else {
							break loop;
						}
					} else {
						//���ʵڴ� ��
						if((x + 1 < n) && arr[y][x + 1] != 1) {
							x = x + 1;
						} else {
							break loop;
						}
					}
					dirCnt = 0;
					dir = leftDir[temp];
				}
			}
			cleanBot.setX(x);
			cleanBot.setY(y);
			//print(arr, cleanBot, temp, visited);
		}
		System.out.println(cleanNum);
	}

	//û�ұⰡ �������� �ڸ� Ȯ���ϴ� �뵵
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
	private int dir; //����
	private int cleanNum; //û�� Ƚ��
	
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