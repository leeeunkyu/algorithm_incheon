package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_4014 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			String str[] = br.readLine().split(" ");		
			int n = Integer.parseInt(str[0]);
			int x = Integer.parseInt(str[1]);
			
			int arr [][] = new int [n][n];
			for (int i = 0; i < arr.length; i++) {
				String temp [] = br.readLine().split(" ");
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = Integer.parseInt(temp[j]); 
				}
			}
			
			int cnt = setRunway(arr, x, n);
			sb.append("#"+tc+" "+cnt+"\n");
		}
		
		System.out.println(sb);
	}

	private static int setRunway(int[][] arr, int x, int n) {
		int cnt = 0;
		int sub = 0;
		
		boolean slopeArr [][] = new boolean [n][n];
		for (int i = 0; i < n; i++) {
			//가로줄.. arr[n][n] - arr[n][n+1] - arr[n][n+2} - ..
			boolean dir = true; //왼쪽 오른쪽 방향 결정 변수
			slopeArr = new boolean [n][n]; //경사로 설치 변수
			
			for (int j = 0; j < n; j++) {
				sub = 0;
				if(j + 1 < n)
					sub = arr[i][j] - arr[i][j+1];
				if(sub > 1 || sub < -1) {
					break;
				}
				if(sub == 1 || sub == -1) {				
					if(sub > 0) {
						dir = true;
					} else {
						dir = false;
					}
					if(!setSlope(arr, slopeArr,i, j, x, true, dir)) {
						break;
					}					
				}
				if(j == n - 1) {
					cnt++;
				}
			}
			
			//세로줄 arr[n][n] - arr[n+1][n] - arr[n+2][n] - ..
			dir = true;
			slopeArr = new boolean [n][n];
			
			for (int j = 0; j < n; j++) {
  				sub = 0;
				if(j + 1 < n)
					sub = arr[j][i] - arr[j+1][i];
				if(sub > 1 || sub < -1) {
					break;
				}
				if(sub == 1 || sub == -1) {
					if(sub > 0) {
						dir = true;
					} else {
						dir = false;
					}
					if(!setSlope(arr, slopeArr, i, j, x, false, dir)) {					
						break;
					}	
				}				
				if(j == n - 1) {
					cnt++;
				}
			}
		}		
		return cnt;
	}

	private static boolean setSlope(int[][] arr, boolean[][] slopeArr, int i, int j, int slopeLength, boolean type, boolean dir) {
		int x = 0;	//j + 1 + x < n
		int y = 0;
		int l = 0;
		int val = 0;
		//세로냐 가로냐
		if(type) {
			//왼쪽이냐 오른쪽이냐
			if(dir) {
				x = j + 1;	//j + 1 + x < n
				y = i;
				if(dir && x + (slopeLength - 1) >= arr.length) {
					return false;
				}
				if(x >= arr.length) {
					return false;
				}
				val = arr[y][x];
				for (; l < slopeLength; x++) {
					if(x >= arr.length || val != arr[y][x] || slopeArr[y][x]) {
						return false;
					} else {
						slopeArr[y][x] = true;
						val = arr[y][x];
					}
					l++;
				}	
			} else {
				x = j;	//j - 1 - x >= 0
				y = i;
				if(!dir && x - (slopeLength - 1) < 0) {
					return false;
				}
				if(x < 0) {
					return false;
				}
				val = arr[y][x];
				for (; l < slopeLength ; x--) {
					if(x < 0 || val != arr[y][x] || slopeArr[y][x]) {
						return false;
					} else {
						slopeArr[y][x] = true;
						val = arr[y][x];
					}
					l++;
				}	
			}
			
		} else {
			if(dir) {
				x = i;	//j + 1 + x < n
				y = j + 1;
				if(dir && y - (slopeLength - 1) >= arr.length) {
					return false;
				}
				if(y >= arr.length) {
					return false;
				}
				val = arr[y][x];
				for (; l < slopeLength ; y++) {
					if(y >= arr.length || val != arr[y][x] || slopeArr[y][x]) {
						return false;
					} else {
						slopeArr[y][x] = true;
						val = arr[y][x];
					}
					l++;
				}	
			} else {
				x = i;	//j - 1 - x >= 0
				y = j;
				if(!dir && y - (slopeLength - 1) < 0) {
					return false;
				}
				if(y < 0) {
					return false;
				}
				val = arr[y][x];
				for (; l < slopeLength ; y--) {
					if(y < 0 || val != arr[y][x] || slopeArr[y][x]) {
						return false;
					} else {
						slopeArr[y][x] = true;
						val = arr[y][x];
					}
					l++;
				}	
			}
		}
		return true;
	}
}
