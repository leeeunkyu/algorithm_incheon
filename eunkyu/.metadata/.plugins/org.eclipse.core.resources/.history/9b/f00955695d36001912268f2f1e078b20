package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1149 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];
		House_1149[] houses = new House_1149[n];
		String[] houseInfo = br.readLine().split(" ");
		
		for (int i = 0; i < n; i++) {
			houses[i] = 
					new House_1149(
							Integer.parseInt(houseInfo[0]),
							Integer.parseInt(houseInfo[1]),
							Integer.parseInt(houseInfo[2]));
		}
		
		for (int i = 0; i < n; i++) {
			//dp[i] = dp[i-1] + max(houses[i], dp[i-1] 과 겹치지 않는)
		}
	}
}

class House_1149 {
	int red;
	int green;
	int blue;
	
	public House_1149(int red, int green, int blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	
}
