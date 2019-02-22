package swexpertacademy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P_5653_Stem_cell_culture2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		int[][] dxdy = {{-1,0},{1,0},{0,-1},{0,1}};
		for (int test = 1; test < t+1; test++) {
			PriorityQueue<Point_5653> que1 = new PriorityQueue<>((p1,p2)->{
				return p2.max-p1.max;
			});
			PriorityQueue<Point_5653> que2 = new PriorityQueue<>((p1,p2)->{
				return p2.max-p1.max;
			});
			boolean[][] map = new boolean[700][700];
			int N = scan.nextInt();
			int M = scan.nextInt();
			int K = scan.nextInt();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int max = scan.nextInt();
					if(max!=0) {
						map[300+i][300+j] = true;
						que1.add(new Point_5653(300+i, 300+j, max));
					}
				}
			}
			for (int k = 0; k < K; k++) {
				int que_size = que1.size();
				for (int i = 0; i < que_size; i++) {
					Point_5653 point = que1.poll();
					point.status--;
					if(point.status>-1) {
						que2.add(point);
					}else {
						if(point.status==-1) {
							for (int j = 0; j < 4; j++) {
								int n_i = point.pi + dxdy[j][0];
								int n_j = point.pj + dxdy[j][1];
								if(map[n_i][n_j])continue;
								map[n_i][n_j] = true;
								que2.add(new Point_5653(n_i, n_j, point.max));
							}
						}
						if(point.status!=point.max*-1) {
							que2.add(point);
						}
					}
				}
				PriorityQueue<Point_5653> temp = que1;
				que1 = que2;
				que2 = temp;
			}
			System.out.println("#"+test+" "+que1.size());
		}
	}
}
class Point_5653{
	int pi;
	int pj;
	int max;
	int status;
	public Point_5653(int pi, int pj, int max) {
		this.pi = pi;
		this.pj = pj;
		this.max = max;
		this.status = max;
	}

}