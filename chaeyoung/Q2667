import java.util.*;

public class q2667 {

	static int len;
	static int[][] loc;
	static boolean[][] visited;
	static int cnt = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		len = Integer.parseInt(input);
		loc = new int[len+1][len+1];
		visited = new boolean[len+1][len+1];
		int tot_house[];
		
		for(int i=0; i < len ;i++){
			String[] line = sc.nextLine().split("");
			for(int j=0; j < len; j++){
				loc[i][j] = Integer.parseInt(line[j]); //입력
			}
		}

		for(int i=0;i<len;i++){
			for(int j=0;j<len;j++){
				if(loc[i][j]==1 && visited[i][j]==false){ //1이고 방문하지 않았을시 DFS 실행
					DFS(i,j);
					cnt++;
				}
			}
		}

		System.out.println(cnt-1); //1부터 시작했으므로
		tot_house = new int[cnt];
		
		for(int i=0; i < len; i++){
			for(int j=0; j<len; j++){
				if(loc[i][j] > 0){
					tot_house[loc[i][j]]++;
				}
			}
		}

		Arrays.sort(tot_house); //정렬
		for(int i=1;i<cnt;i++){
			System.out.println(tot_house[i]);
		}
}


	public static void DFS(int x, int y){
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};

		loc[x][y] = cnt; //숫자 변경
		visited[x][y] = true; //처음 오면 방문했으므로 방문처리.

		for(int i= 0; i < 4; i++){
			int xx = x + dx[i];
			int yy = y + dy[i];

			if(xx>=0 && yy>=0 && xx<len && yy<len){
				if(loc[xx][yy] == 1 && visited[xx][yy]== false){
					DFS(xx,yy);
				}
			}
		}
	}
}
