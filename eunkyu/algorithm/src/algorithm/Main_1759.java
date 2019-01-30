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
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		l = Integer.parseInt(str[0]); //l 개의 암호문자
		c = Integer.parseInt(str[1]); //c 개의 예상문자
		visited = new boolean[26];
		
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
		/*for (int i = 0; i < c - l; i++) {
			dfs(i, "");			
		}*/
		StringBuilder sb = new StringBuilder();
		dfs(0, "");
	}
	//b c d e f i
	//b c d # f #
	//e i
	private static void dfs(int idx, String str) {
		visited[idx] = true;
		
		if(str.length() == l) {
			System.out.println("답: "+str);
			return;
		}
		for (int i = idx; i < l; i++) {
			
			str += cryptoList[i];
			dfs(++idx, str);	
			str = str.substring(0, str.length() - 1);
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
