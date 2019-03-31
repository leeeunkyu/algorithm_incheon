package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		String[] info = new String[str.length - 3];
		
		for (int i = 0; i < info.length; i++) {
			info[i] = str[i + 3];
		}

		int size = info.length;
		int min = Integer.parseInt(str[1]);
		int max = Integer.parseInt(str[2]);
		
		
		
		//3
		//000 001 010 100 011 110 111
		for (int i = 0; i < (1 << size); i++) {
			if(Integer.bitCount(i) == min) {
				String ans = "";
				int[] tempArr = new int[min];
				int tempIdx = 0;
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						tempArr[tempIdx] = j;
						tempIdx += 1;
					}
				}
				
				for (int idx = 0; idx < min; idx++) {
					ans = "";
					int temp2 = tempArr[min - 1];
					tempArr[min - 1] = tempArr[0];
					tempArr[0] = temp2;
					for (int j = 0; j < min; j++) {
						ans += info[tempArr[j]];
					}
					sb.append("\""+ans+"\" ");
				}
				
			}
			
			if(Integer.bitCount(i) == max) {
				String ans = "";
				int[] tempArr = new int[max];
				int tempIdx = 0;
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						tempArr[tempIdx] = j;
						tempIdx += 1;
					}
				}
				
				for (int idx = 0; idx < max; idx++) {
					ans = "";
					int temp2 = tempArr[max - 1];
					tempArr[max - 1] = tempArr[0];
					tempArr[0] = temp2;
					for (int j = 0; j < max; j++) {
						ans += info[tempArr[j]];
					}
					sb2.append("\""+ans+"\" ");
				}
			}
		}
		
		System.out.println(sb);
		System.out.println(sb2);
	}
}