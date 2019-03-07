package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
		String[] list = br.readLine().split(" ");
		Queue<String[]> strq = new LinkedList<String[]>();
		for (int i = 0; i < (1 << c); i++) {
			if(Integer.bitCount(i) == l) {
				String[] text = new String[l];
				int cnt = 0;
				for (int j = 0; j < c; j++) {
					if(((1 << j) & i) > 0) {
						text[cnt] = list[j];
						cnt++;
					}
				}
				Arrays.sort(text);
				strq.add(text);
			}
		}
		PriorityQueue<String> pq = new PriorityQueue<>();
		while(!strq.isEmpty()) {
			String[] temp = strq.poll();
			if(check(temp)) {
				String a = "";
				for (int i = 0; i < temp.length; i++) {
					a += temp[i];
				}
				pq.add(a);
			}
		}
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	
	}
	private static boolean check(String[] temp) {
	//한개의 모음 두개의 자음
		//모음 a e i o u
		int acnt = 0;
		int bcnt = 0;
		for (int i = 0; i < temp.length; i++) {
			String val = temp[i];
			
			if (val.equals("a") || val.equals("e") || val.equals("i" ) ||
					val.equals("o") || val.equals("u")) {
				acnt++;
			} else {
				bcnt++;
			}
		}
		
		if(acnt >=1 && bcnt >= 2) {
			return true;
		}
		return false;
	}
	
}
