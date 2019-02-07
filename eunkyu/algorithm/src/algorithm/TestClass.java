package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TestClass {
	
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(2);
		if (stack.size() == 0) {
			System.out.println("stack size 0");
			return;
		}
		
		if (stack.isEmpty()) {
			System.out.println("stack isEmpty");
			return;
		}
		
		if (stack.peek() != 0 && stack.peek() != 1) {
			System.out.println("stack peek");
			return;
		}
	}

	
}
