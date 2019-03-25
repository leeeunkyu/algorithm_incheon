package baekjoon;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class P_2629_Weighing_scales2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] ws = new int[n];
		for (int i = 0; i < n; i++) {
			ws[i] = scan.nextInt();
		}
		HashSet<Integer> set_1 = new HashSet<>();
		HashSet<Integer> set_2 = new HashSet<>();
		set_1.add(0);
		for (int i = 0; i < n; i++) {
			Iterator<Integer> it = set_1.iterator();
			while(it.hasNext()) {
				int a = it.next();
				set_2.add(a);
				set_2.add(a-ws[i]);
				set_2.add(a+ws[i]);
			}
			set_1.clear();
			HashSet<Integer> temp =set_2;
			set_2 = set_1;
			set_1 = temp;
		}
		int m = scan.nextInt();
		for (int i = 0; i < m; i++) {
			System.out.print((set_1.contains(scan.nextInt())?"Y ":"N "));
		}
	}
}