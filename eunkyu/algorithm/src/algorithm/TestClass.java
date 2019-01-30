package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dfs(sb);
		System.out.println(sb);
	}

	private static void dfs(StringBuilder sb) {
		if(sb.length() == n)
			return ;
		
		for (int i = 1; i <= 3; i++) {
			sb.append(i);
			if(check(sb)) {
				dfs(sb);
			}else {
				sb.delete(sb.length() - 1, sb.length());				
			}
		}
	}

	private static boolean check(StringBuilder sb) {
		int length = sb.length();
		int size = length / 2;
		for (int i = 1; i < size + 1; i++) {
			String str1 = sb.substring(length - i, length);
			String str2 = sb.substring(length - (i * 2), length - i);
			if(str1.equals(str2))
				return false;
		}
		return true;
	}
}