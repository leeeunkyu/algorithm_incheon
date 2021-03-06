package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int primeNumber [] = {2, 3, 5, 7};
		for (int i = 0; i < primeNumber.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(primeNumber[i]);
			dfs(n, sb);	
		}
	}

	private static void dfs(int n, StringBuilder sb) {
		if(sb.length() == n) {
			System.out.println(sb.toString());
			return;
		}
		for (int i = 0; i <= 9; i++) {
			sb.append(i);
			//System.out.println(sb.toString());
			if (checkPrime(sb)) {
				dfs(n, sb);
			}
			sb.deleteCharAt(sb.length() - 1);			
		}
	}

	private static boolean checkPrime(StringBuilder sb) {
		int val = Integer.parseInt(sb.toString());
		for (int i = 2; i <= (int)Math.sqrt(val); i++) {
			if(val % i == 0) {
				//System.out.println(val+" / "+i);
				return false;
			}
		}
		//System.out.println(val);
		return true;
	}
}
