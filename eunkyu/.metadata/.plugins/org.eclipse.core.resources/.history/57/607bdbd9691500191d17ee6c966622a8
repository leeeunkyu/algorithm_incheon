package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_14888 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		int numList[] = new int[2];
		int operList[] = new int[4];
		
		for (int i = 0; i < cnt; i++) {
			numList[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < 4; i++) {
			operList[i] = Integer.parseInt(br.readLine());
		}
		int tempOper[] = Arrays.copyOf(operList, operList.length);
		int max = 0;
		int min = 99999;
		int temp = numList[0];
		for (int i = 0; i < numList.length; i++) {
			for (int j = 1; j < numList.length; j++) {
				if(tempOper[0] >= 1) {
					temp = temp + numList[j];
					tempOper[0] -= 1;
				}else if(tempOper[1] >= 1) {
					temp = temp - numList[j];					
					tempOper[1] -= 1;
				}else if(tempOper[2] >= 1) {
					temp = temp * numList[j];
					tempOper[2] -= 1;
				}else if(tempOper[3] >= 1) {
					temp = temp / numList[j];
					tempOper[3] -= 1;
				}
			}
			if(temp > max) {
				max = temp;
			}
			if(temp < min) {
				min = temp;
			}
		}
		
	}
}
