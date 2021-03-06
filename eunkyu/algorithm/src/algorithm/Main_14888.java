package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//14:15~ 15:15
public class Main_14888 {
	
	static int n;
	static int o = 4;
	static BigInteger min = new BigInteger("-1000000000");
	static BigInteger max = new BigInteger("1000000000");
	static BigInteger[] numList;
	static int orderList[];
	static BigInteger res1;
	static BigInteger res2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] info = br.readLine().split(" ");
		numList = new BigInteger[n];
		for (int i = 0; i < n; i++) {
			numList[i] = new BigInteger(info[i]);
		}
		
		String[] order = br.readLine().split(" ");
		orderList = new int[o];
		for (int i = 0; i < o; i++) {
			orderList[i] = Integer.parseInt(order[i]);
 		}
		res1 = new BigInteger("-1000000000");
		res2 = new BigInteger("1000000000");
		dfs(1, numList[0]);

		System.out.println(res1.intValue());
		System.out.println(res2.intValue());
	}

	private static void dfs(int cnt, BigInteger val) {
		if(n == cnt) {
			if(res1.compareTo(val) == -1) {
				res1 = val;
			}
			if(res2.compareTo(val) == 1) {
				res2 = val;
			}
			return;
		}
		
		for (int i = 0; i < o; i++) {
			int order = orderList[i];
			if(order == 0)
				continue;
			BigInteger val2 = new BigInteger("0");
			switch (i) {
			case 0:
				val2 = val.add(numList[cnt]);
				break;
			case 1:
				val2 = val.subtract(numList[cnt]);
				break;
			case 2:
				val2 = val.multiply(numList[cnt]);
				break;
			case 3:
				val2 = val.divide(numList[cnt]);
				break;
			}
			
			int a = val2.compareTo(min);
			int b = val2.compareTo(max);
			
			if((a > 0 || a == 0) && (b < 0 || b == 0)) {
				orderList[i] -= 1;
				dfs(cnt + 1, val2);
				orderList[i] += 1;
			}
		}
	}
}