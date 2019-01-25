package baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_16236_baby_shark {
	
	static int[][] dxdy= {{-1,0},{0,-1},{0,1},{1,0}};
	static int n,shark_size,result,one_turn_move,alive_fish,exp;
	static int[][] sea;
	static Point_16236 baby_shark;
	static ArrayList<Point_16236> feed;
	static Queue<Point_16236> route;
	static boolean[][] visited;
	static void init_turn() {
		visited = new boolean[n][n];
		route = new LinkedList<>();
		route.add(baby_shark);
		visited[baby_shark.pi][baby_shark.pj] = true;
		feed = new ArrayList<>();
		one_turn_move=0;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		sea = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a = scan.nextInt();
				if(a==9) {
					baby_shark = new Point_16236(i, j);
					init_turn();
					shark_size = 2;
				}else {
					sea[i][j] = a;
					if(a!=0)alive_fish++;
				}
			}
		}
		int route_size;
		while((route_size=route.size())!=0&&alive_fish>0){
			one_turn_move++;
//			System.out.println("---------------------");
//			System.out.println("행동의 갯수" + route_size);
			for (int i = 0; i < route_size; i++) {
				Point_16236 one_shark = route.poll();
//				System.out.println("기존 아재" + one_shark.pi + ", "+ one_shark.pj);
				for (int j = 0; j < 4; j++) {
					int n_pi = one_shark.pi+ dxdy[j][0];
					int n_pj = one_shark.pj+ dxdy[j][1];
					if(isboundary(n_pi,n_pj)||visited[n_pi][n_pj])continue;
					visited[n_pi][n_pj] = true;
					if(sea[n_pi][n_pj]>shark_size)continue;
					if(sea[n_pi][n_pj]<shark_size&&sea[n_pi][n_pj]!=0) {
						feed.add(new Point_16236(n_pi, n_pj));
					}else { //없거나 사이즈가 같다
						route.add(new Point_16236(n_pi, n_pj));
					}
				}
			}
//			System.out.println("먹이 사이즈 : " + feed.size());
			if(feed.size()>0) {
				baby_shark = whoisfeed();
				sea[baby_shark.pi][baby_shark.pj] = 0;
				exp++;
				if(exp==shark_size) {
					shark_size++;
					exp=0;
				}
				alive_fish--;
//				System.out.println("----움직인 횟수 "+one_turn_move);
				result+=one_turn_move;
				init_turn();
			}
		}
		System.out.println(result);
	}
	
	/*
	 * 상어 먹는 규칙
	 * 2. 동일한 거리가 있으면 맨위에 있는 물고기, 그래도 2마리 이상이면 가장 왼쪽에 있는 물고기
	 */
	
	static Point_16236 whoisfeed() {
		Point_16236 f1 = feed.get(0);
		for (int i = 1; i < feed.size(); i++) {
			Point_16236 f2 = feed.get(i);
			if(f1.pi>f2.pi) {
				f1 = f2;
			}else if(f1.pi==f2.pi) {
				f1 = f1.pj<f2.pj?f1:f2;
			}
		}
		return f1;
	}
	static boolean isboundary(int a, int b) {
		return a<0||b<0||a==n||b==n;
	}
}
class Point_16236 {
	int pi;
	int pj;
	public Point_16236(int pi, int pj) {
		this.pi = pi;
		this.pj = pj;
	}
	
}