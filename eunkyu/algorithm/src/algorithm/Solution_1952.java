package algorithm;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

//18:08
public class Solution_1952 {
	
	static int[] priceMap;
	static int[] monthPrice;
	static int price;
	static int minPrice;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
	
		for (int tc = 1; tc <= testCase; tc++) {
			priceMap = new int[4];
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				priceMap[i] = Integer.parseInt(str[i]);
			}
			monthPrice = new int[12];
			String[] info = br.readLine().split(" ");
			for (int i = 0; i < 12; i++) {
				monthPrice[i] = Integer.parseInt(info[i]);
			}
		
			price = 0;
			minPrice = Integer.MAX_VALUE;
			goGame(1);
			if(minPrice > priceMap[3])
				minPrice = priceMap[3];
			sb.append("#"+tc+" "+minPrice+"\n");
		}
		System.out.println(sb);
	}
	private static void goGame(int m) {
		if(m >= 13) {
			if(minPrice > price)
				minPrice = price;
			return;
		}
		for (int j = 0; j < 3; j++) {
			switch (j) {
			case 0:
				int val = monthPrice[m - 1];
				if(val != 0)
					price += val * priceMap[j];
				goGame(m + 1);
				if(val != 0) {
					price -= val * priceMap[j];
				}
				break;
			case 1:
				price += priceMap[j];
				goGame(m + 1);
				price -= priceMap[j];

				break;
			case 2:
				price += priceMap[j];
				goGame(m + 3);
				price -= priceMap[j];
				break;
			}
		}
	}
}
