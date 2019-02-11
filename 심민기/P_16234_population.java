package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_16234_population {
	static int n, l, r, result =-1, sub_cnt, sub_loc;
	static int[][] field, dxdy = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] visited, sub_visited;
	static boolean ismove=true;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str_nlr = br.readLine().split(" ");
		n = Integer.parseInt(str_nlr[0]);
		l = Integer.parseInt(str_nlr[1]);
		r = Integer.parseInt(str_nlr[2]);
		field = new int[n][n];
		sub_visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String[] datas = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(datas[j]);
			}
		}
		
		while(ismove) {
			result++;
			visited = new boolean[n][n];
			ismove=false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(visited[i][j])continue;
					sub_cnt = 0;
					sub_loc = 0;
					search_adjacency(i,j);
					if(sub_loc>1) {
						ismove = true;
						change_feild(i, j, sub_cnt/sub_loc);
					}else sub_visited[i][j]=false;
				}
			}
		}
		System.out.println(result);
	}
	
	static void change_feild(int pi, int pj, int ch_v) {
		sub_visited[pi][pj] = false;
		field[pi][pj] = ch_v;
		for (int i = 0; i < dxdy.length; i++) {
			int n_pi = pi + dxdy[i][0];
			int n_pj = pj + dxdy[i][1];
			if(ch_boundary(n_pi, n_pj)||!sub_visited[n_pi][n_pj])continue;
			change_feild(n_pi, n_pj, ch_v);
		}
	}
	
	static void search_adjacency(int pi, int pj) {
		visited[pi][pj] = true;
		sub_visited[pi][pj] = true;
		sub_loc++;
		sub_cnt+=field[pi][pj];
		for (int i = 0; i < dxdy.length; i++) {
			int n_pi = pi + dxdy[i][0];
			int n_pj = pj + dxdy[i][1];
			if(ch_boundary(n_pi, n_pj)||visited[n_pi][n_pj])continue;
			int gap = Math.abs(field[pi][pj] - field[n_pi][n_pj]);
			if(l<=gap&&gap<=r) search_adjacency(n_pi,n_pj);
		}
	}
	static boolean ch_boundary(int a, int b){
		return a<0||b<0||a==n||b==n;
	}
}