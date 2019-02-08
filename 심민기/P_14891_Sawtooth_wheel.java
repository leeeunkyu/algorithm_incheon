package baekjoon;

import java.util.Scanner;

public class P_14891_Sawtooth_wheel {
	// N���� 0, S���� 1
	// ����� ���� �ε����� 6, ������ �ε����� 2, 12�ù��� �ε����� 0
	static int[][] wheels = new int[5][8];
	static int[] wheel_right = new int[5];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		for (int i = 1; i < wheels.length; i++) {
			String[] strs = scan.nextLine().split("|");
			for (int j = 0; j < wheels[0].length; j++) {
				wheels[i][j] = Integer.parseInt(strs[j]);
			}
			wheel_right[i] = 2;
		}
		int t = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < t; i++) {
			String[] strs = scan.nextLine().split(" ");
			rotate(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]),-1);
		}
		System.out.println((wheels[1][(wheel_right[1]+8-2)%8])+
				(wheels[2][(wheel_right[2]+8-2)%8])*2+
				(wheels[3][(wheel_right[3]+8-2)%8])*4+
				(wheels[4][(wheel_right[4]+8-2)%8])*8);
	}

	static void rotate(int who, int turn_direct, int move_direct) { // move_direct ���ʹ��� 0, �����ʹ��� 1, ó�� -1
		int wr_idx = wheel_right[who];
		int wl_idx = (wr_idx+4)%8;
		//������ ���� �˻�����
		//���ʲ��� ���ư���?
		if(move_direct!=1&&who!=1&&wheels[who-1][wheel_right[who-1]]!=wheels[who][wl_idx]) {
			rotate(who-1,turn_direct*-1,0);
		}
		//�����ʲ��� ���ư���?
		if(move_direct!=0&&who!=4&&wheels[who+1][(wheel_right[who+1]+4)%8]!=wheels[who][wr_idx]) {
			rotate(who+1,turn_direct*-1,1);
		}
		//����!
		wheel_right[who] = (wheel_right[who]-turn_direct+8)%8;
	}
}
