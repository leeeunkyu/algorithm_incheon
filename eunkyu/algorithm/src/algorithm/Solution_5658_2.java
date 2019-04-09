package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5658_2 {
	
	static int n;
	static int k;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			k = Integer.parseInt(str[1]);
		}
	
	}
}
