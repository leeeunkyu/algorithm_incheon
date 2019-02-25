package baekjoon;

import java.util.Scanner;

public class P_15662_Sawtooth_wheel_two {
	static int[][] wheels;
	static int[] wheel_right;
	static int w_cnt;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		w_cnt = Integer.parseInt(scan.nextLine());
		wheels = new int[w_cnt+1][8];
		wheel_right = new int[w_cnt+1];
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
		int result = 0;
		for (int i = 1; i < w_cnt+1; i++) {
			result+=(wheels[i][(wheel_right[i]+8-2)%8]);
		}
		System.out.println(result);
	}

	static void rotate(int who, int turn_direct, int move_direct) { // move_direct 왼쪽방향 0, 오른쪽방향 1, 처음 -1
		int wr_idx = wheel_right[who];
		int wl_idx = (wr_idx+4)%8;
		if(move_direct!=1&&who!=1&&wheels[who-1][wheel_right[who-1]]!=wheels[who][wl_idx]) {
			rotate(who-1,turn_direct*-1,0);
		}
		if(move_direct!=0&&who!=w_cnt&&wheels[who+1][(wheel_right[who+1]+4)%8]!=wheels[who][wr_idx]) {
			rotate(who+1,turn_direct*-1,1);
		}
		wheel_right[who] = (wheel_right[who]-turn_direct+8)%8;
	}
}
