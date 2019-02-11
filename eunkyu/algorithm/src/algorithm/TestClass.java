package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.plaf.SliderUI;

public class TestClass {
	
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		double start;
		double end;	
		
		Stack<Integer> stack = new Stack<>();
		start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			stack.add(0);
		}
		String str[] = new String[100];
		System.out.println("--");
		for (int i = 0; i < 100; i++) {
			stack.add(0);
		}
		String str2[] = new String[100];
		System.out.println("--");
		for (int i = 0; i < 100; i++) {
			stack.add(0);
		}
		String str3[] = new String[100];
		System.out.println("--");
		
		end = System.currentTimeMillis();
		System.out.println("array: " + (end - start));
	}

	
}
