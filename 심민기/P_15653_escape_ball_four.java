package baekjoon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_15653_escape_ball_four {
	static int N, M, cnt;
	static boolean[][] field;
	static Queue<Ball_15653[]> que = new LinkedList<>();
	static Ball_15653 hole, last_blue;
	static int[][] dxdy = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean bluefalse;
	static HashSet<String> visited;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] n_s = scan.nextLine().split(" ");
		N = Integer.parseInt(n_s[0]);
		M = Integer.parseInt(n_s[1]);
		field = new boolean[N][M];
		visited = new HashSet<>();
		
		Ball_15653[] balls = new Ball_15653[2];
		for (int i = 0; i < N; i++) {
			String[] line_one = scan.nextLine().split("|");
			for (int j = 0; j < M; j++) {
				if (line_one[j].equals("#")) continue;
				field[i][j] = true;
				if (line_one[j].equals("R")) {
					balls[0] = new Ball_15653(i, j);
				}else if (line_one[j].equals("B")) {
					balls[1] = new Ball_15653(i, j);
				}else if(line_one[j].equals("O"))
					hole = new Ball_15653(i, j);
			}
		}
		String visited_str = balls[0].pi+"|"+balls[0].pj+"|"+balls[1].pi+"|"+balls[1].pj;
		visited.add(visited_str);
		que.add(balls);
		cnt = 0;
		while (!que.isEmpty()) {
			cnt++;
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Ball_15653[] one_balls = que.poll();
				//System.out.println("공꺼낸당 " + one_balls[0].pi+" | "+one_balls[0].pj+" | "+one_balls[1].pi+" | "+one_balls[1].pj+" | ");
				if(move_ball(one_balls)) {
					if(bluefalse) cnt=-1;
					System.out.println(cnt);
					return;
				}
			}
		}
		System.out.println(-1);
	}
	// 빨간색 옮기고, 파란색 옮기고, 빨간색 옮기고
	static boolean move_ball(Ball_15653[] balls) {
		Ball_15653 r_b = balls[0];
		Ball_15653 b_b = balls[1];
		for (int i = 0; i < 4; i++) {
			Ball_15653 n_r_b = new Ball_15653(r_b.pi,r_b.pj);
			Ball_15653 n_b_b = new Ball_15653(b_b.pi,b_b.pj);
			if(move_one_ball(i,n_r_b,n_b_b)) { //빨간공 옮긴다
				//파란공도 빠지는지 확인
				int save_pi = n_r_b.pi;
				int save_pj = n_r_b.pj;
				n_r_b.pi = -1;
				n_r_b.pj = -1;
				if(move_one_ball(i,n_b_b,n_r_b)) {
					n_r_b.pi=save_pi;
					n_r_b.pj=save_pj;
					continue;
				}
				return true;
			}
			if(move_one_ball(i,n_b_b,n_r_b)) { //파란공 옮긴다
				//drawmap(n_r_b,n_b_b);
				continue;
			}
			if(move_one_ball(i,n_r_b,n_b_b)) { //빨간공 옮긴다
				//파란공도 빠지는지 확인
				int save_pi = n_r_b.pi;
				int save_pj = n_r_b.pj;
				n_r_b.pi = -1;
				n_r_b.pj = -1;
				if(move_one_ball(i,n_b_b,n_r_b)) {
					n_r_b.pi=save_pi;
					n_r_b.pj=save_pj;
					continue;
				}
				return true;
			}
			//위치 변동이 없으면 continue
			//drawmap(n_r_b,n_b_b);
			if(r_b.pi==n_r_b.pi&&r_b.pj==n_r_b.pj&&b_b.pi==n_b_b.pi&&b_b.pj==n_b_b.pj) {
				//System.out.println("움직이지 않아 빠이");
				continue;
			}
			String visited_str = n_r_b.pi+"|"+n_r_b.pj+"|"+n_b_b.pi+"|"+n_b_b.pj;
			if(visited.contains(visited_str)) {
				//System.out.println("곂친다 빠이");
				continue;
			}
			visited.add(visited_str);
			Ball_15653[] n_balls = new Ball_15653[2];
			n_balls[0] = n_r_b;
			n_balls[1] = n_b_b;
			
			que.add(n_balls);
		}
		return false;
	}
	//구멍에 빠지면 true
	static boolean move_one_ball(int dict, Ball_15653 b1, Ball_15653 b2) {
		int n_pi = b1.pi;
		int n_pj = b1.pj;
		while(true) {
			n_pi +=dxdy[dict][0];
			n_pj +=dxdy[dict][1];
			if(n_pi==hole.pi&&n_pj==hole.pj) {
				//System.out.println("골인~");
				return true;
			}
			if(n_pi==b2.pi&&n_pj==b2.pj)break; //공있으면 멈춰
			if(!field[n_pi][n_pj])break; //벽이면 멈춰
			b1.pi = n_pi;
			b1.pj = n_pj;
		}
		
		return false;
	}
	static void drawmap(Ball_15653 rb, Ball_15653 bb) {
		String[][] m = new String[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!field[i][j]) {
					m[i][j]="#";
				}else {
					m[i][j]=".";
				}
				if(hole.pi==i&&hole.pj==j)m[i][j]="O";
				if(rb.pi==i&&rb.pj==j)m[i][j]="R";
				if(bb.pi==i&&bb.pj==j)m[i][j]="B";
			}
		}
		System.out.println("-------------------------");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
}

class Ball_15653{
	int pi;
	int pj;
	public Ball_15653(int pi, int pj) {
		this.pi = pi;
		this.pj = pj;
	}
}