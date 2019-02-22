package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str;
		for (int i = 0; i < 100; i++) {
			str = br.readLine();
			sb.append(str+"\n");
		}
		System.out.println(sb);
	}
}