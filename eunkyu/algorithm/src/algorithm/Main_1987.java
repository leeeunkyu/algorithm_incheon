package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 말00000
 * 000000
 * 000000
 * 000000
 */
public class Main_1987 {
	static int R = 0;
	static int C = 0;
	static boolean checkVist[][];
//	static ArrayList<Character> visited;
//	static int max = 0;
//	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);	//행 수
		C = Integer.parseInt(str[1]);	//열 수
		char arr[][] = new char[R][C];
		checkVist = new boolean[R][C];
		ArrayList<Character> visited = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		checkVist[0][0] = true;
		visited.add(arr[0][0]);
		int cnt = dfs(arr, 0, 0, visited);
		System.out.println(cnt);
		
	}
	
	public static int dfs(char arr[][], int x, int y, ArrayList<Character> visited) {
		int cnt = 0;
		int dx[] = {0,0,1,-1};
		int dy[] = {1,-1,0,0};
				
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX >=  0 && nextX < C && nextY >= 0 && nextY < R && !checkVist[nextY][nextX]) {
				if(check(arr[nextY][nextX], visited)) {
					checkVist[nextY][nextX] = true;
					visited.add(arr[nextY][nextX]);
					cnt = Math.max(cnt ,dfs(arr, nextX, nextY, visited));				
					checkVist[nextY][nextX] = false;
				}
			}
		}
		
		visited.remove(visited.size() - 1);
		return cnt + 1;
	}

	private static boolean check(char val, ArrayList<Character> visited) {
		for (int i = 0; i < visited.size(); i++) {
			if(visited.get(i) == val)
				return false;
		}
		return true;
	}
}

