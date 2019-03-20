package swexpertacademy;

import java.util.Scanner;
/*
 * ��Ϲ�ȣ (1~5)�� ���� ����            �׳� �迭�� ������!!
 *       ���� ���������� ����(1) |  ���� �Ʒ������� ����(2)   | ���� �������� ����(3)  |   ���� �������� ����  (4)
 *  1��                  3                    1                     4                    2 
 *  2��                  3                    4                     2                    1
 *  3��                  2                    4                     1                    3
 *  4��                  4                    3                     1                    2
 *  5��                  3                    4                     1                    2
 *  
 */

public class P_5650_pinball_game {
	static int[][] map_status, worm_hole_data;
	static int n,s_i,s_j,d_k;
	static int[][] dxdy = {{0,0},{0,1},{1,0},{0,-1},{-1,0}};
	//change_moving �ε��� ���� : ù��° �ε��� - ����� ��ȣ, �ι�° �ε��� - ���� ����
	static int[][] change_moving = {{0,0,0,0,0},{0,3,1,4,2},{0,3,4,2,1},{0,2,4,1,3},{0,4,3,1,2},{0,3,4,1,2}};
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {

			n = scan.nextInt();
			
			map_status =  new int[n+2][n+2];
			worm_hole_data = new int[16][];
			//�� �����, ��Ȧ ������ �����ϱ�
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					int v = map_status[i][j] = scan.nextInt();
					
					if(v>=6) {
						if(worm_hole_data[v]==null) {
							int[] v_Arr = {i,j};
							worm_hole_data[v] = v_Arr;
						}else {
							map_status[i][j] = v+5;
							worm_hole_data[v+5] = worm_hole_data[v];
							int[] v_Arr = {i,j};
							worm_hole_data[v] = v_Arr;
						}
					}
				}
			}
			
			//�ܰ��� �� 5�� ����� �ֱ�
			for (int i = 1; i < n+1; i++) {
				map_status[0][i] = 5;
				map_status[i][0] = 5;
				map_status[n+1][i] = 5;
				map_status[i][n+1] = 5;
			}
			
			
			int best_score = 0;
			
			//��� �ʿ��� �߻� �غ���
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if(map_status[i][j]!=0)continue;
					//���� �׷����� ���� ���� s_i, s_j, d_k
					s_i = i; s_j = j;
					for (int k = 1; k < 5; k++) {
						d_k =k;
						int score = start_one_game(i,j,k);
						best_score = best_score>score?best_score:score;
					}
				}
			}
			System.out.println("#"+test+" "+ best_score);
		}
	}
	static void print_map(int pi, int pj) {
		System.out.println("--------------------------"+d_k);
		for (int i = 0; i < n+2; i++) {
			for (int j = 0; j < n+2; j++) {
				if(pi ==i&& pj == j) {
					System.out.print("X"+ " ");
				}else if(i==s_i&& j == s_j){
					System.out.print("S"+" ");
				}else {
					System.out.print(map_status[i][j]+" ");
				}
			}
			System.out.println("");
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static int start_one_game(int pi, int pj, int direction) {
		//System.out.println("���� ����!");
		int score=0;
		int n_i = pi + dxdy[direction][0];
		int n_j = pj + dxdy[direction][1];
		while(true) {
			int status = map_status[n_i][n_j];
			print_map(n_i,n_j);
			//���� �ε�ġ�� ���
			if(pi == n_i && pj == n_j) {
				break;
			}else if(1<=status&&status<=5) {
				score++;
				direction = change_moving[status][direction];
				n_i = n_i + dxdy[direction][0];
				n_j = n_j + dxdy[direction][1];
				continue;
			}else if(6<=status&&status<=15) {
				n_i = worm_hole_data[status][0]+dxdy[direction][0];
				n_j = worm_hole_data[status][1]+dxdy[direction][1];
			}else if(status==-1){
				break;
			}else {
				n_i = n_i + dxdy[direction][0];
				n_j = n_j + dxdy[direction][1];
			}
		}
		return score;
	}
}
