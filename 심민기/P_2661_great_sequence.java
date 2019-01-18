package baekjoon;

import java.util.Scanner;

public class P_2661_great_sequence {
	static int n;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		StringBuilder sb = new StringBuilder();
		dfs(sb);
		System.out.println(sb);
	}
	static boolean dfs(StringBuilder sb) {
		if(sb.length()==n) return true;
		for (int i = 1; i < 4; i++) {
			sb.append(i);
			if(check_sequence(sb)) {
				if(dfs(sb))return true;
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return false;
	}
	static boolean check_sequence(StringBuilder sb) {
		int size =sb.length();
		int half_size = size/2;
		for (int i = 1; i < half_size+1; i++) {
			String s1 = sb.substring(size-i*2, size-i);
			String s2 = sb.substring(size-i, size);
			if(s1.equals(s2)) return false;
		}
		return true;
	}
}
