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
		
		int arr[] = new int[1000000];
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			arr[i] = 0;
		}
		for (int i = 0; i < 1000000; i++) {
			int a = arr[i];
		} 
		end = System.currentTimeMillis();
		System.out.println("array: " + (end - start));
	}

	
}
