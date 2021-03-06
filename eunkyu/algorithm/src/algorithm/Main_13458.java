package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//14:00 ~ 14:45
public class Main_13458 {

	static int n;
	static long[] arr;
	static long b;	//��
	static long c;	//��
	static long bCnt;
	static long cCnt;
	static long[][] resMap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		resMap = new long[n][2];
		
		String[] str = br.readLine().split(" ");
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		String[] info = br.readLine().split(" ");
		b = Integer.parseInt(info[0]);
		c = Integer.parseInt(info[1]);
		bCnt = arr.length;
		
		for (int i = 0; i < n; i++) {
			arr[i] -= b;
		}
		
		go();
		long val = bCnt;
		for (int i = 0; i < n; i++) {
			val += resMap[i][1];
		}
	
		System.out.println(val);
	}
	private static void go() {
		for (int i = 0; i < n; i++) {
			long val = arr[i];
			long temp = arr[i];
			resMap[i][0] = val;
			if(i > 0) {
				if(arr[i] > arr[i - 1]) { 
					val -= resMap[i - 1][0];
					resMap[i][1] = resMap[i - 1][1];
				}
			}
			cCnt = 0;
			while(true) {
				if(val > 0) {
					val -= c;
					++cCnt;
				} else if(val <= 0) {
					break;
				}
				
					
			}
			long tempVal = temp - val;
			resMap[i][0] = tempVal;
			resMap[i][1] += cCnt;	
		}
	}
	
}
