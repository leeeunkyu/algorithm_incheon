package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
//10:30
public class Main_2290 {
	
	static int s;
	static BigInteger big;
	static String[][] map = {
			{ "--", " ", "--", "--",  "      ", "--", "--", "--", "--", "--"},
			{"|  |","|", "  |","  |","|  |","|  ","|  ","  |","|  |","|  |"},
			{"|  |","|", "  |","  |","|  |","|  ","|  ","  |","|  |","|  |"},
			{ "  ", " ", "--", "--",  "--", "--", "--", "  ", "--", "--"},
			{"|  |","|", "|  ","  |","  |","  |","|  |","  |","|  |","  |"},
			{"|  |","|", "|  ","  |","  |","  |","|  |","  |","|  |","  |"},
			{ "--", " ", "--", "--",  "  ", "--", "--", "", "--", "--"}};
	public static void main(String[] args) throws IOException {
		// 각 숫자는 모두 s+2의 가로와 2s+3의 세로로 이루어 진다. 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		s = Integer.parseInt(str[0]);
		String pivot = str[1];
		int size = pivot.length();
		big = new BigInteger(pivot);
		for (int idx = 0; idx < (2 * s) + 3; idx++) {
			for (int i = 0; i < size; i++) {
				int num = Integer.parseInt(pivot.substring(i, i + 1));
				draw(num, sb);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
	private static void draw(int num, StringBuilder sb) {
		switch (num) {
		case 0:
			sb.append(" ");
			for (int i = 0; i < s; i++) {
				sb.append("-");
			}
			sb.append(" ");
			break;

		default:
			break;
		}
		
	}
}
