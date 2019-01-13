import java.util.*;

public class q1012 {
	static int[][] cabbages;
	static boolean[][] visited;
	static int count;
	static int[] bug;
	static int x1;
	static int y1;
	static int[] ans;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		ans = new int[cases];
		
		for (int i = 0 ; i < cases; i++) {
			int ini_x = sc.nextInt();
			int ini_y = sc.nextInt();
			int total = sc.nextInt();
			
			x1 = ini_x;
			y1 = ini_y;
			
			cabbages = new int[ini_x+1][ini_y+1];
			visited = new boolean[ini_x][ini_y];
			
			for (int j = 0 ; j < total; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				cabbages[x][y] = 1;
				
			}
			
			for (int j = 0 ; j < ini_x; j++) {
				for (int k = 0; k < ini_y; k++) {
					if (cabbages[j][k] == 1 && visited[j][k] == false) {
						DFS(j,k);
						count++;
					}
				}
			}
			
			ans[i] = count;
			count = 0;
		}
		
		for (int i = 0; i < ans.length ; i++) {
			System.out.println(ans[i]);
		}
		
	}
	
	
	
	static void DFS(int x, int y) {
		int[] dx = {1,0,-1,0};
		int[] dy = {0,1, 0,-1};
		
		visited[x][y] = true;
		
		for (int i = 0 ; i<4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if ( xx >= 0 && yy >= 0 && x < x1 && y < y1) {
				if (cabbages[xx][yy] == 1 && visited[xx][yy] == false) {
				DFS(xx,yy);
				}
			}
		}
	}
}
