package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * () = 2
 * [] = 3
 * ([]) = 2 * 3
 * ()[] = 2 + 3
 */
public class Main_2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(System.in));
		char c[] = br.readLine().toCharArray();
		
		check(c);
	}

	private static void check(char[] c) {
		Stack<Character> a = new Stack<Character>();
		Stack<Character> b = new Stack<Character>();
		
		int cntA = 0;
		int cntB = 0;
		int cntC = 0;
		int cntD = 0;
		
		int size = c.length / 2; // �׻� ¦��
		for (int i = 0; i < size; i++) {
			a.add(c[i]);
			b.add(c[size - i]);
		}
		while(!a.isEmpty()) {
			char aTop = a.pop();
			char bTop = b.pop();
			
			if(aTop == '(' && a.peek() == ')') {
				++cntA;
			} else if(aTop == '[' && b.peek() == ']') {
				++cntB;
			} else {
				++cntD;
			}
			if(bTop == '(' && b.peek() == ')') {
				++cntA;
			} else if(aTop == '[' && a.peek() == ']') {
				++cntB;
			} else {
			}
			
			
		}

	}
	
}
