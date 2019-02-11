package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		for (int i = 0; i < arr.length; i++) {
			String str2[] = br.readLine().split(" ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(str2[j]); 
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		bfs(arr);
	}

	private static void bfs(int[][] arr) {
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		
		Queue<Country> q = new LinkedList<Country>();
		boolean visted [][] = new boolean [arr.length][arr.length];
		//ArrayList<ArrayList<Country>> countryList = new ArrayList<ArrayList<Country>>();
		Country countryList[][] = new Country[n][n];
		q.add(new Country(0, 0, new ArrayList<>()));
		visted[0][0] = true;
		while(!q.isEmpty()) {
			Country country = q.poll();
			int x = country.getX();
			int y = country.getY();
			ArrayList<Country> neighbor = country.getNeighbor();
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n 
						&& !visted[nextY][nextX]) {
					visted[nextY][nextX] = true;
					
					int sub = Math.abs(arr[y][x] - arr[nextY][nextX]); //현 위치와 다음위치간의 절대값 차이
					if(sub >= l && sub <= r) {
						neighbor.add(new Country(nextX, nextY, new ArrayList<Country>()));
					}
					q.add(new Country(nextX, nextY, new ArrayList<Country>()));
				}
			}
			country.setNeighbor(neighbor);
			countryList[y][x] = country;
		}
		
		for (int i = 0; i < countryList.length; i++) {
			for (int k = 0; k < countryList[i].length; k++) {
				System.out.print(countryList[i][k].toString()+"  -  ");
			}
			System.out.println();
		}
		
		moveCountry(arr, countryList);
		
	}

	private static void moveCountry(int[][] arr, Country[][] countryList) {
		for (int i = 0; i < countryList.length; i++) {
			for (int j = 0; j < countryList[i].length; j++) {
				ArrayList<Country> neighbor = countryList[i][j].getNeighbor();
				if(neighbor.size() > 0) {
					
				}
			}
		}
		
	}
}

class Country {
	int x;
	int y;
	ArrayList<Country> neighbor;
	
	public Country(int x, int y, ArrayList<Country> neighbor) {
		super();
		this.x = x;
		this.y = y;
		this.neighbor = neighbor;
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
	public ArrayList<Country> getNeighbor() {
		return neighbor;
	}
	public void setNeighbor(ArrayList<Country> neighbor) {
		this.neighbor = neighbor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", neighbor=");
		builder.append(neighbor);
		builder.append("]");
		return builder.toString();
	}
	
	
}
