package algorithm_review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2309 {
	
	static final int SIZE = 9;
	static final int C_SIZE = 7;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < (1 << SIZE); i++) {
			int sum = 0;
			Queue<Integer> tempq = new LinkedList<Integer>();
			if(Integer.bitCount(i) == C_SIZE) {
				for (int j = 0; j < SIZE; j++) {
					if(((1 << j) & i) > 0) {
						sum += arr[j];
						tempq.add(arr[j]);
					}
					
				}
				if(sum == 100) {
					while (!tempq.isEmpty()) {
						System.out.println(tempq.poll());
					}
					break;
				}
			}
		}
		
	}
}
