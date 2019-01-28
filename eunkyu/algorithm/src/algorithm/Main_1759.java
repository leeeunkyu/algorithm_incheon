package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1759 {
	//암호는 L개의 알파벳 소문자
	//최소 한개의 모음과 두개의 자음 오름차순
	//c개의 예상 암호문자
	static char cryptoList[];
	static int cnt;	//한개의 모음이 들어갔나
	static int l;
	static int c;
	static char type[] = {'a', 'e', 'i', 'o', 'u'};
	static char constArray[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		l = Integer.parseInt(str[0]); //l 개의 암호문자
		c = Integer.parseInt(str[1]); //c 개의 예상문자
		
		
		char temp[] = br.readLine().toCharArray();
		cryptoList = new char[c];
		int idx = 0;
		for (int i = 0; i < temp.length; i++) {
			if(temp[i] != ' ') {
				cryptoList[idx] = temp[i];
				idx++;
			}
		}
		
		Arrays.sort(cryptoList); //예상문자 집합
		String tempStr = "";	//모음 집합

		for (int i = 0; i < cryptoList.length; i++) {
			System.out.print(cryptoList[i]);
		}
		System.out.println();
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < 5; j++) {
				if(type[j] == cryptoList[i]) {
					tempStr += cryptoList[i];
				}
			}
		}
		
		constArray = tempStr.toCharArray(); //모음 집합
		cnt = 0;
		for (int i = 0; i < c; i++) {
			dfs(i, 0, 0, "");			
		}
	}
	//b c d e f i
	//b c d # f #
	//e i
	private static void dfs(int idx, int vowel, int consonant, String str) {
		if (str.length() == l) {
			if (vowel >=1 && consonant >= 2) {
				System.out.println(str);
			} 
			return;
		}
		if(idx == c) {
			return;
		}
		
		if (check(cryptoList[idx])) {
			dfs(idx+1, vowel +1, consonant, str + cryptoList[idx]);
		} else {
			dfs(idx+1, vowel, consonant + 1, str + cryptoList[idx]);
		}
		
	}
	private static boolean check(char val) {
		if (val == 'a' || val == 'e'  || val == 'i'  || val == 'o'  || val == 'u' ) {
			return true;
		} else {
			return false;
		}
	}
}
