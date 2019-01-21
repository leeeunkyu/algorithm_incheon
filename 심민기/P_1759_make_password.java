package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class P_1759_make_password {
	static StringBuilder result;
	static String[] cc;
	static String vowel = "aeiou";
	static int l,c, c_cnt, v_cnt;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] lc = scan.nextLine().split(" ");
		l = Integer.parseInt(lc[0]);
		c = Integer.parseInt(lc[1]);
		cc = scan.nextLine().split(" ");
		result = new StringBuilder();
		Arrays.sort(cc);
		dfs(0,0);
	}
	static void dfs(int st, int depth) {
		if(depth==l) {
			if(c_cnt<2||v_cnt<1||c_cnt+v_cnt!=l)return;
			System.out.println(String.valueOf(result));
			return;
		}
		for (int i = st; i < c; i++) {
			
			String ch = cc[i];
			boolean isvowel =(-1!=vowel.indexOf(ch));
			if(isvowel) v_cnt++;
			else c_cnt++;
			result.append(ch);
			
			dfs(i+1,depth+1);
			result.deleteCharAt(result.length()-1);
			if(isvowel) v_cnt--;
			else c_cnt--;
		}
	}
}
