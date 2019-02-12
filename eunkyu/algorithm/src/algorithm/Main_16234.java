package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16234 {

	static int n;	//땅의 크기
	static int l;	//인구차이가 L명 이상 R명 이하이면 두 나라가 공유하는
			//국경선을 하루동안 연다.
	static int r;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");		
		n = Integer.parseInt(str[0]);
		l = Integer.parseInt(str[1]);
		r = Integer.parseInt(str[2]);
		int arr[][] = new int[n][n];
		int cpArr[][] = new int[n][n];
		
		
		for (int i = 0; i < arr.length; i++) {
			String str2[] = br.readLine().split(" ");
			for (int j = 0; j < arr[i].length; j++) {
				int val = Integer.parseInt(str2[j]);
				arr[i][j] = val;
				cpArr[i][j] = val;
			}
		}
		
		int cnt = 0;
		
		while(true) {
			int isMove = 0;
			boolean visted [][] = new boolean [arr.length][arr.length];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(!visted[i][j]) {
						isMove += bfs(arr, cpArr, visted, i, j);
					}
				}
			}
			if(isMove == 0) {
				break;
			}
			for (int i = 0; i < cpArr.length; i++) {
				for (int j = 0; j < cpArr[i].length; j++) {
					arr[i][j] = cpArr[i][j];
				}
			}
			cnt++;
		}
		System.out.println(cnt);
		
	}

	private static int bfs(int[][] arr, int[][] cpArr, boolean[][] visted, int i, int j) {
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		int peopleCnt = arr[i][j];
		int countryCnt = 1;
		Queue<Country> q = new LinkedList<Country>();
		q.add(new Country(j, i));
		visted[i][j] = true;
		while(!q.isEmpty()) {
			Country country = q.poll();
			int x = country.getX();
			int y = country.getY();
			for (int idx = 0; idx < 4; idx++) {
				int nextX = x + dx[idx];
				int nextY = y + dy[idx];
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visted[nextY][nextX]) {					
					int sub = Math.abs(arr[y][x] - arr[nextY][nextX]); //현 위치와 다음위치간의 절대값 차이
					if(sub >= l && sub <= r) {
						q.add(new Country(nextX, nextY));						
						countryCnt++;
						peopleCnt += arr[nextY][nextX];
						visted[nextY][nextX] = true;
					}
				}
			}
		}
		
		if(countryCnt == 1) {
			return 0;
		}
		moveCountry(cpArr, visted, peopleCnt/countryCnt);
		return 1;

	}

	private static void moveCountry(int[][] cpArr, boolean[][] visted, int movePeople) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visted[i][j]) {
					cpArr[i][j] = movePeople;
				}
			}
		}
	}
}

class Country {
	int x;
	int y;
	
	public Country(int x, int y) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
	
	
}
