package swexpertacademy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class P_2382_Microbial_isolation {
	static int N,M,K; // N:가로세로, M:경과시간, K: 군집수
	// 이동방향 상(1), 하(2), 좌(3), 우(4);
	static int[][] dxdy = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static Node_2382[][][] map;
	static int[][] m_cnt;
	static HashSet<Node_2382> m_set;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {
			N = scan.nextInt();
			map = new Node_2382[N][N][4];
			m_cnt = new int[N][N];
			M = scan.nextInt();
			K = scan.nextInt();
			m_set = new HashSet<>();
			for (int i = 0; i < K; i++) {
				Node_2382 m =new Node_2382(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
				insert_microbial(m);
				m_set.add(m);
			}
			
			for (int i = 0; i < M; i++) {
				move_microbial();
				check_merge();
			}
			System.out.println("#"+test+" "+total_cnt());
		}
	}
	static int total_cnt() {
		int total_cnt = 0;
		Iterator<Node_2382> it = m_set.iterator();
		while(it.hasNext()) {
			Node_2382 m = it.next();
			total_cnt+=m.cnt;
		}
		return total_cnt;
	}
	
	static void merge_microbial(int p_i, int p_j) {
		int max_cnt=-1;
		int max_idx=-1;
		for (int i = 0; i < 4; i++) {
			Node_2382 m = map[p_i][p_j][i];
			if(m==null) continue;
			if(max_cnt<m.cnt) {
				max_cnt = m.cnt;
				max_idx = i;
			}
		}
		Node_2382 max_m = map[p_i][p_j][max_idx];
		for (int i = 0; i < 4; i++) {
			Node_2382 m = map[p_i][p_j][i];
			if(m==null||i==max_idx) continue;
			max_m.cnt+=m.cnt;
			remove_microbial(m);
			m_set.remove(m);
		}
		
	}
	static void check_merge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(m_cnt[i][j]>1) merge_microbial(i,j);
			}
		}
	}
	
	static void move_microbial() {
		Iterator<Node_2382> it = m_set.iterator();
		while(it.hasNext()) {
			Node_2382 m = it.next();
			remove_microbial(m);
			m.pi+=dxdy[m.dic][0];
			m.pj+=dxdy[m.dic][1];
			if(check_boundary(m))m.damage();
			insert_microbial(m);
		}
	}
	static void remove_microbial(Node_2382 m) {
		map[m.pi][m.pj][m.pos] = null;
		m_cnt[m.pi][m.pj]--;
	}
	static void insert_microbial(Node_2382 m) {
		for (int i = 0; i < 4; i++) {
			if(map[m.pi][m.pj][i]==null) {
				map[m.pi][m.pj][i]=m;
				m.pos = i;
				m_cnt[m.pi][m.pj]++;
				break;
			}
		}
	}
	static boolean check_boundary(Node_2382 m) {
		if((m.pi==0)||(m.pi==N-1)||(m.pj==N-1)||(m.pj==0)) return true;
		return false;
	}
}

class Node_2382{
	int pi;
	int pj;
	int cnt;
	int dic;
	int pos; //삼차원 배열에서 마지막 차원에 있는 4칸짜리배열 중 어디에 위치해있는지 기록
	public Node_2382(int pi, int pj, int cnt, int dic) {
		this.pi = pi;
		this.pj = pj;
		this.cnt = cnt;
		this.dic = dic;
	}
	public void damage() {
		this.cnt/=2;
		int d = this.dic;
		if(d==1) {
			this.dic=2;
		}else if(d==2) {
			this.dic=1;
		}else if(d==3) {
			this.dic=4;
		}else {
			this.dic=3;
		}
	}
}
