package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_2178_maze_search {
		
		static int[] dx = {-1,1,0,0}; //왼쪽 오른쪽 위 아래
		static int[] dy = {0,0,-1,1}; //왼쪽 오른쪽 위 아래
		static Queue<int[]> que = new LinkedList<>();
		static boolean[][] checked;
		static int[][] stage;
		static int n,m;
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			String[] strarr = str.split(" ");
			n = Integer.parseInt(strarr[0]);
			m = Integer.parseInt(strarr[1]);
			checked = new boolean[n][m];
			stage = new int[n][m];
			for(int i=0; i<n;i++){
				String oneline = scan.nextLine();
				String[] onlinearr = oneline.split("|");
				for(int j =0; j< m; j++){
					stage[i][j] = Integer.parseInt(onlinearr[j]);
				}
			}
			
			int [] target = new int[3];
			target[0] =0;
			target[1] =0;
			target[2] =1;
			que.offer(target);
			checked[0][0] = true;
			System.out.println(bfs());
		}
		static int bfs(){
			while(!que.isEmpty()){
				int [] target = que.poll();
				int x = target[0];
				int y = target[1];
				int depth = target[2];
				if(x==n-1&&y==m-1) return depth;
				for(int i=0; i<4; i++){
					int[] ar = new int[3];
					if(dx[i]+x<0||dy[i]+y<0||dy[i]+y==m||dx[i]+x==n||checked[dx[i]+x][dy[i]+y]==true) continue;
					if(stage[dx[i]+x][dy[i]+y]==0){
						checked[dx[i]+x][dy[i]+y]=true;
						continue;
					}
					ar[0] = dx[i]+x;
					ar[1] = dy[i]+y;
					ar[2] = depth+1;
					checked[dx[i]+x][dy[i]+y] = true;
					que.offer(ar);
				}
			}
			return -1;
		}
	}