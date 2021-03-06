package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12813 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*String bitA = br.readLine();
		String bitB = br.readLine();*/
		int[] bitA = new int[100001];
		int[] bitB = new int[100001];
		String str = br.readLine();
		int n = str.length();
		for (int i = 0; i < n; i++) {
			bitA[i] = str.charAt(i) - '0';
		}
		str = br.readLine();
		for (int i = 0; i < n; i++) {
			bitA[i] = str.charAt(i) - '0';
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(bitA[i]&bitB[i]);
		}
		sb.append("\n");
		for (int i = 0; i < n; i++) {
			sb.append(bitA[i]|bitB[i]);
		}
		sb.append("\n");
		for (int i = 0; i < n; i++) {
			sb.append(bitA[i]^bitB[i]);
		}
		sb.append("\n");
		for (int i = 0; i < n; i++) {
			sb.append(bitA[i]^1);
		}
		sb.append("\n");
		for (int i = 0; i < n; i++) {
			sb.append(bitB[i]^1);
		}
		sb.append("\n");
		System.out.println(sb);
	}
}
