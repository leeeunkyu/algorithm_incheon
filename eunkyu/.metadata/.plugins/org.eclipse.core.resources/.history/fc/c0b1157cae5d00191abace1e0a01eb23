package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1094 {
	static final int INIT_SIZE = 64;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int x = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> sticks = new PriorityQueue<Integer>();
		sticks.add(INIT_SIZE);
		int size = 0;
		int[] arr = new int[INIT_SIZE];
		int sum = 0;
		while (!sticks.isEmpty()) {
			int val = sticks.poll();
			sum += val;
			arr[size] = val; 
			size++;
		}
		if(sum != x) {
			
		}
	}
}
