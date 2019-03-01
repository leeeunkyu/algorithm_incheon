package swexpertacademy;

import java.util.Arrays;
import java.util.Scanner;

public class P_5644_Wireless_charging_print {
	static int M,A,result;
	static int[][] user_A_B,move_1_2,map,charging_info;
	static int[][] dxdy = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {
			result = 0;
			M = scan.nextInt();
			A = scan.nextInt();
			move_1_2 = new int[2][M];
			map = new int[10][10];
			user_A_B = new int[2][2];
			//유저 A,B 위치 초기화
			user_A_B[0][0] = 0; user_A_B[0][1] = 0; user_A_B[1][0] = 9; user_A_B[1][1] = 9;
			//두명의 사용자 이동데이터 저장
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < M; j++) {
					move_1_2[i][j] = scan.nextInt();
				}
			}
			
			//BC의 데이터를 정리
			charging_info = new int[A][4];
			for (int i = 0; i < A; i++) {
				// 아..... x좌표가 j값이야... y좌표는 i값이고....
				charging_info[i][1] = scan.nextInt()-1; //문제는 1,1부터 소스코드는 0,0이라서 1씩빼주기
				charging_info[i][0] = scan.nextInt()-1;
				charging_info[i][2] = scan.nextInt();
				charging_info[i][3] = scan.nextInt();
			}
			
			//BC데이터를 내림차순으로 정렬
			Arrays.sort(charging_info, (arr1,arr2) -> {
				return arr2[3]-arr1[3];
			});
			
			
			
			//show_charging_info();
			
			
			
			//MAP에 BC데이터를 비트마스트 형태로 삽입
			for (int i = 0; i < A; i++) {
				write_charging(charging_info[i][0],charging_info[i][1],charging_info[i][2],i);
			}
			
			
			
			//draw_map();
			
			
			//움직이기전에 처음자리에서 충전해야 되니 charging_power() 호출
			charging_power();
			
			// 유저를 움직이면서 충전하자
			for (int i = 0; i < M; i++) {
				//System.out.println(i+"시간 이동중...");
				move_user(i);
			}
			System.out.println("#"+test+" "+result);
		}
	}
	static void show_charging_info() {
		System.out.println("-----충전기 정보-----");
		for (int i = 0; i < A; i++) {
			System.out.println("인덱스 : "+i+", 좌표 : "+charging_info[i][0]+","+charging_info[i][1]+" | 거리  : "+charging_info[i][2]+", power "+charging_info[i][3]);
		}
		System.out.println("----------------");
	}
	static void draw_map() {
		System.out.println("-----------------------------");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(user_A_B[0][0]==i&&user_A_B[0][1]==j) {
					System.out.print("A ");
				}else if(user_A_B[1][0]==i&&user_A_B[1][1]==j) {
					System.out.print("B ");
				}else {
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}
	
	static int searching_bc(int data, int idx) {
		for (int i = idx; i < A; i++) {
			if((data&(1<<i))!=0) {
				return i;
			}
		}
		return -1;
	}
	
	static void charging_power() {
		int check_A = -1;
		int check_B = -1;
		int bc_1_data = map[user_A_B[0][0]][user_A_B[0][1]];
		int bc_2_data = map[user_A_B[1][0]][user_A_B[1][1]];
		int bc_1 = searching_bc(bc_1_data,0);
		int bc_2 = searching_bc(bc_2_data,0);
		if(bc_1==-1&&bc_2==-1)return;
		if(bc_1!=bc_2) {
			if(bc_1!=-1) {
				check_A = bc_1;
				result+=charging_info[bc_1][3];
			}
			if(bc_2!=-1) {
				check_B = bc_2;
				result+=charging_info[bc_2][3];
			}
		}else { // bc_1==bc_2 인 경우
			int n_bc_1 = searching_bc(bc_1_data,bc_1+1);
			int n_bc_2 = searching_bc(bc_2_data,bc_2+1);
			if(n_bc_1==-1&&n_bc_2==-1) { //bc가 하나밖에 없는 상황
				check_A = check_B = bc_1;
				result+=charging_info[bc_1][3];
			}else{
				result+=charging_info[bc_1][3]; //가장큰거 하나 충전하고 bc_1이랑 bc_2랑 같아서 bc_2로 써도됨
				//그 다음 큰 애로 더하기
				if(n_bc_1==-1) {
					check_A = bc_1;
					check_B = n_bc_2;
					result+=charging_info[n_bc_2][3];
				}else if(n_bc_2==-1) {
					check_B = bc_1;
					check_A = n_bc_1;
					result+=charging_info[n_bc_1][3];
				}else if(n_bc_1<n_bc_2) {
					check_B = bc_1;
					check_A = n_bc_1;
					result+=charging_info[n_bc_1][3];
				}else {
					check_A = bc_1;
					check_B = n_bc_2;
					result+=charging_info[n_bc_2][3];
				}
			}
		}
		//print_charging_info(check_A,check_B);
	}
	static void print_charging_info(int check_A, int check_B) {
		if(check_A!=-1&&check_B!=-1&&check_A==check_B) {
			System.out.println("A와 B는 같은 충전기"+check_B +"를 사용했다. 총 충전량은"+charging_info[check_A][3]);
		}else {
			System.out.println(check_A==-1?"A는 충전을 하지 못했다.":("A는"+check_A+"를 이용했다. 해당기기의 충전량은 "+charging_info[check_A][3]));
			System.out.println(check_B==-1?"B는 충전을 하지 못했다.":("B는 "+check_B+"를 이용했다. 해당기기의 충전량은"+charging_info[check_B][3]));
		}
		System.out.println("누적 충전량은 "+result);
	}
	static void move_user(int sec) {
		for (int i = 0; i < 2; i++) {
			user_A_B[i][0] += dxdy[move_1_2[i][sec]][0];
			user_A_B[i][1] += dxdy[move_1_2[i][sec]][1];
		}
		//draw_map();
		charging_power();
	}
	static void write_charging(int c_i, int c_j, int c_dis, int shift_idx) {
		for (int i = 0; i < c_dis; i++) {
			for (int j = 0; j < 1+i*2; j++) {
				int n_i = c_i-c_dis+i;
				int n_j = c_j+j-i;
				if(n_i<0||n_j<0||n_i>=10||n_j>=10)continue;
				map[n_i][n_j] |= (1<<shift_idx);
			}
		}
		
		for (int i = 0; i < c_dis+1; i++) {
			for (int j = 0; j < (c_dis-i)*2+1; j++) {
				int n_i = c_i+i;
				int n_j = c_j-c_dis+i+j;
				if(n_i<0||n_j<0||n_i>=10||n_j>=10)continue;
				map[n_i][n_j] |= (1<<shift_idx);
			}
		}
	}
}
