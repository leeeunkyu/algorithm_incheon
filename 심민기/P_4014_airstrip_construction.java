package swexpertacademy;

import java.util.Scanner;

public class P_4014_airstrip_construction {
	static int N,X;
	static int[][] map;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {
			N = scan.nextInt();
			X = scan.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
				}
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				if(check_one_line(true,i))result++;
				if(check_one_line(false,i))result++;
			}
			System.out.println("#"+test+" "+result);
		}
	}
	static boolean check_one_line(boolean is_i_fix, int p_idx) {
		int safe_cnt = 1; // 이전인덱스보다 높인 애를 만났을때 safe_cnt가 X이상이면 OK
		int dead_cnt = 0; // 이전인덱스보다 낮은 애를 만났을때 값은 X-1로 초기화 되며 한칸씩 이동 할때마다 dead_cnt는 1씩감소 된다.
		                  // 감소 되는 중간에는 safe_cnt는 증가 할 수 없다. 감소되는 중간에 높낮이 변화를 감지하면 탈락
						  // dead_cnt가 
		int p_i,p_j,ii,jj,before_v;
		if(is_i_fix) {
			p_i = p_idx;
			p_j = 1;
			ii=0;
			jj=1;
			before_v = map[p_i][0];
		}else {
			p_i = 1;
			p_j = p_idx;
			ii=1;
			jj=0;
			before_v = map[0][p_j];
		}

		while(p_i < N&&p_j < N) {
			if(before_v == map[p_i][p_j]) {
				if(dead_cnt==0) {
					safe_cnt++;
				}else {
					dead_cnt--;
				}
				p_i+=ii; p_j+=jj;
				continue;
			}else if(Math.abs(before_v - map[p_i][p_j])!=1) {
				return false;
			}
			if(before_v < map[p_i][p_j]) {
				if(dead_cnt>0){
					return false;
				}
				if(safe_cnt>=X) {
					safe_cnt = 1;
					before_v = map[p_i][p_j];
				}else {
					return false;
				}
			}else if(before_v > map[p_i][p_j]) {
				if(dead_cnt!=0)return false;
				safe_cnt=0;
				dead_cnt=X-1;
				before_v = map[p_i][p_j];
			}
			p_i+=ii; p_j+=jj;
		}
		if(dead_cnt!=0)return false;
		//System.out.println((is_i_fix?"i":"j") + p_idx);
		return true;
	}
}
