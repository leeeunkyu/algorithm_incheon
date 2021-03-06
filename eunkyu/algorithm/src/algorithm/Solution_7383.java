package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//14:30
public class Solution_7383 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		BigInteger pivotB = new BigInteger("2017");
		StringBuilder sb = new StringBuilder();
		int pivotM = 1;
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			BigInteger big = new BigInteger(str[0]);
			int mon = Integer.parseInt(str[1]);
			big = big.subtract(pivotB);
			big = big.multiply(new BigInteger("13"));
			int sub = mon - pivotM;
			big = big.add(new BigInteger(""+sub));
			big = big.subtract(new BigInteger("2"));
			BigInteger a = big.divide(new BigInteger("13"));
			BigInteger b = big.remainder(new BigInteger("13"));
			mon += b.intValue();
			a = a.add(pivotB);
			
			if(mon > 13) {
				mon -= 13;
				a = a.add(BigInteger.ONE);
			}
			sb.append("#"+tc+" "+a+" "+mon+"\n");
		}
		System.out.println(sb);
	}
}
