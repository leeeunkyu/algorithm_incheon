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
		
		for (int i = 0; i < c; i++) {
			if(temp[i] != ' ')
				cryptoList[i] = temp[i];
		}
		
		Arrays.sort(cryptoList);
		String tempStr = "";	//모음 집합

		
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < 5; j++) {
					if(type[j] == cryptoList[i]) {
						tempStr += cryptoList[i];
					}
				}
			}
		constArray = tempStr.toCharArray(); //모음 집합
		cnt = 0;
		System.out.println("??");
		System.out.println(dfs(0, ""));
		System.out.println("??");
	}
	//b c d e f i
	//b c d # f #
	//e i
	private static String dfs(int idx, String str) {
		if(str.length() == l) {
			for (int i = 0; i < constArray.length; i++) {
				for (int j = 0; j < l; j++) {
					if(constArray[i] == str.charAt(j)) {
						cnt ++;
					}
				}
			}
			if(cnt < 1 || l - cnt < 2) {
				str = str.substring(0, str.length()-1);				
			} else {
				return str;				
			}
		}
		for (int i = idx; i < cryptoList.length; i++) {
			System.out.println("!!");
			dfs(++idx, str + cryptoList[i]);
		}
		return str;
	}
}
