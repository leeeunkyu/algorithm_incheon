package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2309 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int keyList[] = new int[9];
		int sum = 0;
		for (int i = 0; i < keyList.length; i++) {
			keyList[i] = Integer.parseInt(br.readLine());			
			sum += keyList[i];
		}
		loop:
		for (int i = 0; i < keyList.length; i++) {
			for (int j = 0; j < keyList.length; j++) {
				if(i == j)
					continue;
				if(sum - (keyList[i]+keyList[j]) == 100) {
					System.out.println(keyList[j]+" "+keyList[i]);
					keyList[i] = -1;
					keyList[j] = -1;
					break loop;
				}
			}
		}
		
		Arrays.sort(keyList);
		for (int i = 0; i < keyList.length; i++) {
			if(keyList[i] == -1)
				continue;
			if(keyList.length-1 != i)
				System.out.println(keyList[i]);
			else
				System.out.print(keyList[i]);
		}
	}
}
