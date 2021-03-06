package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_13913 {
	static int n;	//¼öºóÀÌ À§Ä¡
	static int k; 	//µ¿»ý
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		if(n >= k) {
			System.out.println(n - k);
			for (int i = n; i >= k; i--) {
				System.out.print(i+" ");
			}
		}
		else
			bfs();
	}
	
	private static void bfs() {
		int dx[] = {1, -1};
		int cnt = 0;
		int visited[] = new int [100001];
		visited[n] = 1;
		Queue<Walk_13913> q = new LinkedList<Walk_13913>();
		q.add(new Walk_13913(n, n+""));
		
		while(!q.isEmpty()) {
			Walk_13913 walk = q.poll();
			int tempN = walk.getX();
			String walks = walk.getWalks();
			
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
					q.add(new Walk_13913(nextN, walks+" "+nextN));
				}
				if(nextN == k) {
					System.out.println(visited[k] - 1);
					System.out.println(walks+" "+k);
					return;
				}
			}	
		}		
	}
}

class Walk_13913 {
	int x;
	String walks;
	
	public Walk_13913(int x, String walks) {
		super();
		this.x = x;
		this.walks = walks;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public String getWalks() {
		return walks;
	}
	public void setWalks(String walks) {
		this.walks = walks;
	}
}
