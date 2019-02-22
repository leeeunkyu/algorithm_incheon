package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15486 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ConsultInfo[] dp = new ConsultInfo [n + 1];
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			int day = Integer.parseInt(info[0]);
			int pay = Integer.parseInt(info[1]);
			dp[i] = new ConsultInfo(day, pay);
		}
		
		for (int i = 0; i < n; i++) {
			if(i + dp[i].getDay() > n + 1)
				continue;
			//dp[i + dp[i].getDay()] = 
		}
	}
}

class ConsultInfo {
	int day;
	int pay;
	
	public ConsultInfo(int day, int pay) {
		super();
		this.day = day;
		this.pay = pay;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	
}
