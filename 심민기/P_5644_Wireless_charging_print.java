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
			//���� A,B ��ġ �ʱ�ȭ
			user_A_B[0][0] = 0; user_A_B[0][1] = 0; user_A_B[1][0] = 9; user_A_B[1][1] = 9;
			//�θ��� ����� �̵������� ����
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < M; j++) {
					move_1_2[i][j] = scan.nextInt();
				}
			}
			
			//BC�� �����͸� ����
			charging_info = new int[A][4];
			for (int i = 0; i < A; i++) {
				// ��..... x��ǥ�� j���̾�... y��ǥ�� i���̰�....
				charging_info[i][1] = scan.nextInt()-1; //������ 1,1���� �ҽ��ڵ�� 0,0�̶� 1�����ֱ�
				charging_info[i][0] = scan.nextInt()-1;
				charging_info[i][2] = scan.nextInt();
				charging_info[i][3] = scan.nextInt();
			}
			
			//BC�����͸� ������������ ����
			Arrays.sort(charging_info, (arr1,arr2) -> {
				return arr2[3]-arr1[3];
			});
			
			
			
			//show_charging_info();
			
			
			
			//MAP�� BC�����͸� ��Ʈ����Ʈ ���·� ����
			for (int i = 0; i < A; i++) {
				write_charging(charging_info[i][0],charging_info[i][1],charging_info[i][2],i);
			}
			
			
			
			//draw_map();
			
			
			//�����̱����� ó���ڸ����� �����ؾ� �Ǵ� charging_power() ȣ��
			charging_power();
			
			// ������ �����̸鼭 ��������
			for (int i = 0; i < M; i++) {
				//System.out.println(i+"�ð� �̵���...");
				move_user(i);
			}
			System.out.println("#"+test+" "+result);
		}
	}
	static void show_charging_info() {
		System.out.println("-----������ ����-----");
		for (int i = 0; i < A; i++) {
			System.out.println("�ε��� : "+i+", ��ǥ : "+charging_info[i][0]+","+charging_info[i][1]+" | �Ÿ�  : "+charging_info[i][2]+", power "+charging_info[i][3]);
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
		}else { // bc_1==bc_2 �� ���
			int n_bc_1 = searching_bc(bc_1_data,bc_1+1);
			int n_bc_2 = searching_bc(bc_2_data,bc_2+1);
			if(n_bc_1==-1&&n_bc_2==-1) { //bc�� �ϳ��ۿ� ���� ��Ȳ
				check_A = check_B = bc_1;
				result+=charging_info[bc_1][3];
			}else{
				result+=charging_info[bc_1][3]; //����ū�� �ϳ� �����ϰ� bc_1�̶� bc_2�� ���Ƽ� bc_2�� �ᵵ��
				//�� ���� ū �ַ� ���ϱ�
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
			System.out.println("A�� B�� ���� ������"+check_B +"�� ����ߴ�. �� ��������"+charging_info[check_A][3]);
		}else {
			System.out.println(check_A==-1?"A�� ������ ���� ���ߴ�.":("A��"+check_A+"�� �̿��ߴ�. �ش����� �������� "+charging_info[check_A][3]));
			System.out.println(check_B==-1?"B�� ������ ���� ���ߴ�.":("B�� "+check_B+"�� �̿��ߴ�. �ش����� ��������"+charging_info[check_B][3]));
		}
		System.out.println("���� �������� "+result);
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
