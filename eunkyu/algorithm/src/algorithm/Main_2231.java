package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2231 {
	
	static int n;
	static String str;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		n = Integer.parseInt(str);
		int size = str.length();
		
		//n �ڸ��� 
		//n -1 �ڸ������� �˻�
		//n >= 3 �̸� n - 1 �� ���� ���ڸ��� 9
		//System.out.println(n - 1);
		if(size < 3) {
			for (int i = 0; i < n; i++) {
				
				String temp = ""+i;
				int sum = i;
				for (int j = 0; j < temp.length(); j++) {
					sum += temp.charAt(j) - '0';
				}
				
				if(sum == n) {
					System.out.println(i);
					return;
				}
			}	
		} else {
			double pivot = Math.pow(10, size - 2)* 9; 
			//System.out.println(pivot);
			for (int i = (int)pivot; i < n; i++) {
				String temp = ""+i;
				int sum = i;
				for (int j = 0; j < temp.length(); j++) {
					sum += temp.charAt(j) - '0';
				}
				
				if(sum == n) {
					System.out.println(i);
					return;
				}
			}	
		}
		System.out.println(0);
		
		
	}

}