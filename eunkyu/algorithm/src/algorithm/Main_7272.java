package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_7272 {
	
	static int n;
	static HashSet<Character> set;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		char[] word = {'A','D','O','P','Q','R'};
		set = new HashSet<Character>();
		for (int i = 0; i < word.length; i++) {
			set.add(word[i]);
		}
		for (int i = 1; i <= n; i++) {
			String[] str = br.readLine().split(" ");
			String str1 = str[0];
			String str2 = str[1];
			boolean res = check(str1, str2);
			if(res) {
				sb.append("#"+i+" SAME\n");
			} else {
				sb.append("#"+i+" DIFF\n");				
			}
		}
		System.out.println(sb);
	}

	private static boolean check(String str1, String str2) {
		if(str1.length() != str2.length())
			return false;
		for (int i = 0; i < str1.length(); i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			if(set.contains(c1)) {
				if(set.contains(c2)) {
					continue;
				} else {
					return false;
				}
			}
			if(!set.contains(c1) && c1 != 'B') {
				if(!set.contains(c2) && c2 != 'B') {
					continue;
				} else {
					return false;
				}
			}
			
			if((c1 == 'B' && c2 != 'B') || (c1 != 'B' && c2 == 'B')) {
				return false;
			}
		}
		return true;
	}
}
