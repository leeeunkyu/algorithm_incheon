package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10448 {
	
	static int n;
	static int[] tri;
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < testCase; i++) {
			n = Integer.parseInt(br.readLine());
			tri = new int[n + 1];
			for (int j = 1; j < n + 1; j++) {
				tri[j] = j * (j + 1)/2;
			}
			
			res = 0;
			for (int j = 1; j < n + 1; j++) {
				int val = tri[j];
				go(val, 1, n - val, ""+val);
				if(val > n) {
					break;
				}
			}
			if(res != 0)
				sb.append(1+"\n");
			else
				sb.append(0+"\n");

		}
		System.out.println(sb);
	}

	private static void go(int val, int cnt, int num, String str) {
		if(cnt == 3) {
			if(num == 0) {
				//System.out.println(str);
				++res;
			}
			return;
		}
		for (int idx = 1; idx < n + 1; idx++) {
			int v = tri[idx];
			if(v > num) {
				break;
			}
			if(cnt != 2 && v == num) {
				break;
			}
			go(v, cnt + 1, num - v,str+" "+v);
			
		}
	}
}
