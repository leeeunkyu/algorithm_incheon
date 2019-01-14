package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main_2667 {
	
	static int cnt = 0;
	static int arr[][];
	static int visited[][];
	static int sectionNum = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cnt = Integer.parseInt(br.readLine());
		arr = new int[cnt][cnt];
		visited = new int[cnt][cnt];
		
		System.out.println();
		for (int i = 0; i < cnt; i++) {
			char temp[] = br.readLine().toCharArray();
			for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = temp[j]-'0';
			}
		}
		
		int count = 0;
		ArrayList<Integer> sectionArray = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 1 && visited[i][j] == 0) {
					sectionNum = 0;
					dfs(i, j);
					sectionArray.add(sectionNum);
					count++;
				}
			}
		}
		System.out.println(count);
		Collections.sort(sectionArray);
		for (int i = 0; i < sectionArray.size(); i++) {
			System.out.println(sectionArray.get(i)+1);
		}
	}

	private static void dfs(int i, int j) {
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		
		visited[i][j] = 1;
		
		for (int k = 0; k < dy.length; k++) {
			int nextX = j + dx[k];
			int nextY = i + dy[k];
			if (nextX >= 0 && nextX < cnt && nextY >= 0 && nextY < cnt) {
				if(arr[nextY][nextX] == 1 && visited[nextY][nextX] == 0) {
					++sectionNum;
					dfs(nextY, nextX);
				}
			}
		}
	}
}
