package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1697 {
	static int n;	//수빈이 위치
	static int k; 	//동생
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		if(n >= k)
			System.out.println(n - k);
		else
			bfs();
	}
	
	private static void bfs() {
		int dx[] = {1, -1};
		int cnt = 0;
		int visited[] = new int [100001];
		visited[n] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		
		while(!q.isEmpty()) {
			int tempN = q.poll();			
			for (int i = 0; i < dx.length + 1; i++) {
				int nextN;
				if(i == dx.length) {
					cnt = visited[tempN];
					nextN = 2 * tempN;
				}
				else {
					cnt = visited[tempN];
					nextN = tempN + dx[i];
				}
				if(nextN >= 0 && nextN < 100001 && visited[nextN] == 0) {
					visited[nextN] = ++cnt;
					q.add(nextN);
				}
				if(nextN == k) {
					System.out.println(visited[k] - 1);
					return;
				}
			}	
		}		
	}
}

