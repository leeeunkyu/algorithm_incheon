package algorithm_review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2309 {
	
	static int FULL_SIZE = 9;
	static int C_SIZE = 7;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[FULL_SIZE];
		
		for (int i = 0; i < FULL_SIZE; i++) {
			int n = Integer.parseInt(br.readLine());
			arr[i] = n;
		}
		
		for (int i = 0; i < (1 << FULL_SIZE); i++) {
			if(Integer.bitCount(i) == C_SIZE) {
				int sum = 0;
				int[] temp = new int[C_SIZE];
				int cnt = 0;
				for (int j = 0; j < FULL_SIZE; j++) {
					if(((1 << j) & i) > 0) {
						temp[cnt] = arr[j];
						cnt++;
						sum += arr[j];
					}
				}
				if(sum == 100) {
					Arrays.sort(temp);
					for (int j = 0; j < temp.length; j++) {
						System.out.println(temp[j]);
						
					}
					break;

				}
			}
		}
	}
}
